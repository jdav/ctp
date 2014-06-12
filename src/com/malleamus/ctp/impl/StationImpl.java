package com.malleamus.ctp.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.malleamus.ctp.CTPException;
import com.malleamus.ctp.Conversation;
import com.malleamus.ctp.ConversationFactory;
import com.malleamus.ctp.ConversationStatus;
import com.malleamus.ctp.Message;
import com.malleamus.ctp.MessageStatus;
import com.malleamus.ctp.Party;
import com.malleamus.ctp.Receiver;
import com.malleamus.ctp.Sender;
import com.malleamus.ctp.Station;

public class StationImpl implements Station {
	
	private ConversationFactory factory = null;
	private Sender sender = null;
	private Receiver receiver = null;
	private Party associatedParty = null;
	private boolean up = true;
	private final Collection<Conversation> conversationBuffer =
			new ArrayList<Conversation>();
	private final Collection<Conversation> conversations =
			new ArrayList<Conversation>();
	private final Collection<Conversation> archivedConversations =
			new ArrayList<Conversation>();
	
	public StationImpl(ConversationFactory factory,
					   Sender sender,
					   Receiver receiver,
 Party associatedParty) {
		this.factory = factory;
		this.sender = sender;
		this.receiver = receiver;
		this.associatedParty = associatedParty;
	}
	
	public Conversation createConversation() throws CTPException {
		Conversation convo = factory.create();
		convo.addParty(associatedParty);
		convo.setUs(associatedParty);
		convo.setStatus(ConversationStatus.CREATED);
		synchronized (conversationBuffer) {
			conversationBuffer.add(convo);
		}
		return convo;
	}

	@Override
	public void run() {
		while (up) {
			try {
				//Remove terminated conversations
				//from list of monitored 
				Collection<Conversation> terminations =
						new ArrayList<Conversation>();
				for (Conversation convo : conversations) {
					if (convo.getStatus().equals(ConversationStatus.TERMINATED)) {
						terminations.add(convo);
					} else {
						Iterable<Message> messages = convo.getMessages();
						for (Message message : messages) {
							if (!message.getStatus().equals(MessageStatus.SENT)) {
								sender.send(message);
							}
						}
					}
				}
				conversations.removeAll(terminations);
				
				Collection<Message> invitations = receiver.getMessages();
				for (Message invitation : invitations) {
					Conversation convo = createConversation();
					convo.addMessage(invitation);
				}
				
				synchronized (conversationBuffer) {
					conversations.addAll(conversationBuffer);
					conversationBuffer.clear();
				}
			} catch (Exception e) {
				
			}
		}
	}

	@Override
	public void shutDown() {
		up = false;
	}
}
