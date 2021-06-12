package com.bank.runner.application.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametria database table.
 * 
 */
@Entity
@NamedQuery(name="Parametria.findAll", query="SELECT p FROM Parametria p")
public class Parametria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_parametria")
	private int idParametria;

	private String seccion;

	private String subseccion;

	private String url;

	private String valor;

	public Parametria() {
	}

	public int getIdParametria() {
		return this.idParametria;
	}

	public void setIdParametria(int idParametria) {
		this.idParametria = idParametria;
	}

	public String getSeccion() {
		return this.seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getSubseccion() {
		return this.subseccion;
	}

	public void setSubseccion(String subseccion) {
		this.subseccion = subseccion;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}