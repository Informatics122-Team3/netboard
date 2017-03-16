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

import com.netboard.client.NetBoardClient;
import com.netboard.message.HostMessage;



public class HostGameMaker extends GUIMaker{
	JSplitPane splitPane;
	JPanel controlPanel, userPanel, innerPanel;
	JTextField serverTxt, nameTxt;
	JButton loginBtn, exitBtn;
	NetBoardClient client;
	final int TXTWIDTH = 16;
	
//	if you want to spawn the Login window, uncomment this main function
//	public static void main(String[] args) {
//		HostGameMaker hgm = new HostGameMaker();
//	hgm.prepareGUI();
//	hgm.show();
//}
	
	public HostGameMaker(NetBoardClient newClient){
		  mainFrame = new JFrame("NetBoard");
	      headerLabel = new JLabel("Select a Game to Host",JLabel.CENTER );
	      statusLabel = new JLabel(" ",JLabel.CENTER);

	      userPanel = new JPanel();
	      splitPane = new JSplitPane();
	      controlPanel = new JPanel();
	      innerPanel = new JPanel();
	      
	      this.client = newClient;
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
	innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
	userPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));
	}
	
	public void fillFrame(){
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.add(innerPanel);
	}

	public void show(){
			   
      controlPanel.removeAll();
      userPanel.removeAll();
      
      exitBtn = new JButton("Exit");
      exitBtn.setActionCommand("Exit");
      exitBtn.addActionListener(new ButtonClickListener());
      
      //innerPanel.add(Box.createRigidArea(new Dimension(5,0)));
      for(String s : client.getSupportedGames()){
    	  JButton btn = new JButton(s);
    	  btn.setActionCommand(s);
    	  btn.addActionListener(new ButtonClickListener());
    	  innerPanel.add(btn);
      }
      innerPanel.add(exitBtn);
      controlPanel.add(innerPanel, Component.CENTER_ALIGNMENT);
      controlPanel.revalidate();
      controlPanel.repaint();

      mainFrame.setVisible(true);  
	}
	
	private void sendHostMessage(String game){
		HostMessage host = new HostMessage(client.getIP(), client.getName(), game);
		client.writeMessage(host);
	}
	
	private class ButtonClickListener implements ActionListener{

	    public void actionPerformed(ActionEvent e) {
	         String command = e.getActionCommand();  
	         
	         if(command.equals( "Exit" ))  {
	        	 promptAndClose();
	         } else {
	        	 sendHostMessage(command);
	         }
	    }
	}
	
}
