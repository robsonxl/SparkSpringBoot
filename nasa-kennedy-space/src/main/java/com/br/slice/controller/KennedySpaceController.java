package com.br.slice.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.slice.dto.HttpStatusByDateDTO;
import com.br.slice.dto.QuantityOfDistintsHostsDTO;
import com.br.slice.dto.QuantityOfReturnedBytesDTO;
import com.br.slice.dto.TopURLStatusByCodeDTO;
import com.br.slice.service.KennedySpaceService;

@RestController
public class KennedySpaceController {

	@Autowired
	private KennedySpaceService reqServerLogsService;
	
	/**
	 *  Returns total of unique hosts from dataset 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/uniqueHosts")
	public Long getTotalOfDistincstHosts() {
		return reqServerLogsService.getTotalDistincstHosts();
	}
	
	/**
	 * Returns total of unique hosts grouped by hostname from dataset 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/uniqueGroupHosts")
	public List<QuantityOfDistintsHostsDTO>  getTotalOfDistincstByHosts() {
		return reqServerLogsService.getTotalDistincstHostsByHosts();
	}
	
	/**
	 * Returns total of data by http status code
	 * @param httpStatusCode
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/httpStatusCode")
	public Long getDataByHttpStatusCode(@RequestParam("httpStatusCode") Integer httpStatusCode) {
		return reqServerLogsService.getDataByHttpStatusCode(httpStatusCode);
	}
	/**
	 *  Returns top url by http status code
	 * @param httpStatusCode
	 * @param topNumber
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/topUrl")
	public List<TopURLStatusByCodeDTO> top(@RequestParam("httpStatusCode") Integer httpStatusCode, @RequestParam("topNumber") Integer topNumber) {
		return reqServerLogsService.topUrlByStatusCode(httpStatusCode,topNumber);
	}
	
	/**
	 * Returns data by https status code grouped by Date 
	 * @param httpStatusCode
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/httpStatusCodeDate")
	public List<HttpStatusByDateDTO>  getHttpStatusByDate(@RequestParam("httpStatusCode") Integer httpStatusCode) throws ParseException {
		return reqServerLogsService.getHttpStatusByDate(httpStatusCode);
	}
	/**
	 * Return total Bytes from each host
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "totalBytes")
	public List<QuantityOfReturnedBytesDTO> getTotalOfReturnedBytes() {
		return reqServerLogsService.getTotalReturnedBytes();
	}
}
