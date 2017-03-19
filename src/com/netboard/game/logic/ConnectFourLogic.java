package com.netboard.game.logic;
//Adapted from: http://www.javaproblems.com/2013/01/creating-connect-four-game-in-java.html 
//For inf 122 final project
import java.util.ArrayList;
import java.util.Scanner;

import com.netboard.game.board.ConnectFourBoard;
import com.netboard.game.piece.Piece;

//This will be.. a huge code so we will have to make
//lots of methods for each aspect of the game
public class ConnectFourLogic implements com.netboard.game.Logic
{
	private static int count = 0;
	private static ConnectFourBoard board;
	//We need to first create the basic visual pattern 
	public static ConnectFourBoard createPattern()
	{
	  //Although the game is more like a table of 6
	  //columns and 6 rows, we're going to have to make
	  //a 2D array of 7 rows and 15 columns because graphically
	  //there's an extra row to show the ___ at the bottom
	  //and you have double the columns that show | | | 
	  //between each number 
	   ConnectFourBoard CLIBoard = new ConnectFourBoard(7, 15);
	  
	  //Time to loop over each row from up to down
	  for (int i =0;i<CLIBoard.getHeight();i++)
	  {
	    
	     //Time to loop over each column from left to right
	     for (int j =0;j<CLIBoard.getWidth();j++)
	    {
	      //Note how it is always the even column
	      //that has the border and the odd column
	      //between them that will be either empty or
	      //have a number 
	      if (j% 2 == 0) CLIBoard.getBoard().get(i).get(j).setIcon("|");
	      else CLIBoard.getBoard().get(i).get(j).setIcon(" ");
	      
	      //Time to make our lowest row
	      if (i==6) CLIBoard.getBoard().get(i).get(j).setIcon("-");
	    }
	    
	  }
	  return CLIBoard;
	}
	
	//Yes, we even need to make a new method for visually
	//printing our game, but at least it's not hard to do
	public static void printPattern(ConnectFourBoard board)
	{
	  for (int i =0;i<board.getHeight();i++)
	  {
	    for (int j=0;j<board.getWidth();j++)
	    {
	      System.out.print(board.getBoard().get(i).get(j).getIcon());
	    }
	    System.out.println();
	  }
	}
	
	//Here's are basic move, making the lowest empty row
	//of a specific column have a Red
	public static void dropRedPattern(ConnectFourBoard board)
	{
	  //We need to have the user tell us what column he wants
	  //to drop a red into
	  //Note: the user isn't supposed to know that we have 15 columns
	  //starting at index 0 till 14 but just 6 nice ones
	  System.out.println("Drop a red disk at column (0-6): ");
	  Scanner scan = new Scanner (System.in);
	  
	  //Thankfully, there's a simple formula for converting a 1-2-3-4-5-6 
	  //user column number into a 1-3-5-7-9-11-13
	  int c = 2*scan.nextInt()+1;
	 
	  //Now that we know our column, we have to loop
	  //over each row from the bottom to the top
	  //till we find the first  empty space, drop, and
	  //then finish (i.e., break) the move
	  //Note: although as coders we're used to starting from 
	  //0 to the end, here that wouldn't work so well because
	  //it would involve multiple if statements, but try it out
	  //on your own if you want to
	  for (int i =5;i>=0;i--)
	  {
	    if (board.getBoard().get(i).get(c).getIcon() == " ")
	    {
	      board.getBoard().get(i).get(c).setIcon("R");
	      break;
	    }
	    if(i == 0){
	  	  System.out.println("Column is full invalid move.");
	  	  count--;
	    }
	  }
	}
	
	//Same as the above step, just yellow
	public static void dropYellowPattern(ConnectFourBoard board)
	{
	  System.out.println("Drop a yellow disk at column (0-6): ");
	  Scanner scan = new Scanner (System.in);
	  int c = 2*scan.nextInt()+1;
	  for (int i =5;i>=0;i--)
	  {
	    if (board.getBoard().get(i).get(c).getIcon() == " ")
	    {
	      board.getBoard().get(i).get(c).setIcon("Y");
	      break;
	    }
	    if(i == 0){
	  	  System.out.println("Column is full invalid move.");
	  	  count--;
	    }
	  }
	}
	
	public static ConnectFourBoard removelines(ConnectFourBoard board)
	{
		ConnectFourBoard nolines = new ConnectFourBoard(6,7);
		
	  for (int i =0;i<board.getHeight() - 1;i++)
	  {
	    for (int j=0;j<board.getWidth() - 8;j++)
	    {
	  	  nolines.getBoard().get(i).get(j).setIcon(board.getBoard().get(i).get(2*j+1).getIcon());
	    }
	  }
	  
	  return nolines;
	}
	
	public static void printnolines(ConnectFourBoard board)
	{
	  for (int i =0;i<board.getHeight();i++)
	  {
	    for (int j=0;j<board.getWidth();j++)
	    {
	  	  System.out.print(board.getBoard().get(i).get(j).getIcon());
	    }
	    System.out.println("");
	  }
	}
	
	//Here's where it gets hard.
	//That's because there are basically four patterns
	//of Reds or Yellows that can win the game
	//One pattern is a horizontal line of four Rs or Ys,
	//another is a vertical line, another is a left-up to right-down
	//diagonal line, and the last is left-down to right-up diagonal,
	//We thus need to code for each type of line
	//and the various places where the line can be
	public static String checkWinner(ConnectFourBoard board)
	{
	  
	  //Time to look for the first type of winning line,
	  //a horizontal line
	  //This line can be on any row, so let's loop over 
	  //each row starting from 0 to 5 (since 6 is just ___)
	  for (int i =0;i<6;i++)
	  {
	    //On every row, the four-dotted line can look like
	    //----_ _,  _----_, or _ _----
	    //Here, _ can be an empty space or one of the colors 
	    //and - is not empty space  AND every - has the same
	    // color (R or Y)
	    //Note: since our R/Y/Empty's can only be in odd places,
	    //because of how we created the pattern in the first
	    //method, then our count has to be incremented by 2 
	    //and will start from 0 (which will be 1, ----_ _) 
	    //and stop at 6 (which will be 7, _ _----)
	    for (int j=0;j<7;j+=2)
	    {
	      if ((board.getBoard().get(i).get(j + 1).getIcon() != " ")
	      && (board.getBoard().get(i).get(j + 3).getIcon() != " ")
	      && (board.getBoard().get(i).get(j + 5).getIcon() != " ")
	      && (board.getBoard().get(i).get(j + 7).getIcon() != " ")
	      && (board.getBoard().get(i).get(j + 1).getIcon() == board.getBoard().get(i).get(j + 3).getIcon())
	      && (board.getBoard().get(i).get(j + 3).getIcon() == board.getBoard().get(i).get(j + 5).getIcon())
	      && (board.getBoard().get(i).get(j + 5).getIcon() == board.getBoard().get(i).get(j + 7).getIcon()))
	
	      //If we found a same-colored pattern, we'll return 
	      //the color so that we will know who won
	        return board.getBoard().get(i).get(j + 1).getIcon();  
	    }
	  }
	  
	  //For a vertical line, let's first loop over each
	  //odd-numbered column by incrementing with 2
	  //and check for consecutive boxes in the same column
	  //that are the same color
	  //Note: make sure you understand the horizontal line's
	  //codes first or else everything below this point will 
	  //make no sense to you
	  for (int i=1;i<15;i+=2)
	  {
	    //Of course, our lines will look like ----__ but reversed
	    //and there is need to our rows by 2 but just one
	    //and we have to start at the vertical version of ----__ and 
	    //and stop at _ _ ---- so it's from 0 to 2
	    for (int j =0;j<3;j++)
	    {
	          if((board.getBoard().get(j).get(i).getIcon() != " ")
	          && (board.getBoard().get(j + 1).get(i).getIcon() != " ")
	          && (board.getBoard().get(j + 2).get(i).getIcon() != " ")
	          && (board.getBoard().get(j + 3).get(i).getIcon() != " ")
	          && (board.getBoard().get(j).get(i).getIcon() == board.getBoard().get(j + 1).get(i).getIcon())
	          && (board.getBoard().get(j + 1).get(i).getIcon() == board.getBoard().get(j + 2).get(i).getIcon())
	          && (board.getBoard().get(j + 2).get(i).getIcon() == board.getBoard().get(j + 3).get(i).getIcon()))
	            return board.getBoard().get(j).get(i).getIcon();  
	    }  
	  }
	  
	  //For the left-up to right-down diagonal line
	  //We'll have to loop over the 3 uppermost
	  //rows and then go from left to right column-wise
	  for (int i=0;i<3;i++)
	  {
	    
	    //As expected, our uppermost box will start from 1
	    //and increase by 2 until it becomes 7 (the 3rd box
	    //on a row)
	    //Note how we used 1 instead 0 for the count here
	    //There's no real reason to use 1 instead of 0 or 
	    //vice versa, since we're still using an odd index
	    //for the columns and incrementing by 2
	    for (int j=1;j<9;j+=2)
	    {
	          if((board.getBoard().get(i).get(j).getIcon() != " ")
	          && (board.getBoard().get(i+1).get(j+2).getIcon() != " ")
	          && (board.getBoard().get(i+2).get(j+4).getIcon() != " ")
	          && (board.getBoard().get(i+3).get(j+6).getIcon() != " ")
	          && ((board.getBoard().get(i).get(j).getIcon() == board.getBoard().get(i+1).get(j+2).getIcon())
	          && (board.getBoard().get(i+1).get(j+2).getIcon() == board.getBoard().get(i+2).get(j+4).getIcon())
	          && (board.getBoard().get(i+2).get(j+4).getIcon() == board.getBoard().get(i+3).get(j+6).getIcon())))
	            return board.getBoard().get(i).get(j).getIcon();  
	    }  
	  }
	  
	  //Similar to the method above, but we're just reversing our
	  //trajectory, i.e. we're starting from the rightmost column
	  //instead of the leftmost like we did above
	  for (int i=0;i<3;i++)
	  {
	    for (int j=7;j<15;j+=2)
	    {
	          if((board.getBoard().get(i).get(j).getIcon() != " ")
	          && (board.getBoard().get(i+1).get(j-2).getIcon() != " ")
	          && (board.getBoard().get(i+2).get(j-4).getIcon() != " ")
	          && (board.getBoard().get(i+3).get(j-6).getIcon() != " ")
	          && ((board.getBoard().get(i).get(j).getIcon() == board.getBoard().get(i+1).get(j-2).getIcon())
	          && (board.getBoard().get(i+1).get(j-2).getIcon() == board.getBoard().get(i+2).get(j-4).getIcon())
	          && (board.getBoard().get(i+2).get(j-4).getIcon() == board.getBoard().get(i+3).get(j-6).getIcon())))
	            return board.getBoard().get(i).get(j).getIcon();  
	    }  
	  }
	  
	  //If after going over the table and we find no
	  //same colored lines, then we have to return something
	  //that says that we didn't find a winning color :P
	  return null;
	}
	
	
	
	//The easy part: using these methods
	public static void main (String[] args)
	{
	  //Time to make a pattern
	  board = createPattern();
	  //Time to make a condition for our game to keep on
	  //playing
	  boolean loop = true;
	  //We need something to keep track of whose turn it is
	  //int count = 0;
	  printPattern(board);
	  while(loop)
	  {
	     //Let's say that Red gets the first turn and thus
	     //every other turn 
	     if (count % 2 == 0) dropRedPattern(board);
	     else dropYellowPattern(board);
	     count++;//We need to keep track of the turns
	     printPattern(board);
	     
	     System.out.println();
	     
	     ConnectFourBoard nolines = removelines(board);
	     //ConnectFourBoard a = new ConnectFourBoard();
	     //a.grid = nolines;
	     printnolines(nolines);
	     
	     //Let's say we want to check for a winner during every
	     //turn made and say who it is
	     if (checkWinner(board) != null)
	     {
	        if (checkWinner(board) == "R")
	           System.out.println("The red player won.");
	        else if (checkWinner(board)== "Y")
	          System.out.println("The yellow player won.");
	       //Well, if someone wins, then the game has to end
	       loop = false;
	     }
	  }
	}

	public boolean isGameOver() {
		if(checkWinner(board) == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public String getWinner() {
		return checkWinner(board);
	}
	
	
	public boolean updateBoard(ArrayList<ArrayList<String>> board, Piece p) {
		return false;
	}


	public boolean isValidMove(Piece p, int newX, int newY) {
		return false;
	}

}