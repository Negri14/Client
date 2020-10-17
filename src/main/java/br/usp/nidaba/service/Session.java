package br.usp.nidaba.service;

/*
 * Objeto responsável por armazenar os dados da sessão.
 */
public class Session {
	
	private String clientUsername;
	private Integer documentID;
	
	public String getClientUsername() {
		return clientUsername;
	}
	
	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}
	
	public Integer getDocumentID() {
		return documentID;
	}
	
	public void setDocumentID(Integer documentID) {
		this.documentID = documentID;
	}
	
	
}