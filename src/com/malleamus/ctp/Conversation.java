package com.malleamus.ctp;

import java.util.ArrayList;
import java.util.Collection;

public interface Conversation {
	
	//Life-cycle Messages
	public Invitation newInvitation() throws CTPException;
	public Acceptance newAcceptance() throws CTPException;
	public TerminationRequest newTerminationRequest() throws CTPException;
	public TerminationResponse newConversationTerminationResponse() throws CTPException;
	public Termination newTermination() throws CTPException;

	//Generic Messages
	public Notification newNotification() throws CTPException;
	public Request newRequest() throws CTPException;
	public Response newResponse() throws CTPException;
	public Callback newCallback() throws CTPException;
	
	//Conversation Management Methods
	public ConversationStatus getStatus() throws CTPException;
	public void setStatus(ConversationStatus status) throws CTPException;
	
	//Message Management Methods
	public void addMessage(Message message) throws CTPException;
	public void removeMessage(Message message) throws CTPException;
	public Iterable<Message> getMessages() throws CTPException;
	
	//Party Management Methods
	public Party newParty() throws CTPException;
	public void addParty(Party party) throws CTPException;
	public void removeParty(Party party) throws CTPException;
	public Iterable<Party> getParties() throws CTPException;
	public Party getOwner() throws CTPException;
	public void setOwner(Party party) throws CTPException;
	public Party getUs() throws CTPException;
	public void setUs(Party party) throws CTPException;
	public Collection<Response> inviteAll() throws CTPException;
	public Acceptance invite(Party party) throws CTPException;
	public void disinvite(Party party) throws CTPException;
	public void addParties(ArrayList<Party> others);

}
