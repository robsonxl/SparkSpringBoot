package com.br.slice.dto;

public class TopURLStatusByCodeDTO {
	
	private String url;
	private Integer total;
	
	public TopURLStatusByCodeDTO() {
		
	}
	public TopURLStatusByCodeDTO(String url, Integer total) {
		this.url = url;
		this.total = total;
	}
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
 
}
