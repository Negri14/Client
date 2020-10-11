package br.usp.nidaba.event;

import java.io.Serializable;

public class RecoverPasswordRequest implements Serializable {
	private static final long serialVersionUID = 8157775023309067080L;
	
	String email;

	public RecoverPasswordRequest(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
