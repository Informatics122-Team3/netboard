package com.netboard.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.netboard.game.Player;
import com.netboard.message.CommsBridge;
import com.netboard.message.InitMessage;
import com.netboard.message.RefreshMessage;


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
	
	/**
	 * Entry point for the server-side program
	 * @param port the port on which to listen for connections
	 */
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
		log(String.format("Added %s - %s to lobby...", username, gameType));
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
		
		ActiveGameThread agt = new ActiveGameThread(gameType, host, guest, this);
		Thread gameThread = new Thread(agt);
		gameThread.start();
		log("Spawned an ActiveGameThread to handle game");
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
         InitMessage initMsg;
         RefreshMessage refMsg;
         

		initMsg = (InitMessage) CommsBridge.readMessage(s);
		
		if (playerExists(initMsg.getUsername())) {
			initMsg.setInvalidUsername();
			CommsBridge.writeMessage(s, initMsg);
			
			return false;
		}
		else {
			
			refMsg = new RefreshMessage(getPlayerLobby(), supportedGames);
			CommsBridge.writeMessage(s, refMsg);

			return true;
		}

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
		
	private Boolean playerExists(String newUsername){
		Player p = findPlayer(newUsername);
		return p != null;
	}
	
	private void spawnLobbyThread(Socket clientSocket) {
		LobbyThread lt = new LobbyThread(this, clientSocket);
		Thread lobbyThread = new Thread(lt);
		lobbyThread.start();
		log("Spawned a lobby thread to handle client...");
		try {
			listenForConnections();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
