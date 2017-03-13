package com.netboard.client.GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class LobbyMaker extends GUIMaker{

	JSplitPane splitPane;
	JPanel controlPanel, userPanel;
	JTextField serverTxt, nameTxt;
	JButton exitBtn;
	
	public LobbyMaker() {
		// TODO Auto-generated constructor stub
	}
	
	public void initFrame(){
		  mainFrame = new JFrame("NetBoard Lobby");
		  mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	      mainFrame.setSize(STARTWIDTH,STARTHEIGHT);
	      mainFrame.setLayout(new GridLayout(2, 2));
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
	      JSplitPane splitPane = new JSplitPane();
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());
	      userPanel = new JPanel();
	      userPanel.setLayout(new FlowLayout());
	      
		  splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  
	      splitPane.setDividerLocation(200);                    
		  splitPane.setTopComponent(userPanel);                  
		  splitPane.setBottomComponent(controlPanel);          
		}
		
		public void fillFrame(){
	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(userPanel);
	      mainFrame.add(statusLabel);
		}

		public void show(){
				   
	      JButton loginBtn = new JButton("Sign In");
	      JButton exitBtn = new JButton("Exit");

	      controlPanel.removeAll();
	      userPanel.removeAll();
	      //userPanel.removeAll();

	      loginBtn.setActionCommand("Create Parent");
	      exitBtn.setActionCommand("Create Child");

	      loginBtn.addActionListener(new ButtonClickListener());
	      exitBtn.addActionListener(new ButtonClickListener());
	      
	      try{

	      }catch(Exception e){
	    	  
	      }
	      
	      controlPanel.add(serverTxt);
	      controlPanel.add(nameTxt);
	      controlPanel.add(exitBtn);    

	      controlPanel.revalidate();
	      userPanel.revalidate();
	      controlPanel.repaint();
	      userPanel.repaint();

	      mainFrame.setVisible(true);  
		}
		
		private class ButtonClickListener implements ActionListener{

		    public void actionPerformed(ActionEvent e) {
		         String command = e.getActionCommand();  
		         
		         if( command.equals( "Exit" ))  {
		        	 System.exit(0);
		         }
		    }
		}
		
}
