package com.netboard.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import java.util.Map.Entry;
import com.netboard.client.NetBoardClient;
import com.netboard.game.Player;

public class LobbyMaker extends GUIMaker{

	JSplitPane splitPane;
	JPanel innerPanel, listPanel, controlPanel, joinPanel;
	JTextField serverTxt, nameTxt;
	JButton backBtn, refreshBtn, hostBtn, joinBtn, exitBtn;
	JList usernameList;
	JScrollPane listScroller;
	GridBagConstraints backCBG, refreshCBG, hostCBG, joinCBG, cPanel, jPanel, lPanel;
	NetBoardClient client;
	
//	public static void main(String[] args) {
//		
//		LobbyMaker lm = new LobbyMaker();
//		lm.prepareGUI();
//		lm.show();
//	}
	
	public LobbyMaker(NetBoardClient newClient) {
		// TODO Auto-generated constructor stub
		client = newClient;
	}
	
	public void initFrame(){
		  mainFrame = new JFrame("NetBoard - Player Lobby");
		  mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	      mainFrame.setSize(450,500);
	      mainFrame.setLayout(new GridBagLayout());
		}
		
	public void initComponents(){
	      headerLabel = new JLabel("Welcome to the Lobby!",JLabel.CENTER );
	      statusLabel = new JLabel(" ",JLabel.CENTER);        
	      
	      headerLabel.setSize(350,100);
	      statusLabel.setSize(350,100);

		}
	     
	public void setWindowListener(){
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            promptAndClose();
	         }        
	      });
		}
	     
	public void initPanels(){

		  innerPanel = new JPanel();
		  listPanel = new JPanel(new BorderLayout());
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
	      joinPanel = new JPanel();
	      joinPanel.setLayout(new BoxLayout(joinPanel, BoxLayout.LINE_AXIS));
	             
		}
		
	public void fillFrame(){
//		mainFrame.add(headerLabel);
		
		backBtn = new JButton("<< Back");
		refreshBtn = new JButton("Refresh");
		hostBtn = new JButton("Host");
		exitBtn = new JButton("Exit");
		joinBtn = new JButton("Join");
		
		backCBG = new GridBagConstraints();
		refreshCBG  = new GridBagConstraints();
		hostCBG = new GridBagConstraints();
		joinCBG = new GridBagConstraints();
		
		cPanel = new GridBagConstraints();
		lPanel = new GridBagConstraints();
		jPanel = new GridBagConstraints();
		
		//setting the column that each of the top menu buttons will appear in
		backCBG.gridx = 0;
		backCBG.insets = new Insets(15, 20 , 0, 10);
		backCBG.anchor = GridBagConstraints.CENTER;
		
		refreshCBG.gridx = 1;
		refreshCBG.insets = new Insets(15, 10 , 0, 10);
		refreshCBG.anchor = GridBagConstraints.CENTER;
		
		hostCBG.gridx = 2;
		hostCBG.insets = new Insets(15, 10 , 0, 20);
		hostCBG.anchor = GridBagConstraints.LINE_END;
		
		cPanel.gridy = 0;	
		
		lPanel.gridx = 0;
		lPanel.gridy = 1;
		lPanel.gridwidth = 3;
		lPanel.weighty = 0.8;
		lPanel.weightx = 1.0;
		lPanel.anchor = GridBagConstraints.PAGE_END;
		lPanel.fill = GridBagConstraints.BOTH;
		
		jPanel.gridy = 2;
		jPanel.gridx = 2;
		jPanel.anchor = GridBagConstraints.LINE_END;
		jPanel.insets = new Insets(0, 10 , 15, 20);
		
		
//		mainFrame.add(controlPanel, cPanel);
		mainFrame.add(backBtn, backCBG);
		mainFrame.add(refreshBtn, refreshCBG);
		mainFrame.add(hostBtn, hostCBG);
//		mainFrame.add(joinBtn, joinCBG);
		mainFrame.add(listPanel, lPanel);
		mainFrame.add(joinPanel, jPanel);
//		mainFrame.add(statusLabel);
		}

	public void show(){
		   
//		backBtn = new JButton("<< Back");
//		refreshBtn = new JButton("Refresh");
//		hostBtn = new JButton("Host");
//		exitBtn = new JButton("Exit");
		  
//		joinBtn = new JButton("Join");
		
		controlPanel.removeAll();
		joinPanel.removeAll();
		
		backBtn.setActionCommand("Back");
		refreshBtn.setActionCommand("Refresh");
		hostBtn.setActionCommand("Host");
		exitBtn.setActionCommand("Exit");
		
		backBtn.addActionListener(new ButtonClickListener());
		refreshBtn.addActionListener(new ButtonClickListener());
		hostBtn.addActionListener(new ButtonClickListener());
		exitBtn.addActionListener(new ButtonClickListener());
		  
//		innerPanel.add(Box.createRigidArea(new Dimension(0,50)));
//		innerPanel.add(backBtn);
//		innerPanel.add(refreshBtn);
//		innerPanel.add(hostBtn);
//		controlPanel.add(innerPanel, Component.CENTER_ALIGNMENT);
		  
		//Player player1 = new Player("darksteelknight", "ConnectFour");
		//Player player2 = new Player("desoron", "Checkers");
		//Player player3 = new Player("paulusm", "Battleship");
		
		//create the list and populate it with the string username elements
		//DefaultListModel<String> listModel = new DefaultListModel<String>();
		//listModel.addElement(player1.getUsername() + " - " + player1.getGameType());
		//listModel.addElement(player2.getUsername() + " - " + player2.getGameType());
		//listModel.addElement(player3.getUsername()  + " - " + player3.getGameType());
		
		//get playerInfo from client, display current players
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (String e : client.getPlayerInfo()){
			listModel.addElement(e);
		}
		
		//initialize the actual JList component that will appear in the frame
		usernameList = new JList(listModel);
		usernameList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		usernameList.setLayoutOrientation(JList.VERTICAL);
		usernameList.setVisibleRowCount(-1);
		listScroller = new JScrollPane(usernameList);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		listScroller.setBorder(BorderFactory.createCompoundBorder( 
            BorderFactory.createEmptyBorder(20, 20, 20, 20), border));
		listScroller.setPreferredSize(new Dimension(300, 400));
		
//		listPanel.add(usernameList);
		listPanel.add(listScroller, BorderLayout.CENTER);
		joinPanel.add(joinBtn);
		
		controlPanel.revalidate();
		joinPanel.revalidate();
		controlPanel.repaint();
		joinPanel.repaint();
		
		mainFrame.setVisible(true);  
}
		
	
	private class ButtonClickListener implements ActionListener{

	    public void actionPerformed(ActionEvent e) {
	         String command = e.getActionCommand();  
	         
	         if(command.equals( "Exit" ))  {
	        	 promptAndClose();
	         } else if(command.equals( "Login" )){
	        	 //login();
	         }
	    }
	}
		
}
