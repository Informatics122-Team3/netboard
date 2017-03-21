package com.netboard.message;

import java.util.List;

import com.netboard.game.board.Board;
import com.netboard.game.piece.Piece;


public class BoardUpdateMessage extends Message {
	private List<Board> boardState;
	private boolean isValid;
	private boolean isConnected;
	private String turn;
	private String gameType;
	
	/**
	 * 
	 * @param bs the boardstate
	 * @param isValid was the previously made move a valid one
	 * @param isConnected does the sender still wish to stay connected
	 * @param turn who's turn is it
	 * @param gameType the gametype
	 */
	public BoardUpdateMessage(List<Board> bs, boolean isValid, boolean isConnected, String turn, String gameType) {
		super("boardupdate");

		this.gameType = gameType;
		this.boardState = bs;
		this.isValid = isValid;
		this.isConnected = isConnected;
		this.turn = turn;
	}
	
	public List<Board> getBoardState() {
		return boardState;
	}
	
	public boolean inValidState() {
		return isValid;
	}
	
	public boolean inConnectedState() {
		return isConnected;
	}
	
	public String getGameType() {
		return this.gameType;
	}
	
	public String getTurn() {
		return turn;
	}
}