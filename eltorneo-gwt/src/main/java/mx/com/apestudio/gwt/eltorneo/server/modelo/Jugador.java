package mx.com.apestudio.gwt.eltorneo.server.modelo;

import java.util.Date;

import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;

public class Jugador {

	@Id Long id;
	String nombre;
	String apellidos;
	Date fechaNacimiento;
	Float altura;
	Float peso;
	String email;
	Blob foto;
}
