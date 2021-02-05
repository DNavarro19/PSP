//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.02.05 a las 09:30:29 PM CET 
//


package com.pspro.xml.concesionario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Modelo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Modelo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caballos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="combustible" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marca" type="{http://www.pspro.com/xml/concesionario}Marca"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Modelo", propOrder = {
    "nombre",
    "caballos",
    "combustible",
    "marca"
})
public class Modelo {

    @XmlElement(required = true)
    protected String nombre;
    protected int caballos;
    @XmlElement(required = true)
    protected String combustible;
    @XmlElement(required = true)
    protected Marca marca;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad caballos.
     * 
     */
    public int getCaballos() {
        return caballos;
    }

    /**
     * Define el valor de la propiedad caballos.
     * 
     */
    public void setCaballos(int value) {
        this.caballos = value;
    }

    /**
     * Obtiene el valor de la propiedad combustible.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombustible() {
        return combustible;
    }

    /**
     * Define el valor de la propiedad combustible.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombustible(String value) {
        this.combustible = value;
    }

    /**
     * Obtiene el valor de la propiedad marca.
     * 
     * @return
     *     possible object is
     *     {@link Marca }
     *     
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * Define el valor de la propiedad marca.
     * 
     * @param value
     *     allowed object is
     *     {@link Marca }
     *     
     */
    public void setMarca(Marca value) {
        this.marca = value;
    }

}
