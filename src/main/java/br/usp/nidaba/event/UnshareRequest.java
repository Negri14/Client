package br.usp.nidaba.event;

public class UnshareRequest {
	
	private int fileID;
	private String username;
	
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