package com.netboard.game;

import java.util.ArrayList;
import java.util.Arrays;

import com.netboard.game.piece.Piece;

public class CheckersGame extends Game{
	//Member Variables
	private com.netboard.game.board.CheckersBoard board;
	private com.netboard.game.logic.CheckersLogic logic;
	private boolean gameOver;
	
	public com.netboard.game.board.CheckersBoard getBoard() {
		return this.board;
	}
	
//CONSTRUCTORS ---------------------------------------------------------------------------------
	//Default Constructor
	public CheckersGame(String player1, String player2)
	{
		setPlayers(player1, player2);
		board = new com.netboard.game.board.CheckersBoard(getPlayer1(), getPlayer2());
		boardState = new ArrayList<com.netboard.game.board.Board>(Arrays.asList(board));
		logic = new com.netboard.game.logic.CheckersLogic(board);
		//Player Turn (0: Player 1, 1: Player 2)
		gameOver = false;
		setWhosTurn(true);
	}
//END CONSTRUCTORS -----------------------------------------------------------------------------

//MEMBER METHODS -------------------------------------------------------------------------------
	
	//Prints the board (for CLI)
	public void printTable()
	{
		board.printBoard();
		System.out.println("Player 1 Pieces: " + board.getp1Pieces() + "\tPlayer 2 Pieces: " + board.getp2Pieces());
	}
	
	public int getCheckersTurn(){ return getWhosTurn()? 0 : 1;}
	
	
	public void getGameOver(){
		if(logic.checkWinner() == 0)
			System.out.println(getPlayer1() + " Wins!");
		else if(logic.checkWinner() == 1)
			System.out.println(getPlayer2() + " Wins!");
	}
	
	
	//returns the player turn
	public String getPlayerTurn()
	{
		if(getCheckersTurn() == 0)
			return "Player 1";
		return "Player 2";
	}
//END MEMBER METHODS ---------------------------------------------------------------------------
	
//ENGINE PORTION -------------------------------------------------------------------------------
	
	//method that checks move validity 
	public boolean checkLogic(Piece p, int newX, int newY) {
		if(logic.isValidMove(p, newX, newY))
			return true;
		return false;
	}

	
	//method that processes the user's desired move
	public boolean makeMove(Piece p, int newX, int newY){
			
		if(logic.isValidMove(p, newX, newY))
		{
			board.updateBoard(board.findPiece(p.getX(), p.getY()), newX, newY);
			return true;
		}
		else
		{
//			System.out.println("Invalid input, try again.");
			return false;
		}
	}
//END ENGINE PORTION ---------------------------------------------------------------------------
}

