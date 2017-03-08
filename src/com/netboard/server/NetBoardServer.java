package com.netboard.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class NetBoardServer {
	public static final int PORT = 56789;
	private ServerSocket ss;
	private List<String> supportedGames;
	private List<Player> playerLobby;
	
	public static void main(String[] args) {
		new NetBoardServer(PORT);
	}
	
	public static void log(String message) {
		System.err.println(message);
	}
	
	public NetBoardServer(int port) {
		//TODO 
		try {
			ss = new ServerSocket(port);
			listenForConnections(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			NetBoardServer.log("Socket Error");
		}
	}
	
	private void listenForConnections(int port) throws IOException {
		Socket s = ss.accept();
		log("Client connected!");
		
		//TODO serialize incoming JSON messages into java objects
	}
	
	private void createNewActiveGameThread(Game gameInstance, Player host, Player guest) {
		Thread gameThread = new Thread(new ActiveGameThread(gameInstance, host, guest));
		gameThread.start();
	}

}
