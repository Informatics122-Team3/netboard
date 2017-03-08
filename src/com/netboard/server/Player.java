package com.netboard.server;

import java.net.Socket;

public class Player {

	private Socket socket;
	private String username;
	private String gameType;
	
	public String getUsername() {
		return this.username;
	}
	
	public String getGameType() {
		return this.gameType;
	}
	
}
