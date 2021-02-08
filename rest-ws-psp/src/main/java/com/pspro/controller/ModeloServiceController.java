package com.pspro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pspro.modelo.Modelo;

@RestController
public class ModeloServiceController {
   private static Map<String, Modelo> modeloRepo = new HashMap<>();
   static {
      Modelo honey = new Modelo();
      honey.setId("1");
      honey.setNumPlazas(5);
      honey.setMotores(null);
      modeloRepo.put(honey.getId(), honey);
      
      Modelo almond = new Modelo();
      almond.setId("2");
      almond.setNumPlazas(5);
      almond.setMotores(null);
      modeloRepo.put(almond.getId(), almond);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
      modeloRepo.remove(id);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Modelo product) { 
      modeloRepo.remove(id);
      product.setId(id);
      modeloRepo.put(id, product);
      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Modelo product) {
      modeloRepo.put(product.getId(), product);
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(modeloRepo.values(), HttpStatus.OK);
   }
}
