package com.netboard.game;

public class GameFactory {
	/**
	 * @param gameType a string representing a game type
	 * @return a Game object of the specified type
	 */
	public static Game createGame(String gameType) {
		// TODO
		switch (gameType) {
		case "connect4":
			break;
		case "battleship":
			break;
		case "checkers":
			break;
		default:
		}
		
		return null;
	}
}
