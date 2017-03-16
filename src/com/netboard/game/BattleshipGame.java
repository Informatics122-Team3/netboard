package com.netboard.game;

import java.util.ArrayList;

import com.netboard.game.board.BattleshipBoard;
import com.netboard.GameLogic.BattleshipLogic;
import com.netboard.game.board.Board;

public class BattleshipGame {
	
	// Initialize game ships
	private BattleshipBoard bb;
	
	// Initializes the ships and its coordinates
	// x1 and y1 are its starting coordinates and x2 and y2 are its ending coordinates
	public BattleshipGame(int x1, int y1, int vert_or_hor){
		bb = new BattleshipBoard(x1,y1,vert_or_hor);
	}
	
	public void updateBoard(ArrayList<Board> a) {
		//
	}
	
}
