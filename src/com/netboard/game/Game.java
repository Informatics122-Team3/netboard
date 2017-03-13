package com.netboard.game;

import java.util.List;

public abstract class Game {
	private List<Board> boardState;
	private Logic logic;
	private String name;
	private boolean whosTurn;
	// TODO i think this class might also have to keep track of its players
	
	
	/**
	 * 
	 * @return the username of the player possesses the current turn
	 */
	public String getPlayerTurn() {
		// TODO
		return "";
	}
	
	/**
	 * Toggles between player1 and player2's turn
	 */
	public void toggleTurn() {
		
	}
}
