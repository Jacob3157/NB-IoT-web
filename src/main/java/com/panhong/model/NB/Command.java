package com.panhong.model.NB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "command", catalog = "nbinfo")
public class Command {

	@Id
	@Column(name="id",nullable=false,unique=true)
	@GeneratedValue
	private int id;
	
	@Column(name = "requestName", nullable = true, length = 20)
	private String requestName;
	@Column(name = "responseName", nullable = true, length = 20)
	private String responseName;
	@Column(name = "request", nullable = true, length = 20)
	private String request;
	@Column(name = "response", nullable = true, length = 20)
	private String response;
	@Column(name = "requestHex", nullable = true, length = 40)
	private String requestHex;
	@Column(name = "responseHex", nullable = true, length = 40)
	private String responseHex;
	
	
	public Command() {
		
	}
	
	public Command(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
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
