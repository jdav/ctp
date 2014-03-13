package com.malleamus.ctp;

public interface Response extends Message {
	
	public void send() throws CTPException;
	public void setRequest(Request request) throws CTPException;
	public Request getRequest() throws CTPException;
	public Party getResponder() throws CTPException;

}
