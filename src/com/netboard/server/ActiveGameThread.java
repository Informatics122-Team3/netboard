package com.netboard.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.netboard.game.Game;
import com.netboard.game.GameFactory;
import com.netboard.game.Player;
import com.netboard.game.board.Board;
import com.netboard.message.BoardUpdateMessage;

public class ActiveGameThread implements Runnable {

	private Game gameInstance;
	private Player host;
	private Player guest;
	
	
	public ActiveGameThread(String gameType, Player host, Player guest) {
		try {
			this.gameInstance = GameFactory.createGame(gameType);
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
			
			try {
				Player activePlayer = getActivePlayer();
				ObjectInputStream playerIn = activePlayer.getObjectInputStream();
				BoardUpdateMessage boardMsg = (BoardUpdateMessage) playerIn.readObject();
				
				if (!boardMsg.inConnectedState()) {
					broadcastBoardUpdate(
							boardMsg.getBoardState(),
							true,
							false, // this indicates that someone wants to disconnect
							boardMsg.getTurn()
					);
					break;
				}
				if (gameInstance.applyBoardUpdate(boardMsg.getBoardState())) {
					broadcastBoardUpdate(
							gameInstance.getBoardState(),
							true,
							true,
							gameInstance.getTurn()
					);
				}
				else {
					BoardUpdateMessage invalidMoveMsg = new BoardUpdateMessage(
							boardMsg.getBoardState(),
							false, // this indicates that the previous move was invalid
							true,
							boardMsg.getTurn()
					);
					
					ObjectOutputStream playerOut = activePlayer.getObjectOutputStream();
					playerOut.writeObject(invalidMoveMsg);
					playerOut.close();
					
				}
				
				playerIn.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			gameInstance.toggleTurn();
		}
		
	}
	
	private Player getActivePlayer() {
		return (Math.random() < 0.5 ? host : guest) ;
		//return gameInstance.getTurn().equals(host.getUsername()) ? host : guest;
	}
	
	private void broadcastBoardUpdate(List<Board> boardState, boolean isValid, boolean isConnected, String turn) {
		
		BoardUpdateMessage boardMsg = 
				new BoardUpdateMessage(boardState, isValid, isConnected, turn);
		
		try {
			ObjectOutputStream hostOut = host.getObjectOutputStream();
			hostOut.writeObject(boardMsg);
			hostOut.close();
			
			ObjectOutputStream guestOut = host.getObjectOutputStream();
			guestOut.writeObject(boardMsg);
			guestOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
