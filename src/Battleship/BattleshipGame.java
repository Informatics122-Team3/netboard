package Battleship;

import com.netboard.game.board.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BattleshipGame{
	
	// the player board is in this order
	// [p1Denfense, p2Defense]
	private ArrayList<BattleshipDefenseBoard> PlayerDenfenseBoards;
	
	// The offense boards is in this order
	// [p1offense, p2offense]
	// the offense board is taken from the tried board in each defense board, it is
	// an 2D arrarylist of integer
	// the value 1 for tried and his, 2 for tried and miss, 0 for not tried
	private ArrayList<ArrayList<ArrayList<Integer>>> PlayerOffenseBoards;
	
	// 1 for player 1, 2 for player 2
	private int turn;
	
	public BattleshipGame(){
		
		// initialize the defense board
		PlayerDenfenseBoards = new ArrayList<BattleshipDefenseBoard>();
		PlayerDenfenseBoards.add(new BattleshipDefenseBoard());
		PlayerDenfenseBoards.add(new BattleshipDefenseBoard());
		
		// initialize the offense board
		PlayerOffenseBoards = new ArrayList<ArrayList<ArrayList<Integer>>>();
		PlayerOffenseBoards.add(PlayerDenfenseBoards.get(1).getTried());
		PlayerOffenseBoards.add(PlayerDenfenseBoards.get(0).getTried());
		this.turn = 1;
	}
	
	public void switchTurn()
	{
		if(this.turn == 1) this.turn = 2;
		else this.turn = 1;
	}
	
	// returns 0 if its a miss, 1 if its a hit, 3 if its invalid
	public int makeMove(int x, int y)
	{
		if(this.turn == 1)
		{
			int result = this.PlayerDenfenseBoards.get(1).hit(x, y);
			if(result == 3) return 3;
			else
			{
				this.switchTurn();
				return result;
			}
		}
		else
		{
			int result = this.PlayerDenfenseBoards.get(0).hit(x, y);
			if(result == 3) return 3;
			else
			{
				this.switchTurn();
				return result;
			}
		}
	}
	
	// return the winner number or 0 if there is no winner
	public int isWinning()
	{
		if (this.turn == 1)
		{
			if (this.PlayerDenfenseBoards.get(1).allSunk()) return 1;
		}
		else
		{
			if (this.PlayerDenfenseBoards.get(0).allSunk()) return 2;
		}
		return 0;
	}
	
	public int getTurn()
	{
		return this.turn;
	}
	
	public ArrayList<BattleshipDefenseBoard> getPlayerDenfenseBoards()
	{
		return this.PlayerDenfenseBoards;
	}
	
	public ArrayList<ArrayList<ArrayList<Integer>>> getPlayerOffenseBoards()
	{
		return this.PlayerOffenseBoards;
	}
	
//	public static void main(String[] args)
//	{
//		Scanner scan = new Scanner(System.in);
//		BattleshipGame b = new BattleshipGame();
//		
//		while(true)
//		{
//			System.out.println("It is player " + b.getTurn() + "'s turn");
//			System.out.println("Enter cord");
//			String s = scan.nextLine();
//			String[] cord = s.split(" ");
//			b.makeMove(Integer.parseInt(cord[0]), Integer.parseInt(cord[1])) ;
//			b.getPlayerDenfenseBoards().get(0).printBoard();
//			System.out.println();
//			b.getPlayerDenfenseBoards().get(0).printTriedBoard(b.getPlayerOffenseBoards().get(0));
//			if (b.isWinning()!= 0) break;
//		}
//		
//		scan.close();
//	}
}
