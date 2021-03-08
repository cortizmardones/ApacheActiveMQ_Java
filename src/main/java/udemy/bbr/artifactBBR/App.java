package udemy.bbr.artifactBBR;

import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import ApacheActiveMQ.MessageCreator;
import Model.SolicitudOrdenes;

public class App {

	private static final String MENSAJE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<SolicitudOrdenes>\r\n" + 
			" 	<codproveedor>93830000</codproveedor>\r\n" + 
			"  	<codcomprador>codcomprador</codcomprador>\r\n" + 
			"  	<nombreportal>nombreportal</nombreportal>\r\n" + 
			"  	<fecha_activacion>2019-07-01 00:00:00</fecha_activacion>\r\n" + 
			"  	<estadoSoa>RECIBIDO_OK</estadoSoa>\r\n" + 
			"  	<documentos>\r\n" + 
			"  		<documento>\r\n" + 
			"  			<id>8498255</id>\r\n" + 
			"  		</documento>\r\n" + 
			"  		 <documento>\r\n" + 
			"  			<id>10287156</id>\r\n" + 
			"  		</documento>\r\n" + 
			"  		 <documento>\r\n" + 
			"  			<id>10287155</id>\r\n" + 
			"  		</documento>\r\n" + 
			"  	</documentos>\r\n" + 
			"</SolicitudOrdenes>";

	public static void main(String[] args) throws JAXBException {
		
		System.out.println("********** Inicio ejecucción **********");

		MessageCreator messageCreator = new MessageCreator();

		// Simulo muchos mensajes
    	for(int i = 0 ; i < 15 ; i++) {
    		messageCreator.sendMessage(MENSAJE);
    	}

		//messageCreator.sendMessage(MENSAJE);
		messageCreator.readMessage();

		ArrayList<String> listaObject = messageCreator.traerMensajes();
		String mensajeCasteado = listaObject.get(0).toString();
		
		for(String lista: listaObject) {
			try {
				
				JAXBContext context2 = JAXBContext.newInstance(SolicitudOrdenes.class);
				Unmarshaller unmarshaller2 = context2.createUnmarshaller();
				//SolicitudOrdenes solicitudOrdenes = (SolicitudOrdenes) unmarshaller2.unmarshal(new File("src/main/java/SolicitudOrdenes.xml"));
				SolicitudOrdenes solicitudOrdenes = (SolicitudOrdenes) unmarshaller2.unmarshal(new StringReader(lista));
				
				System.out.println("********** Datos como objeto Unmarshall **********");
				
				System.out.println("Código Comprador (Proveedor): " + solicitudOrdenes.getCodcomprador());
				System.out.println("Código Proveedor (Retail): " + solicitudOrdenes.getCodproveedor());
				System.out.println("Nombre Portal : " + solicitudOrdenes.getNombreportal());
				System.out.println("Fecha Activación: " + solicitudOrdenes.getFechaActivacion());
				System.out.println("Estado SOA: " + solicitudOrdenes.getEstadoSoa());
				System.out.println("Documentos: " + solicitudOrdenes.getDocumentos().getDocumento()[0].getId());

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}



		System.out.println("********** Fin ejecucción **********");

	}
}
