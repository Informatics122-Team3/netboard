package com.netboard.game;

import java.util.ArrayList;
import java.util.Arrays;

import com.netboard.game.board.Board;
import com.netboard.game.logic.ConnectFourLogic;
import com.netboard.game.board.ConnectFourBoard;
import com.netboard.game.piece.Piece;

public class ConnectFourGame extends Game {
	private ConnectFourLogic logic;
	private ConnectFourBoard board;
	
	public ConnectFourGame(int rows, int columns)
	{
		logic = new ConnectFourLogic();
		board = new ConnectFourBoard(rows, columns);
	}
	
	@Override
	public boolean checkLogic(Piece p, int newX, int newY) 
	{
		return (logic.isValidMove(p, newX, newY));
	}

	@Override
	public boolean makeMove(Piece p, int newX, int newY) 
	{
		logic.setBoard(board);
		
		if(logic.isValidMove(p, newX, newY))
		{
//			if(logic.isGameOver())
//			{
//				return false;
//			}
			
			board.updateBoard(newX, getTurn());
			
//			toggleTurn();
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isGameOver()
	{
		return logic.isGameOver();
	}
	
	public ArrayList<Board> getBoardState()
	{
		return new ArrayList(Arrays.asList(board));
	}
	
	public void printBoard()
	{
		board.printBoard();
	}
}
