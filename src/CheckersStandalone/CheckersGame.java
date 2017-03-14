package CheckersStandalone;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckersGame {
	//Member Variables
	private com.netboard.game.board.CheckersBoard board;
	private int turn;
	private boolean gameOver;
	//Default Constructor
	public CheckersGame()
	{
		board = new com.netboard.game.board.CheckersBoard();
		
		//Player Turn (0: Player 1, 1: Player 2)
		gameOver = false;
		turn = 0;
	}
	//Prints the board
	public void printTable()
	{
		board.printBoard();
		System.out.println("Player 1 Pieces: " + board.getp1Pieces() + "\tPlayer 2 Pieces: " + board.getp2Pieces());
	}
	//returns what type of piece is at the x,y coordinates (o, x, "")
	public String pieceAt(int x, int y)	{ return board.findPiece(x, y).getIcon(); }
	//Accessor methods
	public int getTurn(){ return turn; }
	public boolean getGameOver(){ return gameOver; }
	//returns the player turn
	public String getPlayerTurn()
	{
		if(turn == 0)
			return "Player 1";
		return "Player 2";
	}
	public boolean isP1Turn()
	{
		if(turn == 0)
			return true;
		return false; 
	}	
	//switches player turn
	public void switchTurn(){ turn = (turn + 1) % 2; }
	
//LOGIC PORTION --------------------------------------------------------------------------------
	
	//Finds all moves, move numbers correspond to: 
	
	public boolean checkLeft1Up1(int x, int y, String type)
	{
		if(x > 0 && y > 0)
			if(pieceAt(x-1, y-1).equals(""))
				return true;
		return false;
	}
	public boolean checkLeft2Up2(int x, int y, String type)
	{
		if(x > 0 && y > 0)
		{
			if(type.equals("x") || type.equals("X"))
			{
				if(pieceAt(x-1, y-1).equals("o") || pieceAt(x-1,y-1).equals("O")) //left 1 and down 1
					if(x-1 > 0 && y-1 > 0  && pieceAt(x-2,y-2).equals("")) //left 2 and up 2 
						return true;
			}
			else if(type.equals("O"))
			{
				if(pieceAt(x-1, y-1).equals("x") || pieceAt(x-1,y-1).equals("X")) //left 1 and up 1
					if(x-1 > 0 && y-1 > 0  && pieceAt(x-2,y-2).equals("")) //left 2 and up 2 
						return true;
			}
		}
		return false;
	}
	public boolean checkLeft1Down1(int x, int y, String type)
	{
		if(x > 0 && y < board.getHeight())
			if(pieceAt(x-1, y+1).equals(""))
				return true;
			return false;	
	}
	public boolean checkLeft2Down2(int x, int y, String type)
	{
		if(x > 0 && y < board.getHeight())
		{
			if(type.equals("o") || type.equals("O"))
				if(pieceAt(x-1, y+1).equals("x") || pieceAt(x-1,y+1).equals("X")) //left 1 and down 1
					if(x-1 > 0 && y+1 < board.getHeight() && pieceAt(x-2,y+2).equals("")) //left 2 and down 2 
						return true;
			
			if(type.equals("X"))
				if(pieceAt(x-1, y+1).equals("o") || pieceAt(x-1, y+1).equals("O")) //left 1 and down 1
					if(x-1 > 0 && y+1 < board.getHeight() && pieceAt(x-2,y+2).equals("")) //left 2 and down 2 
						return true;
		}
		return false;
	}
	public boolean checkRight1Up1(int x, int y, String type)
	{
		if(x < board.getWidth() && y > 0)
			if(pieceAt(x+1, y-1).equals(""))
				return true;
			return false;	
	}
	public boolean checkRight2Up2(int x, int y, String type)
	{
		if(x < board.getWidth() && y > 0)
		{
			if(type.equals("x") || type.equals("X"))
			{
				if(pieceAt(x+1, y-1).equals("o") || pieceAt(x+1,y-1).equals("O")) //right 1 and up 1
					if(x+1 < board.getWidth() && y-1 > 0  && pieceAt(x+2,y-2).equals("")) //right 2 and up 2 
						return true;
			}
			else if(type.equals("O"))
			{
				if(pieceAt(x+1, y-1).equals("x") || pieceAt(x+1,y-1).equals("X")) //right 1 and up 1
					if(x+1 < board.getWidth()&& y-1 > 0  && pieceAt(x+2,y-2).equals("")) //right 2 and up 2 
						return true;
			}
		}
		return false;
	}
	public boolean checkRight1Down1(int x, int y, String type)
	{
		if(x < board.getWidth()&& y < board.getHeight())
			if(pieceAt(x+1, y+1).equals(""))
				return true;
		return false;	
	}
	public boolean checkRight2Down2(int x, int y, String type)
	{
		if(x < board.getWidth() && y < board.getHeight())
		{
			if(type.equals("o") || type.equals("O"))
				if(pieceAt(x+1, y+1).equals("x") || pieceAt(x+1,y+1).equals("X")) //left 1 and down 1
					if(x+1 < board.getWidth() && y+1 < board.getHeight() && pieceAt(x+2,y+2).equals("")) //left 2 and down 2 
						return true;
			
			if(type.equals("X"))
				if(pieceAt(x+1, y+1).equals("o") || pieceAt(x+1, y+1).equals("O")) //left 1 and down 1
					if(x+1 < board.getWidth() && y+1 < board.getHeight() && pieceAt(x+2,y+2).equals("")) //left 2 and down 2 
						return true;
		}
		return false;
	}	

	public ArrayList<ArrayList<Integer>> getPieceMoves(int x, int y, String type)
	{
		/*  
		 *  1       2
		 *    3   4
		 *      x  
		 *    5   6    
		 *  7       8
		 */
		ArrayList<ArrayList<Integer>> to_return = new ArrayList<ArrayList<Integer>>();
		
		if(type.equals("x"))
		{
			if(checkLeft1Up1(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-1,y-1)));
			if(checkLeft2Up2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-2,y-2)));
			if(checkRight1Up1(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+1,y-1)));
			if(checkRight2Up2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+2,y-2)));
		}

		if(type.equals("o"))
		{
			if(checkLeft1Down1(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-1,y+1)));
			if(checkLeft2Down2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-2,y+2)));
			if(checkRight1Down1(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+1,y+1)));
			if(checkRight2Down2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+2,y+2)));
		}
		if(type.equals("O") || type.equals("X"))
		{
			if(checkLeft1Up1(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-1,y-1)));
			if(checkLeft2Up2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-2,y-2)));
			if(checkRight1Up1(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+1,y-1)));
			if(checkRight2Up2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+2,y-2)));
			
			if(checkLeft1Down1(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-1,y+1)));
			if(checkLeft2Down2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-2,y+2)));
			if(checkRight1Down1(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+1,y+1)));
			if(checkRight2Down2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+2,y+2)));
		}
		
		return to_return;
	}
	public ArrayList<ArrayList<Integer>> getJumpMoves(int x, int y, String type)
	{
		ArrayList<ArrayList<Integer>> to_return = new ArrayList<ArrayList<Integer>>();
		if(type.equals("x"))
		{
			if(checkLeft2Up2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-2,y-2)));
			if(checkRight2Up2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+2,y-2)));
		}
		if(type.equals("o"))
		{
			if(checkLeft2Down2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-2,y+2)));
			if(checkRight2Down2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+2,y+2)));
		}
		if(type.equals("X") || type.equals("O"))
		{
			if(checkLeft2Up2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-2,y-2)));
			if(checkRight2Up2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+2,y-2)));
			
			if(checkLeft2Down2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x-2,y+2)));
			if(checkRight2Down2(x,y,type))
				to_return.add(new ArrayList<Integer>(Arrays.asList(x+2,y+2)));
			
		}
		
		return to_return;
	}
	
	//check for winner
	public int checkWinner()
	{
		if(board.getp1Pieces() == 0)
			return 1;
		else if(board.getp2Pieces() == 0)
			return 0;
		else
			return -1;
	}

	public boolean canJump(int x, int y, String type)
	{
		if(getJumpMoves(x,y,type).isEmpty())
			return true;
		return false;
	}
//END LOGIC ------------------------------------------------------------------------------------	
//ENGINE PORTION -------------------------------------------------------------------------------
	
	//method that processes the user's desired move
	public void makeMove(String type, ArrayList<Integer> old, ArrayList<Integer> updated, boolean Jumping){
			
		if(Jumping && getJumpMoves(old.get(0), old.get(1), type).contains(updated))
		{
			board.updateBoard(board.findPiece(old.get(0), old.get(1)), updated.get(0), updated.get(1));
			
			System.out.println("Jumped piece " + type + " from [" + old.get(0) + "," + old.get(1) + "] to [" + updated.get(0) + "," + updated.get(1) + "]");
		}
		
		else if(Jumping == false && getPieceMoves(old.get(0), old.get(1), type).contains(updated))
		{
			board.updateBoard(board.findPiece(old.get(0),old.get(1)), updated.get(0), updated.get(1));
			
			System.out.println("Moved piece " + type + " from [" + old.get(0) + "," + old.get(1) + "] to [" + updated.get(0) + "," + updated.get(1) + "]");
		}
		else
		{
			System.out.println("Invalid input, try again.");
		}
	}
}

