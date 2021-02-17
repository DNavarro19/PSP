package com.restApp.web.model;

/**
 * La clase Modelo
 */
public class Modelo {

	/** El id del modelo. */
	private String id;

	/** El nombre del modelo. */
	private String nombre;

	/** El número de plazas del modelo. */
	private int numPlazas;

	/**
	 * Instancia un nuevo modelo vacío.
	 */
	public Modelo() {

	}

	/**
	 * Instancia un nuevo modelo con el id.
	 *
	 * @param id el id del modelo
	 */
	public Modelo(String id) {
		this.id = id;
	}

	/**
	 * Devuelve el id del modelo.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Da valor al id del modelo.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Devuelve el nombre del modelo.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Da valor al nombre del modelo.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el número de plazas del modelo.
	 *
	 * @return el número de plazas
	 */
	public int getNumPlazas() {
		return numPlazas;
	}

	/**
	 * Da valor al número de plazas del modelo.
	 *
	 * @param numPlazas el nuevo número de plazas
	 */
	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Compara el id de dos modelos.
	 *
	 * @param obj el modelo con el que comparar
	 * @return true, si los ids son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Modelo) obj).getId());
	}

}
