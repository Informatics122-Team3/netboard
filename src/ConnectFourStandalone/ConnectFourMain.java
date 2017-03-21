package ConnectFourStandalone;
import com.netboard.game.piece.Piece;


public class ConnectFourMain {
	public static void main(String[] args) {
		//this.gameInstance = GameFactory.createGame(gameType);
		com.netboard.game.ConnectFourGame c = new com.netboard.game.ConnectFourGame(7, 15);
		//gameInstance.setPlayers(host.getUsername(), guest.getUsername());
		c.setPlayers("Jot", "Dan");
		
		int count = 0;
		while(true){
			if(c.makeMove(new Piece(), count, 0)){
				if(c.isGameOver()){
					c.printBoard();
					System.out.println(c.getTurn() + " wins!");
					break;
				}
			}
			else
			{
				//workaround for toggling the turn after every move even when invalid
				c.toggleTurn();
				//gameInstance.getGameName()
				//GameName never gets set not sure why this is used in ActiveGameThread
			}
			
			//turn should not be toggled here if its an invalid move @MaxP
			c.toggleTurn();
			
			count++;
			if (count == 7) {count = 0;}
		}
		
		/*
		 * ******IGNORE********** (more test cases)
		System.out.println("BOARD 1");
		c.printBoard();
		c.makeMove(new Piece(), 0, 0);
		System.out.println("BOARD 2");
		c.printBoard();
		c.makeMove(new Piece(), 1, 0);
		System.out.println("BOARD 3");
		c.printBoard();
		c.makeMove(new Piece(), 0, 0);
		System.out.println("BOARD 4");
		c.printBoard();
		c.makeMove(new Piece(), 1, 0);
		System.out.println("BOARD 5");
		c.printBoard();
		c.makeMove(new Piece(), 0, 0);
		System.out.println("BOARD 6");
		c.printBoard();
		c.makeMove(new Piece(), 0, 0);
		System.out.println("BOARD 7");
		c.printBoard();
		c.makeMove(new Piece(), 0, 0);
		System.out.println("BOARD 8");
		c.printBoard();
		c.makeMove(new Piece(), 1, 0);
		System.out.println("BOARD 9");
		c.printBoard();
		c.makeMove(new Piece(), 0, 0);
		System.out.println("BOARD 10");
		c.printBoard();
		c.makeMove(new Piece(), 1, 0);
		System.out.println("BOARD 11");
		c.printBoard();
		c.makeMove(new Piece(), 1, 0);
		System.out.println("BOARD 12");
		c.printBoard();*/
		
		
		
		/*System.out.println("BOARD 1");
		c.printBoard();
		c.makeMove(new Piece(), 0, 0);
		System.out.println("BOARD 2");
		c.printBoard();
		c.makeMove(new Piece(), 6, 0);
		System.out.println("BOARD 3");
		c.printBoard();
		c.makeMove(new Piece(), 1, 0);
		System.out.println("BOARD 4");
		c.printBoard();
		c.makeMove(new Piece(), 5, 0);
		System.out.println("BOARD 5");
		c.printBoard();
		c.makeMove(new Piece(), 2, 0);
		System.out.println("BOARD 6");
		c.printBoard();
		c.makeMove(new Piece(), 4, 0);
		System.out.println("BOARD 7");
		c.printBoard();
		c.makeMove(new Piece(), 3, 0);
		System.out.println("BOARD 8");
		c.printBoard();
		c.makeMove(new Piece(), 1, 0);
		System.out.println("BOARD 9");
		c.printBoard();*/
		
		/*
		System.out.println("BOARD 1");
		c.printBoard();
		c.makeMove(new Piece(), 6, 0);
		System.out.println("BOARD 2");
		c.printBoard();
		c.makeMove(new Piece(), 6, 0);
		System.out.println("BOARD 3");
		c.printBoard();
		c.makeMove(new Piece(), 6, 0);
		System.out.println("BOARD 4");
		c.printBoard();
		c.makeMove(new Piece(), 5, 0);
		System.out.println("BOARD 5");
		c.printBoard();
		c.makeMove(new Piece(), 6, 0);
		System.out.println("BOARD 6");
		c.printBoard();
		c.makeMove(new Piece(), 5, 0);
		System.out.println("BOARD 7");
		c.printBoard();
		c.makeMove(new Piece(), 4, 0);
		System.out.println("BOARD 8");
		c.printBoard();
		c.makeMove(new Piece(), 0, 0);
		System.out.println("BOARD 9");
		c.printBoard();
		c.makeMove(new Piece(), 4, 0);
		System.out.println("BOARD 10");
		c.printBoard();
		c.makeMove(new Piece(), 1, 0);
		System.out.println("BOARD 11");
		c.printBoard();
		c.makeMove(new Piece(), 5, 0);
		System.out.println("BOARD 12");
		c.printBoard();
		c.makeMove(new Piece(), 2, 0);
		System.out.println("BOARD 13");
		c.printBoard();
		c.makeMove(new Piece(), 3, 0);
		System.out.println("BOARD 14");
		c.printBoard();
		c.makeMove(new Piece(), 3, 0);
		System.out.println("BOARD 15");
		c.printBoard();
		*/

//		c.printBoard();
//		c.makeMove(new Piece(), 3, 0);
//		c.makeMove(new Piece(), 3, 0);
//		c.makeMove(new Piece(), 5, 0);
//		c.makeMove(new Piece(), 4, 0);
//		c.makeMove(new Piece(), 3, 0);
//		c.makeMove(new Piece(), 3, 0);
//		c.makeMove(new Piece(), 3, 0);
//		c.makeMove(new Piece(), 4, 0);
//		c.makeMove(new Piece(), 5, 0);
//		c.makeMove(new Piece(), 4, 0);
//		c.makeMove(new Piece(), 4, 0);
//		c.makeMove(new Piece(), 3, 0);
//		c.makeMove(new Piece(), 1, 0);
//		c.makeMove(new Piece(), 0, 0);
//		c.makeMove(new Piece(), 1, 0);
//		c.makeMove(new Piece(), 1, 0);
//		c.makeMove(new Piece(), 4, 0);
//		c.makeMove(new Piece(), 4, 0);
//		c.makeMove(new Piece(), 1, 0);
//		c.makeMove(new Piece(), 1, 0);
//		c.makeMove(new Piece(), 5, 0);
//		c.makeMove(new Piece(), 5, 0);
//		c.makeMove(new Piece(), 2, 0);
//		c.makeMove(new Piece(), 2, 0);
//		c.makeMove(new Piece(), 1, 0);
//		c.makeMove(new Piece(), 5, 0);
//		c.makeMove(new Piece(), 5, 0);
//		c.makeMove(new Piece(), 2, 0);
//		c.makeMove(new Piece(), 2, 0);
//		c.makeMove(new Piece(), 0, 0);
//		c.makeMove(new Piece(), 6, 0);
//		c.makeMove(new Piece(), 6, 0);
//		c.makeMove(new Piece(), 6, 0);
//		c.makeMove(new Piece(), 0, 0);
//		c.makeMove(new Piece(), 0, 0);
//		c.makeMove(new Piece(), 0, 0);
//		c.makeMove(new Piece(), 2, 0);
//		c.makeMove(new Piece(), 2, 0);
//		c.makeMove(new Piece(), 0, 0);
//		c.makeMove(new Piece(), 6, 0);
//		c.makeMove(new Piece(), 6, 0);
//		c.makeMove(new Piece(), 6, 0);
		

		
		//c.printBoard();
		
//		System.out.println(c.getBoardState().get(0).at(0,6));
//		System.out.println(c.getBoardState().get(0).at(0,0));
//		System.out.println(c.getBoardState().get(0).at(1,0));
//		System.out.println(c.getBoardState().get(0).at(2,0));
//		System.out.println(c.getBoardState().get(0).at(3,0));
//		System.out.println(c.getBoardState().get(0).at(4,0));
//		System.out.println(c.getBoardState().get(0).at(5,0));
		
		
		
		
		
	}
	
}
