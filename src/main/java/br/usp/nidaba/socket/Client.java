package br.usp.nidaba.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import br.usp.nidaba.event.Event;
import br.usp.nidaba.window.NidabaApplicationWindow;

public class Client {
	
    final static int ServerPort = 1234; 
    
    private ObjectOutputStream output;        
    private ObjectInputStream input;
    
    private  Socket socket; 
    private Thread readMessage;
    
    private NidabaApplicationWindow applicationWindow; 
    
    boolean shutDown = false;
    
	public Client(NidabaApplicationWindow nidabaApplicationWindow) {
		
		applicationWindow = nidabaApplicationWindow;
		
	}
	
	/*

	new Socket(ip, porta) -> Cria um stream socket e conecta ao host e porta passados como parâmetros
	
	Cria um object output stream e um object input stream para o socket. 

	Criar uma thread para ler objetos enviados pelo servidor
	
	Envia o objeto recebido pelo servidor (evento) para o método recebeEvento da classe ApplicationWindow, onde o evento é interprado. 
	 
	Se ocorrer algum problema na conexão socket, a conexão é fechada. 
	 

	*/
	public void initializeSocket() throws IOException {
		
        InetAddress ip = InetAddress.getByName("localhost"); 

        socket = new Socket(ip, ServerPort); 
        
        output = new ObjectOutputStream(socket.getOutputStream());        
        input = new ObjectInputStream(socket.getInputStream());
        
        readMessage = new Thread(new Runnable()  
        
        { 
            @Override
            public void run() { 
  
                while (!shutDown) { 
                    try { 

	                    Event eventReceived = (Event) input.readObject(); 
                        applicationWindow.recebeEventos(eventReceived);
                        
                    } catch (Exception  e) { 
                    	
                    	closeSocketConnection();
                        
                    } 
                } 
            } 
        }); 
  
        readMessage.start(); 

	}
	
	/*

	 Envia objetos (eventos) para o servidor. Os eventos são criados durante a interação do usuário com o sistema. 

	*/
	public void sendEvent(Event event) {
		
		try {
			
			output.writeObject(event);
			
		} catch (IOException e) { 
			
			e.printStackTrace();
			
		}
		
	}
	
	/*

		Fecha a conexão socket

	*/
	public void closeSocketConnection() {
		try {
			socket.close();
			shutDown = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
