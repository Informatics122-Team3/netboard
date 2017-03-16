package com.netboard.game.board;

import java.util.ArrayList;

public abstract class Board {
	private int width,height,p1Pieces,p2Pieces;
	private ArrayList<ArrayList<com.netboard.game.piece.Piece>> boardState;
	
	public Board(){
		boardState = new ArrayList<ArrayList<com.netboard.game.piece.Piece>>();
	}

	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getp1Pieces(){ return p1Pieces; }
	public int getp2Pieces(){ return p2Pieces; }
	public ArrayList<ArrayList<com.netboard.game.piece.Piece>> getBoard(){ return boardState; }
	
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public void setp1Pieces(int amount){ p1Pieces = amount; }
	public void setp2Pieces(int amount){ p2Pieces = amount; }
	public void setBoardState(ArrayList<ArrayList<com.netboard.game.piece.Piece>> boardState ){ this.boardState = boardState; }

	public abstract void setBoard();
	public abstract void printBoard();
	public abstract void updateBoard();
	
	public abstract void addPiece(int x, int y, String type); //NOTE: you can compare the string type, and then create the object accordingly.
	public abstract void removePiece(int x, int y, String type);
	
}


