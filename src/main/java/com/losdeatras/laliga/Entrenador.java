
package com.losdeatras.laliga;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro
 */

@XmlRootElement
public class Entrenador {
    
    @XmlElement(name = "IDENTRENADOR", required = true)
    private  int idEntrenador;
    @XmlElement(name = "NOMBRENTREN", required = true)
    private String nombre;
    @XmlElement(name = "EDAD", required = true)
    private int edad;

    public Entrenador(int idEntrenador, String nombre, int edad) {
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.edad = edad;
    }
    
    @XmlTransient
    public int getIdEntrenador() {
        return idEntrenador;
    }
    
     public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }
    
    @XmlTransient
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @XmlTransient
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Entrenador{" + "idEntrenador=" + idEntrenador + ", nombre=" + nombre + ", edad=" + edad + '}';
    }
    
    
}
