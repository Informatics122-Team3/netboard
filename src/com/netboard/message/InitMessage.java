package com.netboard.message;

public class InitMessage extends Message {

	private String username;
	private boolean validUsername;
	
	public InitMessage(String username) {
		super("init");
		this.username = username;
		this.validUsername = true;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public boolean isValidUsername() {
		return this.validUsername;
	}
	
	public void setInvalidUsername() {
		this.validUsername = false;
	}

}
