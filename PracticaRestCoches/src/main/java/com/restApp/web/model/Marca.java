package com.restApp.web.model;

import java.util.List;

public class Marca {

	private String id;
	private String nombre;
	private int anioCreacion;
	private List<Modelo> modelos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnioCreacion() {
		return anioCreacion;
	}

	public void setAnioCreacion(int añoCreacion) {
		this.anioCreacion = añoCreacion;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

}
