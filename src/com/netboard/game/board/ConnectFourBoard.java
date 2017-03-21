package com.netboard.game.board;
import java.util.ArrayList;

public class ConnectFourBoard extends Board{
	private int rowR;
	private int colC;
	
	public com.netboard.game.piece.Piece at(int row, int col)
	{
		return getBoard().get(row).get(col);
	}
	
	public ConnectFourBoard(int rows, int columns)
	{
		rowR = rows;
		colC = columns;
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
	
	public void printBoard()
	{
			for (int i = 0; i < rowR; i++) {
				for (int j = 0; j < colC; j++) {
					System.out.print(getBoard().get(i).get(j).getIcon());
				}
				System.out.println("");
			}
	}
	
	public void updateBoard(int newX, String player)
	{
			for(int i = 5; i >= 0; i--)
			{
				if(getBoard().get(i).get(newX).getIcon() == " ")
				{
					getBoard().get(i).get(newX).setIcon(player);
					break;
				}
			}
	}
	
	@Override
	public void updateBoard()
	{
	}
	
	public void setBoard()
	{
		//Not Needed for Connect 4
	}
	
	public void addPiece(int x, int y, String type) //NOTE: you can compare the string type, and then create the object accordingly.
	{	
		//Not Needed for Connect 4
	}
	public void removePiece(int x, int y, String type)
	{
		//Not Needed for Connect 4
	}
}
