package com.malleamus.ctp;

import java.io.IOException;

public interface Sender extends Runnable {
	public void startUp() throws IOException;
	public void shutDown();
	public void send(Message request);
}
