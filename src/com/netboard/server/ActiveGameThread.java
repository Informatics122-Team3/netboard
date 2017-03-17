package com.netboard.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.netboard.game.Game;
import com.netboard.game.GameFactory;
import com.netboard.game.Player;
import com.netboard.game.board.Board;
import com.netboard.message.ApplyMoveMessage;
import com.netboard.message.BoardUpdateMessage;
import com.netboard.message.CommsBridge;

public class ActiveGameThread implements Runnable {

	private Game gameInstance;
	private Player host;
	private Player guest;
	
	public ActiveGameThread(String gameType, Player host, Player guest) {
		try {
			this.gameInstance = GameFactory.createGame(gameType);
			gameInstance.setPlayers(host.getUsername(), guest.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.host = host;
		this.guest = guest;
	}
	
	public void run() {
		listenForBoardUpdates();		
	}
	
	private void listenForBoardUpdates() {
		
		while (true) {
			
			Player activePlayer = getActivePlayer();
			
			ApplyMoveMessage moveMsg = 
					(ApplyMoveMessage) CommsBridge.readMessage(activePlayer.getSocket());
			
			if (!moveMsg.inConnectedState()) {
				broadcastBoardUpdate(
						null,
						true,
						false, // this indicates that someone wants to disconnect
						null
				);
				break;
			}
			if (gameInstance.makeMove(moveMsg.getPiece(), moveMsg.getNewX(), moveMsg.getNewY())) {
				broadcastBoardUpdate(
						gameInstance.getBoardState(),
						true,
						true,
						gameInstance.getTurn()
				);
			}
			else {
				BoardUpdateMessage invalidMoveMsg = new BoardUpdateMessage(
						null,
						false, // this indicates that the previous move was invalid
						true,
						null
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
				new BoardUpdateMessage(boardState, isValid, isConnected, turn);
		
		CommsBridge.writeMessage(host.getSocket(), boardMsg);
		CommsBridge.writeMessage(guest.getSocket(), boardMsg);
	}

}
