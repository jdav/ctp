package com.malleamus.ctp;

public abstract interface Message {
	
	public MessageStatus getStatus() throws CTPException;
	public void setPayload(String payload) throws CTPException;
	public String getPayload() throws CTPException;
	public Conversation getConversation() throws CTPException;

}
