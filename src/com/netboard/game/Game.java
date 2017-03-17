package com.netboard.game;

import java.util.List;

import com.netboard.game.board.Board;
import com.netboard.game.piece.Piece;

public abstract class Game {
	private List<Board> boardState;
	private Logic logic;
	private String name;
	private boolean whosTurn;
	private String player1;
	private String player2;
	
	public void setPlayers(String p1, String p2) {
		this.player1 = p1;
		this.player2 = p2;
	}
	
	/**
	 * 
	 * @return the username of the player possesses the current turn
	 */
	public String getTurn() {
		return whosTurn ? player1 : player2;
	}
	
	public List<Board> getBoardState() {
		return this.boardState;
	}
	
	/**
	 * Toggles between player1 and player2's turn
	 */
	public void toggleTurn() {
		whosTurn = !whosTurn;
	}
	
	public abstract boolean makeMove(Piece p, int newX, int newY);
}
