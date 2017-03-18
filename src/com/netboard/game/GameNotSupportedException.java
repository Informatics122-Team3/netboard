package com.netboard.game;

public class GameNotSupportedException extends Exception {
	
	private String gameType;

	public GameNotSupportedException(String gameType) {
		this.gameType = gameType;
	}
	
	@Override
	public String getMessage() {
		return String.format("Game Type: %s not supported", gameType);
	}
}
