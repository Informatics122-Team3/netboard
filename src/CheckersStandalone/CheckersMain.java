package CheckersStandalone;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckersMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckersGame c = new CheckersGame();
		c.printTable();
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(1,2)),new ArrayList<Integer>(Arrays.asList(0,3)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(0,3)),new ArrayList<Integer>(Arrays.asList(1,4)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(2,1)),new ArrayList<Integer>(Arrays.asList(1,2)) , false);
		c.makeMove("x", new ArrayList<Integer>(Arrays.asList(2,5)),new ArrayList<Integer>(Arrays.asList(0,3)) , false);
		c.makeMove("x", new ArrayList<Integer>(Arrays.asList(0,3)),new ArrayList<Integer>(Arrays.asList(2,1)) , true);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(3,2)),new ArrayList<Integer>(Arrays.asList(2,3)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(4,1)),new ArrayList<Integer>(Arrays.asList(3,2)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(3,0)),new ArrayList<Integer>(Arrays.asList(4,1)) , false);
		c.makeMove("x", new ArrayList<Integer>(Arrays.asList(2,1)),new ArrayList<Integer>(Arrays.asList(3,0)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(1,0)),new ArrayList<Integer>(Arrays.asList(2,1)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(3,2)),new ArrayList<Integer>(Arrays.asList(4,3)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(5,2)),new ArrayList<Integer>(Arrays.asList(6,3)) , false);
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(3,0)),new ArrayList<Integer>(Arrays.asList(1,2)) , false);
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(1,2)),new ArrayList<Integer>(Arrays.asList(3,4)) , true);
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(3,4)),new ArrayList<Integer>(Arrays.asList(5,2)) , true);
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(5,2)),new ArrayList<Integer>(Arrays.asList(3,0)) , true);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(5,0)),new ArrayList<Integer>(Arrays.asList(4,1)) , false);
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(3,0)),new ArrayList<Integer>(Arrays.asList(5,2)) , false);
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(5,2)),new ArrayList<Integer>(Arrays.asList(7,4)) , true);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(7,2)),new ArrayList<Integer>(Arrays.asList(6,3)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(0,1)),new ArrayList<Integer>(Arrays.asList(1,2)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(1,2)),new ArrayList<Integer>(Arrays.asList(2,3)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(6,1)),new ArrayList<Integer>(Arrays.asList(5,2)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(5,2)),new ArrayList<Integer>(Arrays.asList(4,3)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(7,0)),new ArrayList<Integer>(Arrays.asList(6,1)) , false);
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(7,4)),new ArrayList<Integer>(Arrays.asList(5,2)) , false);
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(5,2)),new ArrayList<Integer>(Arrays.asList(3,4)) , true);
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(3,4)),new ArrayList<Integer>(Arrays.asList(1,2)) , true);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(6,1)),new ArrayList<Integer>(Arrays.asList(5,2)) , false);		
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(5,2)),new ArrayList<Integer>(Arrays.asList(4,3)) , false);
		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(4,3)),new ArrayList<Integer>(Arrays.asList(5,4)) , false);	
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(1,2)),new ArrayList<Integer>(Arrays.asList(2,3)) , false);			
		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(2,3)),new ArrayList<Integer>(Arrays.asList(3,4)) , false);	
		c.makeMove("x", new ArrayList<Integer>(Arrays.asList(6,5)),new ArrayList<Integer>(Arrays.asList(4,3)) , false);	
		c.printTable();
		if(c.checkWinner() == 1)
			System.out.println("Player 2 Wins!");

	}

}
