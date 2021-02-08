package com.pspro.modelo;

import java.util.List;

public class Marca {
	
	private String id;
	private String nombre;
	private int añoCreacion;
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
	public int getAñoCreacion() {
		return añoCreacion;
	}
	public void setAñoCreacion(int añoCreacion) {
		this.añoCreacion = añoCreacion;
	}
	public List<Modelo> getModelos() {
		return modelos;
	}
	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	
}
