package br.usp.nidaba.event;

import java.io.Serializable;

public class LoginResponse implements Serializable {
	
	private static final long serialVersionUID = 8626944469889821464L;
	
	private LoginStatus loginStatus;
	
	public LoginResponse(LoginStatus loginStatus) {
		super();
		this.loginStatus = loginStatus;
	}

	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	
	
	

}
