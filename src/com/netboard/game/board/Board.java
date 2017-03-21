package com.netboard.game.board;

import java.util.ArrayList;

import com.netboard.game.piece.Piece;

public abstract class Board {
	private int width,height,p1Pieces,p2Pieces, turn;
	private ArrayList<ArrayList<com.netboard.game.piece.Piece>> boardState;
	private String player1, player2;
	
	public Board(){
		boardState = new ArrayList<ArrayList<com.netboard.game.piece.Piece>>();
		player1 = "";
		player2 = "";
		turn = 0;
	}
	
	public Board(String player1, String player2)
	{
		boardState = new ArrayList<ArrayList<com.netboard.game.piece.Piece>>();
		this.player1 = player1;
		this.player2 = player2;
		turn = 0; 
	}

	public int getWidth() 	{ return width; }
	public int getHeight() 	{ return height; }
	public int getp1Pieces(){ return p1Pieces; }
	public int getp2Pieces(){ return p2Pieces; }
	public int getTurn() 	{ return turn; }
	
	//takes in coordinates, puts out Piece.
	public abstract com.netboard.game.piece.Piece at (int x, int y);
	
	
	public ArrayList<ArrayList<com.netboard.game.piece.Piece>> getBoard(){ return boardState; }
	
	public String getPlayer1() { return player1; }
	public String getPlayer2() { return player2; }
	
	public void setWidth(int width) 	{ this.width = width; }
	public void setHeight(int height) 	{ this.height = height; }
	public void setp1Pieces(int amount)	{ p1Pieces = amount; }
	public void setp2Pieces(int amount)	{ p2Pieces = amount; }
	
	public void setBoardState(ArrayList<ArrayList<com.netboard.game.piece.Piece>> boardState ){ this.boardState = boardState; }

	public void changeTurn() { turn = (turn + 1) % 2; }
	
	public abstract void setBoard();
	public abstract void printBoard();
	public abstract void updateBoard();
	
	public abstract void addPiece(int x, int y, String type); //NOTE: you can compare the string type, and then create the object accordingly.
	public abstract void removePiece(int x, int y, String type);
}


