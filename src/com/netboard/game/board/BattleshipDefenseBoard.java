package com.netboard.game.board;

import java.util.ArrayList;

import com.netboard.game.piece.*;

public class BattleshipDefenseBoard extends Board{
	
	private AircraftCarrier aircraftcarrier;
	private Battleship battleship;
	private Cruiser cruiser;
	private Submarine submarine;
	private Destroyer destroyer;
	
	// array keeping track whether a location is tried
	// 0 if not tried, 1 if hit, 2 if miss
	private ArrayList<ArrayList<Integer>> tried;
	
	public BattleshipDefenseBoard()
	{
		super();
		this.setWidth(10);
		this.setHeight(10);
		
		aircraftcarrier = new AircraftCarrier();
		battleship = new Battleship();
		cruiser = new Cruiser();
		submarine = new Submarine();
		destroyer = new Destroyer();
		this.tried = new ArrayList<ArrayList<Integer>>();
		
		// set all grid locations to null
		for (int i = 0; i < this.getHeight(); i++)
		{
			this.getBoard().add(new ArrayList<Piece>());
			for (int j = 0; j < this.getWidth(); j++)
			{
				this.getBoard().get(i).add(null);
			}
		}
		
		// set all tried to 0, meaning not tried to hit
		for (int i = 0; i < this.getHeight(); i++)
		{
			this.tried.add(new ArrayList<Integer>());
			for (int j = 0; j < 10; j++)
			{
				this.tried.get(i).add(0);
			}
		}
		
		this.shipInit();
	}
	
	public void updateBoard() {}
	public void setBoard() {}
	public void addPiece(int x, int y, String type) {}
	public void removePiece(int x, int y, String type) {}
	
	private void shipInit()
	{
		this.addPiece(0, aircraftcarrier);
		this.addPiece(2, battleship);
		this.addPiece(4, cruiser);
		this.addPiece(6, submarine);
		this.addPiece(8, destroyer);
	}
	
	private void addPiece(int rowNum, Ship p)
	{
		for (int i = 0; i < p.getLength(); i++)
		{
			this.getBoard().get(rowNum).set(i, p);
		}
	}
	
	public void printBoard()
	{
		for(int row = 0; row < 10; row ++)
		{
			for(int col = 0; col < 10; col++)
			{
				if(this.getBoard().get(row).get(col) == null)
				{
					System.out.print(".");
				}
				else
				{
					System.out.print("X");
				}
			}
			System.out.println();
		}
	}
	
	// returns 0 if its a miss, 1 if its a hit, 3 if its invalid
	public int hit(int x, int y)
	{
		if (tried.get(x).get(y) == 0)
		{
			if(this.getBoard().get(x).get(y) != null)
			{
				this.getBoard().get(x).set(y, null); // wipe the piece reference
				tried.get(x).set(y, 1); // set it to tried
				return 1;
			}
			else
			{
				tried.get(x).set(y, 2); // set it to tried
				return 0;
			}
		}
		else
		{
			return 3;
		}
	}
	
	// check whether all ships are sunk
	public boolean allSunk()
	{
		for(int row = 0; row < 10; row ++)
		{
			for(int col = 0; col < 10; col++)
			{
				if(this.getBoard().get(row).get(col) != null)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public ArrayList<ArrayList<Integer>> getTried()
	{
		return this.tried;
	}
	
	public void printTriedBoard(ArrayList<ArrayList<Integer>> in)
	{
		for(int row = 0; row < 10; row ++)
		{
			for(int col = 0; col < 10; col++)
			{
				if(in.get(row).get(col) == 0)
				{
					System.out.print(".");
				}
				else if (in.get(row).get(col) == 1) // hit
				{
					System.out.print("X");
				}
				else
				{
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}

	@Override
	public Piece at(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
