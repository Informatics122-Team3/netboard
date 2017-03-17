package com.netboard.game.board;

import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;

import com.netboard.game.piece.AircraftCarrier;
import com.netboard.game.piece.Battleship;
import com.netboard.game.piece.Cruiser;
import com.netboard.game.piece.Destroyer;
import com.netboard.game.piece.Ship;
import com.netboard.game.piece.Submarine;

public class BattleshipBoard extends Board {

	private ArrayList<ArrayList<String>> board;
	private ArrayList<Ship> player;
	
	public BattleshipBoard(int x1, int y1, int vert_or_hor){
		// initializes the game board size
		setWidth(10);
		setHeight(10);
		
		AircraftCarrier a = new AircraftCarrier(x1,y1,vert_or_hor);
		Battleship b = new Battleship(x1,y1,vert_or_hor);
		Cruiser c = new Cruiser(x1,y1,vert_or_hor);
		Destroyer d = new Destroyer(x1,y1,vert_or_hor);
		Submarine s = new Submarine(x1,y1,vert_or_hor);
		
		player = new ArrayList<Ship>();
		player.add(a);
		player.add(b);
		player.add(c);
		player.add(d);
		player.add(s);
	}

	public void setBoard() {}
	
	public void printBoard() {}
	
	public void addPiece(int x, int y, String type) {} //NOTE: you can compare the string type, and then create the object accordingly.
	
	public void removePiece(int x, int y, String type){}

	@Override
	public void updateBoard() {
		// TODO Auto-generated method stub
		
	}
	
}
