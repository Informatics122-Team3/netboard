package com.netboard.game;

import java.util.ArrayList;
import java.util.Arrays;

import com.netboard.game.board.Board;
import com.netboard.game.piece.Piece;

public class ConnectFourGame extends Game {
	private com.netboard.game.logic.ConnectFourLogic logic;
	private com.netboard.game.board.ConnectFourBoard board;
	
	public com.netboard.game.board.ConnectFourBoard getBoard() {
		return this.board;
	}
	
	public ConnectFourGame(int rows, int columns){
		logic = new com.netboard.game.logic.ConnectFourLogic();
		board = new com.netboard.game.board.ConnectFourBoard(rows, columns);
	}
	
	@Override
	public boolean checkLogic(Piece p, int newX, int newY) {
		//always returns false since isValidMove not implemented
		if(logic.isValidMove(p, newX, newY))
			return true;
		return false;
	}

	@Override
	public boolean makeMove(Piece p, int newX, int newY) {
		if(logic.isValidMove(p, newX, newY))
		{
			//needs changing when board class is finished
			board.updateBoard();
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

}
