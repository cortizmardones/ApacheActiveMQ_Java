package Enum;

public enum Continentes {
	
	AFRICA(53),
	EUROPA(46),
	ASIA(44),
	AMERICA(34),
	OCEANIA(14);
	
	private int cantidadPaises;
	
	Continentes(int cantidadPaises) {
		this.cantidadPaises = cantidadPaises;
	}
	
	public int getCantidadPaises() {
		return this.cantidadPaises;
	}
	
	//No hay SET porque los atributos son de tipo FINAL y no deberian cambiar su valor.

}
