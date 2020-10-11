package br.usp.nidaba.event;

import java.io.Serializable;

public class ShareRequest implements Serializable{
	private static final long serialVersionUID = -3415314305689235063L;
	
	private int fileID;
	private String username;
	
	public ShareRequest(int fileID, String username) {
		this.fileID = fileID;
		this.username = username;
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
