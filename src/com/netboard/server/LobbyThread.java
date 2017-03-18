package com.netboard.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.netboard.game.Player;
import com.netboard.message.HostMessage;
import com.netboard.message.JoinMessage;
import com.netboard.message.RefreshMessage;

public class LobbyThread implements Runnable {

	private NetBoardServer nbs;
	private Socket s;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	
	public LobbyThread(NetBoardServer nbs, Socket clientSocket) {
		this.nbs = nbs;
		this.s = clientSocket;
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listenForActions() {
			
		while (true) {
			Object msgObj = null;
			
			try {
				msgObj = in.readObject();
				if(msgObj instanceof HostMessage)
				{
				    HostMessage hostMsgObj = (HostMessage) msgObj;
				    nbs.addHostToLobby(hostMsgObj.getHostUsername(), s, hostMsgObj.getGameType());
				    break;
				}
				else if(msgObj instanceof JoinMessage)
				{
					JoinMessage joinMsgObj = (JoinMessage) msgObj;
					
					String guestUsername = joinMsgObj.getGuestUserName(),
							hostUsername = joinMsgObj.getHostUsername();
					
					
					Player host = nbs.findPlayer(hostUsername);
					// TODO if (host == null) {} 
					String gameType = host.getGameType();
					
					Player guest = new Player(guestUsername, s, gameType);
					
					nbs.spawnActiveGameThread(gameType, host, guest);
					break;
				}
				else if (msgObj instanceof RefreshMessage) {
					
					RefreshMessage refMsg = 
							new RefreshMessage(nbs.getPlayerLobby(), NetBoardServer.supportedGames);
					
					out.writeObject(refMsg);
				}
				else {
					System.err.println("Something has gone horribly awry...\n");
				}
			}
			catch (ClassNotFoundException e) { e.printStackTrace(); } 
			catch (IOException e) { e.printStackTrace(); }
		}
		
		close();
	}
	
	public void run() {
		listenForActions();
	}
	
	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
