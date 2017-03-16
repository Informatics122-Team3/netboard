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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.netboard.game.Player;

public class GameMaker extends GUIMaker {

	Player host;
	JPanel board1Panel, board2Panel, board1constrain;
	GridBagConstraints board1CBG, board2CBG, disconnectCBG, 
		restartCBG, sendMoveCBG, selectedButtonCBG;
	
	JButton disconnectBtn, restartBtn, sendMoveBtn;
	JLabel selectedButton;
	
	int rows = 6;
	int cols = 7;
	JButton[][] boardSquares = new JButton[rows][cols]; //TODO don't hardcode board dimensions
	JButton[][] boardSquares2 = new JButton[rows][cols];
	// checkers: 10 rows x 10 cols
	// connectfour: 6 rows x 7 cols
	// battleship: 10 rows x 10 cols, 2 boards

	
//	public static void main(String[] args) {
//	
//		Player player1 = new Player("desoron", "Checkers");
//		Player player2 = new Player("paulusm", "Battleship");
//		Player player3 = new Player("darksteelknight", "ConnectFour");
//		GameMaker gm = new GameMaker(player3);
//		gm.prepareGUI();
//		gm.show();
//}
	
	public GameMaker() {
		// TODO Auto-generated constructor stub
	}
	
	public GameMaker(Player player) {
		// TODO takes in Player object in order to
		// display the gameType and Username in the window
		this.host = player;
	}
	
	public void show(){
		mainFrame.setVisible(true);
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
	        	 
	         }
	         else
	         {
	        	 selectedButton.setText("You have selected [row,col]: [" + command + "]");
	         }
	    }
	}

	void initFrame()
	{
		String frameTitle = "NetBoard - " + host.getGameType() + ": " + host.getUsername();
		mainFrame = new JFrame(frameTitle);
		if (host.getGameType().equals("Battleship")) {
			mainFrame.setSize(900, 1000);
		}
		else {
			mainFrame.setSize(700, 600);
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
		
		if (host.getGameType().equals("Checkers")) {
			colorCheckersBoard(boardSquares);
		}
		
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
	
	void makeBoard(JPanel boardPanel, JButton[][] boardTiles) {
		Insets buttonMargin = new Insets(7, 7, 7, 7);
//		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
//				JButton b = new JButton(Integer.toString(i) + ", " + Integer.toString(j));
				JButton b = new JButton();
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
	}
	
	void colorCheckersBoard(JButton[][] boardTiles) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if ((j % 2 == 1 && i % 2 == 1) || (j % 2== 0 && i % 2 == 0)) {
					boardTiles[i][j].setBackground(Color.RED);
				}
				else {
					boardTiles[i][j].setBackground(Color.BLACK);
				}
			}
		}
	}
	
	

}
