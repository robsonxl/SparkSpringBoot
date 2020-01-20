package com.br.slice.sparkConfig;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfiguration {
	
	@Value("${spark.app.name}")
	private String appName;
	@Value("${spark.master}")
	private String masterUri;
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getMasterUri() {
		return masterUri;
	}

	public void setMasterUri(String masterUri) {
		this.masterUri = masterUri;
	}

	@Bean
	public SparkConf getSparkConfiguration() {
		 SparkConf spc = new SparkConf();
		 spc.setAppName(this.appName);
		 spc.setMaster(this.masterUri);
		 return spc;
	}
	
	@Bean
	public JavaSparkContext sparkContext(){
		return new JavaSparkContext(getSparkConfiguration());
	}
}
