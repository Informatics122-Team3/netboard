package com.netboard.game;

public class GameFactory {
	/**
	 * @param gameType a string representing a game type
	 * @return a Game object of the specified type
	 * @throws GameNotSupportedException 
	 * @throws Exception if the game is not supported
	 */
	public static Game createGame(String gameType) throws GameNotSupportedException {
		
		// TODO make the concrete gametypes
		switch (gameType) {
		case "connect4":
			return new Connect4Game();
		case "battleship":
			return new BattleshipGame(0, 0, 0); //TODO idk what these params are
		case "checkers":
			return new CheckersGame();
		default:
			throw new GameNotSupportedException(gameType);
		}
	}
}
