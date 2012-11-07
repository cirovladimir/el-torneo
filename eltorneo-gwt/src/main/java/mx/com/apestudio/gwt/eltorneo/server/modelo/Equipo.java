package mx.com.apestudio.gwt.eltorneo.server.modelo;

import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;

public class Equipo {

	@Id Long id;
	String nombre;
	Blob logo;
	
	public Equipo() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}
	
	public void setLogo(byte[] bytes){
		this.logo = new Blob(bytes);
	}
	
}
