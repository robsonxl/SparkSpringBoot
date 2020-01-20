package com.br.slice.dto;

import java.util.Date;

public class HttpStatusByDateDTO {
	
	private Date date;
	
	private Integer totalDataByStatusCode;
	
	public HttpStatusByDateDTO() {
		
	}
	
	public HttpStatusByDateDTO(Date date, Integer totalDataByStatusCode) {
		this.date = date;
		this.totalDataByStatusCode = totalDataByStatusCode;
	}
	public int getTotalDataByStatusCode() {
		return totalDataByStatusCode;
	}
	public void setTotalDataByStatusCode(Integer totalDataByStatusCode) {
		this.totalDataByStatusCode = totalDataByStatusCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
