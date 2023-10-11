
package com.losdeatras.laliga;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro
 */
@XmlRootElement
public class Jugador {
    @XmlElement(name = "IDJUGADOR", required = true)
    private int idJugador;
    @XmlElement(name = "DORSAL", required = true)
    private int dorsal;
    @XmlElement(name = "POSICION", required = true)
    private String posicion;
    @XmlElement(name = "EDAD", required = true)
    private int edad;
    @XmlElement(name = "NOMBREJUGADOR", required = true)
    private String nombre;
    @XmlElement(name = "NUMGOLES", required = true)
    private int numGol;

    public Jugador(int idJugador, int dorsal, String posicion, int edad, String nombre, int numGol) {
        this.idJugador = idJugador;
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.edad = edad;
        this.nombre = nombre;
        this.numGol = numGol;
    }
    
    @XmlTransient
    public int getIdJugador() {
        return idJugador;
    }
    
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    
    @XmlTransient
    public int getDorsal() {
        return dorsal;
    }
    
     public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }
    
    @XmlTransient
    public String getPosicion() {
        return posicion;
    }
    
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    @XmlTransient
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    @XmlTransient
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @XmlTransient
    public int getNumGol() {
        return numGol;
    }
    
     public void setNumGol(int numGol) {
        this.numGol = numGol;
    }

    @Override
    public String toString() {
        return "Jugador{" + "idJugador=" + idJugador + ", dorsal=" + dorsal + ", posicion=" + posicion + ", edad=" + edad + ", nombre=" + nombre + ", numGol=" + numGol + '}';
    }
    
    
    
}
