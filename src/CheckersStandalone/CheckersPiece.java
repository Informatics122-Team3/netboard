package CheckersStandalone;

public class CheckersPiece {

	private int x,y;
	private String icon;
	private boolean isKing;
	
	public CheckersPiece()
	{
		setX(-1);
		setY(-1);
		setIcon("");
		setKing(false);
	}
	
	public CheckersPiece(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	public CheckersPiece(int x, int y, String icon)
	{
		setX(x);
		setY(y);
		setIcon(icon);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isKing() {
		return isKing;
	}

	public void setKing(boolean isKing) {
		this.isKing = isKing;
	}
	
}
