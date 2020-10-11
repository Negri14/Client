package br.usp.nidaba.event;

import java.io.Serializable;
import java.util.Date;

public class Modificacao implements Serializable{
	private static final long serialVersionUID = 935364007886773237L;
	
	private Integer fileID;
	private Date dataModificacao;
	private String usuario;
	private String snapShot;
	
	public Modificacao(Integer fileID, Date dataModificacao, String usuario, String snapShot) {
		this.fileID = fileID;
		this.dataModificacao = dataModificacao;
		this.usuario = usuario;
		this.snapShot = snapShot;
	}

	public Date getDataMoficacao() {
		return dataModificacao;
	}
	
	public void setDataMoficacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSnapShot() {
		return snapShot;
	}
	
	public void setSnapShot(String snapShot) {
		this.snapShot = snapShot;
	}

	public Integer getFileID() {
		return fileID;
	}

	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}
	
}


