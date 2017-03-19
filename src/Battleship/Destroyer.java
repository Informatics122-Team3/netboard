package Battleship;

public class Destroyer extends Ship{

	// destroyer is length 2
	int hit_record[] = new int[length];
	int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	
	// initializing ships start and end points
	public Destroyer(){
		for (int i = 0; i < length; i++){
			hit_record[i] = 0;
		}
		this.setName("Destroyer");
		this.setLength(2);
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
