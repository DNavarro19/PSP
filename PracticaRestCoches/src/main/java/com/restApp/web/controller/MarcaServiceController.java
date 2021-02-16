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
import com.restApp.web.model.Repository;




@RestController
public class MarcaServiceController {

	Repository repo = new Repository();

	@DeleteMapping(value = "/marca/{id}")
	public ResponseEntity<Object> deleteMarca(@PathVariable("id") String id) {
		repo.removeMarca(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	@PutMapping(value = "/marca/{id}")
	public ResponseEntity<Object> updateMarca(@PathVariable("id") String id, @RequestBody Marca marca) {
		repo.removeMarca(id);
		marca.setId(id);
		repo.putMarca(id, marca);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@PostMapping(value = "/marca")
	public ResponseEntity<Object> createMarca(@RequestBody Marca marca) {
		repo.putMarca(marca.getId(), marca);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@GetMapping(value = "/marca")
	public ResponseEntity<Object> getMarcas() {
		return new ResponseEntity<>(repo.getMarcas(), HttpStatus.OK);
	}

	@GetMapping(value = "/marca/{id}")
	public ResponseEntity<Object> getMarca(@PathVariable("id") String id) {
		return new ResponseEntity<>(repo.getMarca(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/marca/{idMarca}/modelo/{idModelo}")
	public ResponseEntity<Object> deleteModelo(@PathVariable("idModelo") String idModelo,
			@PathVariable("idMarca") String idMarca, @RequestBody Modelo modelo) {
		repo.removeModelo(idMarca, modelo);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	@PutMapping(value = "/marca/{idMarca}/modelo/{idModelo}")
	public ResponseEntity<Object> updateModelo(@PathVariable("idModelo") String idModelo,
			@PathVariable("idMarca") String idMarca, @RequestBody Modelo modelo) {
		repo.removeModelo(idModelo, modelo);
		modelo.setId(idModelo);
		repo.putModelo(idMarca, modelo);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@PostMapping(value = "/marca/{idMarca}/modelo")
	public ResponseEntity<Object> createModelo(@PathVariable("idMarca") String idMarca, @RequestBody Modelo modelo) {
		repo.putModelo(idMarca, modelo);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@GetMapping(value = "/marca/{idMarca}/modelo")
	public ResponseEntity<Object> getModelos(@PathVariable("idMarca") String idMarca) {
		return new ResponseEntity<>(repo.getModelos(idMarca), HttpStatus.OK);
	}

	@GetMapping(value = "/marca/{idMarca}/modelo/{idModelo}")
	public ResponseEntity<Object> getModelo(@PathVariable("idMarca") String idMarca,
			@PathVariable("idModelo") String idModelo) {
		return new ResponseEntity<>(repo.getModelo(idMarca, idModelo), HttpStatus.OK);
	}
}