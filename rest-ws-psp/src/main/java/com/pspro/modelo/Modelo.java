package com.pspro.modelo;

import java.util.List;

public class Modelo {
	
	private String id;
	private int numPlazas;
	private List<Motor> motores;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumPlazas() {
		return numPlazas;
	}
	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}
	public List<Motor> getMotores() {
		return motores;
	}
	public void setMotores(List<Motor> motores) {
		this.motores = motores;
	}

	
}
