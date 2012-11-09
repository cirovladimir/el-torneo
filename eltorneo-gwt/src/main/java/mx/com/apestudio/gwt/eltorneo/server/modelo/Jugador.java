/* Copyright (c) 2012, Ciro Vladimir Arreola Camacho. */
package mx.com.apestudio.gwt.eltorneo.server.modelo;

import java.util.Date;

import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;

/**
 * A class to represent the entity Player
 * @author cirovladimir
 *
 */
public class Jugador {

	@Id Long id;
	String nombre;
	String apellidos;
	Date fechaNacimiento;
	Float altura;
	Float peso;
	String email;
	Blob foto;
	
	public Jugador() {
		// TODO Auto-generated constructor stub
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Float getAltura() {
		return altura;
	}

	public void setAltura(Float altura) {
		this.altura = altura;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Blob getFoto() {
		return foto;
	}

	public void setFoto(Blob foto) {
		this.foto = foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = new Blob(foto);
	}
	
}
