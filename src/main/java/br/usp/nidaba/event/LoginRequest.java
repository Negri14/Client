package br.usp.nidaba.event;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class LoginRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8525752447250988070L;
	private String username;
	private String password;
	
	
	
	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
