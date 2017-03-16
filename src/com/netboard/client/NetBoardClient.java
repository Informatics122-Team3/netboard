package com.netboard.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

import com.netboard.client.GUI.GameMaker;
import com.netboard.client.GUI.LobbyMaker;
import com.netboard.client.GUI.LoginMaker;
import com.netboard.game.Player;
import com.netboard.message.HostMessage;
import com.netboard.message.InitMessage;

public class NetBoardClient {
	private static LoginMaker loginGUI;
	private static LobbyMaker lobbyGUI;
	private static GameMaker gameGUI;

	private static Map<String, Player> players;
	

	public static final int PORT = 57575;
	private static Scanner cin = new Scanner(System.in);
	private Socket s;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public static void main(String[] args) {
		new NetBoardClient();
	}
	
	public NetBoardClient() {
		

		// this is just a network test TODO remove this
		System.out.print("Enter ip address:");
		String host = cin.nextLine();
		
		try {
			InetAddress ip = InetAddress.getByName(host);
			s = new Socket(ip, PORT);
			String username = "maxypoo43"; //TODO remove
			System.out.println("Server Connected: " + s.getInetAddress().toString() + ", " + s.getPort());
			
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			
			out.writeObject(new InitMessage(username));
			Object replyMsg = in.readObject();
			
			if(replyMsg instanceof InitMessage) {
				System.err.println("username already exists");
				return;
			}
			
			// otherwise InitMessage was successful
			while(true) {
				System.out.print(">> ");
				String message = cin.nextLine();
				
				switch(message) {
				case "host":
					out.writeObject(new HostMessage(s.getInetAddress().toString(), username, "connect4"));
					break;
				case "join":
					break;
				default:
					
				}
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
