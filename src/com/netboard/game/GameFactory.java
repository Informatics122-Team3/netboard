package com.netboard.game;

public class GameFactory {
	/**
	 * @param gameType a string representing a game type
	 * @return a Game object of the specified type
	 * @throws Exception if the game is not supported
	 */
	public static Game createGame(String gameType) {
		
		// TODO
		switch (gameType) {
		case "connect4":
			//return new Connect4Game();
		case "battleship":
			//return new BattleShipGame();
		case "checkers":
			//return new CheckersGame();
		default:
			System.out.println(String.format("GameFactory: created %s game", gameType));
		}
		
		return null;
	}
}
