package com.netboard.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetBoardClient {
	private static LoginMaker loginGUI;
	private static LobbyMaker lobbyGUI;
	private static GameMaker gameGUI;

	private static Map<String, Player> players;
	

	public static final int PORT = 57575;
	private static Scanner cin = new Scanner(System.in);
	private Socket s;
	
	public static void main(String[] args) {
		new NetBoardClient();
	}
	
	public NetBoardClient() {
		loginGUI = new LoginMaker();
		lobbyGUI = new LobbyMaker();
		gameGUI = new GameMaker();
		players = new HashMap<String, Player>();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		Client UserInterface = new Client();	//create a new arcade
		loginGUI.prepareGUI();
		lobbyGUI.prepareGUI();
		gameGUI.prepareGUI(); 
		loginGUI.show();			//start in the login gui
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// this is just a network test TODO remove this
		//System.out.print("Enter ip address:");
		//String host = cin.nextLine();
		
		/*try {
			InetAddress ip = InetAddress.getByName(host);
			s = new Socket(ip, PORT);
			System.out.println("Server Connected: " + s.getInetAddress().toString() + ", " + s.getPort());
			
			PrintWriter out = new PrintWriter(s.getOutputStream());
			
			while(true) {
				System.out.print(">> ");
				String message = cin.nextLine();
				out.println(message);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
