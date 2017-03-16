package com.netboard.client.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class LoginMaker extends GUIMaker{
	JSplitPane splitPane;
	JPanel controlPanel, userPanel, innerPanel;
	JTextField serverTxt, nameTxt;
	JButton loginBtn, exitBtn;
	final int TXTWIDTH = 16;
	
//	if you want to spawn the Login window, uncomment this main function
	public static void main(String[] args) {
	LoginMaker lm = new LoginMaker();
	lm.prepareGUI();
	lm.show();
}
	
	public LoginMaker(){
		  mainFrame = new JFrame("NetBoard Login");
	      headerLabel = new JLabel("Select a Server & Sign In",JLabel.CENTER );
	      statusLabel = new JLabel(" ",JLabel.CENTER);
	      serverTxt = new JTextField("Server", TXTWIDTH);
	      nameTxt = new JTextField("Username", TXTWIDTH);
	      userPanel = new JPanel();
	      splitPane = new JSplitPane();
	      controlPanel = new JPanel();
	      
	      innerPanel = new JPanel();
	}
	
	public void initFrame(){
	  mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      //mainFrame.setSize(STARTWIDTH,STARTHEIGHT);
	  mainFrame.setSize(350, 500);
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
      controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
      userPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
      userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));
      
      
	}
	
	public void fillFrame(){

	mainFrame.add(headerLabel);
//	mainFrame.add(splitPane);
	mainFrame.add(userPanel);
	mainFrame.add(controlPanel);
	mainFrame.add(statusLabel);
	}

	public void show(){
			   
      loginBtn = new JButton("Enter Lobby");
      exitBtn = new JButton("Exit");

      controlPanel.removeAll();
      userPanel.removeAll();

      loginBtn.setActionCommand("Enter Lobby");
      exitBtn.setActionCommand("Exit");

      loginBtn.addActionListener(new ButtonClickListener());
      exitBtn.addActionListener(new ButtonClickListener());
      
      try{

      }catch(Exception e){
    	  
      }
      
      userPanel.add(serverTxt);
      userPanel.add(Box.createRigidArea(new Dimension(0,15)));
      userPanel.add(nameTxt);
      innerPanel.add(loginBtn);
      innerPanel.add(Box.createRigidArea(new Dimension(5,0)));
      innerPanel.add(exitBtn);
      controlPanel.add(innerPanel, Component.CENTER_ALIGNMENT);
      controlPanel.revalidate();
      userPanel.revalidate();
      controlPanel.repaint();
      userPanel.repaint();

      mainFrame.setVisible(true);  
	}
	
	private void loginError(){
		int output = JOptionPane.showConfirmDialog(mainFrame
			       ,"Login Error"
				   ,"Bad Server/Name"
				   ,JOptionPane.OK_CANCEL_OPTION
				   ,JOptionPane.INFORMATION_MESSAGE);

			        if(output == JOptionPane.OK_OPTION){
			           
			        } else if(output == JOptionPane.CANCEL_OPTION){
			        	promptAndClose();
			        }
	}
	
	private void login(){
//		if (client.connect(serverTxt.getText(), nameTxt.getText()))
//			client.showLobby();
//		else
//			loginError();
	}
	
	private class ButtonClickListener implements ActionListener{

	    public void actionPerformed(ActionEvent e) {
	         String command = e.getActionCommand();  
	         
	         if(command.equals( "Exit" ))  {
	        	 promptAndClose();
	         } else if(command.equals( "Enter Lobby" )){
	        	 login();
	         }
	    }
	}
	
}
