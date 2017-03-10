package com.netboard.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class NetBoardServer {
	public static final int PORT = 57575;
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
			ss = new ServerSocket(PORT);
			listenForConnections();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			NetBoardServer.log("Socket Error");
			e.printStackTrace();
		}
	}
	
	private void listenForConnections() throws IOException {
//      Example of serialization code (you can remove this):
//		Message m =  new HostMessage("12.32.23 - ip address", "jimmy - username", "checkers - gametype");
//		System.out.println(m.serialize());
		log("Listening for connections...");
		Socket s = ss.accept();
		log("Client connected: " + s.getInetAddress().toString() + ", " + s.getPort());
		
		Scanner in = new Scanner(s.getInputStream());
		PrintWriter out = new PrintWriter(s.getOutputStream(), true); //true means a newline will flush the output
		
		out.println("Welcome to NetBoardServer V_1.0");
		
		while (true) {
			String message = in.nextLine();
			if (message.equals("exit")) break;
			System.out.println(message);
			out.println(message);
		}
		//TODO serialize incoming JSON messages into java objects
		
		
		in.close();
		out.close();
	}
	
	private void createNewActiveGameThread(Game gameInstance, Player host, Player guest) {
		Thread gameThread = new Thread(new ActiveGameThread(gameInstance, host, guest));
		gameThread.start();
	}

}
