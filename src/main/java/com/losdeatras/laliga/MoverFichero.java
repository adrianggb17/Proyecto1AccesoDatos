/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.losdeatras.laliga;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class MoverFichero {
    public static void main(String[] args) {
        // Especifica la ubicación del archivo original y de destino
        String rutaOrigen = "ruta_del_archivo_original.txt";
        String rutaDestino = "ruta_del_archivo_destino.txt";
        moverFichero(rutaOrigen, rutaDestino);
        // Crea objetos File para el archivo original y de destino
        File archivoOrigen = new File(rutaOrigen);
        File archivoDestino = new File(rutaDestino);

        try {
            // Mueve el archivo desde la ubicación original a la ubicación de destino
            Files.move(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo movido exitosamente de " + rutaOrigen + " a " + rutaDestino);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al mover el archivo.");
        }
    }
    
    public static void moverFichero(String origen, String destino){
        File archivoOrigen = new File(origen);
        File archivoDestino = new File(destino);
        try {
            // Mueve el archivo desde la ubicación original a la ubicación de destino
            Files.move(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo movido exitosamente de " + origen + " a " + destino);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al mover el archivo.");
        }
        
        
    }
}