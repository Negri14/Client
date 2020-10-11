package br.usp.nidaba.event;

import java.io.Serializable;

public class UnshareResponse implements Serializable {
	private static final long serialVersionUID = 7982168394962000510L;
	
	private String username;
	private Integer fileID;
	
	public UnshareResponse(String username, Integer fileID) {
		this.username = username;
		this.fileID = fileID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getFileID() {
		return fileID;
	}

	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}
	
	
}
