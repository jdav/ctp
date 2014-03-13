package com.malleamus.ctp;

public interface Notification extends Message {

	public void send() throws CTPException;

	public void sendAll();
	
}
