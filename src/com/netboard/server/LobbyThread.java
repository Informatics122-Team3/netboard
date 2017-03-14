package com.netboard.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

import com.netboard.message.BoardUpdateMessage;
import com.netboard.message.HostMessage;
import com.netboard.message.JoinMessage;
import com.netboard.message.Message;
import com.netboard.message.NewPlayerMessage;

public class LobbyThread implements Runnable {

	private NetBoardServer nbs;
	private Socket s;
//	private Scanner in;
	private ObjectInputStream in;
	
	
	public LobbyThread(NetBoardServer nbs, Socket clientSocket) {
		this.nbs = nbs;
		this.s = clientSocket;
		try {
			in = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listenForActions() {
			
		while (true) {
			Object newObj = null;
			
			try {
				newObj = in.readObject();
				if(newObj instanceof HostMessage)
				{
				    HostMessage msgObj = (HostMessage) newObj;
				    nbs.addHostToLobby(msgObj.getHostusername(), s, msgObj.getGameType());
				}
				else if(newObj instanceof JoinMessage)
				{
					JoinMessage msgObj = (JoinMessage) newObj;
				}
				else if(newObj instanceof BoardUpdateMessage)
				{
					BoardUpdateMessage msgObj = (BoardUpdateMessage) newObj;
				}
				else {
					System.out.println("Something has Gone Awry...\n");
				}
			}
			catch (ClassNotFoundException e) { e.printStackTrace(); } 
			catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		listenForActions();
	}	
}
