package com.br.slice.dto;

public class QuantityOfDistintsHostsDTO {
	
	public QuantityOfDistintsHostsDTO() {}
	
	public QuantityOfDistintsHostsDTO(String hostname, int quantityOfDistinctsHosts) {
		this.hostname = hostname;
		this.quantityOfCallToHost = quantityOfDistinctsHosts;
	}
	
	private String hostname;
	private int quantityOfCallToHost;
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public int getQuantityOfCallToHost() {
		return quantityOfCallToHost;
	}
	public void setQuantityOfCallToHost(int totalOfHosts) {
		this.quantityOfCallToHost = totalOfHosts;
	}
}
