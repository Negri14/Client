package br.usp.nidaba.service;

import java.text.SimpleDateFormat;
import java.util.List;

import br.usp.nidaba.event.DeleteRequest;
import br.usp.nidaba.event.EditingRequest;
import br.usp.nidaba.event.EditorRequest;
import br.usp.nidaba.event.Event;
import br.usp.nidaba.event.EventType;
import br.usp.nidaba.event.File;
import br.usp.nidaba.event.HomeRequest;
import br.usp.nidaba.event.LoginRequest;
import br.usp.nidaba.event.NewFileRequest;
import br.usp.nidaba.event.RecoverPasswordRequest;
import br.usp.nidaba.event.RequestEditionRequest;
import br.usp.nidaba.event.ShareRequest;
import br.usp.nidaba.event.SignUpRequest;
import br.usp.nidaba.event.UnshareRequest;
import br.usp.nidaba.event.UpdateFileRequest;
import br.usp.nidaba.socket.Client;


/* 
 * 	Classe contém os métodos  responsáveis por criar o objeto EVENTO e enviar para o servidor via socket.
 */

public class EventService {
	
	private Client clientSocket;
	
	public EventService(Client clientSocket) {
		
		this.clientSocket = clientSocket;
		
	}

	public void enviarEventoEdicao(boolean estaEditando) {
		
		Event e = new Event(EventType.EDITING, new EditingRequest(estaEditando));
		clientSocket.sendEvent(e);
	}
	
	public void enviarUpdate(UpdateFileRequest updateFileRequest) {
		Event e = new Event(EventType.UPDATEFILE, updateFileRequest);
		clientSocket.sendEvent(e);
	}
	
	public void compartilharArquivo(String username, Integer fileID) {
		
		Event e = new Event(EventType.SHARE, new ShareRequest(fileID, username));
		clientSocket.sendEvent(e);
		
	}
	
	public void pararCompartilharArquivo(String username, Integer fileID) {
		
		Event e = new Event(EventType.UNSHARE, new UnshareRequest(fileID, username));
		clientSocket.sendEvent(e);
		
	}
	
	public void enviarEventoSolicitacaoEdicao(Integer fileID, String username) {
		
		Event e = new Event(EventType.REQUEST_EDITION, new RequestEditionRequest(fileID, username));
		clientSocket.sendEvent(e);
		
	}
	
	public void enviarEmailSenha(String email) {
		Event e = new Event(EventType.RECOVER_PASSWORD, new RecoverPasswordRequest(email));
		clientSocket.sendEvent(e);
	}
	
	public void removerArquivo(DeleteRequest deleteRequest) {
		Event e = new Event(EventType.DELETE, deleteRequest);
		clientSocket.sendEvent(e);
		
	}
	
	public void realizarCadastro(String usuario, String email, String senha) { 
		Event e = new Event(EventType.REGISTER, new SignUpRequest(usuario, email, senha));
		clientSocket.sendEvent(e);
	}
	
	public void realizarLogin(String usuario, String senha) { 
		
		Event e = new Event(EventType.LOGIN, new LoginRequest(usuario, senha));
		clientSocket.sendEvent(e);
		
	}
	
	public void obterArquivo(String name, String username, String date, List<File> listOfFiles, SimpleDateFormat formatador) {

		for ( File f : listOfFiles) { 
			
			if (f.getName().equalsIgnoreCase(name) && f.getOwner().equalsIgnoreCase(username) && formatador.format(f.getCreationDate()).equalsIgnoreCase(date)) {
				
				Event event = new Event(EventType.EDITOR, new EditorRequest(f.getId()));
				clientSocket.sendEvent(event);

				break;
			}
		}
	}
	
	
	public void criarNovoArquivoUpload(String conteudo, String nomeArquivo, String usuario) {
		NewFileRequest request = new NewFileRequest(nomeArquivo, usuario, conteudo);
		Event e = new Event(EventType.NEWFILE, request);
		clientSocket.sendEvent(e);
	}
	
	public void obterListaArquivos(String usuario) {
		HomeRequest homeRequest = new HomeRequest(usuario);
		Event e = new Event(EventType.HOME, homeRequest);
		clientSocket.sendEvent(e);
	}
	
}
