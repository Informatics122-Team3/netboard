package Battleship;

public class BattleshipGame {
	
	// Initialize game ships
	Ship ships[] = new Ship[5];
	
	// Initializes the ships and its coordinates
	// x1 and y1 are its starting coordinates and x2 and y2 are its ending coordinates
	public BattleshipGame(int x1, int x2, int y1, int y2){
		AircraftCarrier a = new AircraftCarrier(x1, x2, y1, y2);
		Battleship b = new Battleship(x1, x2, y1, y2);
		Cruiser c = new Cruiser(x1, x2, y1, y2);
		Destroyer d = new Destroyer(x1, x2, y1, y2);
		Submarine s = new Submarine(x1, x2, y1, y2);
		
		ships[0] = a;
		ships[1] = b;
		ships[2] = c;
		ships[3] = d;
		ships[4] = s;
	}
	
	// A Battleship board is 10 x 10
	
	
	
}
