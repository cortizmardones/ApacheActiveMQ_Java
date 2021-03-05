package Model;

import java.util.ArrayList;

public class ListaPersonas {
	
	private ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
	
	public void agregarPersonas(Persona persona) {
		if(listaPersonas.contains(persona)) {
			System.out.println("La persona: '"+ persona.getNombre().concat(" ").concat(persona.getApellido()) +"' ya existe en los registros");
			return;
		}
		listaPersonas.add(persona);
	}
	
	public void eliminarPersona(Persona persona) {
		if(listaPersonas.contains(persona)) {
			listaPersonas.remove(persona);
		} else {
			System.out.println("La persona: '"+ persona.getNombre().concat(" ").concat(persona.getApellido()) +"' no existe en los registros , no es posible eliminar");
		}
		System.out.println();
	}
	
	public void listarPersonas() {
		System.out.println("Listado de personas: ");
		for(int i = 0; i < listaPersonas.size() ;i++) {
			System.out.println(listaPersonas.get(i).getId() + " " +listaPersonas.get(i).getNombre() + " " + listaPersonas.get(i).getApellido() + " " + listaPersonas.get(i).getEdad() + " " + listaPersonas.get(i).getEmail());
		}
		System.out.println();
	}
	
	public void actualizarPersona(int index , Persona persona) {
		listaPersonas.set(index, persona);
	}
	
	public boolean devolverString() {
		return true;
	}

}
