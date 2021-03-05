package ApacheActiveMQ;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageCreator{

	private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final String USER = "admin";
	private static final String PASSWORD = "admin";
	private static final String MY_QUEUE = "QL_MESSAGE_JAVA";
	private static final boolean TRANSACTED_SESSION = false;

	public void sendMessage(String mensaje) {
		try {
			// Se crea la coneción para empezar la mensajeria
			ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, URL);
			Connection connection = activeMQConnectionFactory.createConnection();
			connection.start();

			// Se crea la session
			Session session = connection.createSession(TRANSACTED_SESSION, Session.AUTO_ACKNOWLEDGE);

			// Se define la cola
			Destination destination = session.createQueue(MY_QUEUE);
			// Productor del mensaje
			MessageProducer messageProducer = session.createProducer(destination);

			// Genero el mensaje (objeto) a enviar
//			ObjectMessage objectMessage = session.createObjectMessage(persona1);
//			messageProducer.send(objectMessage);

			// Genero el mensaje (texto) a enviar
			TextMessage textMessage = session.createTextMessage(mensaje);
			
			//Agregandole propiedades manualmente
			textMessage.setBooleanProperty("VeV",false);
			textMessage.setIntProperty("Cantidad", 55);
			textMessage.setDoubleProperty("Precio", 100.0);
			
			//Enviando el mensaje
			messageProducer.send(textMessage);
			//session.commit();
			session.close();

		} catch (JMSException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void readMessage() {
		try {
			// Se crea la coneción para empezar la mensajeria
			ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, URL);
			Connection connection = activeMQConnectionFactory.createConnection();
			connection.start();

			// Se crea la session
			Session session = connection.createSession(TRANSACTED_SESSION, Session.AUTO_ACKNOWLEDGE);

			// Se define la cola
			Destination destination = session.createQueue(MY_QUEUE);

			// Consumidor del mensaje
			MessageConsumer messageConsumer = session.createConsumer(destination);
			
			//Forma asincrona
			TextoListener listener = new TextoListener();
			messageConsumer.setMessageListener(listener);
		    connection.start();
			
//			Forma Sincrona			
//			TextMessage message = (TextMessage) messageConsumer.receive();
//			session.close();
//		    System.out.println(message.getText());
			
		} catch (JMSException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
