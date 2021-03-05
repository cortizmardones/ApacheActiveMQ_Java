package udemy.bbr.artifactBBR;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.activemq.ActiveMQConnection;

import ApacheActiveMQ.MessageCreator;
import Model.ListaPersonas;
import Model.Persona;

public class App {
	
	private static final String MENSAJE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<SolicitudOrdenes xmlns=\"http://www.bbr.cl/SolicitudOrdenes\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.bbr.cl/SolicitudOrdenes SolicitudOrdenes.xsd \">\r\n" + 
			" 	<codproveedor>93830000</codproveedor>\r\n" + 
			"  	<codcomprador>codcomprador</codcomprador>\r\n" + 
			"  	<nombreportal>nombreportal</nombreportal>\r\n" + 
			"  	<fecha_activacion>2019-07-01 00:00:00</fecha_activacion>\r\n" + 
			"  	<estadoSoa>RECIBIDO_OK</estadoSoa>\r\n" + 
			"  	<documentos>\r\n" + 
			"  		<documento>\r\n" + 
			"  			<id>10287149</id>\r\n" + 
			"  		</documento>\r\n" + 
			"  		 <documento>\r\n" + 
			"  			<id>10287156</id>\r\n" + 
			"  		</documento>\r\n" + 
			"  		 <documento>\r\n" + 
			"  			<id>10287155</id>\r\n" + 
			"  		</documento>\r\n" + 
			"  	</documentos>\r\n" + 
			"</SolicitudOrdenes>";
	
    public static void main( String[] args ){
    	
    	//Generación de objetos
    	Persona persona1 = new Persona(1,"Edward","Tejeda",33,"eteja@gmail.com");
    	Persona persona2 = new Persona(2,"Carlos","Ortiz",32,"cortizmardones@gmail.com");
       	Persona persona3 = new Persona(3,"Camila","Sánchez",27,"csanchéz@bbr.cl");
    	Persona persona4 = new Persona(4,"Esteban","Bustos",29,"ebustos@outlook.es");
    	Persona persona5 = new Persona(5,"Lenon","Araya",32,"laraya@jjcc.cl");
       	Persona persona6 = new Persona(6,"Sebastián","Matamala",34,"smatamala@outlook.com");
       	Persona persona7 = new Persona(7,"Javier","Rodriguez",42,"prueba@email.com");
    	
    	
    	//Uso de metodos personalizados
       	ListaPersonas lista = new ListaPersonas();
       	lista.agregarPersonas(persona1);
    	lista.agregarPersonas(persona2);
    	lista.agregarPersonas(persona3);
    	lista.agregarPersonas(persona4);
    	lista.agregarPersonas(persona5);
    	lista.agregarPersonas(persona6);
    	lista.listarPersonas();
    	lista.eliminarPersona(persona7);
    	
    	
    	//ArrayList
    	ArrayList<Persona> arrayListPersonas = new ArrayList<Persona>();
    	arrayListPersonas.add(persona1);
    	arrayListPersonas.add(persona2);
    	arrayListPersonas.add(persona3);
    	
    	for(Persona alias : arrayListPersonas) {
    		if(alias.getNombre() != null) {
    			System.out.println("ArrayList: " + alias.getNombre() + " " +  alias.getApellido() + " " +  alias.getEdad() + " " +  alias.getEmail());
    		}
    	}
    	
    	//HashMap
    	HashMap<Integer,Persona> mapa = new HashMap<Integer, Persona>();
    	mapa.put(persona1.getId(), persona1);
    	mapa.put(persona2.getId(), persona2);
    	mapa.put(persona3.getId(), persona3);
    	
    	for (HashMap.Entry<Integer,Persona> entry : mapa.entrySet()) {
    	    System.out.println("HashMap: " + entry.getKey() + " " + entry.getValue().getNombre() + " " + entry.getValue().getApellido()  + " " + entry.getValue().getEdad() + " " + entry.getValue().getEmail());
    	}
    	
    	//Array
    	int[] array = new int[2];
    	array[0] = 22370;
    	array[1] = 24455;
    	System.out.println(persona1.devolverMayor(array));
    	System.out.println(persona1.devolverMenor(array));
    	
    	
    	MessageCreator messageCreator = new MessageCreator();
    	
    	for(int i = 0 ; i < 200 ; i++) {
    		messageCreator.sendMessage(MENSAJE);
    	}
    	
    	//messageCreator.sendMessage(MENSAJE);
    	messageCreator.readMessage();
    	
    	System.out.println("Fin ejecucción");
    	
    }
}
