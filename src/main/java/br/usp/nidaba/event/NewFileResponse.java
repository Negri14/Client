package br.usp.nidaba.event;

import java.io.Serializable;

public class NewFileResponse implements Serializable{
	private static final long serialVersionUID = -4816070687280527089L;
	
	private File file;
	
	public NewFileResponse(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
