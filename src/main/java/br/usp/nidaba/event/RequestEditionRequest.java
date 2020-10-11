package br.usp.nidaba.event;

import java.io.Serializable;

public class RequestEditionRequest implements Serializable {
	private static final long serialVersionUID = 8030442666237705030L;
	
	private Integer fileID;
	private String username;
	
	public RequestEditionRequest(Integer fileID, String username) {
		this.fileID = fileID;
		this.username = username;
	}

	public Integer getFileID() {
		return fileID;
	}

	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
