package br.usp.nidaba.event;

import java.io.Serializable;

public class DeleteRequest implements Serializable {
	private static final long serialVersionUID = 3878160625650260322L;

	private Integer fileID;

	public DeleteRequest(Integer fileID) {
		this.fileID = fileID;
	}

	public Integer getFileID() {
		return fileID;
	}

	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}	
	
}
