package br.usp.nidaba.event;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {
	private static final long serialVersionUID = 145812707464104731L;
	
	private Integer id;
	private String name;
	private String owner;
	private Date creationDate;
	private Date modificationDate;
	private String content;
	private String[] allowedUsers;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getAllowedUsers() {
		return allowedUsers;
	}
	public void setAllowedUsers(String[] allowedUsers) {
		this.allowedUsers = allowedUsers;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
}
 