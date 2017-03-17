package com.netboard.server;

import java.net.Socket;

import com.netboard.game.Player;
import com.netboard.message.ApplyMoveMessage;
import com.netboard.message.CommsBridge;
import com.netboard.message.HostMessage;
import com.netboard.message.JoinMessage;
import com.netboard.message.RefreshMessage;

public class LobbyThread implements Runnable {

	private NetBoardServer nbs;
	private Socket s;
	
	
	public LobbyThread(NetBoardServer nbs, Socket clientSocket) {
		this.nbs = nbs;
		this.s = clientSocket;
	}
	
	private void listenForActions() {
			
		while (true) {
			Object msgObj = null;
			
			msgObj = CommsBridge.readMessage(s);
			
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
				
				CommsBridge.writeMessage(s, refMsg);
			}
			else if (msgObj instanceof ApplyMoveMessage) {
				System.err.println("LobbyThread: client disconnected from lobby");
				break;
			}
			else {
				System.err.println("LobbyThread: horribly awry...\n");
				break;
			}
		}
	}
	
	public void run() {
		listenForActions();
	}
}
