package com.malleamus.ctp.impl;

import com.malleamus.ctp.CTPException;
import com.malleamus.ctp.Conversation;
import com.malleamus.ctp.ConversationFactory;

public class ConversationFactoryImpl implements ConversationFactory {

	@Override
	public Conversation create() throws CTPException {
		return new ConversationImpl();
	}

}
