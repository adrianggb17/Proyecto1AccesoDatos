package com.losdeatras.laliga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Alumno
 */
public class crearFicherosTexto {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[]args){
        BufferedWriter bfw = null;
        String ruta = "Equipo.txt";
        File f =  new File(ruta);
        int id=1;
        String nombre="";
        int puntos=0;
        int liga=0;
        
        try{
            bfw = new BufferedWriter(new FileWriter(ruta, StandardCharsets.UTF_8));
            if(f.createNewFile()){
                System.out.println("Fichero creado correctamente");
            }
            bfw.write(id);
            bfw.write(nombre);//Esto va aparte
            bfw.write(puntos);
             bfw.write(liga);
            
        }catch (FileNotFoundException fn) {
            System.out.println("No se encuentra el fichero en la ruta indicada");
        } catch (IOException io) {
            System.out.println("Error de E/S");
        }
    }
}
