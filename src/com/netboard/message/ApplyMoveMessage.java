package com.netboard.message;

import com.netboard.game.piece.Piece;

public class ApplyMoveMessage extends Message {

	private Piece piece;
	private int newX;
	private int newY;
	private boolean isConnected;
	
	public ApplyMoveMessage(Piece p, int newX, int newY, boolean isConnected) {
		super("applymove");
		
		this.piece = p;
		this.newX = newX;
		this.newY = newY;
		this.isConnected = isConnected;
		
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public int getNewX() {
		return this.newX;
	}
	
	public int getNewY() {
		return this.newY;
	}
	
	public boolean inConnectedState() {
		return this.isConnected;
	}
	
}
