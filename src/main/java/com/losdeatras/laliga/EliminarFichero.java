/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.losdeatras.laliga;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Miguel
 */
public class EliminarFichero {

    public static void main(String[] args) {
        // Especifica la ubicación del archivo que deseas eliminar
        String rutaArchivo = "ruta_del_archivo_a_eliminar.txt";

        eliminarFichero(rutaArchivo);

    }

    /**
     * @author Miguel
     * @param rutaArchivo 
     */
    public static void eliminarFichero(String rutaArchivo) {
        // Crea un objeto File para representar el archivo a eliminar
        try {
            File archivoAEliminar = new File(rutaArchivo);

            // Utiliza Files.delete() para eliminar el archivo
            Files.delete(archivoAEliminar.toPath());
            System.out.println("Archivo eliminado exitosamente: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al eliminar el archivo: " + rutaArchivo);
        }
    }
}
