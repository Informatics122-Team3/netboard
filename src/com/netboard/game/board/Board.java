package com.netboard.game.board;

public abstract class Board {
	private int width,height,p1Pieces,p2Pieces;
	
	
	public Board(){}

	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getp1Pieces(){ return p1Pieces; }
	public int getp2Pieces(){ return p2Pieces; }
	
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public void setp1Pieces(int amount){ p1Pieces = amount; }
	public void setp2Pieces(int amount){ p2Pieces = amount; }

	public abstract void setBoard();
	public abstract void printBoard();
	public abstract void updateBoard();
	
	public abstract void addPiece(int x, int y, String type); //NOTE: you can compare the string type, and then create the object accordingly.
	public abstract void removePiece(int x, int y, String type);
	
}


