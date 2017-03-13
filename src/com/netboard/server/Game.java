package com.netboard.server;

import java.util.ArrayList;
import com.netboard.server.Board;

public abstract class Game {
	private ArrayList<Board> boardState;
	private Logic logic;
	private String name;
	private String whosTurn;
	
	public String getPlayerTurn(){
		return whosTurn;
	}
}
