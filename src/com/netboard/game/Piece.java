package com.netboard.game;

public class Piece {
	private char value;
	private String iconStr;
	
	public Piece(char value, String iconStr) {
		this.value = value;
		this.iconStr = iconStr;
	}
	
	public Piece() {
		this.value = '\0';
		this.iconStr = "";
	}
	
	public char getValue() {
		return this.value;
	}
	
	public String getIconStr() {
		return iconStr;
	}
}
