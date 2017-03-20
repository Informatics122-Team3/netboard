package com.netboard.game;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Player {

	private String username;
	private Socket socket;
	private String gameType;
	
	public Player(String username, String gameType) {
		//TODO: Remove this constructor after integrating GameMaker with Server/Client
		this.socket = socket;
		this.gameType = gameType;
	}
	
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
	
	public Socket getSocket() {
		return this.socket;
	}
	
	/**
	 * @return the ip address of the Player's machine
	 */
	public String getIPAddress() {
		return socket.getInetAddress().toString();
	}
	

	
}
