package com.netboard.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.netboard.server.Player;

public class NetBoardServer {
	public static final int PORT = 57575;
	private ServerSocket ss;
	private static final List<String> supportedGames = 
			Arrays.asList(
					"connect4", 
					"battleship", 
					"checkers"
			);
	
	private List<Player> playerLobby;
	
	public static void main(String[] args) {
		new NetBoardServer(PORT);
	}
	
	public static void log(String message) {
		System.err.println(message);
	}
	
	public NetBoardServer(int port) {
//		playerLobby = new LinkedList<>();
		try {
			ss = new ServerSocket(PORT);
			listenForConnections();
		} catch (IOException e) {
			NetBoardServer.log("Socket Error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a player to the lobby; sends required info to client
	 */
	public synchronized Boolean addNewPlayer(String username, Socket socket) {

		for(Player p : playerLobby){
			if (p.getUsername().equals(username))
				return false;
		}
		
//		Player p = new Player(username, socket, "Looking for Game");
//		this.playerLobby.add(p);
		log(String.format("Added %s :: %s to lobby...", username));
		return true;
	}
	
	/**
	 * Constructs and adds a new host player to the player lobby 
	 * @param username the username of the host
	 * @param socket the socket connected to the host client
	 * @param gameType the gametype string of the hosted game
	 */
	public synchronized void addHostToLobby(String username, Socket socket, String gameType) {
//		Player p = new Player(username, socket, gameType);
//		this.playerLobby.add(p);
		log(String.format("Added %s :: %s to lobby...", username, gameType));
	}
	
	/**
	 * Removes the player with username from the player lobby.
	 * @param username the username of the player to remove
	 */
	public synchronized void removeHostFromLobby(String username) {
		
		for (Player p : playerLobby) {
			if (p.getUsername().equals(username)) {
				playerLobby.remove(p);
				break;
			}
		}
		
		log(String.format("Removed %s from lobby...", username));
	}
	
	/**
	 * This creates a new game thread which handles the two connected players.
	 * Note: This will only be called from a LobbyThread object
	 * @param gameInstance - an instance of the hosted game type
	 * @param host the Player hosting the game
	 * @param guest the Player joining the game
	 */
	public synchronized void spawnActiveGameThread(String gameType, Player host, Player guest) {
		
//		ActiveGameThread agt = new ActiveGameThread(gameType, host, guest);
//		Thread gameThread = new Thread(agt);
//		gameThread.start();
	}
	
	private void listenForConnections() throws IOException {
//      Example of serialization code (you can remove this):
//		Message m =  new HostMessage("12.32.23 - ip address", "jimmy - username", "checkers - gametype");
//		System.out.println(m.serialize());
		
		while (true) {
			log("Listening for connections...");
			Socket s = ss.accept();
			log("Client connected: " + s.getInetAddress().toString() + ", " + s.getPort());

			if (handleConnection(s))
				spawnLobbyThread(s);
			else {}//handle err
		}
		
	}
	
	private Boolean handleConnection(Socket s){
		 DataInputStream in;
		 DataOutputStream out;
         String newUserName;
         
		try {
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			newUserName = in.readUTF();
			if (playerExists(newUserName)){
				out.writeUTF("retry");
				return false;
			}
			else {
				out.writeUTF("success");
//				playerLobby.add(new Player(newUserName, s, "Looking"));
				out.writeUTF(Arrays.toString(getPlayerNames().toArray()));
				out.writeUTF(Arrays.toString(getPlayerGames().toArray()));
				out.writeUTF(Arrays.toString(supportedGames.toArray()));
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		return false;
	}
	
	public List<String> getPlayerNames(){
		List<String> names = new ArrayList<String>();
		for (Player p : playerLobby){
			names.add(p.getUsername());
		}
		return names;
	}
	public List<String> getPlayerGames(){
		List<String> games = new ArrayList<String>();
		for (Player p : playerLobby){
			games.add(p.getGameType());
		}
		return games;
	}
	
	public Map<String, String> getPlayerInfo(){
		Map<String, String> info = new HashMap<String, String>();
		for(Player p : playerLobby){
			info.put(p.getUsername(), p.getGameType());
		}
		return info;
	}
	
	private Boolean playerExists(String newUserName){
		for (Player p : playerLobby){
			if (p.getUsername().equals(newUserName))
				return true;
		}
		return false;		
	}
	
	private void spawnLobbyThread(Socket clientSocket) {
		log("Spawning lobby thread to handle client...");
//		LobbyThread lt = new LobbyThread(this, clientSocket);
//		Thread lobbyThread = new Thread(lt);
//		lobbyThread.start();
	}

}

