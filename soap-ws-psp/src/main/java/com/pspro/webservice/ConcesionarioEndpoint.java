package com.pspro.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.pspro.xml.concesionario.MarcaDetailsRequest;
import com.pspro.xml.concesionario.MarcaDetailsResponse;
import com.pspro.xml.concesionario.Modelo;
import com.pspro.xml.concesionario.ModeloDetailsRequest;
import com.pspro.xml.concesionario.ModeloDetailsResponse;
import com.pspro.xml.concesionario.ModelosRequest;
import com.pspro.xml.concesionario.ModelosResponse;

 
@Endpoint
public class ConcesionarioEndpoint 
{
    private static final String NAMESPACE_URI = "http://www.pspro.com/xml/concesionario";
 
    private ConcesionarioRepository ConcesionarioRepository;
 
    @Autowired
    public ConcesionarioEndpoint(ConcesionarioRepository ConcesionarioRepository) {
        this.ConcesionarioRepository = ConcesionarioRepository;
    }
 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ModeloDetailsRequest")
    @ResponsePayload
    public ModeloDetailsResponse getModelo(@RequestPayload ModeloDetailsRequest request) {
        ModeloDetailsResponse response = new ModeloDetailsResponse();
        response.setModelo(ConcesionarioRepository.findModelo(request.getName()));
 
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ModelosRequest")
    @ResponsePayload
    public ModelosResponse getModelos(@RequestPayload ModelosRequest request) {
        ModelosResponse response = new ModelosResponse();
        List<Modelo> modelos = ConcesionarioRepository.findModelos(request.getName());
        for (Modelo modelo : modelos) {
			response.getModelos().add(modelo);
		}
 
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MarcaDetailsRequest")
    @ResponsePayload
    public MarcaDetailsResponse getMarcas(@RequestPayload MarcaDetailsRequest request) {
        MarcaDetailsResponse response = new MarcaDetailsResponse();
        response.setMarca(ConcesionarioRepository.findMarca(request.getName()));
 
        return response;
    }
}