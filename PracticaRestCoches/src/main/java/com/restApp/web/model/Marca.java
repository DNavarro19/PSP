package com.restApp.web.model;

/**
 * La clase Marca.
 */
public class Marca {

	/** El id de la marca. */
	private String id;

	/** El nombre de la marca. */
	private String nombre;

	/** El año de creación. */
	private int anioCreacion;

	/**
	 * Devuelve el id de la marca.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Da valor al id de la marca.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Devuelve el nombre de la marca.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Da valor al nombre de la marca.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el año de creacion de la marca.
	 *
	 * @return el año de creacion
	 */
	public int getAnioCreacion() {
		return anioCreacion;
	}

	/**
	 * Da valor al año de creación de la marca.
	 *
	 * @param añoCreacion el nuevo año de creación
	 */
	public void setAnioCreacion(int añoCreacion) {
		this.anioCreacion = añoCreacion;
	}

}
