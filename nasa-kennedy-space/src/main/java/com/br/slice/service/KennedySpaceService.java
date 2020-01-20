package com.br.slice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.slice.dto.HttpStatusByDateDTO;
import com.br.slice.dto.QuantityOfDistintsHostsDTO;
import com.br.slice.dto.QuantityOfReturnedBytesDTO;
import com.br.slice.dto.TopURLStatusByCodeDTO;
import com.br.slice.helper.TupleComparator;

import scala.Tuple2;

@Service
public class KennedySpaceService {

	private static final int HOST_NAME = 0;
	private static final int TIMESTAMP = 1;
	private static final int CURRENT_ROW = 1;
	private static final int REQUISITION_URL = 4;
	private static final int BYTES = 7;

	@Autowired
	DataService dataService;

	/**
	 * 
	 * @return
	 */
	public List<QuantityOfDistintsHostsDTO> getTotalDistincstHostsByHosts() {
		JavaRDD<String> dataFromFile = dataService.loadData();
		JavaPairRDD<String, Integer> data = dataFromFile
				.mapToPair(s -> new Tuple2<String, Integer>(s.split(" ")[HOST_NAME], 1));
		JavaPairRDD<String, Integer> dataGroupSummarized = data.reduceByKey((x, y) -> x + y);
		List<Tuple2<String, Integer>> allData = dataGroupSummarized.collect();
		List<QuantityOfDistintsHostsDTO> totalOfDistinctHostsList = mapToQuantityOfDistinctsHostsDTO(allData);
		return totalOfDistinctHostsList;
	}

	/**
	 * 
	 * @return
	 */
	public Long getTotalDistincstHosts() {
		JavaRDD<String> dataFromFile = dataService.loadData();
		return dataFromFile.map(s -> s.split(" ")[HOST_NAME]).distinct().count();
	}

	/**
	 * 
	 * @param httpStatusCode
	 * @return
	 */
	private JavaRDD<String> geRowsByHttpStatusCode(Integer httpStatusCode) {
		JavaRDD<String> dataFromFile = dataService.loadData();
		return dataFromFile.filter(s -> s.contains(httpStatusCode.toString()));
	}

	/**
	 * 
	 * @param errorCode
	 * @return
	 */
	public Long getDataByHttpStatusCode(Integer errorCode) {
		JavaRDD<String> dataFromFile = dataService.loadData();
		return dataFromFile.filter(s -> s.contains(errorCode.toString())).count();
	}

	/**
	 * 
	 * @param httpsStatusCode
	 * @param topNumber
	 * @return
	 */
	public List<TopURLStatusByCodeDTO> topUrlByStatusCode(Integer httpsStatusCode, Integer topNumber) {
		JavaRDD<String> dataFromFile = geRowsByHttpStatusCode(httpsStatusCode);
		JavaPairRDD<String, Integer> agrupaOnibus = dataFromFile
				.mapToPair(s -> new Tuple2<String, Integer>(s.split(" ")[REQUISITION_URL], CURRENT_ROW))
				.reduceByKey((x, y) -> x + y);
		List<Tuple2<String, Integer>> topDataGroupSummarized = agrupaOnibus.takeOrdered(topNumber,
				TupleComparator.INSTANCE);
		List<TopURLStatusByCodeDTO> urlStatusByCodeDTOList = mapToTopURLStatusByCodeDTO(topDataGroupSummarized);
		return urlStatusByCodeDTOList;
	}

	/**
	 * 
	 * @param errorCode
	 * @return
	 * @throws ParseException
	 */
	public List<HttpStatusByDateDTO> getHttpStatusByDate(Integer errorCode) throws ParseException {
		JavaRDD<String> dataFromFile = geRowsByHttpStatusCode(errorCode);
		JavaPairRDD<String, Integer> dataGroupSummarized = dataFromFile
				.mapToPair(s -> new Tuple2<String, Integer>(s.split(" ")[TIMESTAMP].substring(0, 11), CURRENT_ROW))
				.reduceByKey((x, y) -> x + y);
		List<Tuple2<String, Integer>> data = dataGroupSummarized.collect();
		List<HttpStatusByDateDTO> httpStatusByDateList = mapToHttpStatusByDateDTO(data);

		return httpStatusByDateList;
	}

	/**
	 * 
	 * @return
	 */
	public List<QuantityOfReturnedBytesDTO> getTotalReturnedBytes() {
		JavaRDD<String> dataFromFile = dataService.loadData();
		List<Tuple2<String, String>> dataGroupSummarized = dataFromFile
				.mapToPair(s -> new Tuple2<String, String>(s.split(" ")[HOST_NAME],
						s.split(" ").length - 1 >= BYTES ? s.split(" ")[BYTES] : "no data"))
				.collect();
		List<QuantityOfReturnedBytesDTO> totalOfReturnedByteList = mapToQuantityOfReturnetBytesDTO(dataGroupSummarized);

		return totalOfReturnedByteList;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public List<QuantityOfDistintsHostsDTO> mapToQuantityOfDistinctsHostsDTO(List<Tuple2<String, Integer>> data) {
		List<QuantityOfDistintsHostsDTO> httpReqList = new ArrayList<QuantityOfDistintsHostsDTO>();
		for (Tuple2<String, Integer> tuple2 : data) {
			QuantityOfDistintsHostsDTO request = new QuantityOfDistintsHostsDTO(tuple2._1(), tuple2._2());
			httpReqList.add(request);
		}
		return httpReqList;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public List<QuantityOfReturnedBytesDTO> mapToQuantityOfReturnetBytesDTO(List<Tuple2<String, String>> data) {
		List<QuantityOfReturnedBytesDTO> httpReqList = new ArrayList<QuantityOfReturnedBytesDTO>();
		for (Tuple2<String, String> tuple2 : data) {
			QuantityOfReturnedBytesDTO request = new QuantityOfReturnedBytesDTO(tuple2._1(), tuple2._2());
			httpReqList.add(request);
		}
		return httpReqList;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public List<HttpStatusByDateDTO> mapToHttpStatusByDateDTO(List<Tuple2<String, Integer>> data) {
		List<HttpStatusByDateDTO> httpReqList = new ArrayList<HttpStatusByDateDTO>();
		for (Tuple2<String, Integer> tuple2 : data) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMMM/yyyy");
			try {
				Date ocurrenceDate = formatter.parse(tuple2._1());
				HttpStatusByDateDTO request = new HttpStatusByDateDTO(ocurrenceDate, tuple2._2());
				httpReqList.add(request);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return httpReqList;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public List<TopURLStatusByCodeDTO> mapToTopURLStatusByCodeDTO(List<Tuple2<String, Integer>> data) {
		List<TopURLStatusByCodeDTO> httpReqList = new ArrayList<TopURLStatusByCodeDTO>();
		for (Tuple2<String, Integer> tuple2 : data) {
			TopURLStatusByCodeDTO request = new TopURLStatusByCodeDTO(tuple2._1(), tuple2._2());
			httpReqList.add(request);
		}
		return httpReqList;
	}
}
