package com.restApp.web.model;

/**
 * La clase Motor.
 */
public class Motor {

	/** El id del motor */
	private String id;

	/** Los caballos del motor. */
	private int cv;

	/** El tipo de combustible del motor. */
	private String combustible;

	/**
	 * Instancia un nuevo motor vacío.
	 */
	public Motor() {
	}

	/**
	 * Instancia un nuevo motor con el id.
	 *
	 * @param id el id del motor
	 */
	public Motor(String id) {
		this.id = id;
	}

	/**
	 * Devuelve el id del motor.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Da valor al id del motor.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Devuelve los caballos del motor.
	 *
	 * @return los caballos
	 */
	public int getCv() {
		return cv;
	}

	/**
	 * Da valor a los caballos del motor.
	 *
	 * @param cv los nuevos caballos
	 */
	public void setCv(int cv) {
		this.cv = cv;
	}

	/**
	 * Devuelve el tipo de combustible del motor.
	 *
	 * @return el tipo combustible
	 */
	public String getCombustible() {
		return combustible;
	}

	/**
	 * Da valor al tipo de combustible del motor.
	 *
	 * @param combustible el nuevo tipo de combustible
	 */
	public void setCombustible(String combustible) {
		this.combustible = combustible;
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
	 * Compara el id de dos motores.
	 *
	 * @param obj el motor con el que comparar
	 * @return true, si los ids son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Motor) obj).getId());
	}

}
