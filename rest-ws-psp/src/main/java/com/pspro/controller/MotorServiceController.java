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

import com.pspro.modelo.Motor;


@RestController
public class MotorServiceController {
   private static Map<String, Motor> motorRepo = new HashMap<>();
   static {
      Motor honey = new Motor();
      honey.setId("1");
      honey.setCombustible("Gasolina");
      honey.setCv(100);
      motorRepo.put(honey.getId(), honey);
      
      Motor almond = new Motor();
      almond.setId("2");
      almond.setCombustible("Diesel");
      almond.setCv(90);
      motorRepo.put(almond.getId(), almond);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
      motorRepo.remove(id);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Motor product) { 
      motorRepo.remove(id);
      product.setId(id);
      motorRepo.put(id, product);
      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Motor product) {
      motorRepo.put(product.getId(), product);
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(motorRepo.values(), HttpStatus.OK);
   }
}