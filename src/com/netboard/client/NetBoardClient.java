package com.netboard.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.netboard.client.GUI.GameMaker;
import com.netboard.client.GUI.HostGameMaker;
import com.netboard.client.GUI.LobbyMaker;
import com.netboard.client.GUI.LoginMaker;
import com.netboard.game.Player;
import com.netboard.game.piece.Piece;
import com.netboard.message.ApplyMoveMessage;
import com.netboard.message.BoardUpdateMessage;
import com.netboard.message.CommsBridge;
import com.netboard.message.InitMessage;
import com.netboard.message.RefreshMessage;

public class NetBoardClient {
	private static LoginMaker loginGUI;
	private static LobbyMaker lobbyGUI;
	private static GameMaker gameGUI;
	private static HostGameMaker hostGameGUI;
	
	public static final int PORT = 57575;
	public Socket s;
	private String username;
	private String serverIP;
	private List<String> supportedGames;
	private List<String> playerInfo;
	
	public static void main(String[] args) {
		new NetBoardClient();
		loginGUI.prepareGUI();
		lobbyGUI.prepareGUI();
		hostGameGUI.prepareGUI();
		loginGUI.show();
	}
	
	public NetBoardClient() {
			loginGUI = new LoginMaker(this);
			lobbyGUI = new LobbyMaker(this);
			//
			hostGameGUI = new HostGameMaker(this);
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
		    	
		    	username = newName;
		    	serverIP = host;
		    	
		    	return true;
		    }
		    
		} catch (IOException e) {
			return false;
		}
		return false;
	}
	
	public List<String> getSupportedGames(){
		return this.supportedGames;
	}
	public List<String> getPlayerInfo(){
		return this.playerInfo;
	}

	/**
	 * 
	 * @return the username of this client
	 */
	public String getUsername(){
		return this.username;
	}
	
	public String getServerIP() {
		return this.serverIP;
	}
	
	public void showLobby(){
		lobbyGUI.show();
	}
	
	public void showGame(String hostname, String gameType){
		Player hostPlayer = new Player(this.username, s, gameType);
		gameGUI = new GameMaker(hostPlayer, this);
		gameGUI.prepareGUI();		
		gameGUI.show();
	}
	
	public void showHostGame(){
		hostGameGUI.show();
	}
	
	public void showLogin(){
		loginGUI.show();
	}

	/**
	 * Constructs and sends a RefreshMessage to the server.
	 * Refreshes playerInfo list and supported game list.
	 * Repaints the lobby screen to reflect the refresh.
	 */
	public void refreshLobby() {
		RefreshMessage refRequestMsg =  new RefreshMessage(null,null);
		
		writeMessage(refRequestMsg);
		
		RefreshMessage refResponseMsg = readMessage();
		
		this.playerInfo = refResponseMsg.getPlayerLobby();
		this.supportedGames = refResponseMsg.getSupportedGames();
		
		lobbyGUI.refresh(); // TODO lobbyGUI.refresh();
	}
	
	/**
	 * Sends an ApplyMoveMessage to the server with isConnected = false
	 */
	public void applyDisconnectMove() {
		ApplyMoveMessage disconnectMsg = 
				new ApplyMoveMessage(null, 0, 0, false); // false means you want to disconnect
		writeMessage(disconnectMsg);
	}
	
	/**
	 * Sends an ApplyMoveMessage to the server with the new Move to be made
	 */
	public void applyBoardMove(Piece piece, int newX, int newY) {
		ApplyMoveMessage moveMsg = new ApplyMoveMessage(piece, newX, newY, true);
		writeMessage(moveMsg);
	}

	/**
	 * Writes an object to the server using the CommsBridge
	 * @param msg the object representing the message
	 * @return true if the message was transmitted successfully
	 */
	public boolean writeMessage(Object msg) {
		return CommsBridge.writeMessage(s, msg);
	}
	
	/**
	 * Reads an incoming object from the server
	 * @return a generic object to be cast into the object of your choice (if they're compatible)
	 */
	public <T> T readMessage(){
		return CommsBridge.readMessage(s);
	}
	
	public void waitForBoardUpdate() {
		
		BoardUpdateMessage boardMsg = readMessage();
		
		if (!boardMsg.getTurn().equals(username)) {
			
			JFrame waitDialog = new JFrame();
			waitDialog.add(new JLabel("Waiting for opponent..."));
			
			waitDialog.show();
			
			boardMsg = readMessage();
			
			waitDialog.dispose();
		}
		
		gameGUI.updateBoardGUI(boardMsg.getBoardState(), boardMsg.getGameType());
	}							
	
	
}

