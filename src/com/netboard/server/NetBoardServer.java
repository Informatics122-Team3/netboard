//package com.netboard.server;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import com.netboard.game.Player;
//
//public class NetBoardServer {
//	public static final int PORT = 57575;
//	private ServerSocket ss;
//	private static final List<String> supportedGames = 
//			Arrays.asList(
//					"connect4", 
//					"battleship", 
//					"checkers"
//			);
//	
//	//volatile means this will be modified by multiple threads
//	private volatile List<Player> playerLobby;
//	
//	public static void main(String[] args) {
//		new NetBoardServer(PORT);
//	}
//	
//	public static void log(String message) {
//		System.err.println(message);
//	}
//	
//	public NetBoardServer(int port) {
//		playerLobby = new ArrayList<>();
//		try {
//			ss = new ServerSocket(PORT);
//			listenForConnections();
//		} catch (IOException e) {
//			NetBoardServer.log("Socket Error");
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * Constructs and adds a new host player to the player lobby 
//	 * @param username the username of the host
//	 * @param socket the socket connected to the host client
//	 * @param gameType the gametype string of the hosted game
//	 */
//	public synchronized void addHostToLobby(String username, Socket socket, String gameType) {
//		Player p = new Player(username, socket, gameType);
//		this.playerLobby.add(p);
//		log(String.format("Added %s :: %s to lobby...", username, gameType));
//	}
//	
//	/**
//	 * Removes the player with username from the player lobby.
//	 * @param username the username of the player to remove
//	 */
//	public synchronized void removeHostFromLobby(String username) {
//		
//		for (Player p : playerLobby) {
//			if (p.getUsername().equals(username)) {
//				playerLobby.remove(p);
//				break;
//			}
//		}
//		
//		log(String.format("Removed %s from lobby...", username));
//	}
//	
//	/**
//	 * This creates a new game thread which handles the two connected players.
//	 * Note: This will only be called from a LobbyThread object
//	 * @param gameInstance - an instance of the hosted game type
//	 * @param host the Player hosting the game
//	 * @param guest the Player joining the game
//	 */
//	public synchronized void spawnActiveGameThread(String gameType, Player host, Player guest) {
//		
//		ActiveGameThread agt = new ActiveGameThread(gameType, host, guest);
//		Thread gameThread = new Thread(agt);
//		gameThread.start();
//	}
//	
//	private void listenForConnections() throws IOException {
//		
//		while (true) {
//			log("Listening for connections...");
//			Socket s = ss.accept();
//			log("Client connected: " + s.getInetAddress().toString() + ", " + s.getPort());
//			
//			// TODO send the client the player lobby and the supported games
//			
//			spawnLobbyThread(s);
//		}
//		
//	}
//	
//	private void spawnLobbyThread(Socket clientSocket) {
//		log("Spawning lobby thread to handle client...");
//		LobbyThread lt = new LobbyThread(this, clientSocket);
//		Thread lobbyThread = new Thread(lt);
//		lobbyThread.start();
//	}
//
//}

package com.netboard.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.netboard.game.Player;

public class NetBoardServer {
	public static final int PORT = 57575;
	private ServerSocket ss;
	
	public static final List<String> supportedGames = 
			Arrays.asList(
					"connect4", 
					"battleship", 
					"checkers"
			);
	
	//volatile means this will be modified by multiple threads
	private volatile List<Player> playerLobby;
	
	public static void main(String[] args) {
		new NetBoardServer(PORT);
	}
	
	public void log(String message) {
		System.err.println(message);
	}
	
	public NetBoardServer(int port) {
		playerLobby = new ArrayList<>();
		try {
			ss = new ServerSocket(PORT);
			listenForConnections();
		} catch (IOException e) {
			log("Socket Error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructs and adds a new host player to the player lobby 
	 * @param username the username of the host
	 * @param socket the socket connected to the host client
	 * @param gameType the gametype string of the hosted game
	 */
	public synchronized void addHostToLobby(String username, Socket socket, String gameType) {
		Player p = new Player(username, socket, gameType);
		this.playerLobby.add(p);
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
		
		ActiveGameThread agt = new ActiveGameThread(gameType, host, guest);
		Thread gameThread = new Thread(agt);
		gameThread.start();
	}
	
	private void listenForConnections() throws IOException {
		while (true) {
			log("Listening for connections...");
			Socket s = ss.accept();
			log("Client connected: " + s.getInetAddress().toString() + ", " + s.getPort());

			if (handleConnection(s)) {
				spawnLobbyThread(s);
			}
			else {} //you'll get here if the client gives a duplicate username
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
				out.writeUTF(Arrays.toString(getPlayerLobby().toArray()));
				out.writeUTF(Arrays.toString(NetBoardServer.supportedGames.toArray()));				

				// sending the player lobby and supported games is sufficient
				// out.writeUTF("success");
				// We don't add the player to the lobby yet
				//playerLobby.add(new Player(newUserName, s, "Looking"));
				//out.writeUTF(Arrays.toString(getPlayerNames().toArray()));
				//out.writeUTF(Arrays.toString(getPlayerGames().toArray()));
				
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		return false;
	}
	
	public synchronized List<String> getPlayerLobby() {
		List<String> namesAndGames = new ArrayList<String>();
		
		for (Player p : playerLobby) {
			String username = p.getUsername(),
					gameStr = p.getGameType();
			
			namesAndGames.add(String.format("%s - %s", username, gameStr));
		}
		
		return namesAndGames;
	}
	
	/**
	 * 
	 * @param username the username of the desired player
	 * @return the Player if found, null otherwise
	 */
	public synchronized Player findPlayer(String username) {
		for (Player p : playerLobby) {
			if (p.getUsername().equals(username)) {
				return p;
			}
		}
		
		return null;
	}
	
//	public List<String> getPlayerNames(){
//		List<String> names = new ArrayList<String>();
//		for (Player p : playerLobby){
//			names.add(p.getUsername());
//		}
//		return names;
//	}
//	public List<String> getPlayerGames(){
//		List<String> games = new ArrayList<String>();
//		for (Player p : playerLobby){
//			games.add(p.getGameType());
//		}
//		return games;
//	}	
//	public Map<String, String> getPlayerInfo(){
//		Map<String, String> info = new HashMap<String, String>();
//		for(Player p : playerLobby){
//			info.put(p.getUsername(), p.getGameType());
//		}
//		return info;
//	}	
	private Boolean playerExists(String newUserName){
		for (Player p : playerLobby){
			if (p.getUsername().equals(newUserName))
				return true;
		}
		return false;		
	}
	
	private void spawnLobbyThread(Socket clientSocket) {
		log("Spawning lobby thread to handle client...");
		LobbyThread lt = new LobbyThread(this, clientSocket);
		Thread lobbyThread = new Thread(lt);
		lobbyThread.start();
	}

}
