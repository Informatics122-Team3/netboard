package com.netboard.game.piece;

import java.io.Serializable;

public class Piece implements Serializable {
	private int x,y;
	private char value;
	private String icon;
	
	public Piece(int x, int y, char value, String icon) {
		setX(x);
		setY(y);
		setValue(value);
		setIcon(icon);
	}
	public Piece(int x, int y, String icon)
	{
		setX(x);
		setY(y);
		setValue('\0');
		setIcon(icon);
	}
	public Piece(int x, int y)
	{
		setX(x);
		setY(y);
		setValue('\0');
		setIcon("");
	}

	/**
	 * defaults to value='\0', iconStr=""
	 */
	public Piece() {
		setX(-1);
		setY(-1);
		this.value = '\0';
		this.icon = "";
	}

	public char getValue() { return this.value;	}
	public String getIconStr() { return icon; }
	public int getX() {	return x; }
	public int getY() {	return y; }
	public String getIcon() { return icon; }
	
	public void setIcon(String icon) { this.icon = icon; }
	public void setY(int y) { this.y = y; }	
	public void setX(int x) { this.x = x; }
	private void setValue(char value) {	this.value = value;	}
	
}
