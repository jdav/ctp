package com.malleamus.ctp.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Collection;

import com.malleamus.ctp.Message;
import com.malleamus.ctp.RequestFactory;
import com.malleamus.ctp.RequestProcessor;
import com.malleamus.ctp.Receiver;

public class ReceiverImpl implements Receiver {
	
	private RequestFactory factory = null;
	private int port = 0;
	private boolean up = false;

	public ReceiverImpl(RequestFactory factory, int port) {
		this.factory = factory;
		this.port = port;
	}
	
	@Override
	public void startUp() throws IOException {
		up = true;
		ServerSocket ss = new ServerSocket(port);

		do {
			Socket socket = null;
			
			try {
				ss.setSoTimeout(5000);
				socket = ss.accept();
				if (up) {
					//These lines will not execute if timeout on accept is exceeded
					Message message = new MessageImpl();
				}
			} catch (SocketTimeoutException ste) {
				//Ignore
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while (up);
		
		ss.close();
	}
	
	public void shutDown() {
		up = false;
	}

	@Override
	public void run() {
		try {
			startUp();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Message> getMessages() {
		// TODO Auto-generated method stub
		return null;
	}
}
