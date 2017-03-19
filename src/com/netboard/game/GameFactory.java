package com.netboard.game;

public class GameFactory {
	/**
	 * @param gameType a string representing a game type
	 * @return a Game object of the specified type
	 * @throws GameNotSupportedException 
	 * @throws Exception if the game is not supported
	 */
	public static Game createGame(String gameType) throws GameNotSupportedException {
		
		switch (gameType) {
		case "connect4":
			return new ConnectFourGame(7, 15);
		case "battleship":
			//return new BattleshipGame(0, 0, 0); // TODO make the concrete BattleShip game type
		case "checkers":
			return new CheckersGame("player1", "player2");
		default:
			throw new GameNotSupportedException(gameType);
		}
	}
}
