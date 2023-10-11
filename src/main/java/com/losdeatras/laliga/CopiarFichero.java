
package com.losdeatras.laliga;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopiarFichero {
    public static void main(String[] args) {
        // Especifica la ubicación del archivo original y de destino
        String rutaOrigen = "ruta_del_archivo_original.txt";
        String rutaDestino = "ruta_del_archivo_destino.txt";

        
            copiarFichero(rutaOrigen, rutaDestino);
            
    }

    /**
     * @author Miguel 
     * @param rutaOrigen
     * @param rutaDestino
     * @throws IOException 
     */
    public static void copiarFichero(String rutaOrigen, String rutaDestino){
try {
        // Crea objetos File para el archivo original y de destino
        File archivoOrigen = new File(rutaOrigen);
        File archivoDestino = new File(rutaDestino);

        // Utiliza Files.copy() para copiar el archivo desde la ubicación original a la ubicación de destino
        Files.copy(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Archivo copiado exitosamente de " + rutaOrigen + " a " + rutaDestino);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al copiar el archivo.");
        }
    }
    }