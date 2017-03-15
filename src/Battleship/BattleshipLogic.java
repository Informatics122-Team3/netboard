package Battleship;

import com.netboard.game.piece.Piece;

public class BattleshipLogic {

	public BattleshipLogic(){
		
	}
	
	// Checks if the battleship was hit or not
	public boolean hit(int x1, int y1, int [][] board, Ship [] ships){
		for (int i = 0; i < ships.length; i++){
			for (int j = 0; j < ships[i].length; j++){

			}
		}
		return true;
	}
	
	// Checks if the battleship was sunk
	public boolean didShipSink(Ship [] ships){
		for (int i = 0; i < ships.length; i++){
			for (int j = 0; j < ships[i].length; j++){
				if(ships[i].ship[j] == 0){
					return false;
				}
			}
		}
		return true;
	}

	// Checks if the game is over
	public boolean isGameOver(int [][] board){
		return false;
	}
	
	// Declares winner of match
	public String getWinner(int [][] board){
		return "";
	}
	
	// Updates the gameboard
	public boolean updateBoard(Piece[][] board, Piece p){
		return false;
	}
	
	// Declares the player whos turn it currently is
	public String getActivePlayer(int [][] board){
		return "";
	}
	
	// Changes the player who is currently playing
	public void setActivePlayer(int [][] board){
		
	}
	
}