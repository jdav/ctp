package com.malleamus.ctp.test;

import java.util.Collection;

import junit.framework.TestCase;

import org.junit.Test;

import com.malleamus.ctp.CTPException;
import com.malleamus.ctp.Conversation;
import com.malleamus.ctp.ConversationFactory;
import com.malleamus.ctp.Party;
import com.malleamus.ctp.Receiver;
import com.malleamus.ctp.RequestFactory;
import com.malleamus.ctp.Response;
import com.malleamus.ctp.Station;
import com.malleamus.ctp.impl.ConversationFactoryImpl;
import com.malleamus.ctp.impl.ConversationImpl;
import com.malleamus.ctp.impl.PartyImpl;
import com.malleamus.ctp.impl.ReceiverImpl;
import com.malleamus.ctp.impl.RequestFactoryImpl;
import com.malleamus.ctp.impl.SenderImpl;
import com.malleamus.ctp.impl.StationImpl;

/**
 * Here are the code changes that we need to get.....
 * 
 * @author jdav
 * 
 */
public class TestCTP extends TestCase {
	
	ConversationFactory factory = null;
	RequestFactory requestFactory = null;
	Conversation convo = null;
	Party party1 = null;
	Party party2 = null;
	Party party3 = null;
	Station station1 = null;
	Receiver receiver1 = null;
	Station station2 = null;
	Receiver receiver2 = null;
	Station station3 = null;
	Receiver receiver3 = null;
	
	public void setup() {
		try {
			factory = new ConversationFactoryImpl();
			requestFactory = new RequestFactoryImpl();
			party1 = new PartyImpl();
			party1.setHost("localhost");
			party1.setPort(4032);
			receiver1 = new ReceiverImpl(requestFactory, 4032);
			(new Thread(receiver1)).start();
			station1 = new StationImpl(factory, new SenderImpl(), receiver1,
					party1);
			(new Thread(station1)).start();

			party2 = new PartyImpl();
			party2.setHost("localhost");
			party2.setPort(3948);
			receiver1 = new ReceiverImpl(requestFactory, 4032);
			(new Thread(receiver1)).start();
			station1 = new StationImpl(factory, new SenderImpl(), receiver1,
					party1);
			(new Thread(station1)).start();

			party3 = new PartyImpl();
			party3.setHost("localhost");
			party3.setPort(3949);
			receiver1 = new ReceiverImpl(requestFactory, 4032);
			(new Thread(receiver1)).start();
			station1 = new StationImpl(factory, new SenderImpl(), receiver1,
					party1);
			(new Thread(station1)).start();

			convo = new ConversationImpl();
			convo.addParty(party1);
			convo.addParty(party2);
			convo.addParty(party3);
		} catch (CTPException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void tearDown() {
		receiver1.shutDown();
		receiver2.shutDown();
		receiver3.shutDown();
		station1.shutDown();
		station2.shutDown();
		station3.shutDown();
	}

	@Test
	public void testInvitation() throws CTPException {
		boolean receivedParty1Response = false;
		boolean receivedParty2Response = false;
		boolean receivedParty3Response = false;

		Collection<Response> responses = convo.inviteAll();
		for (Response response : responses) {
			Party responder = response.getResponder();
			if (responder.getPort() == party1.getPort()) {
				receivedParty1Response = true;
			}

			if (responder.getPort() == party2.getPort()) {
				receivedParty2Response = true;
			}

			if (responder.getPort() == party3.getPort()) {
				receivedParty3Response = true;
			}
		}
		
		assertTrue(receivedParty1Response && receivedParty2Response
				&& receivedParty3Response);
	}
}
