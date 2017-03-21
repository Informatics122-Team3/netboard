package com.netboard.game.logic;

import java.util.ArrayList;

import com.netboard.game.BattleshipGame;
import com.netboard.game.board.BattleshipDefenseBoard;
import com.netboard.game.board.Board;
import com.netboard.game.piece.Piece;

public class BattleshipLogic implements Logic{
	
	private ArrayList<BattleshipDefenseBoard> PlayerDenfenseBoards;
	
	public BattleshipLogic(ArrayList<BattleshipDefenseBoard> board) {
		this.PlayerDenfenseBoards = board;
	}

	public boolean isGameOver(){
		if (this.PlayerDenfenseBoards.get(1).allSunk()) return true;
		if (this.PlayerDenfenseBoards.get(0).allSunk()) return true;
		return false;
	}
	
	
	public String getWinner(){
//		if (game.isWinning() == 1){
//			return game.getPlayer1Board().getPlayer1();
//		} else {
//			return game.getPlayer2Board().getPlayer2();
//		}
		if (this.PlayerDenfenseBoards.get(1).allSunk()) return PlayerDenfenseBoards.get(0).getPlayer1();
		if (this.PlayerDenfenseBoards.get(0).allSunk()) return PlayerDenfenseBoards.get(1).getPlayer2();
		return "";
	}
	
	public boolean isValidMove(Piece p, int newX, int newY){
		if (newX < 10 && newY < 10){
			return true;
		}
		return false;
	}
	
}
