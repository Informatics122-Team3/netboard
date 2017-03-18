package com.netboard.game;

import java.util.ArrayList;

import com.netboard.game.board.Board;
import com.netboard.game.piece.Piece;

public class Connect4Game extends Game {

	public Connect4Game() {
		this.boardState = new ArrayList<Board>();
	}
	
	@Override
	public boolean makeMove(Piece p, int newX, int newY) {
		// TODO check logic and make move if valid
		return true;
	}

}
