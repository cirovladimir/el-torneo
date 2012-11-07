package mx.com.apestudio.gwt.eltorneo.server.modelo;

import java.util.ArrayList;
import java.util.List;

public class Modulo {

	Long id;
	String nombre;
	Long idPadre;
	
	public Modulo(Long id, String nombre, Long idPadre) {
		this.id = id;
		this.nombre = nombre;
		this.idPadre = idPadre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}
	
	public static List<Modulo> getModulos(){
		List<Modulo> modulos = new ArrayList<Modulo>();
		modulos.add(new Modulo(1L, "RegistroEquipo", null));
		return modulos;
	}
	
}
