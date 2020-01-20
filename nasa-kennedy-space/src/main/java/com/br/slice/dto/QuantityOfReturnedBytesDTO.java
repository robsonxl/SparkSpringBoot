package com.br.slice.dto;

public class QuantityOfReturnedBytesDTO {
	
	private String hostname;
	private String bytesReturned;
	
	public void QuantityOfDistintsHostsDTO(){
		
	};
	
	public QuantityOfReturnedBytesDTO(String hostname, String returnedBytes){
		this.hostname = hostname;
		this.bytesReturned = returnedBytes;
	};
	
	public String getBytesReturned() {
		return bytesReturned;
	}
	public void setBytesReturned(String bytesReturned) {
		this.bytesReturned = bytesReturned;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

}
