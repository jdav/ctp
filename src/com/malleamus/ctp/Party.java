package com.malleamus.ctp;

//import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.ArrayList;

public interface Party {

	public void setConversationFactory(ConversationFactory factory) throws CTPException;
	public void setHost(String host) throws CTPException;
	public void setPort(int port) throws CTPException;
	public String getHost() throws CTPException;
	public int getPort() throws CTPException;
	public void startListening() throws CTPException;
	public void stopListening() throws CTPException;
	public Conversation startConversation(ArrayList<Party> invitees) throws CTPException;
	public Conversation joinConversation(ArrayList<Party> parties) throws CTPException;
}
