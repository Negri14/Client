package br.usp.nidaba.event;

import java.io.Serializable;

public class DeleteResponse implements Serializable {
	private static final long serialVersionUID = -726596409255602751L;
	
	private Integer fileID;

	public DeleteResponse(Integer fileID) {
		this.fileID = fileID;
	}

	public Integer getFileID() {
		return fileID;
	}

	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}

}
