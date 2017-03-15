package com.netboard.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	 * This is used for reading Objects from a player's socket.
	 * @return the ObjectInputStream of the player's socket
	 * @throws IOException
	 */
	public ObjectInputStream getObjectInputStream() throws IOException {
		return new ObjectInputStream(socket.getInputStream());
	}
	
	/**
	 * This is used for writing objects to a player's socket.
	 * @return the ObjectOutputStream of the player's socket
	 * @throws IOException
	 */
	public ObjectOutputStream getObjectOutputStream() throws IOException {
		return new ObjectOutputStream(socket.getOutputStream());
	}
	
}
