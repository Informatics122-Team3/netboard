package com.netboard.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class LobbyThread implements Runnable {

	private NetBoardServer nbs;
	private Socket s;
	private Scanner in;
	
	public LobbyThread(NetBoardServer nbs, Socket clientSocket) {
		this.nbs = nbs;
		this.s = clientSocket;
		try {
			in = new Scanner(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listenForActions() {
		
		while (true) {
			
			//String msgStr = in.next("" /*insert regex here*/);
			String msgStr = in.nextLine();
			String[] msgArr = msgStr.split(" ");
			
			// TODO Message msgObj = Message.deserialize(msgStr);
			// TODO switch(msgObj.messageType)...
			
			switch(msgArr[0]) { //placeholder until previous line works
			case "host":
				//TODO
				nbs.addHostToLobby(msgArr[1], s, msgArr[2]);
				return;
			case "join":
				//TODO
				return;
			case "refresh":
				//TODO
				break;
			default:
				// TODO some error handling
			}
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		listenForActions();
	}	
}
