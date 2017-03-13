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
	
	/**
	 * @return the username of the player
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * @return string representation of the gameType the player is hosting/playing
	 */
	public String getGameType() {
		return this.gameType;
	}
	
	/**
	 * @return the ip address of the Player's machine
	 */
	public String getIPAddress() {
		return socket.getInetAddress().toString();
	}
	
	/**
	 * This is used for creating a Scanner from a player's socket.
	 * @return the InputStream of the player's socket
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException {
		return socket.getInputStream();
	}
	
	/**
	 * This is used for creating a PrintWriter from a player's socket.
	 * @return the OutputStream of the player's socket
	 * @throws IOException
	 */
	public OutputStream getOutputStream() throws IOException {
		return socket.getOutputStream();
	}
	
}
