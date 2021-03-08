package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SolicitudOrdenes")
public class SolicitudOrdenes {
	
    private String codproveedor;
    private String codcomprador;
    private String nombreportal;
    private String fechaActivacion;
    private String estadoSoa;
    private Documentos documentos;
    
    public SolicitudOrdenes() {
    	super();
    }
    
	public SolicitudOrdenes(String codproveedor, String codcomprador, String nombreportal , String fechaActivacion, String estadoSoa , Documentos documentos) {
		super();
		this.codproveedor = codproveedor;
		this.codcomprador = codcomprador;
		this.nombreportal = nombreportal;
		this.fechaActivacion = fechaActivacion;
		this.estadoSoa = estadoSoa;
		this.documentos = documentos;
		
	}

	public String getCodproveedor() {
		return codproveedor;
	}

	public void setCodproveedor(String codproveedor) {
		this.codproveedor = codproveedor;
	}

	public String getCodcomprador() {
		return codcomprador;
	}

	public void setCodcomprador(String codcomprador) {
		this.codcomprador = codcomprador;
	}

	public String getNombreportal() {
		return nombreportal;
	}

	public void setNombreportal(String nombreportal) {
		this.nombreportal = nombreportal;
	}

	@XmlElement(name="fecha_activacion")
	public String getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public String getEstadoSoa() {
		return estadoSoa;
	}

	public void setEstadoSoa(String estadoSoa) {
		this.estadoSoa = estadoSoa;
	}

	public Documentos getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Documentos documentos) {
		this.documentos = documentos;
	}


	
	
	
	

}
