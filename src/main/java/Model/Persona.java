package Model;

import java.io.Serializable;

public class Persona implements Serializable {

	private int id;
	private String nombre;
	private String apellido;
	private int edad;
	private String email;

	public Persona() {

	}

	public Persona(int id, String nombre, String apellido, int edad, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String devolverMayor(int[] array) {
		if (array[0] > array[1]) {
			return "Número mayor: " + String.valueOf(array[0]);
		} else if (array[0] == array[1]) {
			return "Los números son iguales , no hay diferencia de numeros";
		} else {
			return "Número mayor: " + String.valueOf(array[1]);
		}
	}

	public String devolverMenor(int[] array) {
		if (array[0] > array[1]) {
			return "Número menor: " + String.valueOf(array[1]);
		} else if (array[0] == array[1]) {
			return "Los números son iguales , no hay diferencia de numeros";
		} else {
			return "Número menor: " + String.valueOf(array[0]);
		}
	}

}
