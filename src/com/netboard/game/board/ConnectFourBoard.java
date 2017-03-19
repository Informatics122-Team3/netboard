package com.netboard.game.board;
import java.util.ArrayList;

public class ConnectFourBoard extends Board{
	
	public com.netboard.game.piece.Piece at(int x, int y)
	{
		return new com.netboard.game.piece.Piece();
	}
	
	public ConnectFourBoard(int rows, int columns)
	{
		setWidth(columns);
		setHeight(rows);
		for(int i = 0; i < rows; i++)
		{
			getBoard().add(new ArrayList<com.netboard.game.piece.Piece>());
			for(int j = 0; j < columns; j++)
			{
				getBoard().get(i).add(new com.netboard.game.piece.Piece(i, j, " "));
			}
		}
	}
	public void setBoard()
	{
		//setWidth(7);
		//setHeight(6);
	}
	public void printBoard()
	{
		
	}
	public void updateBoard()
	{
		
	}
	
	public void addPiece(int x, int y, String type) //NOTE: you can compare the string type, and then create the object accordingly.
	{	
		
	}
	public void removePiece(int x, int y, String type)
	{
		
	}
	
}
