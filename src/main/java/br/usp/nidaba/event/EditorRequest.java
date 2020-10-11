package br.usp.nidaba.event;

import java.io.Serializable;

public class EditorRequest implements Serializable {
	private static final long serialVersionUID = 223212788273506778L;
	
	Integer fileID;

	public EditorRequest(Integer fileID) {
		this.fileID = fileID;
	}

	public Integer getFileID() {
		return fileID;
	}

	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}
	
	
}
