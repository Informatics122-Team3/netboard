package com.netboard.game;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Player {

	private String username;
	private Socket socket;
	private String gameType;
	
	public Player(String username, Socket socket, String gameType) {
		this.username = username;
		this.socket = socket;
		this.gameType = gameType;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getGameType() {
		return this.gameType;
	}
	
	public String getIPAddress() {
		return socket.getInetAddress().toString();
	}
	
	public InputStream getInputStream() throws IOException {
		return socket.getInputStream();
	}
	
	public OutputStream getOutputStream() throws IOException {
		return socket.getOutputStream();
	}
	
}
