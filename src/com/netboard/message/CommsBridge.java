package com.netboard.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommsBridge {

	public static boolean writeMessage(Socket s, Object msgObj) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(msgObj);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Object readMessage(Socket s) {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(s.getInputStream());
			return in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
