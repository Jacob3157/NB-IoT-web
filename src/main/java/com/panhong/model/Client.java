package com.panhong.model;

public class Client {
	
	private String IP;
	private Long Port;
	
	private String content;
	private String response;
	
	private String contentHex;
	private String responseHex;
	
	public Client() {
		
	}
	public Client(String IP, Long Port) {
		this.IP = IP;
		this.Port = Port;
	}
	
	public Client(String IP, Long Port, String content) {
		this.IP = IP;
		this.Port = Port;
		this.content = content;
	}
	
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public Long getPort() {
		return Port;
	}
	public void setPort(Long port) {
		Port = port;
	}

	 
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	 
	public String getContentHex() {
		return contentHex;
	}
	public void setContentHex(String contentHex) {
		this.contentHex = contentHex;
	}
	
	
	public String getResponseHex() {
		return responseHex;
	}
	public void setResponseHex(String responseHex) {
		this.responseHex = responseHex;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IP == null) ? 0 : IP.hashCode());
		result = prime * result + ((Port == null) ? 0 : Port.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (IP == null) {
			if (other.IP != null)
				return false;
		} else if (!IP.equals(other.IP))
			return false;
		if (Port == null) {
			if (other.Port != null)
				return false;
		} else if (!Port.equals(other.Port))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Client [IP=" + IP + ", Port=" + Port + "]";
	}
	
	
	
}
