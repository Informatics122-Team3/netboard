package com.netboard.game;

public class Piece {
	private char value;
	private String iconStr;
	
	public Piece(char value, String iconStr) {
		this.value = value;
		this.iconStr = iconStr;
	}
	
	/**
	 * defaults to value='\0', iconStr=""
	 */
	public Piece() {
		this.value = '\0';
		this.iconStr = "";
	}
	
	/**
	 * 
	 * @return the value of the piece
	 */
	public char getValue() {
		return this.value;
	}
	
	/**
	 * 
	 * @return the icon string of the piece
	 */
	public String getIconStr() {
		return iconStr;
	}
}
