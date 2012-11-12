package mx.com.apestudio.gwt.eltorneo.server.model;

import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;

public class Team {

	@Id Long id;
	String name;
	Blob logo;
	
	public Team() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
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
