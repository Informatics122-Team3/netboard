package CheckersStandalone;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckersBoard {

	private int width,height,p1Pieces,p2Pieces;
	private ArrayList<CheckersStandalone.Player1Piece> p1;
	private ArrayList<CheckersStandalone.Player2Piece> p2;
	
	private ArrayList<ArrayList<String>> CLIBoard;
	
	public CheckersBoard() {
		// TODO Auto-generated constructor stub
		setWidth(8);
		setHeight(8);
		p1 = new ArrayList<CheckersStandalone.Player1Piece>();
		p2 = new ArrayList<CheckersStandalone.Player2Piece>();
		CLIBoard = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < 3; i = i + 2)
			for(int j = 1; j < width; j = j + 2)
			{
				p1.add(new CheckersStandalone.Player1Piece(j,i));
				p2.add(new CheckersStandalone.Player2Piece((width-1)-j,i+5));
			}
		for(int i = 0; i < width; i = i + 2)
		{
			p1.add(new CheckersStandalone.Player1Piece(i,1));
			p2.add(new CheckersStandalone.Player2Piece((width-1)-i,6));
		}
		
		setp1Pieces(p1.size());
		setp2Pieces(p2.size());
		setBoard();
	}

	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getp1Pieces(){ return p1Pieces; }
	public int getp2Pieces(){ return p2Pieces; }
	
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public void setp1Pieces(int amount){ p1Pieces = amount; }
	public void setp2Pieces(int amount){ p2Pieces = amount; }

	public void setBoard()
	{
		for(int i = 0; i < height; i++)
			CLIBoard.add(new ArrayList<String>(Arrays.asList(" "," "," "," "," "," "," "," ")));
		for(CheckersStandalone.Player1Piece p : p1)
			CLIBoard.get(p.getY()).set(p.getX(), p.getIcon());
		for(CheckersStandalone.Player2Piece p : p2)
			CLIBoard.get(p.getY()).set(p.getX(), p.getIcon());

	}
	
	public void printBoard()
	{
		String topBar = "  ";
		for(int i = 0; i < width; i++)
			topBar = topBar + i + " ";
		System.out.println(topBar);
		for(int i = 0; i < height; i++)
		{
			System.out.println(" -----------------");
			String row = i + "|";
			for(int j = 0; j < width; j++)
				row = row.concat(CLIBoard.get(i).get(j).concat("|"));
			System.out.println(row);
		}
		System.out.println(" -----------------");
	}
	
	public void updateBoard(CheckersStandalone.CheckersPiece p, int newX, int newY)
	{
		if(p.getX() - newX == 2 && p.getY() - newY == 2)
			removePiece(p.getX() - 1, p.getY()-1, p.getIcon());
		if(p.getX() - newX == 2 && newY - p.getY() == 2)
			removePiece(p.getX() - 1, p.getY() + 1, p.getIcon());
		if(newX - p.getX() == 2 && p.getY() - newY == 2)
			removePiece(p.getX() + 1, p.getY() - 1, p.getIcon());
		if(newX - p.getX() == 2 && newY - p.getY() == 2)
			removePiece(p.getX() + 1, p.getY()+1, p.getIcon());
		
		CLIBoard.get(p.getY()).set(p.getX(), " ");
		p.setX(newX);
		p.setY(newY);
		
		//kinging a piece
		if(p.getIcon() == "x" && p.getY() == 0)
		{
			p.setIcon("X");
			p.setKing(true);
		}
		if(p.getIcon() == "o" && p.getY() == height - 1)
		{
			p.setIcon("O");
			p.setKing(true);
		}
		
		CLIBoard.get(p.getY()).set(p.getX(), p.getIcon());
		
	}

	public CheckersStandalone.CheckersPiece findPiece(int x, int y)
	{
		for(CheckersStandalone.Player1Piece p : p1)
			if(p.getX() == x && p.getY() == y)
				return p;
		
		for(CheckersStandalone.Player2Piece p : p2)
			if(p.getX() == x && p.getY() == y)
				return p;
		return new CheckersStandalone.CheckersPiece();
	}
	public void removePiece(int x, int y, String type)
	{
		if(type.equals("o") || type.equals("O"))
			for(CheckersStandalone.Player2Piece p : p2)
			{
				if(p.getX() == x && p.getY() == y)
					{
					p2.remove(p);
					CLIBoard.get(p.getY()).set(p.getX(), " ");

					break;
					}
			}
		else if(type.equals("x") || type.equals("X"))
			for(CheckersStandalone.Player1Piece p : p1)
				if(p.getX() == x && p.getY() == y)
				{
					p1.remove(p);
					CLIBoard.get(p.getY()).set(p.getX(), " ");
					break;
				}
		setp1Pieces(p1.size());
		setp2Pieces(p2.size());
	}
}
