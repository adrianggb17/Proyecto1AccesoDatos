package com.losdeatras.laliga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class crearFicherosTexto {
final static int LONGITUD_NOMBRE = 100;
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        BufferedWriter bfw = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try {
            //Introducimos el ID
            System.out.println("Introduce el ID del equipo");
            int id = br.read();
            br.readLine();
            //Introducimos el nombre
            System.out.println("Introduce el nombre del equipo");
            String nombre = br.readLine();
            System.out.println("Me suda la polla esto");
            //Creamos la ruta del archivo
            String ruta = nombre + ".txt";
            //Introducimos los puntos que ha conseguido el equipo
            System.out.println("Introduce la cantidad de puntos obtenida por el "+nombre);
            int puntos = br.read();
            //Introducimos la liga en la que juega
            System.out.println("¿Este equipo juega en la liga 1 o en la liga 2?");
            int liga = -1;
            do{
            liga = br.read();
            }while(liga !=1 && liga !=2);
            File f = new File(ruta);

            bfw = new BufferedWriter(new FileWriter(ruta, StandardCharsets.UTF_8));
            if (!f.createNewFile()) {
                System.out.println("Ha habido un problema al crear el fichero");
            }else{
                System.out.println("Fichero creado correctamente");
                //INTRODUCIMOS EL ID
                bfw.write(id);
                //INTRODUCIMOS EL NOMBRE CARACTER A CARACTER CON UN TAMAÑO PREFIJADO
                for (int i = 0; i < LONGITUD_NOMBRE; i++) {
                    if(i<nombre.length()){
                        bfw.write(nombre.charAt(i));
                    }
                    else{
                    bfw.write(' ');
                }
                }
                //INTRODUCIMOS LOS PUNTOS
                bfw.write(puntos);
                //INTRODUCIMOS LA LIGA EN LA QUE SE ENCUENTRA
                bfw.write(liga);
            }
            

        } catch (FileNotFoundException fn) {
            System.out.println("No se encuentra el fichero en la ruta indicada");
        } catch (IOException io) {
            System.out.println("Error de E/S");
        }
    }
    
}
