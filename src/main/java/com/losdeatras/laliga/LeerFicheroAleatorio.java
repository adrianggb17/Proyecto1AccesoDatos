
package com.losdeatras.laliga;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class LeerFicheroAleatorio {

    public static void main(String[] args) {
        leerArchivoAleatorio();
    }
    /**
     * @author Miguel
     */
    public static void leerArchivoAleatorio() {
        int TAMANO_NOMBRE = 40;
        int id, edad;
        char nombre[] = new char[TAMANO_NOMBRE];
        char cLeido;
        String nom="";

        File f = new File("/Entrenador/EntrenadorX");
        RandomAccessFile file;
        try {
            file = new RandomAccessFile(f, "r");
        
            

        while (file.getFilePointer() < file.length()) {
            id = file.readInt(); 
            
            for (int i = 0; i < nombre.length; i++) {
                cLeido = file.readChar();
                nombre[i] = cLeido;    //los voy guardando en el array
                
            }
            nom = new String(nombre);
            edad = file.readInt();
            System.out.println("ID: "+id+", Nombre: "+nom+", Edad: "+edad);

        }

        file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerFicheroAleatorio.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException e) {
            Logger.getLogger(LeerFicheroAleatorio.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
