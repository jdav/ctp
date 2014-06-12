package com.malleamus.ctp;

//import java.net.InetAddress;

public interface Party {

	public void setConversationFactory(ConversationFactory factory) throws CTPException;
	public void setHost(String host) throws CTPException;
	public void setPort(int port) throws CTPException;
	public String getHost() throws CTPException;
	public int getPort() throws CTPException;
}
