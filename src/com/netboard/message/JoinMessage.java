package com.netboard.message;


public class JoinMessage extends Message {
	protected String guestIP;
	protected String guestUsername;
	protected String hostUsername;
	protected JoinMessage(String guestIP, String guestUsername, String guestType) {
		messageType = "join";
		this.guestIP = guestIP;
		this.guestUsername = guestUsername;
		this.hostUsername = guestType;
	}
}
