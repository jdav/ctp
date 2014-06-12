package com.malleamus.ctp.impl;

import java.util.ArrayList;

import com.malleamus.ctp.CTPException;
import com.malleamus.ctp.Conversation;
import com.malleamus.ctp.ConversationFactory;
import com.malleamus.ctp.Party;
import com.malleamus.ctp.Receiver;
import com.malleamus.ctp.Sender;

public class PartyImpl implements Party {
	
	private ConversationFactory factory = null;
	private String host = null;
	private int port = 0;
	private Receiver receiver = null;
	private final Sender sender = null;

	@Override
	public void setHost(String host) throws CTPException {
		this.host = host;
	}

	@Override
	public void setPort(int port) throws CTPException {
		this.port = port;
	}

	@Override
	public String getHost() throws CTPException {
		return host;
	}

	@Override
	public int getPort() throws CTPException {
		return port;
	}

	@Override
	public void startReceiving() throws CTPException {
		receiver = new ReceiverImpl(null, port);
		(new Thread(receiver)).start();
	}

	@Override
	public void stopReceiving() throws CTPException {
		receiver.shutDown();	
	}
	
	@Override
	public Conversation startConversation(ArrayList<Party> invitees) throws CTPException {
		StartConversation sc = 
				new StartConversation(this, invitees);
		sc.start();
		return null;
	}
	
	public class StartConversation extends Thread {
		
		private Party owner = null;
		private ArrayList<Party> invitees = null;
		
		public StartConversation(Party owner, ArrayList<Party> invitees) {
			super();
			this.owner = owner;
			this.invitees = invitees;
		}
		
		@Override
		public void run() {
			try {
				Conversation convo = factory.create();
				convo.setOwner(owner);
				convo.addParties(invitees);
			} catch (CTPException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Conversation joinConversation(ArrayList<Party> parties)
			throws CTPException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConversationFactory(ConversationFactory factory)
			throws CTPException {
		this.factory = factory;
	}

}
