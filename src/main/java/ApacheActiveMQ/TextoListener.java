package ApacheActiveMQ;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TextoListener implements MessageListener {
	
	private ArrayList<String> listaMensajes = new ArrayList<String>();

	public void onMessage(Message message) {
		
		TextMessage msg = null;
		
		try {
			
			if (message instanceof TextMessage) {
				msg = (TextMessage) message;
				System.out.println("Recibido asíncrono [" + msg.getText() + "]");
				listaMensajes.add(msg.getText());
			} else {
				System.err.println("El mensaje no es de tipo texto");
			}
			
		} catch (JMSException e) {
			System.err.println("JMSException en onMessage(): " + e.toString());
		} catch (Throwable t) {
			System.err.println("Exception en onMessage():" + t.getMessage());
		}
		
		
	}
	
	public ArrayList<String> traerMensajes(){
		return listaMensajes;
	}

}
