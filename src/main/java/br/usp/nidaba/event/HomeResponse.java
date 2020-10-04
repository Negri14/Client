package br.usp.nidaba.event;

import java.io.Serializable;
import java.util.List;

public class HomeResponse implements Serializable{
	private static final long serialVersionUID = 6950919213141892312L;
	
	private List<File> listOfFiles;
	 
	 public HomeResponse(List<File> files) {
		 this.listOfFiles = files;
	 }

	public List<File> getListOfFiles() {
		return listOfFiles;
	}

	public void setListOfFiles(List<File> listOfFiles) {
		this.listOfFiles = listOfFiles;
	}
}
