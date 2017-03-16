package com.netboard.message;

import java.util.List;

public class RefreshMessage extends Message {
	
	private List<String> playerLobby;
	private List<String> supportedGames;
	
	public RefreshMessage(List<String> pl, List<String> games) {
		super("refresh");
		this.playerLobby = pl;
		this.supportedGames = games;
	}
	
	public List<String> getPlayerLobby() {
		return playerLobby;
	}
	
//	public void setPlayerLobby(List<String> pl) {
//		this.playerLobby = pl;
//	}
	
	public List<String> getSupportedGames() {
		return this.supportedGames;
	}
	
//	public void setSupportedGames(List<String> games) {
//		this.supportedGames = games;
//	}
}
