package com.netboard.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommsBridge {
	
	public static <T> boolean writeMessage(Socket s, T msg){
		try {
			ObjectOutputStream objOut = new ObjectOutputStream(s.getOutputStream());
			objOut.writeObject(msg);
			return true;
		} catch (IOException e) { 
			e.printStackTrace(); 
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> T readMessage(Socket s){
		T someMsg = null;
		try {
			ObjectInputStream objIn = new ObjectInputStream(s.getInputStream());
			someMsg = (T) objIn.readObject();
		} 
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		return someMsg;
	}
}
