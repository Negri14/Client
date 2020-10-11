package br.usp.nidaba.event;

import java.io.Serializable;

public class EditingResponse implements Serializable{
	private static final long serialVersionUID = -2644798925918487213L;
	
	private boolean estaEditando;
	private String username;

	public EditingResponse(boolean estaEditando, String username) {
		this.estaEditando = estaEditando;
		this.username = username;
	}

	public boolean isEstaEditando() {
		return estaEditando;
	}

	public void setEstaEditando(boolean estaEditando) {
		this.estaEditando = estaEditando;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
