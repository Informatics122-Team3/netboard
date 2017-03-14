package com.netboard.game.piece;

public class CheckersPiece extends Piece {
	private boolean isKing;
	
	public CheckersPiece()
	{
		super();
		setKing(false);
	}
	
	public CheckersPiece(int x, int y)
	{
		super(x,y);
	}
	
	public CheckersPiece(int x, int y, String icon)
	{
		super(x,y,icon);
	}
	
	public CheckersPiece(int x, int y, String icon, boolean isKing)
	{
		super(x,y,icon);
		setKing(isKing);
	}

	public boolean isKing() {
		return isKing;
	}

	public void setKing(boolean isKing) {
		this.isKing = isKing;
	}
	
}
