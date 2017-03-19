package Battleship;

import com.netboard.game.piece.Piece;

public class Ship extends Piece {

	int ship[];
	int length;
	int x1, x2, y1, y2;
	private String name;
	
	public Ship()
	{
		super();
		this.name = "";
	}
	
	// starting x-coordinate
	public int get_x1(){
		return x1;
	}
	
	// ending x-coordinate
	public int get_x2(){
		return x2;
	}
	
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}
	
	public int getLength()
	{
		return this.length;
	}
	
	public void setLength(int length)
	{
		this.length = length;
	}
	
	// starting y-coordinate
	public int get_y1(){
		return y1;
	}
	
	// ending y-coordinate
	public int get_y2(){
		return y2;
	}
	
}
