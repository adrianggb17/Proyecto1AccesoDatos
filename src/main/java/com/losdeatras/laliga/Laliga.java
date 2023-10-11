package com.losdeatras.laliga;

import com.losdeatras.laliga.Entrenador;
import com.losdeatras.laliga.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Alvaro
 */
public class Laliga {

    static Scanner sc = new Scanner(System.in);

    public void verEquipos() {

        System.out.println("Introduce el nombre del equipo");

        String nombreArchivo = sc.next();

        String ruta = "Equipos/" + nombreArchivo;

        try {
            FileReader fileReader = new FileReader(ruta);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void anadirEquipos(String abreviatura, int idEquipo, String ciudad, String nombre, int puntos, int liga) {

        String ruta = "Equipos/" + nombre;

        try {
            FileWriter fileWriter = new FileWriter("Equipos/" + nombre);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Equipo: " + abreviatura);
            bufferedWriter.newLine();
            bufferedWriter.write("idEquipo: " + idEquipo);
            bufferedWriter.newLine();
            bufferedWriter.write("Ciudad: " + ciudad);
            bufferedWriter.newLine();
            bufferedWriter.write("NombreEquipo: " + nombre);
            bufferedWriter.newLine();
            bufferedWriter.write("Puntos: " + puntos);
            bufferedWriter.newLine();
            bufferedWriter.write("Liga: " + liga);

            // Cierra el flujo de escritura
            bufferedWriter.close();

            System.out.println("Archivo escrito con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void verEntrenadores() {
        try {

            System.out.println("Introduce el nombre del archivo del entrenador");
            String entrenador = sc.next();
            File archivoXML = new File(entrenador + ".xml");

            JAXBContext context = JAXBContext.newInstance(Entrenador.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Entrenador e = (Entrenador) unmarshaller.unmarshal(archivoXML);

            System.out.println("idEntrenador " + e.getIdEntrenador());
            System.out.println("NombreEntrenador " + e.getNombre());
            System.out.println("Edad " + e.getEdad());
        
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void verJugadores() {
        try {

           System.out.println("Introduce el nombre del archivo del jugador");
            String jugador = sc.next();
            File archivoXML = new File(jugador + ".xml");

            JAXBContext context = JAXBContext.newInstance(Jugador.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Jugador j = (Jugador) unmarshaller.unmarshal(archivoXML);

            System.out.println("idJugador " + j.getIdJugador());
            System.out.println("Dorsal " + j.getDorsal());
            System.out.println("Posicion " + j.getPosicion());
            System.out.println("Edad " + j.getEdad());
            System.out.println("Nombre " + j.getNombre());
            System.out.println("NumGoles " + j.getNumGol());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Laliga helper = new Laliga();
        int opcion;
        boolean salir = false;
        String abreviatura;
        String nombreEquipo;
        int idEquipo;
        int liga;
        int puntos;
        String ciudad;

        do {

            System.out.println("Selecciona una opcion");
            System.out.println("1. ver Equipos");
            System.out.println("2. Ver Jugadores");
            System.out.println("3. Ver Entrenadores");
            System.out.println("4. Crear Equipo");
            System.out.println("5. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    helper.verEquipos();
                    salir = false;
                    break;

                case 2:
                    helper.verJugadores();
                    salir = false;
                    break;

                case 3:
                    helper.verEntrenadores();
                    salir = false;
                    break;

                case 4:
                    System.out.println("Introduce el nombre del equipo");
                    nombreEquipo = sc.next();

                    System.out.println("Introduce el id del equipo");
                    idEquipo = sc.nextInt();

                    System.out.println("Introduce la abreviatura");
                    abreviatura = sc.next();

                    System.out.println("Introduce la ciudad del equipo");
                    ciudad = sc.next();

                    System.out.println("Introduce los puntos del equipo");
                    puntos = sc.nextInt();

                    System.out.println("Introduce la liga del equipo");
                    liga = sc.nextInt();

                    helper.anadirEquipos(abreviatura, idEquipo, ciudad, nombreEquipo, puntos, liga);
                    break;

                case 5:
                    salir = true;
                    break;
            }
        } while (salir != true);
    }
}
