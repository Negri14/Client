package br.usp.nidaba.event;

import java.io.Serializable;
import java.util.List;

public class EditorResponse implements Serializable{
	private static final long serialVersionUID = -6355205360345528588L;
	
	private File file;
	private String userdEditing;
	private List<Modificacao> modificacoes;
	
	public EditorResponse(File file,List<Modificacao> modificacoes) {
		this.file = file;
		this.modificacoes = modificacoes;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getUserdEditing() {
		return userdEditing;
	}

	public void setUserdEditing(String userdEditing) {
		this.userdEditing = userdEditing;
	}

	public List<Modificacao> getModificacoes() {
		return modificacoes;
	}

	public void setModificacoes(List<Modificacao> modificacoes) {
		this.modificacoes = modificacoes;
	}
	

	
}
