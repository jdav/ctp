package com.malleamus.ctp.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import com.malleamus.ctp.CTPException;
import com.malleamus.ctp.Message;
import com.malleamus.ctp.Party;
import com.malleamus.ctp.RequestFactory;
import com.malleamus.ctp.RequestProcessor;
import com.malleamus.ctp.Sender;

public class SenderImpl implements Sender {

	private boolean up = false;
	private Queue<Message> queue = new SynchronousQueue<Message>();

	@Override
	public void startUp() throws IOException {
		up = true;
		do if (up) try {
			//This is serial -- should be a thread...
			Message msg = queue.remove();
			Iterable<Party> parties = msg.getConversation().getParties();
			for (Party party : parties) {
				Socket socket = new Socket(party.getHost(), party.getPort());
				PrintWriter out = 
					new PrintWriter(socket.getOutputStream(), true);
				out.println(msg.getPayload());
				socket.close();
			}
		} catch (UnknownHostException e) {
		} catch  (IOException e) {
		} catch (CTPException e) {
		} while (up);
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
	public void send(Message message) {
		queue.add(message);
	}
}
