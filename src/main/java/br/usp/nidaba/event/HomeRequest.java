package br.usp.nidaba.event;

import java.io.Serializable;

public class HomeRequest implements Serializable {
	private static final long serialVersionUID = -6851152892928138088L;
	
	private String username;

	public HomeRequest(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
