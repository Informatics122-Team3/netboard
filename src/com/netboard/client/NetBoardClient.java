package com.netboard.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.netboard.client.GUI.GameMaker;
import com.netboard.client.GUI.HostGameMaker;
import com.netboard.client.GUI.LobbyMaker;
import com.netboard.client.GUI.LoginMaker;
import com.netboard.game.Player;
import com.netboard.message.InitMessage;
import com.netboard.message.RefreshMessage;

public class NetBoardClient {
	private static LoginMaker loginGUI;
	private static LobbyMaker lobbyGUI;
	private static GameMaker gameGUI;
	private static HostGameMaker hostGameGUI;
	
	public static final int PORT = 57575;
	private Socket s;
	private String name;
	private List<String> supportedGames;
	private List<String> playerInfo;
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
			gameGUI = new GameMaker(new Player("maxypoo43", s, "connect4"));
			hostGameGUI = new HostGameMaker();
			supportedGames = new ArrayList<String>();
			
			playerInfo = new ArrayList<String>();
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
		    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		    
		    InitMessage initMsg = new InitMessage(newName);
		    
		    out.writeObject(initMsg);
		    
		    //Reads validation response,
		    //if success: populate names & games on client side
		    //for display in GUIs
		    //else if "retry": loginGUI knows to keep displaying login window
		    ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		    
		    Object response = in.readObject();
		    
		    if (response instanceof InitMessage) {
		    	//failure
		    	return false;
		    }
		    else if (response instanceof RefreshMessage){
		    	//success
		    	RefreshMessage refMsg = (RefreshMessage) response;
		    	
		    	playerInfo = refMsg.getPlayerLobby();
		    	supportedGames = refMsg.getSupportedGames();
		    	
		    	name = newName;
		    	
		    	return true;
		    }
		    
		} catch (IOException | ClassNotFoundException e) {
			return false;
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
	
	public List<String> getSupportedGames(){
		return this.supportedGames;
	}
	public List<String> getPlayerInfo(){
		return this.playerInfo;
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

