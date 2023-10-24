package com.losdeatras.laliga;

import com.losdeatras.laliga.Entrenador;
import com.losdeatras.laliga.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
    
    public void FicheroDat (){

            String nombreArchivo = "datosAleatorios.dat";
            Scanner scanner = new Scanner(System.in);

            try {
                // Crear un archivo para acceso aleatorio en modo de lectura y escritura
                RandomAccessFile archivo = new RandomAccessFile(nombreArchivo, "rw");

                // Solicitar datos por consola
                System.out.print("Ingrese un entero: ");
                int entero = scanner.nextInt();
                scanner.nextLine(); // Limpiar el búfer de entrada
                System.out.print("Ingrese una cadena: ");
                String cadena = scanner.nextLine();

                // Escribir datos en el archivo en ubicaciones específicas
                archivo.writeInt(entero);                  
                archivo.writeUTF(cadena);                 

                // Leer datos desde ubicaciones específicas
                archivo.seek(0);
                int enteroLeido = archivo.readInt();
                archivo.seek(12);
                String stringLeido = archivo.readUTF();

                // Mostrar los datos leídos
                System.out.println("Entero leído: " + enteroLeido);
                System.out.println("String leído: " + stringLeido);

                // Cerrar el archivo
                archivo.close();
                scanner.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    private static void convertirXMLaHTML(Node node, FileWriter htmlFile) throws IOException {
        
        try {
            if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            htmlFile.write("<p>");
            NodeList children = element.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                convertirXMLaHTML(children.item(i), htmlFile);
            }
            htmlFile.write("</p>");
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            htmlFile.write(node.getTextContent());
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void XMLtoHTMLConverter(){
        try {
            File xmlFile = new File("Equipos/");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            FileWriter htmlFile = new FileWriter("Equipos/");
            
            Element rootElement = doc.getDocumentElement();            
            htmlFile.write("""
                           <html>
                           <title>Archivo XML a HTML</title>
                           <body>
                           """);            
            convertirXMLaHTML(rootElement, htmlFile);            
            htmlFile.write("""
                           
                           </body>
                           </html>""");
            htmlFile.close();

            System.out.println("El archivo HTML se ha generado correctamente.");
        } catch (Exception e) {
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
            System.out.println("5. Crear fichero aleatorio");
            System.out.println("6. Convertir ");
            System.out.println("7. Salir");
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
                    helper.FicheroDat();
                    break;
                case 6:
                    helper.XMLtoHTMLConverter();
                    break;
                case 7:
                    salir = true;
                    break;
            }
        } while (salir != true);
    }
}
