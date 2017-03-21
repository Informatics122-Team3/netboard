package com.netboard.client.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import Battleship.BattleshipGame;

import com.netboard.client.NetBoardClient;
import com.netboard.game.CheckersGame;
import com.netboard.game.ConnectFourGame;
import com.netboard.game.Player;
import com.netboard.game.board.BattleshipDefenseBoard;
import com.netboard.game.board.Board;
import com.netboard.game.board.CheckersBoard;
import com.netboard.game.board.ConnectFourBoard;
import com.netboard.game.piece.CheckersPiece;
import com.netboard.game.piece.Piece;
import com.netboard.message.BoardUpdateMessage;
//import Checkers Game Logic

public class GameMaker extends GUIMaker {

	Player host;
	JPanel board1Panel, board2Panel, board1constrain;
	GridBagConstraints board1CBG, board2CBG, disconnectCBG, 
		restartCBG, sendMoveCBG, selectedButtonCBG;
	
	JButton disconnectBtn, restartBtn, sendMoveBtn;
	JLabel selectedButton;
	Board board;
	String playerTurn;
	
	int prevRow, prevCol, selectedRow, selectedCol;
	int moveRow = -1;
	int moveCol = -1;
	
	boolean buttonSelected = false;
	boolean sendReady = false;
	protected static String checkersName = "checkers";
	protected static String battleshipName = "battleship";
	protected static String connect4Name = "connect4";
	
	//TODO -----TEMP------
	CheckersGame checkers = new CheckersGame("Player 1", "Player 2");
	CheckersBoard board1 = checkers.getBoard();
	
	ConnectFourGame c4 = new ConnectFourGame(6, 7);
	ConnectFourBoard board2 = c4.getBoard();
	
	//Battleship
	BattleshipGame bat = new BattleshipGame();
	BattleshipDefenseBoard board3 = bat.getPlayerDenfenseBoards().get(0); //TODO: only gets the first board right now
	
	String null_val = "";
	String black_val = "o";
	String black_king = "O";
	String red_val = "x";
	String red_king = "X";
	JLabel p1score, p2score, winner;
	GridBagConstraints p1scoreCBG, p2scoreCBG, winnerCBG;
	// ----- TEMP --------
		
	int rows = 8;
	int cols = 8;
	JToggleButton[][] boardSquares = new JToggleButton[rows][cols]; //TODO don't hardcode board dimensions
	JToggleButton[][] boardSquares2 = new JToggleButton[rows][cols];
	// checkers: 10 rows x 10 cols
	// connectfour: 6 rows x 7 cols
	// battleship: 10 rows x 10 cols, 2 boards

	
//	public static void main(String[] args) {
//	
//		Player player1 = new Player("desoron", checkersName);
//		Player player2 = new Player("paulusm", "battleship");
//		Player player3 = new Player("darksteelknight", "connect4");
//		GameMaker gm = new GameMaker(player1);
//		gm.prepareGUI();
//		gm.show();
//}
	
	public GameMaker(Player player, NetBoardClient nbc) {
		super(nbc);
		this.host = player;
		
		//BoardUpdateMessage msg = client.readMessage();
		//this.playerTurn = msg.getTurn();
		
		if (host.getGameType().equals(checkersName))
			this.board = board1;
		else if (host.getGameType().equals("connect4"))
			this.board = board2;
		else if (host.getGameType().equals("battleship"))
			this.board = board3;
		
	}

	
	public void show(){
		mainFrame.setVisible(true);
	}

	private class HandleRight implements MouseListener {

		public void mouseClicked(MouseEvent e)
		{
			if (SwingUtilities.isRightMouseButton(e) && buttonSelected) {
                System.out.println("Right Worked");
                for (int i = 0; i < rows; i++)
                {
                    for (int j = 0; j < cols; j++)
                    {
                        if (boardSquares[i][j] == e.getSource())
                        { 
                        	System.out.println("Button " + i + ", " + j + " was clicked"); //i = row, j = col
                        	Boolean validMove = checkers.checkLogic((Piece) ((CheckersBoard)board).findPiece(selectedCol, selectedRow), j, i);
                        	
                        	if (!validMove) {
//								moveRow = -1;
//								moveCol = -1;
//								sendReady = false;
                        	}
                        	else if (validMove && (i == moveRow && j == moveCol)) {
                        		boardSquares[i][j].setSelected(false);
								moveRow = -1;
								moveCol = -1;
								sendReady = false;
                        	}
                        	else if (validMove && (moveRow == -1 && moveCol == -1)) {
                        		boardSquares[i][j].setSelected(true);
								moveRow = i;
								moveCol = j;
								sendReady = true;
                        	}
                        	else if (validMove && (i != moveRow || j != moveCol)) {
                        		boardSquares[moveRow][moveCol].setSelected(false);
                        		boardSquares[i][j].setSelected(true);
								moveRow = i;
								moveCol = j;
								sendReady = true;
                        	}

                        	System.out.println("Move from : " + selectedRow + ", " + selectedCol + 
                        		" to " + i + ", " + j + " is " + validMove);

                        	return;
                        }
                    }
                }
            }
		}

		public void mousePressed(MouseEvent e) { }

		public void mouseReleased(MouseEvent e) { }

		public void mouseEntered(MouseEvent e) { }

		public void mouseExited(MouseEvent e) { }
		
	}
	
	private class ButtonClickListener implements ActionListener{

	    public void actionPerformed(ActionEvent e) {
	         String command = e.getActionCommand();  
	         
	         if( command.equals( "Exit" ))  {
	        	 promptAndClose();
	         }
	         else if ( command.equals("Disconnect/Concede" )) {
	        	 //TODO Needs to be changes to something else later
	        	 promptAndClose();
	         }
	         else if ( command.equals("Restart Game" )) {
	        	 
	         }
	         else if ( command.equals("Send Move" )) {
	        	 if (sendReady) {
	        		 //send ApplyMoveMessage
	        		 
	        		 if (host.getGameType().equals(checkersName)) {
	        			 Piece selectedPiece = board1.findPiece(selectedCol, selectedRow);
	        			 checkers.makeMove(selectedPiece, moveCol, moveRow);
	        			 System.out.println("Current turn is: " + checkers.getPlayerTurn());
	        			 //updateCheckersBoardGUI();
	        			 checkers.toggleTurn();
	        			 boardSquares[selectedRow][selectedCol].setSelected(false);
	        			 boardSquares[moveRow][moveCol].setSelected(false);
	        			 
	        			 sendReady = false;
	        			 buttonSelected = false;
	        			 
	        			 client.applyBoardMove(selectedPiece, moveCol, moveRow);
	        			 client.waitForBoardUpdate();
	        			 
	        		 }
	        		 /* for non-server/client version:
	        		  * 
	        		  * 	if (host.getGameType().equals(checkersName))
	        		  *			//apply checkers logic here manually
	        		  *			//if there's no winner yet, apply move
	        		  *			//call updateCheckersGUI
	        		  *		else if (host.getGameType().equals("connect4"))
	        		  *			//apply c4 logic here manually
	        		  *		else if (host.getGameType().equals("Battleship"))
	        		  *			//apply battleship logic here manually
	        		  */
	        		 
	        		 
	        	 }
	         }
	         else
	         {	 //instructions to play the game on the right hand side
	        	 
	        	 boardSquares[prevRow][prevCol].setSelected(false);
	        	 selectedButton.setText("You have selected [row,col]: [" + command + "]");
	        	 List<String> coordList = Arrays.asList(command.split(","));
	        	 int row = Integer.parseInt(coordList.get(0));
	        	 int col = Integer.parseInt(coordList.get(1));
	        	
	        	 if (host.getGameType().equals(checkersName)) {
	        		 System.out.println("Top: --- buttonSelected is: " + buttonSelected + " ---\n");
	        		 chooseCheckersPiece(row, col);
 
	        		 System.out.println("Bottom: --- buttonSelected is: " + buttonSelected + " ---\n");
	        		 
		        	 //if selected button is not a piece, then setSelected(false)
	        	 	 
		        	 //check if piece occupies that spot (x,y coord)
		        	  //if there isnt anything there, ignore it
		        	  //if the piece isn't the player's (not their turn)
		        	 //otherwise store the last clicked piece as a variable
		        	 //usually, the last piece clicked is highlighted
		        	 
		        	 //if pieceIsSelected
		        	 // then make move
	        	 }

	        	 else if (host.getGameType().equals("connect4")){
	        		 
	        	 }
	         }
	    }

		
	}

	void chooseCheckersPiece(int row, int col) {
		
		String piece1, piece2;
		
		if (checkers.getTurn().equals(checkers.getPlayer1())) {
			piece1 = "o";
			piece2 = "O";
		}
		else {
			piece1 = "x";
			piece2 = "X";
		}
		
		if (row == selectedRow && col == selectedCol) {
			System.out.println("First if statement");
			System.out.println("Value of buttonSelected is: " + buttonSelected + " selectedRow is: " + selectedRow + " selectedCol is: " + selectedCol);
			buttonSelected = false;
			selectedRow = -1;
			selectedCol = -1;
			
			if (moveRow != -1 && moveCol != -1) {
				boardSquares[moveRow][moveCol].setSelected(false);
				moveRow = -1;
				moveCol = -1;
			}

			sendReady = false;
			System.out.println("Value of buttonSelected is: " + buttonSelected + " selectedRow is: " + selectedRow + " selectedCol is: " + selectedCol);
			return;
			
		}
		
		Piece selectedPiece = board.findPiece(col, row);
		
		if ((!buttonSelected) && (selectedPiece.getIcon().equals(piece1) || selectedPiece.getIcon().equals(piece2))) {
			System.out.println("Second if statement");
			System.out.println("Value of buttonSelected is: " + buttonSelected + " selectedRow is: " + selectedRow + " selectedCol is: " + selectedCol);
			buttonSelected = true;
			selectedRow = row;
			selectedCol = col;
			System.out.println("Value of buttonSelected is: " + buttonSelected + " selectedRow is: " + selectedRow + " selectedCol is: " + selectedCol);
		}
		else if (buttonSelected && (selectedPiece.getIcon().equals(piece1) || selectedPiece.getIcon().equals(piece2))) {
			System.out.println("Else if statement");
			System.out.println("Value of buttonSelected is: " + buttonSelected + " selectedRow is: " + selectedRow + " selectedCol is: " + selectedCol);
			boardSquares[selectedRow][selectedCol].setSelected(false);
			selectedRow = row;
			selectedCol = col;
			
			if (moveRow != -1 && moveCol != -1) {
				boardSquares[moveRow][moveCol].setSelected(false);
				moveRow = -1;
				moveCol = -1;
			}
			sendReady = false;
			
			System.out.println("Value of buttonSelected is: " + buttonSelected + " selectedRow is: " + selectedRow + " selectedCol is: " + selectedCol);

		}


		if (!selectedPiece.getIcon().equals(piece1) || selectedPiece.getIcon().equals(piece2)) {
			boardSquares[row][col].setSelected(false);
		}
		
		System.out.println("Checkers move was called");
		System.out.println("Row is: " + row + ", Col is: " + col);
		System.out.println("Checkers piece at this spot is: " + selectedPiece.getIcon());
		System.out.println("Value of buttonSelected is: " + buttonSelected + ", selectedRow is: " + selectedRow + ", selectedCol is: " + selectedCol);
		System.out.println();
//		prevRow = row;
//		prevCol = col;
		}
	
	void chooseC4Piece(int row, int col){
		
	}
	
	void initFrame()
	{
//		String frameTitle = "NetBoard - " + client.getUsername(); //TODO: put this line back in when testing with client/server
		String frameTitle = "NetBoard - " + host.getUsername();
		mainFrame = new JFrame(frameTitle);
		if (host.getGameType().equals("Battleship")) {
			mainFrame.setSize(900, 1000);
		}
		else {
			mainFrame.setSize(900, 720);
		}
		mainFrame.setLayout(new GridBagLayout());
		
	}

	void initComponents()
	{
		// TODO Auto-generated method stub
		
	}

	void setWindowListener()
	{
		// TODO Auto-generated method stub
		
	}

	void initPanels()
	{

		if (rows != cols) {
			board1Panel = new JPanel(new GridLayout(rows, cols))
			{         
				@Override
				public final Dimension getPreferredSize() {
	            Dimension d = super.getPreferredSize();
	            Dimension prefSize = null;
	            Component c = getParent();
	            if (c == null) {
	                prefSize = new Dimension(
	                        (int)d.getWidth(),(int)d.getHeight());
	            } else if (c!=null &&
	                    c.getWidth()>d.getWidth() &&
	                    c.getHeight()>d.getHeight()) {
	                prefSize = c.getSize();
	            } else {
	                prefSize = d;
	            }
	            int w = (int) prefSize.getWidth();
	            int h = (int) prefSize.getHeight();
	            // the smaller of the two sizes
	            //int s = (w>h ? h : w);
	            return new Dimension(w,h);
				}
			}
			;

		}
		else {
			//TODO Don't hardcode the gameboard size!
			board1Panel = new JPanel(new GridLayout(0, cols)) {         
				@Override
				public final Dimension getPreferredSize() {
	            Dimension d = super.getPreferredSize();
	            Dimension prefSize = null;
	            Component c = getParent();
	            if (c == null) {
	                prefSize = new Dimension(
	                        (int)d.getWidth(),(int)d.getHeight());
	            } else if (c!=null &&
	                    c.getWidth()>d.getWidth() &&
	                    c.getHeight()>d.getHeight()) {
	                prefSize = c.getSize();
	            } else {
	                prefSize = d;
	            }
	            int w = (int) prefSize.getWidth();
	            int h = (int) prefSize.getHeight();
	            // the smaller of the two sizes
	            int s = (w>h ? h : w);
	            return new Dimension(s,s);
				}
			};
		}
		
		board2Panel = new JPanel(new GridLayout(0, 10));
		
		board1constrain = new JPanel(new GridBagLayout());
		
	}
	
	void fillFrame()
	{
		selectedButton = new JLabel(" ", JLabel.CENTER);
		// JButton disconnectBtn, restartBtn, sendMoveBtn;
		disconnectBtn = new JButton("Disconnect/Concede");
		restartBtn = new JButton("Restart Game");
		sendMoveBtn = new JButton("Send Move");
		
		selectedButtonCBG = new GridBagConstraints();
		disconnectCBG = new GridBagConstraints();
		restartCBG = new GridBagConstraints();
		sendMoveCBG = new GridBagConstraints();
		board1CBG = new GridBagConstraints();
		
		disconnectCBG.gridx = 1;
		disconnectCBG.gridy = 0;
		disconnectCBG.fill = GridBagConstraints.HORIZONTAL;
		disconnectCBG.insets = new Insets(15, 0, 0, 15);
		disconnectCBG.anchor = GridBagConstraints.CENTER;
		
		restartCBG.gridx = 1;
		restartCBG.gridy = 1;
		restartCBG.fill = GridBagConstraints.HORIZONTAL;
		restartCBG.insets = new Insets(10, 0, 0, 15);
		restartCBG.anchor = GridBagConstraints.PAGE_START;
		
		sendMoveCBG.gridx = 1;
		sendMoveCBG.gridy = 3;
		sendMoveCBG.anchor = GridBagConstraints.LINE_END;
		sendMoveCBG.fill = GridBagConstraints.HORIZONTAL;
		sendMoveCBG.insets = new Insets(0, 0, 15, 15);
		
		board1CBG.gridx = 0;
		board1CBG.gridy = 1;
		board1CBG.weighty = 0.8;
		board1CBG.weightx = 0.8;
		board1CBG.anchor = GridBagConstraints.PAGE_END;
		board1CBG.fill = GridBagConstraints.BOTH;
		board1CBG.insets = new Insets(15, 15, 15, 15);
		
		disconnectBtn.setActionCommand("Disconnect/Concede");
		restartBtn.setActionCommand("Restart Game");
		sendMoveBtn.setActionCommand("Send Move");
		
		disconnectBtn.addActionListener(new ButtonClickListener());
		restartBtn.addActionListener(new ButtonClickListener());
		sendMoveBtn.addActionListener(new ButtonClickListener());
		
		
//		board1constrain.add(board1Panel, board1CBG);
		board1constrain.add(board1Panel);
		mainFrame.add(board1constrain);
		
		makeBoard(board1Panel, boardSquares);
//		colorCheckersBoard(boardSquares); //TODO: take this out after you're done
		
//		if (host.getGameType().equals(checkersName)) {
//			colorCheckersBoard(boardSquares);
//		}
		
		/*
		 * if host.getGameType().equals("Battleship")
		 * board1Panel.gridy++;
		 */
		
		mainFrame.add(board1constrain, board1CBG);
		mainFrame.add(selectedButton, selectedButtonCBG);
//		mainFrame.add(board1Panel, board1CBG);
		mainFrame.add(disconnectBtn, disconnectCBG);
		mainFrame.add(restartBtn, restartCBG);
		mainFrame.add(sendMoveBtn, sendMoveCBG);
		
	}
	
	void makeCheckersBoard(JPanel boardPanel, JToggleButton[][] boardTiles) {
		CheckersBoard checkersBoard = (CheckersBoard) board;
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				JToggleButton b = new JToggleButton();
				b.addMouseListener(new HandleRight());
				if (checkersBoard.findPiece(j, i).getIcon().equals(red_val)) {
					try {
					java.net.URL imgURL = getClass().getResource("/red_piece.png");
					ImageIcon img = new ImageIcon(imgURL);
					b.setIcon(img);
					
					java.net.URL imgURL2 = getClass().getResource("/red_piece_selected.png");
					ImageIcon img2 = new ImageIcon(imgURL2);
					b.setSelectedIcon(img2);

				}
				catch (Exception e) {
					System.out.println(e);
				}
				}
				else if (checkersBoard.findPiece(j, i).getIcon().equals(black_val)) {
					try {
					java.net.URL imgURL = getClass().getResource("/black_piece.png");
					ImageIcon img = new ImageIcon(imgURL);
					b.setIcon(img);
					
					java.net.URL imgURL2 = getClass().getResource("/black_piece_selected.png");
					ImageIcon img2 = new ImageIcon(imgURL2);
					b.setSelectedIcon(img2);
					
				}
				catch (Exception e) {
					System.out.println(e);
				}
				}
				
				else if (checkersBoard.findPiece(j, i).getIcon().equals(black_king)) {
					try {
					java.net.URL imgURL = getClass().getResource("/black_king.png");
					ImageIcon img = new ImageIcon(imgURL);
					b.setIcon(img);
					
					java.net.URL imgURL2 = getClass().getResource("/black_king_selected.png");
					ImageIcon img2 = new ImageIcon(imgURL2);
					b.setSelectedIcon(img2);
					
				}
				catch (Exception e) {
					System.out.println(e);
				}
				}
				
				else if (checkersBoard.findPiece(j, i).getIcon().equals(red_king)) {
					try {
					java.net.URL imgURL = getClass().getResource("/red_king.png");
					ImageIcon img = new ImageIcon(imgURL);
					b.setIcon(img);
					
					java.net.URL imgURL2 = getClass().getResource("/red_king_selected.png");
					ImageIcon img2 = new ImageIcon(imgURL2);
					b.setSelectedIcon(img2);
					
					}
				

					catch (Exception e) {
						System.out.println(e);
					}
				}
				
				b.setActionCommand(i + "," + j);
				b.addActionListener(new ButtonClickListener());
				b.setMargin(buttonMargin);
				b.setBackground(Color.WHITE);
				boardTiles[i][j] = b;
			}
		}
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				boardPanel.add(boardSquares[i][j]);
			}
		}
		
		colorCheckersBoard(boardTiles);
	}
	
	void makeC4Board(JPanel boardPanel, JToggleButton[][] boardTiles) {
		
	}
	
	void makeBattleshipBoard(JPanel boardPanel, JToggleButton[][] boardTiles) {
		
	}
	
	void makeBoard(JPanel boardPanel, JToggleButton[][] boardTiles) {
		
		if (host.getGameType().equals(checkersName))
			makeCheckersBoard(boardPanel, boardTiles);
		else if (host.getGameType().equals("connect4"))
			makeC4Board(boardPanel, boardTiles);
		else if (host.getGameType().equals("Battleship"))
			makeBattleshipBoard(boardPanel, boardTiles);
	
	}
	
	void colorCheckersBoard(JToggleButton[][] boardTiles) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if ((j % 2 == 1 && i % 2 == 1) || (j % 2== 0 && i % 2 == 0)) {
					Color coral = new Color(248,131,121);
					boardTiles[i][j].setBackground(coral);
				}
				else {
					Color gray = new Color(128,128,128);
					boardTiles[i][j].setBackground(gray);
				}
			}
		}
	}
	
	void updateCheckersBoardGUI(Board board) {
		CheckersBoard checkersBoard = (CheckersBoard) board;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (checkersBoard.findPiece(j, i).getIcon().equals(red_val)) {
					try {
						java.net.URL imgURL = getClass().getResource("/red_piece.png");
						ImageIcon img = new ImageIcon(imgURL);
						boardSquares[i][j].setIcon(img);
						
						java.net.URL imgURL2 = getClass().getResource("/red_piece_selected.png");
						ImageIcon img2 = new ImageIcon(imgURL2);
						boardSquares[i][j].setSelectedIcon(img2);
					}
					catch (Exception e) {
					System.out.println(e);
					}
				}
				else if (checkersBoard.findPiece(j, i).getIcon().equals(black_val)) {
					try {
						java.net.URL imgURL = getClass().getResource("/black_piece.png");
						ImageIcon img = new ImageIcon(imgURL);
						boardSquares[i][j].setIcon(img);
						
						java.net.URL imgURL2 = getClass().getResource("/black_piece_selected.png");
						ImageIcon img2 = new ImageIcon(imgURL2);
						boardSquares[i][j].setSelectedIcon(img2);
					
					}
					catch (Exception e) {
					System.out.println(e);
					}
				}
				
				else if (checkersBoard.findPiece(j, i).getIcon().equals(black_king)) {
					try {
						java.net.URL imgURL = getClass().getResource("/black_king.png");
						ImageIcon img = new ImageIcon(imgURL);
						boardSquares[i][j].setIcon(img);
						
						java.net.URL imgURL2 = getClass().getResource("/black_king_selected.png");
						ImageIcon img2 = new ImageIcon(imgURL2);
						boardSquares[i][j].setSelectedIcon(img2);
					
					}
					catch (Exception e) {
					System.out.println(e);
					}
				}
				
				else if (checkersBoard.findPiece(j, i).getIcon().equals(red_king)) {
					try {
						java.net.URL imgURL = getClass().getResource("/red_king.png");
						ImageIcon img = new ImageIcon(imgURL);
						boardSquares[i][j].setIcon(img);
						
						java.net.URL imgURL2 = getClass().getResource("/red_king_selected.png");
						ImageIcon img2 = new ImageIcon(imgURL2);
						boardSquares[i][j].setSelectedIcon(img2);
					}

					catch (Exception e) {
					System.out.println(e);
					}
				}
				
				else if (checkersBoard.findPiece(j, i).getIcon().equals(null_val)) {
					try {
						boardSquares[i][j].setIcon(null);
					}
					catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}
		//boardPanel.revalidate();
		mainFrame.revalidate();
		
		//boardPanel.repaint();
		mainFrame.repaint();
	}
	
	void updateC4BoardGUI () {
		ConnectFourBoard c4Board = (ConnectFourBoard) board;
	}
	
	void updateBattleshipBoardGUI () {
		BattleshipDefenseBoard batBoard = (BattleshipDefenseBoard) board;
	}
	
	
 	public void updateBoardGUI(List<Board> boards, String gameType){
 		if (gameType.equals(checkersName)) {
 			updateCheckersBoardGUI(boards.get(0));
 		}
 		else if (gameType.equals(battleshipName)) {
 			//updateBattleshipBoardGUI(boards.get(0), boards.get(1));
 		}
 		else if (gameType.equals(connect4Name)) {
 			//updateC4BoardGUI(boards.get(0));
 		}
 	}
	
	public void refresh(List<Board> boardState) {
		// TODO Auto-generated method stub
		
	}
}
