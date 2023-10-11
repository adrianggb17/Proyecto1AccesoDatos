
package com.losdeatras.laliga;

/**
 *
 * @author Alvaro
 */
public class Entrenador {
    
    private  int idEntrenador;
    private String nombre;
    private int edad;

    public Entrenador(int idEntrenador, String nombre, int edad) {
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Entrenador{" + "idEntrenador=" + idEntrenador + ", nombre=" + nombre + ", edad=" + edad + '}';
    }
    
    
}
