package com.netboard.client.GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class LoginMaker extends GUIMaker{
	JSplitPane splitPane;
	JPanel controlPanel, userPanel;
	JTextField serverTxt, nameTxt;
	JButton loginBtn, exitBtn;
	final int TXTWIDTH = 16;
	
	public LoginMaker(){
		  mainFrame = new JFrame("NetBoard Login");
	      headerLabel = new JLabel("Select a Server & Sign In",JLabel.CENTER );
	      statusLabel = new JLabel(" ",JLabel.CENTER);
	      serverTxt = new JTextField("Server", TXTWIDTH);
	      nameTxt = new JTextField("Name", TXTWIDTH);
	      userPanel = new JPanel();
	      splitPane = new JSplitPane();
	      controlPanel = new JPanel();
	}
	
	public void initFrame(){
	  mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      mainFrame.setSize(STARTWIDTH,STARTHEIGHT);
      mainFrame.setLayout(new GridLayout(4, 1));
	}
	
	public void initComponents(){
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
      controlPanel.setLayout(new FlowLayout());
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
			   
      loginBtn = new JButton("Login");
      exitBtn = new JButton("Exit");

      controlPanel.removeAll();
      userPanel.removeAll();

      loginBtn.setActionCommand("Login");
      exitBtn.setActionCommand("Exit");

      loginBtn.addActionListener(new ButtonClickListener());
      exitBtn.addActionListener(new ButtonClickListener());
      
      try{

      }catch(Exception e){
    	  
      }
      
      controlPanel.add(serverTxt);
      controlPanel.add(nameTxt);
      controlPanel.add(loginBtn);
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
	        	 promptAndClose();
	         } else if( command.equals("Login")){
	        	 statusLabel.setText("You have logged in as: " + nameTxt.getText() + "\n On Server: " + serverTxt.getText());
	         }
	    }
	}
	
}