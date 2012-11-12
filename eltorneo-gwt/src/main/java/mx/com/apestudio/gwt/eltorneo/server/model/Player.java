/* Copyright (c) 2012, Ciro Vladimir Arreola Camacho. */
package mx.com.apestudio.gwt.eltorneo.server.model;

import java.util.Date;

import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;

/**
 * A class to represent the entity Player
 * @author cirovladimir
 *
 */
public class Player {

	@Id Long id;
	String name;
	String lastname;
	Date birthDate;
	Float height;
	Float weight;
	String email;
	Blob picture;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String apellidos) {
		this.lastname = apellidos;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date fechaNacimiento) {
		this.birthDate = fechaNacimiento;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float altura) {
		this.height = altura;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float peso) {
		this.weight = peso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob foto) {
		this.picture = foto;
	}
	
	public void setFoto(byte[] foto) {
		this.picture = new Blob(foto);
	}
	
}
