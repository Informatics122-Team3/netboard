package com.netboard.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.netboard.client.GUI.GameMaker;
import com.netboard.client.GUI.HostGameMaker;
import com.netboard.client.GUI.LobbyMaker;
import com.netboard.client.GUI.LoginMaker;
import com.netboard.game.Player;

public class NetBoardClient {
	private static LoginMaker loginGUI;
	private static LobbyMaker lobbyGUI;
	private static GameMaker gameGUI;
	private static HostGameMaker hostGameGUI;
	
	public static final int PORT = 57575;
	private Socket s;
	private String name;
	private ArrayList<String> supportedGames;
	private Map<String, String> playerInfo;
	private ObjectInputStream objIn;
	private ObjectOutputStream objOut;
	
	public static void main(String[] args) {
		new NetBoardClient();
		loginGUI.prepareGUI();
		lobbyGUI.prepareGUI();
		gameGUI.prepareGUI();
		hostGameGUI.prepareGUI();
		loginGUI.show();
	}
	
	public NetBoardClient() {
			loginGUI = new LoginMaker(this);
			lobbyGUI = new LobbyMaker(this);
			gameGUI = new GameMaker(this);
			hostGameGUI = new HostGameMaker(this);
			supportedGames = new ArrayList<String>();
			
			//TODO	change this to ArrayList<Player> ??
			playerInfo = new HashMap<String, String>();
	}
	
	/**
	 * Attempts to connect to server, and read in initial player names
	 * and supported games
	 * Called by loginGUI, using the serverTxt & nameTxt text fields
	 * @param newName: the username of the player
	 * @param host: 
	 * @return whether socket connection was successful
	 */
	public Boolean connect(String host, String newName){
		try {
			InetAddress ip = InetAddress.getByName(host);
			s = new Socket(ip, PORT);
			
			//TODO encapsulate into its own init function
			//This sends the name to the server for validation
		    OutputStream toServer = s.getOutputStream();
		    DataOutputStream out = new DataOutputStream(toServer);
		    out.writeUTF(newName);
		    
		    //Reads validation response,
		    //if "success": populate names & games on client side
		    //for display in GUIs
		    //else if "retry": loginGUI knows to keep displaying login window
		    InputStream inFromServer = s.getInputStream();
		    DataInputStream in = new DataInputStream(inFromServer);
		    String response = in.readUTF();
		    if (response.equals("success")){
		    	
		    	String line = in.readUTF();
				String names[] = line.split(" ");
		    	line = in.readUTF();
				String playerGames[] = line.split(" ");
		    	line = in.readUTF();
				String serverGameList[] = line.split(" ");
				
				for(int i = 0; i < names.length; ++i){
					playerInfo.put(names[i], playerGames[i]);
				}
				supportedGames = new ArrayList<>(Arrays.asList(serverGameList));
				name = newName;
		    	return true;
		    }
		    else if (response.equals("retry")){
		    	return false;
		    }
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

	public <T> void writeMessage(T msg){
		try {
			objOut = new ObjectOutputStream(s.getOutputStream());
			objOut.writeObject(msg);
		} catch (IOException e) { e.printStackTrace(); }
	}

	@SuppressWarnings("unchecked")
	public <T> T readMessage(){
		T someMsg = null;
		try {
			objIn = new ObjectInputStream(s.getInputStream());
			someMsg = (T) objIn.readObject();
		} 
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		return someMsg;
	}
	
	public String getIP(){
		return s.getInetAddress().getHostAddress();
	}
	public ArrayList<String> getSupportedGames(){
		return this.supportedGames;
	}
	public Set<Entry<String, String>> getPlayerInfo(){
		return this.playerInfo.entrySet();
	}

	public String getName(){
		return this.name;
	}
	
	public void showLobby(){
		lobbyGUI.show();
	}
	public void showGame(){
		gameGUI.show();
	}
	public void showHostGame(){
		hostGameGUI.show();
	}
	public void showLogin(){
		loginGUI.show();
	}
	//
}


