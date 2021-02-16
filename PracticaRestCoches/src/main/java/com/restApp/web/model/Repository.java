package com.restApp.web.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Repository {
	static Map<String, Marca> marcaRepo = new HashMap<>();
	static Map<String, ArrayList<Modelo>> modeloRepo = new HashMap<>();
	static Map<String, ArrayList<Motor>> motorRepo = new HashMap<>();

	public Repository() {
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
		modelo.setMotores(motores);
		motorRepo.put(modelo.getId(), motores);
		mods.add(modelo);

		modelo = new Modelo();
		modelo.setId("2");
		modelo.setNombre("Fiesta");
		modelo.setNumPlazas(4);
		modelo.setMotores(motores);
		motorRepo.put(modelo.getId(), motores);
		mods.add(modelo);
		modeloRepo.put(marca.getId(), mods);
		marca.setModelos(mods);
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
		modelo.setMotores(motores);
		motorRepo.put(modelo.getId(), motores);
		mods.add(modelo);

		modelo = new Modelo();
		modelo.setId("2");
		modelo.setNombre("Megane");
		modelo.setNumPlazas(5);
		modelo.setMotores(motores);
		motorRepo.put(modelo.getId(), motores);
		mods.add(modelo);
		modeloRepo.put(marca.getId(), mods);
		marca.setModelos(mods);
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
		modelo.setMotores(motores);
		motorRepo.put(modelo.getId(), motores);
		mods.add(modelo);

		modelo = new Modelo();
		modelo.setId("2");
		modelo.setNombre("Duster");
		modelo.setNumPlazas(5);
		modelo.setMotores(motores);
		motorRepo.put(modelo.getId(), motores);
		mods.add(modelo);
		modeloRepo.put(marca.getId(), mods);
		marca.setModelos(mods);
		marcaRepo.put(marca.getId(), marca);
	}

	public Collection<Marca> getMarcas() {
		return marcaRepo.values();
	}

	public Marca getMarca(String id) {
		return marcaRepo.get(id);
	}

	public void putMarca(String id, Marca marca) {
		marcaRepo.put(id, marca);
	}

	public void removeMarca(String id) {
		marcaRepo.remove(id);
	}

	public Collection<Modelo> getModelos(String id) {
		return modeloRepo.get(id);
	}

	public Modelo getModelo(String idMarca, String idModelo) {
		return modeloRepo.get(idMarca).get(Integer.parseInt(idModelo)-1);
	}

	public void putModelo(String idMarca, Modelo modelo) {
		modeloRepo.get(idMarca).add(modelo);
	}

	public void removeModelo(String idMarca, Modelo modelo) {
		modeloRepo.get(idMarca).remove(modelo);
	}

	public Collection<Motor> getMotores(String id) {
		return motorRepo.get(id);
	}

	public Motor getMotor(String idModelo, String idMotor) {
		return motorRepo.get(idModelo).get(Integer.parseInt(idMotor)-1);
	}

	public void putMotor(String idModelo, Motor motor) {
		motorRepo.get(idModelo).add(motor);
	}

	public void removeMotor(String idModelo, Motor motor) {
		motorRepo.get(idModelo).remove(motor);
	}

}
