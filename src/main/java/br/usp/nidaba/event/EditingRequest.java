package br.usp.nidaba.event;

import java.io.Serializable;

public class EditingRequest implements Serializable{
	private static final long serialVersionUID = -3456479212041944088L;
	
	private boolean estaEditando;

	public EditingRequest(boolean estaEditando) {
		this.estaEditando = estaEditando;
	}

	public boolean isEstaEditando() {
		return estaEditando;
	}

	public void setEstaEditando(boolean estaEditando) {
		this.estaEditando = estaEditando;
	}
	
	

}
