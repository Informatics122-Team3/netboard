package com.netboard.client.GUI;

import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import com.netboard.client.NetBoardClient;


public class HostGameMaker extends GUIMaker
{
	
	private List<String> gameList;
	private List<JButton> gameButtons;

	public HostGameMaker(NetBoardClient client) {
		super(client);
		this.gameList = client.getSupportedGames();
		mainFrame = new JFrame("NetBoard Login");
	    headerLabel = new JLabel("Select a Server & Sign In",JLabel.CENTER );
	    statusLabel = new JLabel(" ",JLabel.CENTER);
	}
	
	void initFrame()
	{
		mainFrame = new JFrame("NetBoard - Choose Game");
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.setSize(450,500);
		mainFrame.setLayout(new GridBagLayout());
	}

	void initComponents()
	{
		headerLabel = new JLabel("Choose a game to host...",JLabel.CENTER );
	    statusLabel = new JLabel(" ",JLabel.CENTER);        
	      
	    headerLabel.setSize(350,100);
	    statusLabel.setSize(350,100);
	}

	void setWindowListener()
	{
		// TODO Auto-generated method stub
		
	}

	void initPanels()
	{
		// TODO Auto-generated method stub
		
	}

	void fillFrame()
	{
		// TODO Auto-generated method stub
		
	}

	public void show()
	{
		// TODO Auto-generated method stub
		
	}

}
