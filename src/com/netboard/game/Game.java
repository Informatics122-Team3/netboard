package com.netboard.game;

import java.util.List;

import com.netboard.game.board.Board;
import com.netboard.game.piece.Piece;

public abstract class Game {
	protected List<Board> boardState;
	protected Logic logic;
	protected String name;
	protected boolean whosTurn;
	protected String player1;
	protected String player2;
	
	public void setPlayers(String p1, String p2) {
		this.player1 = p1;
		this.player2 = p2;
	}
	
	/**
	 * 
	 * @return the username of the player possessing the current turn
	 */
	public String getTurn() {
		return whosTurn ? player1 : player2;
	}
	
	/**
	 * This will be what ActiveGameThread calls to get the new boardstate after updating
	 * @return the current state of the board game
	 */
	public List<Board> getBoardState() {
		return this.boardState;
	}
	
	/**
	 * Toggles between player1 and player2's turn
	 */
	public void toggleTurn() {
		whosTurn = !whosTurn;
	}
	
	/**
	 * This will be what the ActiveGameThread calls to apply moves to the board
	 * @param p the active piece
	 * @param newX the new x position of the desired move
	 * @param newY the new y position of the desired move
	 * @return true if the move was valid
	 */
	public abstract boolean makeMove(Piece p, int newX, int newY);
}
