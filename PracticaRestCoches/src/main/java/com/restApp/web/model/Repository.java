package com.restApp.web.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * La clase Repository.
 */
@RestController
public class Repository {

	/** El repositorio de marcas. */
	static Map<String, Marca> marcaRepo = new HashMap<>();

	/** El repositorio de modelos. */
	static Map<String, ArrayList<Modelo>> modeloRepo = new HashMap<>();

	/** El repositorio de motores. */
	static Map<String, ArrayList<Motor>> motorRepo = new HashMap<>();

	/**
	 * Instancia un nuevo repositorio.
	 */
	public Repository() {
		initData();
	}

	/**
	 * Inicia los datos del repositorio.
	 */
	public void initData() {
		ArrayList<Modelo> mods = new ArrayList<>();
		ArrayList<Motor> motores = new ArrayList<>();
		Motor motor = new Motor();
		motor.setId("1");
		motor.setCv(65);
		motor.setCombustible("Diésel");
		motores.add(motor);

		motor = new Motor();
		motor.setId("2");
		motor.setCv(65);
		motor.setCombustible("Gasolina");
		motores.add(motor);

		motor = new Motor();
		motor.setId("3");
		motor.setCv(90);
		motor.setCombustible("Diésel");
		motores.add(motor);

		motor = new Motor();
		motor.setId("4");
		motor.setCv(90);
		motor.setCombustible("Gasolina");
		motores.add(motor);

		Marca marca = new Marca();
		marca.setId("1");
		marca.setNombre("Ford");
		marca.setAnioCreacion(1903);

		Modelo modelo = new Modelo();
		modelo.setId("1");
		modelo.setNombre("Focus");
		modelo.setNumPlazas(5);
		motorRepo.put(modelo.getNombre(), motores);
		mods.add(modelo);

		modelo = new Modelo();
		modelo.setId("2");
		modelo.setNombre("Fiesta");
		modelo.setNumPlazas(4);
		motorRepo.put(modelo.getNombre(), motores);
		mods.add(modelo);
		modeloRepo.put(marca.getId(), mods);
		marcaRepo.put(marca.getId(), marca);

		mods = new ArrayList<>();

		marca = new Marca();
		marca.setId("2");
		marca.setNombre("Renault");
		marca.setAnioCreacion(1898);

		modelo = new Modelo();
		modelo.setId("1");
		modelo.setNombre("Clio");
		modelo.setNumPlazas(5);
		motorRepo.put(modelo.getNombre(), motores);
		mods.add(modelo);

		modelo = new Modelo();
		modelo.setId("2");
		modelo.setNombre("Megane");
		modelo.setNumPlazas(5);
		motorRepo.put(modelo.getNombre(), motores);
		mods.add(modelo);
		modeloRepo.put(marca.getId(), mods);
		marcaRepo.put(marca.getId(), marca);

		mods = new ArrayList<>();

		marca = new Marca();
		marca.setId("3");
		marca.setNombre("Dacia");
		marca.setAnioCreacion(1966);

		modelo = new Modelo();
		modelo.setId("1");
		modelo.setNombre("Sandero");
		modelo.setNumPlazas(4);
		motorRepo.put(modelo.getNombre(), motores);
		mods.add(modelo);

		modelo = new Modelo();
		modelo.setId("2");
		modelo.setNombre("Duster");
		modelo.setNumPlazas(5);
		motorRepo.put(modelo.getNombre(), motores);
		mods.add(modelo);
		modeloRepo.put(marca.getId(), mods);
		marcaRepo.put(marca.getId(), marca);
	}

	/**
	 * Devuelve todas las marcas del repositorio.
	 *
	 * @return las marcas
	 */
	public Collection<Marca> getMarcas() {
		if (marcaRepo.isEmpty()) {
			return Collections.emptyList();
		}
		return marcaRepo.values();
	}

	/**
	 * Devuelve una marca específica del repositorio.
	 *
	 * @param id el id de la marca
	 * @return la marca
	 */
	public Marca getMarca(String id) {
		return marcaRepo.get(id);
	}

	/**
	 * Crea una nueva marca en el repositorio.
	 *
	 * @param id    el id de la marca
	 * @param marca la marca
	 */
	public void putMarca(String id, Marca marca) {
		marcaRepo.put(id, marca);
	}

	/**
	 * Elimina una marca del repositorio.
	 *
	 * @param id el id de la marca
	 */
	public void removeMarca(String id) {
		marcaRepo.remove(id);
		for (Modelo modelo : modeloRepo.get(id)) {
			motorRepo.remove(modelo.getNombre());
		}
		modeloRepo.remove(id);
	}

	/**
	 * Devuelve todos los modelos de una marca.
	 *
	 * @param id el id de la marca
	 * @return los modelos
	 */
	public Collection<Modelo> getModelos(String id) {
		if (modeloRepo.containsKey(id)) {
			return modeloRepo.get(id);
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Devuelve un modelo específico de una marca.
	 *
	 * @param idMarca  el id de la marca a la que pertenece
	 * @param idModelo el id del modelo
	 * @return el modelo
	 */
	public Modelo getModelo(String idMarca, String idModelo) {
		if (modeloRepo.containsKey(idMarca) && modeloRepo.get(idMarca).contains(new Modelo(idModelo))) {
			return modeloRepo.get(idMarca).get(modeloRepo.get(idMarca).indexOf(new Modelo(idModelo)));
		}
		return null;
	}

	/**
	 * Crea un modelo nuevo en una marca.
	 *
	 * @param idMarca el id de la marca a la que pertenece
	 * @param modelo  el modelo
	 */
	public void putModelo(String idMarca, Modelo modelo) {
		if (modeloRepo.get(idMarca) == null) {
			ArrayList<Modelo> modelos = new ArrayList<>();
			modelos.add(modelo);
			modeloRepo.put(idMarca, modelos);
		} else {
			modeloRepo.get(idMarca).add(modelo);
		}
	}

	/**
	 * Elimina un modelo de una marca.
	 *
	 * @param idMarca el id de la marca a la que pertenece
	 * @param modelo  el modelo
	 */
	public void removeModelo(String idMarca, Modelo modelo) {
		if (getModelo(idMarca, modelo.getId()) != null) {
			modeloRepo.get(idMarca).remove(modelo);
			motorRepo.remove(modelo.getNombre());
		}
	}

	/**
	 * Devuelve todos los motores de un modelo.
	 *
	 * @param nombre el nombre del modelo al que pertenecen
	 * @return los motores
	 */
	public Collection<Motor> getMotores(String nombre) {
		if (motorRepo.containsKey(nombre)) {
			return motorRepo.get(nombre);
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Devuelve un motor específico de un modelo
	 *
	 * @param nombreModelo the el nombre del modelo al que pertenece
	 * @param idMotor      el id del motor
	 * @return el motor
	 */
	public Motor getMotor(String nombreModelo, String idMotor) {
		if (motorRepo.containsKey(nombreModelo) && motorRepo.get(nombreModelo).contains(new Motor(idMotor))) {
			return motorRepo.get(nombreModelo).get(motorRepo.get(nombreModelo).indexOf(new Motor(idMotor)));
		}
		return null;
	}

	/**
	 * Crea un motor nuevo en un modelo
	 *
	 * @param nombreModelo el nombre del modelo al que pertenece
	 * @param motor        el motor
	 */
	public void putMotor(String nombreModelo, Motor motor) {
		if (motorRepo.get(nombreModelo) == null) {
			ArrayList<Motor> motores = new ArrayList<>();
			motores.add(motor);
			motorRepo.put(nombreModelo, motores);
		} else {
			motorRepo.get(nombreModelo).add(motor);
		}
	}

	/**
	 * Elimina un motor de un modelo
	 *
	 * @param nombreModelo el nombre del modelo al que pertenece
	 * @param motor        el motor
	 */
	public void removeMotor(String nombreModelo, Motor motor) {
		if (getMotor(nombreModelo, motor.getId()) != null) {
			motorRepo.get(nombreModelo).remove(motor);
		}
	}

}
