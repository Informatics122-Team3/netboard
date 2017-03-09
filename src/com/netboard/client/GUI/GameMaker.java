import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMaker {

	public GameMaker() {
		// TODO Auto-generated constructor stub
	}
	
	public void show(){
		
	}

	public void prepareGUI() {
		// TODO Auto-generated method stub
		
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
