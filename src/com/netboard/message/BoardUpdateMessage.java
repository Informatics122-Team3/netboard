package com.netboard.message;

import java.util.ArrayList;

import com.google.gson.Gson;

public class BoardUpdateMessage extends Message {
	ArrayList<ArrayList<String>> l = new ArrayList<ArrayList<String>>();
	protected BoardUpdateMessage() {
		messageType = "boardupdate";
		ArrayList<String> inner = new ArrayList<String>();
		inner.add("inner value");
		l.add(inner);
	}
	protected String serialize() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}