package com.netboard.game;

import com.netboard.game.piece.Piece;

public interface Logic {
	public boolean isGameOver();
	public String getWinner(); //gets the player who made the last move once isGameOver() == true
	public boolean updateBoard(Piece[][] board, Piece p); //make a move and returns false if the move is invalid
	public String getActivePlayer(); //whose turn is it?
	public void setActivePlayer();
}
