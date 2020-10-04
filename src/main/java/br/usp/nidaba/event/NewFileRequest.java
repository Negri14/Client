package br.usp.nidaba.event;

import java.io.Serializable;

public class NewFileRequest implements Serializable {
	private static final long serialVersionUID = 5180972868189902375L;
	
	private String name;
	private String owner;
	private String content;
	
	
	
	public NewFileRequest(String name, String owner, String content) {
		this.name = name;
		this.owner = owner;
		this.content = content;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}

