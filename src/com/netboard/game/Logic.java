package com.netboard.game;

public interface Logic {
	public boolean isGameOver();
	public String getWinner(); //gets the player who made the last move once isGameOver() == true
	public boolean updateBoard(Piece[][], Piece); //make a move and returns false if the move is invalid
	public String getActivePlayer(); //whose turn is it?
	public void setActivePlayer();
}
