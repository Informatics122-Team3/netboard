package com.netboard.server;

public class ActiveGameThread implements Runnable {

	private Game gameInstance;
	private Player host;
	private Player guest;
	private boolean isFull;
	
	public ActiveGameThread(Game gameInstance, Player host, Player guest) {
		this.gameInstance = gameInstance;
		this.host = host;
		this.guest = guest;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
