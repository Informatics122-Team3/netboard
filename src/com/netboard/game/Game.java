package com.netboard.game;

import java.util.List;

public abstract class Game {
	private List<Board> boardState;
	private Logic logic;
	private String name;
	private boolean whosTurn;
	// TODO i think this class might also have to keep track of its players
	
	public String getPlayerTurn() {
		// TODO
		return "";
	}
	
	public void toggleTurn() {
		
	}
}
