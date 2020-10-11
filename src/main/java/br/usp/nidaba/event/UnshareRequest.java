package br.usp.nidaba.event;

import java.io.Serializable;

public class UnshareRequest implements Serializable {
	private static final long serialVersionUID = -6012212812455339154L;
	
	private int fileID;
	private String username;
	
	public UnshareRequest(Integer fileID2, String username2) {
		this.fileID = fileID2;
		this.username = username2;
	}
	public int getFileID() {
		return fileID;
	}
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}		