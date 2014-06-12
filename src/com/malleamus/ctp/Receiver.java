package com.malleamus.ctp;

import java.io.IOException;
import java.util.Collection;

public interface Receiver extends Runnable {

	public void startUp() throws IOException;

	public Collection<Message> getMessages();

	public void shutDown();

}
