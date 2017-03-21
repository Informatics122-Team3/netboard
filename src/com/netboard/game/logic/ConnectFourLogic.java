//Adapted from: http://www.javaproblems.com/2013/01/creating-connect-four-game-in-java.html 
//For INF 122 final project group 3

package com.netboard.game.logic;

import com.netboard.game.board.ConnectFourBoard;
import com.netboard.game.piece.Piece;
import com.netboard.game.Logic;

public class ConnectFourLogic implements Logic {
	private ConnectFourBoard board;

	public ConnectFourLogic () {}
	
	public ConnectFourLogic(ConnectFourBoard board) {
		this.board = board;
	}
	
	// There are basically four patterns
	// of Reds or Yellows that can win the game
	// One pattern is a horizontal line of four Rs or Ys,
	// another is a vertical line, another is a left-up to right-down
	// diagonal line, and the last is left-down to right-up diagonal,
	// We thus need to code for each type of line
	// and the various places where the line can be
	public static String checkWinner(ConnectFourBoard board) {

		// Time to look for the first type of winning line,
		// a horizontal line
		// This line can be on any row, (sinceso let's loop over
		// each row starting from 0 to 5  6 is just ___)
		for (int i = 0; i < 6; i++) {
			// On every row, the four-dotted line can look like
			// ----_ _, _----_, or _ _----
			// Here, _ can be an empty space or one of the colors
			// and - is not empty space AND every - has the same
			// color (R or Y)
			// Note: since our R/Y/Empty's can only be in odd places,
			// because of how we created the pattern in the first
			// method, then our count has to be incremented by 2
			// and will start from 0 (which will be 1, ----_ _)
			// and stop at 6 (which will be 7, _ _----)
			for (int j = 0; j < 7; j++) {
				if ((board.getBoard().get(i).get(j).getIcon() != " ")
						&& (board.getBoard().get(i).get(j + 1).getIcon() != " ")
						&& (board.getBoard().get(i).get(j + 2).getIcon() != " ")
						&& (board.getBoard().get(i).get(j + 3).getIcon() != " ")
						&& (board.getBoard().get(i).get(j).getIcon() == board.getBoard().get(i).get(j + 1)
								.getIcon())
						&& (board.getBoard().get(i).get(j + 1).getIcon() == board.getBoard().get(i).get(j + 2)
								.getIcon())
						&& (board.getBoard().get(i).get(j + 2).getIcon() == board.getBoard().get(i).get(j + 3)
								.getIcon()))

					// If we found a same-colored pattern, we'll return
					// the color so that we will know who won
					return board.getBoard().get(i).get(j).getIcon();
			}
			
		}

		// For a vertical line, let's first loop over each
		// odd-numbered column by incrementing with 2
		// and check for consecutive boxes in the same column
		// that are the same color
		// Note: make sure you understand the horizontal line's
		// codes first or else everything below this point will
		// make no sense to you
		for (int i = 0; i < 7; i++) {
			// Of course, our lines will look like ----__ but reversed
			// and there is need to our rows by 2 but just one
			// and we have to start at the vertical version of ----__ and
			// and stop at _ _ ---- so it's from 0 to 2
			for (int j = 0; j < 4; j++) {
				if ((board.getBoard().get(j).get(i).getIcon() != " ")
						&& (board.getBoard().get(j + 1).get(i).getIcon() != " ")
						&& (board.getBoard().get(j + 2).get(i).getIcon() != " ")
						&& (board.getBoard().get(j + 3).get(i).getIcon() != " ")
						&& (board.getBoard().get(j).get(i).getIcon() == board.getBoard().get(j + 1).get(i).getIcon())
						&& (board.getBoard().get(j + 1).get(i).getIcon() == board.getBoard().get(j + 2).get(i)
								.getIcon())
						&& (board.getBoard().get(j + 2).get(i).getIcon() == board.getBoard().get(j + 3).get(i)
								.getIcon()))
					return board.getBoard().get(j).get(i).getIcon();
			}
		}

		// For the left-up to right-down diagonal line
		// We'll have to loop over the 3 uppermost
		// rows and then go from left to right column-wise
		for (int i = 0; i < 3; i++) {

			// As expected, our uppermost box will start from 1
			// and increase by 2 until it becomes 7 (the 3rd box
			// on a row)
			// Note how we used 1 instead 0 for the count here
			// There's no real reason to use 1 instead of 0 or
			// vice versa, since we're still using an odd index
			// for the columns and incrementing by 2
			for (int j = 0; j < 4; j += 1) {
				if ((board.getBoard().get(i).get(j).getIcon() != " ")
						&& (board.getBoard().get(i + 1).get(j + 1).getIcon() != " ")
						&& (board.getBoard().get(i + 2).get(j + 2).getIcon() != " ")
						&& (board.getBoard().get(i + 3).get(j + 3).getIcon() != " ")
						&& ((board.getBoard().get(i).get(j).getIcon() == board.getBoard().get(i + 1).get(j + 1)
								.getIcon())
								&& (board.getBoard().get(i + 1).get(j + 1).getIcon() == board.getBoard().get(i + 2)
										.get(j + 2).getIcon())
								&& (board.getBoard().get(i + 2).get(j + 2).getIcon() == board.getBoard().get(i + 3)
										.get(j + 3).getIcon())))
					return board.getBoard().get(i).get(j).getIcon();
			}
		}

		// Similar to the method above, but we're just reversing our
		// trajectory, i.e. we're starting from the rightmost column
		// instead of the leftmost like we did above
		for (int i = 0; i < 3; i++) {
			for (int j = 4; j < 7; j += 1) {
				if ((board.getBoard().get(i).get(j).getIcon() != " ")
						&& (board.getBoard().get(i + 1).get(j - 1).getIcon() != " ")
						&& (board.getBoard().get(i + 2).get(j - 2).getIcon() != " ")
						&& (board.getBoard().get(i + 3).get(j - 3).getIcon() != " ")
						&& ((board.getBoard().get(i).get(j).getIcon() == board.getBoard().get(i + 1).get(j - 1)
								.getIcon())
								&& (board.getBoard().get(i + 1).get(j - 1).getIcon() == board.getBoard().get(i + 2)
										.get(j - 2).getIcon())
								&& (board.getBoard().get(i + 2).get(j - 2).getIcon() == board.getBoard().get(i + 3)
										.get(j - 3).getIcon())))
					return board.getBoard().get(i).get(j).getIcon();
			}
		}

		// If after going over the table and we find no
		// same colored lines, then we have to return something
		// that says that we didn't find a winning color :P
		return null;
	}

	public boolean isGameOver() 
	{
		if (checkWinner(board) == null) 
		{
			return false;
		} else 
		{
			return true;
		}
	}

	public boolean isValidMove(Piece p, int newX, int newY) {
		for (int i = 5; i >= 0; i--) 
		{
			System.out.println("The Icon of X is: " + board.getBoard().get(i).get(newX).getIcon());
			
			if (board.getBoard().get(i).get(newX).getIcon() == " ") 
			{
				break;
			}
			if (i == 0) 
			{
				return false;
			}
		}
		return true;
	}
	
	public void setBoard(ConnectFourBoard b)
	{
		board = b;
	}
	
	//Not used by Connect 4
	public String getWinner() 
	{
		return checkWinner(board);
	}
}