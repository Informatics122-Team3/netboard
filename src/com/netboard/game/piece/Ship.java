package com.netboard.game.piece;

import java.util.ArrayList;
import javafx.util.Pair;

public class Ship extends Piece{

	public int length;
	private ArrayList<Pair<Integer,Integer>> coordinates;
	public int hit;
	
	public Ship(int x1, int y1, int vert_or_hor){
		super(x1,y1);
	}
	
	public ArrayList<Pair<Integer,Integer>> getCoords(){
		return coordinates;
	}
	
	public void hit(int x, int y){
		for (int i = 0; i < coordinates.size(); i++){
			if (coordinates.get(i).getKey() == x && coordinates.get(i).getValue() == y){
				hit += 1;
			}
		}
	}
	
}
