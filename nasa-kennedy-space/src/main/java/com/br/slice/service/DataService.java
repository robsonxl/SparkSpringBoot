package com.br.slice.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DataService {
	
	@Value("${resourceLoader.file.location}")
	private String path;
	
	@Autowired 
	JavaSparkContext sc;
	
	private JavaRDD<String> dataLoaded;
 
	public JavaRDD<String> loadData(){
			JavaRDD<String> data =  sc.textFile(path);
			this.dataLoaded = data.map(s -> (s.replaceAll("[\\[\\]]", "")).replaceAll("\\-\\s", "").replaceAll("\"","")).cache();
		return dataLoaded;
	}
}
