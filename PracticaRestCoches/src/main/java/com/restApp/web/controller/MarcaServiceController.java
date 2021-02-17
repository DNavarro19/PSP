package com.restApp.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restApp.web.model.Marca;
import com.restApp.web.model.Modelo;
import com.restApp.web.model.Motor;
import com.restApp.web.model.Repository;

/**
 * La clase MarcaServiceController.
 */
@RestController
public class MarcaServiceController {

	/** El repositorio. */
	Repository repo = new Repository();

	/**
	 * Elimina una marca especificada, si es que la marca existe.
	 *
	 * @param id el id de la marca
	 * @return una response dependiendo de como haya ido la ejecución
	 */
	@DeleteMapping(value = "/marca/{id}")
	public ResponseEntity<Object> deleteMarca(@PathVariable("id") String id) {
		if (repo.getMarca(id) != null) {
			repo.removeMarca(id);
			return new ResponseEntity<>("La marca ha sido eliminada correctamente", HttpStatus.OK);
		}
		return new ResponseEntity<>("La marca no ha podido eliminarse", HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Actualiza una marca especificada, si es que la marca existe.
	 *
	 * @param id    el id de la marca
	 * @param marca los datos a actualizar de la marca
	 * @return una response dependiendo de como haya ido la ejecución
	 */
	@PutMapping(value = "/marca/{id}")
	public ResponseEntity<Object> updateMarca(@PathVariable("id") String id, @RequestBody Marca marca) {
		if (repo.getMarca(id) != null) {
			repo.removeMarca(id);
			marca.setId(id);
			repo.putMarca(id, marca);
			return new ResponseEntity<>("La marca se ha actualizado correctamente", HttpStatus.OK);
		}
		return new ResponseEntity<>("La marca no ha podido actualizarse correctamente", HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Crea una marca.
	 *
	 * @param marca la marca a crear
	 * @return una response dando el OK a la creación
	 */
	@PostMapping(value = "/marca")
	public ResponseEntity<Object> createMarca(@RequestBody Marca marca) {
		repo.putMarca(marca.getId(), marca);
		return new ResponseEntity<>("La marca se ha creado correctamente", HttpStatus.CREATED);
	}

	/**
	 * Devuelve todas las marcas del repositorio.
	 *
	 * @return las marcas
	 */
	@GetMapping(value = "/marca")
	public ResponseEntity<Object> getMarcas() {
		return new ResponseEntity<>(repo.getMarcas(), HttpStatus.OK);
	}

	/**
	 * Devuelve una marca especificada, si existe, si no devuelve null.
	 *
	 * @param id el id de la marca
	 * @return la marca, puede ser nula
	 */
	@GetMapping(value = "/marca/{id}")
	public ResponseEntity<Object> getMarca(@PathVariable("id") String id) {
		return new ResponseEntity<>(repo.getMarca(id), HttpStatus.OK);
	}

	/**
	 * Elimina un modelo especificado, si es que este existe.
	 *
	 * @param idModelo el id del modelo
	 * @param idMarca  el id de la marca a la que pertenece
	 * @return una response dependiendo de como haya ido la ejecución
	 */
	@DeleteMapping(value = "/marca/{idMarca}/modelo/{idModelo}")
	public ResponseEntity<Object> deleteModelo(@PathVariable("idModelo") String idModelo,
			@PathVariable("idMarca") String idMarca) {
		if (repo.getMarca(idMarca) != null) {
			repo.removeModelo(idMarca, repo.getModelo(idMarca, idModelo));
			return new ResponseEntity<>("El modelo ha sido eliminado correctamente", HttpStatus.OK);
		}
		return new ResponseEntity<>("El modelo no ha podido eliminarse", HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Actualiza un modelo especificado, si es que este existe.
	 *
	 * @param idModelo el id del modelo
	 * @param idMarca  el id de la marca a la que pertenece
	 * @param modelo   los datos a actualizar del modelo
	 * @return una response dependiendo de como haya ido la ejecución
	 */
	@PutMapping(value = "/marca/{idMarca}/modelo/{idModelo}")
	public ResponseEntity<Object> updateModelo(@PathVariable("idModelo") String idModelo,
			@PathVariable("idMarca") String idMarca, @RequestBody Modelo modelo) {
		if (repo.getMarca(idMarca) != null) {
			repo.removeModelo(idMarca, repo.getModelo(idMarca, idModelo));
			modelo.setId(idModelo);
			repo.putModelo(idMarca, modelo);
			return new ResponseEntity<>("El modelo se ha actualizado correctamente", HttpStatus.OK);
		}
		return new ResponseEntity<>("El modelo no ha podido actualizarse correctamente", HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Crea un modelo nuevo en una marca ya existente, si la marca no existe no
	 * podrá crearse.
	 *
	 * @param idMarca el id de la marca a la que pertenece
	 * @param modelo  el modelo a crear
	 * @return una response dependiendo de como haya ido la ejecución
	 */
	@PostMapping(value = "/marca/{idMarca}/modelo")
	public ResponseEntity<Object> createModelo(@PathVariable("idMarca") String idMarca, @RequestBody Modelo modelo) {
		if (repo.getMarca(idMarca) != null) {
			repo.putModelo(idMarca, modelo);
			return new ResponseEntity<>("El modelo se ha creado correctamente", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("El modelo no ha podido crearse correctamente", HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Devuelve todos los modelos de una marca existente.
	 *
	 * @param idMarca el id de la marca a la que pertenecen
	 * @return la colección de modelos de la marca, puede ser vacía, bien porque no
	 *         exista la marca, o porque esta no tenga modelos añadidos
	 */
	@GetMapping(value = "/marca/{idMarca}/modelo")
	public ResponseEntity<Object> getModelos(@PathVariable("idMarca") String idMarca) {
		return new ResponseEntity<>(repo.getModelos(idMarca), HttpStatus.OK);
	}

	/**
	 * Devuelve un modelo específico de una marca existente.
	 *
	 * @param idMarca  el id de la marca a la que pertenece
	 * @param idModelo el id del modelo
	 * @return el modelo, puede ser nulo, bien porque no exista la marca, o porque
	 *         el modelo no exista
	 */
	@GetMapping(value = "/marca/{idMarca}/modelo/{idModelo}")
	public ResponseEntity<Object> getModelo(@PathVariable("idMarca") String idMarca,
			@PathVariable("idModelo") String idModelo) {
		return new ResponseEntity<>(repo.getModelo(idMarca, idModelo), HttpStatus.OK);
	}

	/**
	 * Elimina un motor específico, si es que este existe.
	 *
	 * @param idMarca  el id de la marca a la que pertenece el modelo
	 * @param idModelo el id del modelo al que pertenence el motor
	 * @param idMotor  el id del motor
	 * @return una response dependiendo de como haya ido la ejecución
	 */
	@DeleteMapping(value = "/marca/{idMarca}/modelo/{idModelo}/motor/{idMotor}")
	public ResponseEntity<Object> deleteMotor(@PathVariable("idMarca") String idMarca,
			@PathVariable("idModelo") String idModelo, @PathVariable("idMotor") String idMotor) {
		String nombreModelo = null;
		if (repo.getModelo(idMarca, idModelo) != null) {
			nombreModelo = repo.getModelo(idMarca, idModelo).getNombre();
			repo.removeMotor(nombreModelo, repo.getMotor(nombreModelo, idMotor));
			return new ResponseEntity<>("El motor ha sido eliminado correctamente", HttpStatus.OK);
		}
		return new ResponseEntity<>("El motor no ha podido eliminarse", HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Actualiza un motor específico, si es que este existe.
	 *
	 * @param idMarca  el id de la marca a la que pertenece el modelo
	 * @param idModelo el id del modelo al que pertenece el motor
	 * @param idMotor  el id del motor
	 * @param motor    los datos a actualizar del motor
	 * @return una response dependiendo de como haya ido la ejecución
	 */
	@PutMapping(value = "/marca/{idMarca}/modelo/{idModelo}/motor/{idMotor}")
	public ResponseEntity<Object> updateMotor(@PathVariable("idMarca") String idMarca,
			@PathVariable("idModelo") String idModelo, @PathVariable("idMotor") String idMotor,
			@RequestBody Motor motor) {
		String nombreModelo = null;
		if (repo.getModelo(idMarca, idModelo) != null) {
			nombreModelo = repo.getModelo(idMarca, idModelo).getNombre();
			repo.removeMotor(nombreModelo, repo.getMotor(nombreModelo, idMotor));
			motor.setId(idMotor);
			repo.putMotor(nombreModelo, motor);
			return new ResponseEntity<>("El motor se ha actualizado correctamente", HttpStatus.OK);
		}
		return new ResponseEntity<>("El motor no ha podido actualizarse correctamente", HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Crea un motor nuevo en un modelo ya existente, si el modelo no existe no
	 * podrá crearse.
	 *
	 * @param idMarca  el id de la marca a la que pertenece el modelo
	 * @param idModelo el id del modelo al que pertenece el motor
	 * @param motor    el motor a crear
	 * @return una response dependiendo de como haya ido la ejecución
	 */
	@PostMapping(value = "/marca/{idMarca}/modelo/{idModelo}/motor")
	public ResponseEntity<Object> createMotor(@PathVariable("idMarca") String idMarca,
			@PathVariable("idModelo") String idModelo, @RequestBody Motor motor) {
		String nombreModelo = null;
		if (repo.getModelo(idMarca, idModelo) != null) {
			nombreModelo = repo.getModelo(idMarca, idModelo).getNombre();
			repo.putMotor(nombreModelo, motor);
			return new ResponseEntity<>("El motor se ha creado correctamente", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("El motor no ha podido crearse correctamente", HttpStatus.METHOD_NOT_ALLOWED);

	}

	/**
	 * Devuelve todos los motores de un modelo existente.
	 *
	 * @param idMarca  el id de la marca a la que pertenece el modelo
	 * @param idModelo el id del modelo al que pertenece el motor
	 * @return la colección de motores del modelo, puede ser vacía, bien porque no
	 *         exista el modelo, o porque este no tenga motores añadidos
	 */
	@GetMapping(value = "/marca/{idMarca}/modelo/{idModelo}/motor")
	public ResponseEntity<Object> getMotores(@PathVariable("idMarca") String idMarca,
			@PathVariable("idModelo") String idModelo) {
		String nombreModelo = null;
		if (repo.getModelo(idMarca, idModelo) != null) {
			nombreModelo = repo.getModelo(idMarca, idModelo).getNombre();
		}
		return new ResponseEntity<>(repo.getMotores(nombreModelo), HttpStatus.OK);
	}

	/**
	 * Devuelve un motor específico de un modelo existente.
	 *
	 * @param idMarca  el id de la marca a la que pertenece el modelo
	 * @param idModelo el id del modelo al que pertenece el motor
	 * @param idMotor  el id del motor
	 * @return el motor, puede ser nulo, bien porque no exista el modelo, o porque
	 *         el motor no exista
	 */
	@GetMapping(value = "/marca/{idMarca}/modelo/{idModelo}/motor/{idMotor}")
	public ResponseEntity<Object> getMotor(@PathVariable("idMarca") String idMarca,
			@PathVariable("idModelo") String idModelo, @PathVariable("idMotor") String idMotor) {
		String nombreModelo = null;
		if (repo.getModelo(idMarca, idModelo) != null) {
			nombreModelo = repo.getModelo(idMarca, idModelo).getNombre();
		}
		return new ResponseEntity<>(repo.getMotor(nombreModelo, idMotor), HttpStatus.OK);
	}
}