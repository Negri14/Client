package br.usp.nidaba.event;

import java.io.Serializable;

public class Event implements Serializable {
	private static final long serialVersionUID = 10L;
	
	private EventType eventType;
	
	private Object content;

	public Event(EventType type, Object content) {
		this.eventType = type;
		this.content = content;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	
}


/*
 * LOGIN PERMITIDO : TYPE <HOME>, DOCID < NULL >, DOCUMENTS < NULL >, DOCUMENT < NULL > | TYPE <HOME>, DOCID < NULL >, DOCUMENTS < FILL >, DOCUMENT < NULL > 
 * ENTRAR EDITOR   : TYPE <JOINED>, DOCID < FILL >, DOCUMENTS < NULL >, DOCUMENT < NULL > | TYPE <HOME>, DOCID < NULL >, DOCUMENTS < NULL >, DOCUMENT < FILL > 
 * EDITOU : TYPE <EDITED>, DOCID < FILL >, DOCUMENTS < NULL >, DOCUMENT < NULL > | TYPE <HOME>, DOCID < NULL >, DOCUMENTS < NULL >, DOCUMENT < FILL > 
 * 
 * 
 * 
 * DatagramSocket
 */


/*
  EVENT	: ENUM
  OBJECT: GENERIC
  
  
  EVENT: LOGINREQUEST	EVENT: LOGINRESPONSE
 	- USERNAME   →			- STATUS : ALLOWED | NOT ALLOWED
 	- PASSWORD
 	
  EVENT: HOMEREQUEST	EVENT: HOMERESPONSE
   							- FILES : NAME | ID
  
  EVENT: EDITORREQUEST		EVENT: EDITORRESPONSE
   - DOCID				→ 		- DOCUMENT			→ SEND EVENT TO OTHER USERS EDITING THE DOCUMENT(CONNECTED)
   
   EVENT: NEWREQUEST		EVENT: NEWRESPONSE
   	- TEXT				→ 		- DOCUMENT
   	
   
   EVENT SHARE/STOPSHARING
   	-USERNAME OR LOGIN
   	
   	EVENT DELETE
   	- DOCID
   	
   	
  	
  EVENT: SHARE, DELETE, STOP SHARING, NEW/UPLOAD, 
  
  
  
  EXPORT {} OUT OF SCOPE
  
  
  
 */


/*
 * CHAMA SOCKET -> SE VOLTAR ALLOWED > TELA DE HOME > 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
