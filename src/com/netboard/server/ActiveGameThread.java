package com.netboard.server;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;

import com.netboard.game.Game;
import com.netboard.game.GameFactory;
import com.netboard.game.GameNotSupportedException;
import com.netboard.game.Player;
import com.netboard.game.board.Board;
import com.netboard.message.ApplyMoveMessage;
import com.netboard.message.BoardUpdateMessage;
import com.netboard.message.CommsBridge;

public class ActiveGameThread implements Runnable {

	private Game gameInstance;
	private Player host;
	private Player guest;
	private NetBoardServer nbs;
	
	public ActiveGameThread(String gameType, Player host, Player guest, NetBoardServer nbs) {
		try {
			this.nbs = nbs;
			this.gameInstance = GameFactory.createGame(gameType);
			gameInstance.setPlayers(host.getUsername(), guest.getUsername());
		} catch (GameNotSupportedException e) {
			System.err.println(e.getMessage());
		}
		
		this.host = host;
		this.guest = guest;
	}
	
	public void run() {
		//initializePlayers();
		listenForApplyMoves();		
	}
	
	private void initializePlayers() {
		//host always goes first
		broadcastBoardUpdate(null, true, true, host.getUsername());
		
	}
	
	private void listenForApplyMoves() {
		
		while (true) {
			
			Player activePlayer = getActivePlayer();
			
			ApplyMoveMessage moveMsg = CommsBridge.readMessage(activePlayer.getSocket());
			
			if (!moveMsg.inConnectedState()) {
				broadcastBoardUpdate(
						null,
						true,
						false, // this indicates that someone wants to disconnect
						null
				);
				nbs.removeHostFromLobby(activePlayer.getUsername());
				break;
			}
			if (gameInstance.makeMove(moveMsg.getPiece(), moveMsg.getNewX(), moveMsg.getNewY())) {
				
				if (gameInstance.isGameOver()) {
					broadcastBoardUpdate(
							gameInstance.getBoardState(),
							true,
							false, // this indicates that someone wants to disconnect
							gameInstance.getTurn()
					);
				}
				
				else {
					broadcastBoardUpdate(
							gameInstance.getBoardState(),
							true,
							true,
							gameInstance.getTurn()
					);
				}
			}
			else {
				BoardUpdateMessage invalidMoveMsg = new BoardUpdateMessage(
						null,
						false, // this indicates that the previous move was invalid
						true,
						null,
						gameInstance.getGameName()
				);
				
				CommsBridge.writeMessage(
						activePlayer.getSocket(), invalidMoveMsg);
			}
			
			gameInstance.toggleTurn();
		}
		
	}
	
	private Player getActivePlayer() {
		return gameInstance.getTurn().equals(host.getUsername()) ? host : guest;
	}
	
	private void broadcastBoardUpdate(List<Board> boardState, boolean isValid, boolean isConnected, String turn) {
		
		BoardUpdateMessage boardMsg = 
				new BoardUpdateMessage(boardState, isValid, isConnected, turn, gameInstance.getGameName());
		
		CommsBridge.writeMessage(host.getSocket(), boardMsg);
		CommsBridge.writeMessage(guest.getSocket(), boardMsg);
	}

}
