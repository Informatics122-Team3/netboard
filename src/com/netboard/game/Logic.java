package com.netboard.game;

import java.util.ArrayList;

import com.netboard.game.piece.Piece;

public interface Logic {
	public boolean isGameOver();
	public String getWinner(); //gets the player who made the last move once isGameOver() == true
		
//Ideally this method should only be updating the current board, next version of Logic will only update the local logic Board with a new one.
	public boolean updateBoard(ArrayList<ArrayList<String>> board, Piece p); //make a move and returns false if the move is invalid
	
	public boolean isValidMove(Piece p, int newX, int newY);
	
}
