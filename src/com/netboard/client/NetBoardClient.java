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
	public Socket s;
	private String name;
	private String serverIP;
	private List<String> supportedGames;
	private List<String> playerInfo;
	//private ObjectInputStream objIn;
	//private ObjectOutputStream objOut;
	
	public static void main(String[] args) {
		new NetBoardClient();
		loginGUI.prepareGUI();
		lobbyGUI.prepareGUI();
		//gameGUI.prepareGUI();
		hostGameGUI.prepareGUI();
		loginGUI.show();
	}
	
	public NetBoardClient() {
			loginGUI = new LoginMaker(this);
			lobbyGUI = new LobbyMaker(this);
			//
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
		    
		    InitMessage initMsg = new InitMessage(newName);
		    
		    writeMessage(initMsg);
		    
		    Object response = readMessage();
		    
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
		    	serverIP = host;
		    	
		    	return true;
		    }
		    
		} catch (IOException e) {
			return false;
		}
		return false;
	}
	

	public <T> void writeMessage(T msg){
		try {
			ObjectOutputStream objOut = new ObjectOutputStream(s.getOutputStream());
			objOut.writeObject(msg);
		} catch (IOException e) { e.printStackTrace(); }

	}

	@SuppressWarnings("unchecked")
	public <T> T readMessage(){
		T someMsg = null;
		try {
			ObjectInputStream objIn = new ObjectInputStream(s.getInputStream());
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
	
	public String getServerIP() {
		return this.serverIP;
	}
	
	public void showLobby(){
		lobbyGUI.show();
	}
	public void showGame(){
		GameMaker gameGUI = new GameMaker(new Player("testPlayer", s ,"testGame"));
		gameGUI.prepareGUI();
		gameGUI.show();
	}
	
	public void showHostGame(){
		hostGameGUI.show();
	}
	
	public void showLogin(){
		loginGUI.show();
	}

	public void refresh() {
		RefreshMessage refRequestMsg =  new RefreshMessage(null,null);
		
		writeMessage(refRequestMsg);
		
		RefreshMessage refResponseMsg = readMessage();
		
		this.playerInfo = refResponseMsg.getPlayerLobby();
		this.supportedGames = refResponseMsg.getSupportedGames();
		
		lobbyGUI.redraw();
		
	}
	
}

