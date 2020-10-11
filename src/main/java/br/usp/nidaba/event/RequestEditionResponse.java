package br.usp.nidaba.event;

import java.io.Serializable;

public class RequestEditionResponse implements Serializable {
	private static final long serialVersionUID = -2425222777381985048L;
	
	private String username;

	public RequestEditionResponse(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
