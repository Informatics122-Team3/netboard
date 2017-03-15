package com.netboard.game.board;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckersBoard extends Board{

	private ArrayList<com.netboard.game.piece.CheckersPiece> p1;
	private ArrayList<com.netboard.game.piece.CheckersPiece> p2;
	
	private ArrayList<ArrayList<String>> CLIBoard;
	
	public CheckersBoard() {
		setWidth(8);
		setHeight(8);
		p1 = new ArrayList<com.netboard.game.piece.CheckersPiece>();
		p2 = new ArrayList<com.netboard.game.piece.CheckersPiece>();
		CLIBoard = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < 3; i = i + 2)
			for(int j = 1; j < getWidth(); j = j + 2)
			{
				p1.add(new com.netboard.game.piece.CheckersPiece(j,i,"o"));
				p2.add(new com.netboard.game.piece.CheckersPiece((getWidth()-1)-j,i+5,"x"));
			}
		for(int i = 0; i < getWidth(); i = i + 2)
		{
			p1.add(new com.netboard.game.piece.CheckersPiece(i,1,"o"));
			p2.add(new com.netboard.game.piece.CheckersPiece((getWidth()-1)-i,6,"x"));
		}
		
		setp1Pieces(p1.size());
		setp2Pieces(p2.size());
		setBoard();
	}

	public void setBoard()
	{
		for(int i = 0; i < getHeight(); i++)
			CLIBoard.add(new ArrayList<String>(Arrays.asList(" "," "," "," "," "," "," "," ")));
		for(com.netboard.game.piece.CheckersPiece p : p1)
			CLIBoard.get(p.getY()).set(p.getX(), p.getIcon());
		for(com.netboard.game.piece.CheckersPiece p : p2)
			CLIBoard.get(p.getY()).set(p.getX(), p.getIcon());

	}
	
	public void printBoard()
	{
		String topBar = "  ";
		for(int i = 0; i < getHeight(); i++)
			topBar = topBar + i + " ";
		System.out.println(topBar);
		for(int i = 0; i < getHeight(); i++)
		{
			System.out.println(" -----------------");
			String row = i + "|";
			for(int j = 0; j < getWidth(); j++)
				row = row.concat(CLIBoard.get(i).get(j).concat("|"));
			System.out.println(row);
		}
		System.out.println(" -----------------");
	}
	
	public void updateBoard(com.netboard.game.piece.CheckersPiece p, int newX, int newY)
	{
		if(p.getX() - newX == 2 && p.getY() - newY == 2)
			removePiece(p.getX() - 1, p.getY()-1, p.getIcon());
		if(p.getX() - newX == 2 && newY - p.getY() == 2)
			removePiece(p.getX() - 1, p.getY() + 1, p.getIcon());
		if(newX - p.getX() == 2 && p.getY() - newY == 2)
			removePiece(p.getX() + 1, p.getY() - 1, p.getIcon());
		if(newX - p.getX() == 2 && newY - p.getY() == 2)
			removePiece(p.getX() + 1, p.getY()+1, p.getIcon());
		
		CLIBoard.get(p.getY()).set(p.getX(), " ");
		p.setX(newX);
		p.setY(newY);
		
		//kinging a piece
		if(p.getIcon() == "x" && p.getY() == 0)
		{
			p.setIcon("X");
			p.setKing(true);
		}
		if(p.getIcon() == "o" && p.getY() == getHeight() - 1)
		{
			p.setIcon("O");
			p.setKing(true);
		}
		
		CLIBoard.get(p.getY()).set(p.getX(), p.getIcon());
		
	}

	public com.netboard.game.piece.CheckersPiece findPiece(int x, int y)
	{
		for(com.netboard.game.piece.CheckersPiece p : p1)
			if(p.getX() == x && p.getY() == y)
				return p;
		
		for(com.netboard.game.piece.CheckersPiece p : p2)
			if(p.getX() == x && p.getY() == y)
				return p;
		return new com.netboard.game.piece.CheckersPiece();
	}
	public void removePiece(int x, int y, String type)
	{
		if(type.equals("o") || type.equals("O"))
			for(com.netboard.game.piece.CheckersPiece p : p2)
			{
				if(p.getX() == x && p.getY() == y)
					{
					p2.remove(p);
					CLIBoard.get(p.getY()).set(p.getX(), " ");

					break;
					}
			}
		else if(type.equals("x") || type.equals("X"))
			for(com.netboard.game.piece.CheckersPiece p : p1)
				if(p.getX() == x && p.getY() == y)
				{
					p1.remove(p);
					CLIBoard.get(p.getY()).set(p.getX(), " ");
					break;
				}
		setp1Pieces(p1.size());
		setp2Pieces(p2.size());
	}

	@Override
	public void updateBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPiece(int x, int y, String type) {
		// TODO Auto-generated method stub
		if(CLIBoard.get(y).get(x).equals(" "))
		{
			if(type.equals("x"))
			{
				p1.add(new com.netboard.game.piece.CheckersPiece(x,y,type));
				CLIBoard.get(y).set(x, type);
			}
			else if(type.equals("X"))
			{
				p1.add(new com.netboard.game.piece.CheckersPiece(x,y,type,true));
				CLIBoard.get(y).set(x, type);
			}
			else if(type.equals("o"))
			{
				p2.add(new com.netboard.game.piece.CheckersPiece(x,y,type));
				CLIBoard.get(y).set(x, type);
			}
			else if(type.equals("O"))
			{
				p2.add(new com.netboard.game.piece.CheckersPiece(x,y,type,true));
				CLIBoard.get(y).set(x, type);
			}
			else
			{
				System.out.println("Error, Incorrect piece type: " + type + ".");
			}
		}
		else
		{
			System.out.println("Error, Piece already found at coordinates ["+x+","+y+"].");
		}
			
	}
}
