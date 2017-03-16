package Battleship;

import java.util.ArrayList;

import com.netboard.game.piece.Ship;

public class BattleshipLogic {

	public BattleshipLogic(){}
	
	// Checks if the battleship was hit or not
	public boolean hit(int x1, int y1, Ship [] ships){
		for (int i = 0; i < ships.length; i++){
			for (int j = 0; j < ships[i].getCoords().size(); j++){
				if (ships[i].getCoords().get(j).getKey() == x1 && ships[i].getCoords().get(j).getValue() == y1){
					ships[i].hit(x1,y1);
					return true;
				}
			}
		}
		return false;
	}

	// Checks if the game is over
	public boolean isGameOver(Ship [] ships){
		int count = 0;
		for (int i = 0; i < ships.length; i++){
			count += ships[i].hit;
		}
		
		// total # of hits should be 17
		if (count == 17){
			return false;
		}
		
		return true;
	}
	
	// Declares winner of match
	public String getWinner(ArrayList<ArrayList<String>> a){
		return "";
	}
	
	// Updates the gameboard
	public boolean updateBoard(ArrayList<ArrayList<String>> a){
		return false;
	}
	
	// Declares the player whos turn it currently is
	public String getActivePlayer(ArrayList<ArrayList<String>> a){
		return "";
	}


	// Changes the player who is currently playing
	public void setActivePlayer(ArrayList<ArrayList<String>> a){
		
	}
	
}