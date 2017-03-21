package CheckersStandalone;

public class CheckersMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		com.netboard.game.CheckersGame c = new com.netboard.game.CheckersGame("peater", "vstolle");
		c.printTable();
		c.makeMove(c.getBoardState().get(0).at(1, 2), 0, 3);
		c.makeMove(c.getBoardState().get(0).at(0, 3), 1, 4);
		c.makeMove(c.getBoardState().get(0).at(2, 1), 1, 2);
		c.makeMove(c.getBoardState().get(0).at(2, 5), 0, 3);
		c.makeMove(c.getBoardState().get(0).at(0, 3), 2, 1);
		c.makeMove(c.getBoardState().get(0).at(3, 2), 2, 3);
		c.makeMove(c.getBoardState().get(0).at(4, 1), 3, 2);
		c.makeMove(c.getBoardState().get(0).at(3, 0), 4, 1);
		c.makeMove(c.getBoardState().get(0).at(2, 1), 3, 0);
		c.makeMove(c.getBoardState().get(0).at(1, 0), 2, 1);
		c.makeMove(c.getBoardState().get(0).at(3, 2), 4, 3);
		c.makeMove(c.getBoardState().get(0).at(5, 2), 6, 3);
		c.makeMove(c.getBoardState().get(0).at(3, 0), 1, 2);
		c.makeMove(c.getBoardState().get(0).at(1, 2), 3, 4);
		c.makeMove(c.getBoardState().get(0).at(3, 4), 5, 2);
		c.makeMove(c.getBoardState().get(0).at(5, 2), 3, 0);
		c.makeMove(c.getBoardState().get(0).at(5, 0), 4, 1);
		c.makeMove(c.getBoardState().get(0).at(3, 0), 5, 2);
		c.makeMove(c.getBoardState().get(0).at(5, 2), 7, 4);
		c.makeMove(c.getBoardState().get(0).at(7, 2), 6, 3);
		c.makeMove(c.getBoardState().get(0).at(0, 1), 1, 2);
		c.makeMove(c.getBoardState().get(0).at(1, 2), 2, 3);
		c.makeMove(c.getBoardState().get(0).at(6, 1), 5, 2);
		c.makeMove(c.getBoardState().get(0).at(5, 2), 4, 3);
		c.makeMove(c.getBoardState().get(0).at(7, 0), 6, 1);
		c.makeMove(c.getBoardState().get(0).at(7, 4), 5, 2);
		c.makeMove(c.getBoardState().get(0).at(5, 2), 3, 4);
		c.makeMove(c.getBoardState().get(0).at(3, 4), 1, 2);
		c.makeMove(c.getBoardState().get(0).at(6, 1), 5, 2);
		c.makeMove(c.getBoardState().get(0).at(5, 2), 4, 3);
		c.makeMove(c.getBoardState().get(0).at(4, 3), 5, 4);
		c.makeMove(c.getBoardState().get(0).at(1, 2), 2, 3);
		c.makeMove(c.getBoardState().get(0).at(2, 3), 3, 4);
		c.makeMove(c.getBoardState().get(0).at(6, 5), 4, 3);
		

//		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(6,1)),new ArrayList<Integer>(Arrays.asList(5,2)) , false);		
//		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(5,2)),new ArrayList<Integer>(Arrays.asList(4,3)) , false);
//		c.makeMove("o", new ArrayList<Integer>(Arrays.asList(4,3)),new ArrayList<Integer>(Arrays.asList(5,4)) , false);	
//		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(1,2)),new ArrayList<Integer>(Arrays.asList(2,3)) , false);			
//		c.makeMove("X", new ArrayList<Integer>(Arrays.asList(2,3)),new ArrayList<Integer>(Arrays.asList(3,4)) , false);	
//		c.makeMove("x", new ArrayList<Integer>(Arrays.asList(6,5)),new ArrayList<Integer>(Arrays.asList(4,3)) , false);	
		c.printTable();
		c.getGameOver();

	}

}



