package com.pspro.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.pspro.xml.concesionario.Marca;
import com.pspro.xml.concesionario.Modelo;
 
/**
 * Clase ConcesionarioRepository, instancia todos los modelos y marcas.
 */
@Component
public class ConcesionarioRepository {
    
    /** Hashmap que guarda los modelos. */
    private static final Map<String, Modelo> modelos = new HashMap<>();
    
    /** Hashmap que enlaza todos los modelos de una marca. */
    private static final Map<String, ArrayList<Modelo>> modelosMarca = new HashMap<>();
    
    /** Hashmap que guarda todas las marcas. */
    private static final Map<String, Marca> marcas = new HashMap<>();
 
    /**
     * Inicia los datos (modelos y marcas).
     */
    @PostConstruct
    public void initData() {
    	
    	ArrayList<Modelo> mods = new ArrayList<>();
    	
    	Marca marca = new Marca();
    	marca.setNombre("Ford");
    	marca.setAñoCreacion(1903);
    	marcas.put(marca.getNombre(), marca);
        
        Modelo modelo = new Modelo();
        modelo.setNombre("Focus");
        modelo.setCaballos(95);
        modelo.setCombustible("Diésel");
        modelo.setMarca(marca);
        modelos.put(modelo.getNombre(), modelo);
        mods.add(modelo);
        
        modelo = new Modelo();
        modelo.setNombre("Fiesta");
        modelo.setCaballos(75);
        modelo.setCombustible("Gasolina");
        modelo.setMarca(marca);
        modelos.put(modelo.getNombre(), modelo);
        mods.add(modelo);
        modelosMarca.put(marca.getNombre(), mods);
        mods = new ArrayList<>();
        
        marca = new Marca();
    	marca.setNombre("Renault");
    	marca.setAñoCreacion(1898);
    	marcas.put(marca.getNombre(), marca);

        modelo = new Modelo();
        modelo.setNombre("Clio");
        modelo.setCaballos(65);
        modelo.setCombustible("Gasolina");
        modelo.setMarca(marca);
        modelos.put(modelo.getNombre(), modelo);
        mods.add(modelo);
        
        modelo = new Modelo();
        modelo.setNombre("Megane");
        modelo.setCaballos(115);
        modelo.setCombustible("Diésel");
        modelo.setMarca(marca);
        modelos.put(modelo.getNombre(), modelo);
        mods.add(modelo);
        modelosMarca.put(marca.getNombre(), mods);
        mods = new ArrayList<>();
        
        marca = new Marca();
    	marca.setNombre("Dacia");
    	marca.setAñoCreacion(1966);
    	marcas.put(marca.getNombre(), marca);

        modelo = new Modelo();
        modelo.setNombre("Sandero");
        modelo.setCaballos(67);
        modelo.setCombustible("Gasolina");
        modelo.setMarca(marca);
        modelos.put(modelo.getNombre(), modelo);
        mods.add(modelo);
        
        modelo = new Modelo();
        modelo.setNombre("Duster");
        modelo.setCaballos(115);
        modelo.setCombustible("Diésel");
        modelo.setMarca(marca);
        modelos.put(modelo.getNombre(), modelo);
        mods.add(modelo);
        modelosMarca.put(marca.getNombre(), mods);
        
    }
 
    /**
     * Busca un modelo y devuelve todos sus datos
     *
     * @param name el nombre del modelo
     * @return el modelo
     */
    public Modelo findModelo(String name) {
        Assert.notNull(name, "El nombre del modelo no puede ser nulo");
        return modelos.get(name);
    }
    
    /**
     * Busca una marca y devuelve todos sus datos.
     *
     * @param name el nombre de la marca
     * @return la marca
     */
    public Marca findMarca(String name) {
    	Assert.notNull(name, "El nombre del marca no puede ser nulo");
        return marcas.get(name);
    }
    
    /**
     * Busca todos los modelos de una marca.
     *
     * @param name el nombre de la marca
     * @return la lista de modelos
     */
    public List<Modelo> findModelos(String name){
    	Assert.notNull(name, "El nombre del marca no puede ser nulo");
        return modelosMarca.get(name);
    }
    
}