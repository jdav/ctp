package com.malleamus.ctp;

public interface Request extends Message {

	public Response send() throws CTPException;
	public void setResponse(Response response) throws CTPException;
	public Response getResponse() throws CTPException;
	
}
