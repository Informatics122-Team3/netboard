package com.netboard.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.netboard.game.Game;
import com.netboard.game.GameFactory;
import com.netboard.game.Player;

public class ActiveGameThread implements Runnable {

	private Game gameInstance;
	private Player host;
	private Player guest;
	private boolean isFull;
	
	private Scanner hostIn;
	private PrintWriter hostOut;
	private Scanner guestIn;
	private PrintWriter guestOut;
	
	public ActiveGameThread(String gameType, Player host, Player guest) {
		this.gameInstance = GameFactory.createGame(gameType);
		
		// host initialization
		this.host = host;
		try {
			this.hostIn = new Scanner(host.getInputStream());
			this.hostOut = new PrintWriter(host.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// guest initialization
		this.guest = guest;
		try {
			this.guestIn = new Scanner(guest.getInputStream());
			this.guestOut = new PrintWriter(guest.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void run() {
		listenForBoardUpdates();		
	}
	
	private void listenForBoardUpdates() {
		
		while (true) {
			
			//TODO 
			
			if (gameInstance.getPlayerTurn().equals(host.getUsername())) {
				// TODO
				gameInstance.toggleTurn();
			}
			else {
				// TODO
			}			
		}
		
	}
	
	private void broadcastBoardUpdate() {
		// TODO send board update to host and guest
	}

}
