package br.usp.nidaba.event;

import java.io.Serializable;

public class UpdateFileRequest implements Serializable {
	private static final long serialVersionUID = 321042809513225445L;
	
	private Integer fileID;
	private String snapshot;
	
	public UpdateFileRequest(Integer fileID, String snapshot) {
		this.fileID = fileID;
		this.snapshot = snapshot;
	}

	public Integer getFileID() {
		return fileID;
	}

	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}
	
	
	
	

}
