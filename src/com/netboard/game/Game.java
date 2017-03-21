package com.netboard.game;

import java.util.ArrayList;

import com.netboard.game.board.Board;

public abstract class Game {
	protected ArrayList<Board> boardState;
	private String name;
	private boolean whosTurn;
	private String player1;
	private String player2;
	
	
	public void setPlayers(String p1, String p2) { this.player1 = p1; this.player2 = p2; }
	
	public boolean getWhosTurn() { return whosTurn; }
	public void setWhosTurn(boolean whosTurn) { this.whosTurn = whosTurn; }
	
	public abstract boolean isGameOver();
	public String getTurn() { return whosTurn ? player1 : player2;}
	public String getPlayer1() { return player1; }
	public String getPlayer2() { return player2; }
	public String getGameName() { return name; }
	public void toggleTurn() { whosTurn = !whosTurn; }
	public ArrayList<Board> getBoardState() { return this.boardState; }
	public abstract boolean checkLogic(com.netboard.game.piece.Piece p, int newX, int newY);
	public abstract boolean makeMove(com.netboard.game.piece.Piece p, int newX, int newY);	
}
