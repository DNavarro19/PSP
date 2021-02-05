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
 
@Component
public class ConcesionarioRepository {
    private static final Map<String, Modelo> modelos = new HashMap<>();
    private static final Map<String, ArrayList<Modelo>> modelosMarca = new HashMap<>();
    private static final Map<String, Marca> marcas = new HashMap<>();
 
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
 
    public Modelo findModelo(String name) {
        Assert.notNull(name, "El nombre del modelo no puede ser nulo");
        return modelos.get(name);
    }
    
    public Marca findMarca(String name) {
    	Assert.notNull(name, "El nombre del marca no puede ser nulo");
        return marcas.get(name);
    }
    
    public List<Modelo> findModelos(String name){
    	Assert.notNull(name, "El nombre del marca no puede ser nulo");
        return modelosMarca.get(name);
    }
    
}