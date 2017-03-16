package com.netboard.game.piece;

import java.util.ArrayList;

import javafx.util.Pair;

public class AircraftCarrier extends Ship{

	// aircraft carrier is length 5
	public int length = 5;
	public int hit = 0;
	public int [] ship = new int[length];
	public Pair<Integer,Integer> c;
	public ArrayList<Pair<Integer,Integer>> coordinates;
	
	// initializing ships start and end points
	public AircraftCarrier(int x1, int y1, int vert_or_hor){
		super(x1,y1,vert_or_hor);
		for (int i = 0; i < length; i++){
			ship[i] = 0;
		}
		
		coordinates = new ArrayList<Pair<Integer,Integer>>();
		
		// 0 means vertical
		if (vert_or_hor == 0){
			int temp = x1;
			ArrayList<Pair<Integer,Integer>> temp_array = new ArrayList<Pair<Integer,Integer>>();
			for (int i = 0; i < 5; i++){
				Pair<Integer,Integer> p = new Pair<Integer,Integer>(temp++,y1);
				temp_array.add(p);
			}
			for (int i = 0; i < temp_array.size(); i++){
				if (temp_array.get(i).getKey() > 10 || temp_array.get(i).getValue() > 10){
					System.out.println("That is not a valid spot");
					break;
				}
			}
			coordinates = temp_array;
		}
		
		// 1 means horizontal
		if (vert_or_hor == 1){
			int temp = y1;
			ArrayList<Pair<Integer,Integer>> temp_array = new ArrayList<Pair<Integer,Integer>>();
			for (int i = 0; i < 5; i++){
				Pair<Integer,Integer> p = new Pair<Integer,Integer>(x1,temp++);
				temp_array.add(p);
			}
			for (int i = 0; i < temp_array.size(); i++){
				if (temp_array.get(i).getKey() > 10 || temp_array.get(i).getValue() > 10){
					System.out.println("That is not a valid spot");
					break;
				}
			}
			coordinates = temp_array;
		}
		
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
