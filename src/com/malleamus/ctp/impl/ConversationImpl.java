package com.malleamus.ctp.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.malleamus.ctp.Acceptance;
import com.malleamus.ctp.CTPException;
import com.malleamus.ctp.Callback;
import com.malleamus.ctp.Conversation;
import com.malleamus.ctp.ConversationStatus;
import com.malleamus.ctp.Invitation;
import com.malleamus.ctp.Message;
import com.malleamus.ctp.Notification;
import com.malleamus.ctp.Party;
import com.malleamus.ctp.Request;
import com.malleamus.ctp.Response;
import com.malleamus.ctp.Termination;
import com.malleamus.ctp.TerminationRequest;
import com.malleamus.ctp.TerminationResponse;

public class ConversationImpl implements Conversation {
	
	private ConversationStatus status = null;
	private Party owner = null;
	private final ArrayList<Message> messages = new ArrayList<Message>();
	private final ArrayList<Party> parties = new ArrayList<Party>();

	@Override
	public Notification newNotification() throws CTPException {
		return new NotificationImpl();
	}

	@Override
	public Request newRequest() throws CTPException {
		return new RequestImpl();
	}

	@Override
	public Response newResponse() throws CTPException {
		return new ResponseImpl();
	}

	@Override
	public Callback newCallback() throws CTPException {
		return new CallbackImpl();
	}

	@Override
	public ConversationStatus getStatus() throws CTPException {
		return status;
	}

	@Override
	public void setStatus(ConversationStatus status) throws CTPException {
		this.status = status;
	}

	@Override
	public void addMessage(Message message) throws CTPException {
		messages.add(message);
	}

	@Override
	public void removeMessage(Message message) throws CTPException {
		messages.remove(message);
	}

	@Override
	public Iterable<Message> getMessages() throws CTPException {
		return messages;
	}

	@Override
	public void addParty(Party party) throws CTPException {
		parties.add(party);
	}

	@Override
	public void removeParty(Party party) throws CTPException {
		parties.remove(party);
	}

	@Override
	public Iterable<Party> getParties() throws CTPException {
		return parties;
	}

	@Override
	public Termination newTermination() throws CTPException {
		return new TerminationImpl();
	}

	@Override
	public TerminationRequest newTerminationRequest()
			throws CTPException {
		return new TerminationRequestImpl();
	}

	@Override
	public TerminationResponse newConversationTerminationResponse()
			throws CTPException {
		return new TerminationResponseImpl();
	}

	@Override
	public Acceptance newAcceptance() throws CTPException {
		return new AcceptanceImpl();
	}

	@Override
	public Invitation newInvitation() throws CTPException {
		return new InvitationImpl();
	}

	@Override
	public Party getOwner() throws CTPException {
		return owner;
	}

	@Override
	public void setOwner(Party party) throws CTPException {
		this.owner = party;
	}

	@Override
	public Party newParty() throws CTPException {
		return new PartyImpl();
	}

	@Override
	public Party getUs() throws CTPException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUs(Party party) throws CTPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Response> inviteAll() throws CTPException {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Acceptance invite(Party party) throws CTPException {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disinvite(Party party) throws CTPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addParties(ArrayList<Party> others) {
		// TODO Auto-generated method stub

	}

}
