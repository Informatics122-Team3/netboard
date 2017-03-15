package Battleship;

public class Submarine extends Ship{

	// submarine is length 3
	int length = 3;
	int ship[] = new int[length];
	int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	
	// initializing ships start and end points
	public Submarine(int xstart, int xend, int ystart, int yend){
		for (int i = 0; i < length; i++){
			ship[i] = 0;
		}
		x1 = xstart;
		x2 = xend;
		y1 = ystart;
		y2 = yend;
	}
	
	// starting x-coordinate
	public int get_x1(){
		return x1;
	}
	
	// ending x-coordinate
	public int get_x2(){
		return x2;
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
