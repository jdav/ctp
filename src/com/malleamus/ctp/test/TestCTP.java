package com.malleamus.ctp.test;

import static org.junit.Assert.*;
import com.malleamus.ctp.*;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;

import javax.net.SocketFactory;

import org.junit.Test;

import com.malleamus.ctp.CTPException;
import com.malleamus.ctp.Conversation;
import com.malleamus.ctp.ConversationFactory;
import com.malleamus.ctp.Notification;
import com.malleamus.ctp.Invitation;
import com.malleamus.ctp.Party;
import com.malleamus.ctp.Response;
import com.malleamus.ctp.Script;
import com.malleamus.ctp.impl.ConversationFactoryImpl;
import com.malleamus.ctp.impl.PartyImpl;

public class TestCTP {
	
	ConversationFactory factory = null;
	Party party1 = null;
	Party party2 = null;
	Party party3 = null;
	
	public void setup() throws CTPException {
		factory = new ConversationFactoryImpl();
		
		party1 = new PartyImpl();
		party1.setHost("localhost");
		party1.setPort(4032);
		
		party2 = new PartyImpl();
		party2.setHost("localhost");
		party2.setPort(3948);
		
		party3 = new PartyImpl();
		party3.setHost("localhost");
		party3.setPort(3949);
	}
	


	@Test
	public void test() throws CTPException {
		Script script = new Script() {
			void execute() {
			Collection<Response> responses = convo.inviteAll();
			for (Response response : responses) {
				Party responder = response.getResponder();
				if (response instanceof Acceptance) {
					
				}
				
				if (response instanceof Decline) {
					
				}
				
			}
			Notification notification = convo.newNotification();
			notification.sendAll();
			
			Request request = convo.newRequest();
			}};
		
		ArrayList<Party> invitees = new ArrayList<Party>();
		invitees.add(party2);
		invitees.add(party3);
		assertTrue(true);
	}
}
