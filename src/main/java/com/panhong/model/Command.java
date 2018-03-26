package com.panhong.model;

public class Command {

	private int id;
	private String requestName;
	private String responseName;
	private String request;
	private String response;
	
	private String requestHex;
	private String responseHex;
	
	
	public Command() {
		
	}
	
	public Command(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	public String getRequestHex() {
		return requestHex;
	}

	public void setRequestHex(String requestHex) {
		this.requestHex = requestHex;
	}

	public String getResponseHex() {
		return responseHex;
	}

	public void setResponseHex(String responseHex) {
		this.responseHex = responseHex;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getResponseName() {
		return responseName;
	}

	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}
	
	
	
	
}
