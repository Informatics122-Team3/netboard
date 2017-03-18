package com.netboard.game;

import java.util.ArrayList;

import com.netboard.game.board.Board;
import com.netboard.game.piece.Piece;

public abstract class Game {
<<<<<<< HEAD
	protected ArrayList<Board> boardState;
	private String name;
	private boolean whosTurn;
	private String player1;
	private String player2;
=======
	protected List<Board> boardState;
	protected Logic logic;
	protected String name;
	protected boolean whosTurn;
	protected String player1;
	protected String player2;
>>>>>>> origin/master
	
	public void setPlayers(String p1, String p2) { this.player1 = p1; this.player2 = p2; }
	
<<<<<<< HEAD
	public boolean getWhosTurn() { return whosTurn; }
	public void setWhosTurn(boolean whosTurn) { this.whosTurn = whosTurn; }
	
	public String getTurn() { return whosTurn ? player1 : player2;}
	public String getPlayer1() { return player1; }
	public String getPlayer2() { return player2; }
	public String getGameName() { return name; }
	public void toggleTurn() { whosTurn = !whosTurn; }
	public ArrayList<Board> getBoardState() { return this.boardState; }
	public abstract boolean checkLogic(com.netboard.game.piece.Piece p, int newX, int newY);
	public abstract boolean makeMove(com.netboard.game.piece.Piece p, int newX, int newY);	
=======
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
>>>>>>> origin/master
}
