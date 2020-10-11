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
                    	
                    	try {
							socket.close();
							shutDown = true;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        e.printStackTrace(); 
                    } 
                } 
            } 
        }); 
  
        readMessage.start(); 

	}
	
	
	public void sendEvent(Event event) {
		
		try {
			
			output.writeObject(event);
			
		} catch (IOException e) { 
			
			e.printStackTrace();
			
		}
		
	}
	
	public void closeSocketConnection() {
		try {
			socket.close();
			shutDown = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
