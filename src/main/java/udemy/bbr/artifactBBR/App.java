package udemy.bbr.artifactBBR;

import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import ApacheActiveMQ.MessageCreator;
import ApacheActiveMQ.TextoListener;
import Enum.Continentes;
import Model.SolicitudOrdenes;

public class App {

	private static final String MENSAJE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<SolicitudOrdenes>\r\n"
			+ " 	<codproveedor>93830000</codproveedor>\r\n" + "  	<codcomprador>codcomprador</codcomprador>\r\n"
			+ "  	<nombreportal>nombreportal</nombreportal>\r\n"
			+ "  	<fecha_activacion>2019-07-01 00:00:00</fecha_activacion>\r\n"
			+ "  	<estadoSoa>RECIBIDO_OK</estadoSoa>\r\n" + "  	<documentos>\r\n" + "  		<documento>\r\n"
			+ "  			<id>8498255</id>\r\n" + "  		</documento>\r\n" + "  		 <documento>\r\n"
			+ "  			<id>10287156</id>\r\n" + "  		</documento>\r\n" + "  		 <documento>\r\n"
			+ "  			<id>10287155</id>\r\n" + "  		</documento>\r\n" + "  	</documentos>\r\n"
			+ "</SolicitudOrdenes>";

	public static void main(String[] args) throws JAXBException {

		System.out.println("********** Inicio ejecucción **********");

		MessageCreator messageCreator = new MessageCreator();

		for (int i = 0; i < 2; i++) {
			messageCreator.sendMessage(MENSAJE);
			messageCreator.readMessage();
		}

		// messageCreator.sendMessage(MENSAJE);
		// messageCreator.readMessage();

		ArrayList<String> listaObject = messageCreator.getListaMensajesMC();

		// Este cont es para dar nombres distintos a los archivos excel y no
		// sobreescribir al regenerar la misma instancia y sobreescribir el archivo
		// excel al llamarse igual.
		int cont = 0;

		for (String lista : listaObject) {
			try {

				// Marshalling : Objeto a XML
				// UnMarshalling : XML a objeto
				JAXBContext context2 = JAXBContext.newInstance(SolicitudOrdenes.class);
				Unmarshaller unmarshaller2 = context2.createUnmarshaller();
				// SolicitudOrdenes solicitudOrdenes = (SolicitudOrdenes)
				// unmarshaller2.unmarshal(new File("src/main/java/SolicitudOrdenes.xml"));
				SolicitudOrdenes solicitudOrdenes = (SolicitudOrdenes) unmarshaller2.unmarshal(new StringReader(lista));

				System.out.println("********** Datos como objeto Unmarshall **********");
				System.out.println("Código Proveedor : " + solicitudOrdenes.getCodproveedor());
				System.out.println("Código Comprador : " + solicitudOrdenes.getCodcomprador());
				System.out.println("Nombre Portal : " + solicitudOrdenes.getNombreportal());
				System.out.println("Fecha Activación: " + solicitudOrdenes.getFechaActivacion());
				System.out.println("Estado SOA: " + solicitudOrdenes.getEstadoSoa());
				System.out.println("Documentos: " + solicitudOrdenes.getDocumentos().getDocumento()[0].getId());

				// ########## Excel ##########
				HSSFWorkbook libro = new HSSFWorkbook();
				HSSFSheet hoja = libro.createSheet("Hoja 01 - Solicitud De Ordenes");
				// hoja.protectSheet("Ahola123");

				// ########## Titulos ##########
				HSSFRow filaTitulos = hoja.createRow(0);

				HSSFCell titulo1 = filaTitulos.createCell(0);
				titulo1.setCellValue("Código Proveedor");

				HSSFCell titulo2 = filaTitulos.createCell(1);
				;
				titulo2.setCellValue("Código Comprador");

				HSSFCell titulo3 = filaTitulos.createCell(2);
				titulo3.setCellValue("Nombre Portal");

				HSSFCell titulo4 = filaTitulos.createCell(3);
				titulo4.setCellValue("Fecha Activación");

				HSSFCell titulo5 = filaTitulos.createCell(4);
				titulo5.setCellValue("Estado SOA");

				HSSFCell titulo6 = filaTitulos.createCell(5);
				titulo6.setCellValue("Número de documentos");

				// ########## Centrado de texto ##########
				CellStyle cellStyle = libro.createCellStyle();
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				titulo1.setCellStyle(cellStyle);
				titulo2.setCellStyle(cellStyle);
				titulo3.setCellStyle(cellStyle);
				titulo4.setCellStyle(cellStyle);
				titulo5.setCellStyle(cellStyle);
				titulo6.setCellStyle(cellStyle);

				// ########## Datos ##########
				HSSFRow filaDatos = hoja.createRow(1);

				for (int i = 0; i < 6; i++) {

					HSSFCell cellCodProveedor = filaDatos.createCell(0);
					HSSFCell cellCodComprador = filaDatos.createCell(1);
					HSSFCell cellNombrePortal = filaDatos.createCell(2);
					HSSFCell cellFechaActivacion = filaDatos.createCell(3);
					HSSFCell cellEstadoSoa = filaDatos.createCell(4);
					HSSFCell cellIdDocumento = filaDatos.createCell(5);

					cellCodComprador.setCellValue(Double.parseDouble(solicitudOrdenes.getCodproveedor()));
					cellCodProveedor.setCellValue(solicitudOrdenes.getCodcomprador());
					cellNombrePortal.setCellValue(solicitudOrdenes.getNombreportal());
					cellFechaActivacion.setCellValue(solicitudOrdenes.getFechaActivacion());
					cellEstadoSoa.setCellValue(solicitudOrdenes.getEstadoSoa());
					cellIdDocumento.setCellValue(solicitudOrdenes.getDocumentos().getDocumento()[0].getId() + " - " + solicitudOrdenes.getDocumentos().getDocumento()[1].getId() + " - " + solicitudOrdenes.getDocumentos().getDocumento()[2].getId());

					// ########## Centrado de texto ##########
					cellCodComprador.setCellStyle(cellStyle);
					cellCodProveedor.setCellStyle(cellStyle);
					cellNombrePortal.setCellStyle(cellStyle);
					cellFechaActivacion.setCellStyle(cellStyle);
					cellEstadoSoa.setCellStyle(cellStyle);
					cellIdDocumento.setCellStyle(cellStyle);

					// ########## Ancho de columnas ##########
					hoja.autoSizeColumn(i);

				}

				try {

					cont = cont + 1;

					FileOutputStream elFichero = new FileOutputStream(
							"Excel_número_" + cont + "_Solicitud_Ordenes.xls");
					libro.write(elFichero);
					elFichero.close();
					System.out.println("Archivo Excel Generado");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error : " + e.getMessage());
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println("********** Fin ejecucción **********");

	}
}
