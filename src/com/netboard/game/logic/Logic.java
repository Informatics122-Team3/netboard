package com.netboard.game.logic;

import java.util.ArrayList;

import com.netboard.game.piece.Piece;

public interface Logic {
	public boolean isGameOver();
	public String getWinner(); //gets the player who made the last move once isGameOver() == true
	
	public boolean isValidMove(Piece p, int newX, int newY);
	
}
