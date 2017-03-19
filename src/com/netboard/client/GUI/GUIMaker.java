package com.netboard.client.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.netboard.client.NetBoardClient;

public abstract class GUIMaker {
	
	static final int STARTWIDTH = 900;
	static final int STARTHEIGHT = 900;
	static final int STARTLABELWIDTH = 16;

	protected JFrame mainFrame;
	protected JLabel headerLabel;
	protected JLabel statusLabel;

	protected NetBoardClient client;
	abstract void initFrame();
	abstract void initComponents();  
	abstract void setWindowListener();
	abstract void initPanels();         
	abstract void fillFrame();
	abstract void show();
	
	public GUIMaker(NetBoardClient client) {
		this.client = client;
	}
	
	public final void prepareGUI(){
		initFrame();
		initComponents();  
		setWindowListener();
		initPanels();         
		fillFrame();
	}
	
	public void promptAndClose(){
	   
	    int output = JOptionPane.showConfirmDialog(mainFrame
       , "Are you sure you want to quit?"
	   ,"Exiting NetBoard"
	   ,JOptionPane.YES_NO_OPTION,
	   JOptionPane.INFORMATION_MESSAGE);

        if(output == JOptionPane.YES_OPTION){
        	try {
        		client.applyDisconnectMove();        		
        	} catch (Exception e) { }
        	System.exit(0);
        } else if(output == JOptionPane.NO_OPTION){
          
        }
	}
	         
}
