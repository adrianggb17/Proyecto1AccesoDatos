package com.losdeatras.laliga;

import com.losdeatras.laliga.Entrenador;
import com.losdeatras.laliga.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
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
    private static final Properties myProperties = new Properties();
    
    private static final String ENTRENADOR = "ENTRENADOR";
    private static final String IDENTRENADOR = "IDENTRENADOR";
    private static final String NOMBREENTRENADOR = "NOMBRENTREN";
    private static final String EDAD = "EDAD";

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
            File xmlFile = new File("Jugadores/010.xml");
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
    private void crearDocumentoDOM(){
        
        try {
            CrearDocumentoDOM.myProperties.load(new FileInputStream("libros_prj.properties"));
        } catch (IOException e) {
            System.out.println("No se pueden cargar la inicialización del programa. Saliendo...");
            System.exit(100);
        }

        String fichero_original = CrearDocumentoDOM.myProperties.getProperty("xml_path.old");

        Document doc = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(ENTRENADOR);
            doc.appendChild(rootElement);

            Element entrenador;
            entrenador = CrearDocumentoDOM.createNodeEntrenador(doc, new Entrenador(060, "Jose", 50));
            rootElement.appendChild(entrenador);

            
        } catch (ParserConfigurationException | DOMException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    static Element createNodeEntrenador(Document doc, Entrenador entrenador) throws DOMException {
        // nodo donde colgaremos todos los datos de un libro
        Element nodeEntrenador = null;
        try {
            nodeEntrenador = doc.createElement(ENTRENADOR);

            Element identre = doc.createElement(IDENTRENADOR);
            identre.appendChild(doc.createTextNode(Integer.toString(entrenador.getIdEntrenador())));
            nodeEntrenador.appendChild(identre);
            
            Element nombreEntre = doc.createElement(NOMBREENTRENADOR);
            nombreEntre.appendChild(doc.createTextNode(entrenador.getNombre()));
            nodeEntrenador.appendChild(nombreEntre);
            
            Element edad = doc.createElement(EDAD);
            edad.appendChild(doc.createTextNode(Integer.toString(entrenador.getEdad())));
            nodeEntrenador.appendChild(edad);

        } catch (DOMException e) {
            System.out.println(e.getLocalizedMessage());
            throw e;
        }

        return nodeEntrenador;
    }
    
    private void lecturaXMLDOM(){
         int edad,id;
         String nombre;
         
        // Recuperación del archivo de propiedades para su posterior trabajo
        try {
            LecturaXmlDOM.myProperties.load(new FileInputStream("entrenadores_prj.properties"));
            
            org.w3c.dom.Document doc = null; 

            System.out.println("Recuperación de la información de los entrenadores");
            
            Node raiz = doc.getFirstChild();
            NodeList listaNodosEntrenadores = raiz.getChildNodes();

            for (int i = 0; i < listaNodosEntrenadores.getLength(); i++) {
                
                Node nodoEntrenador = listaNodosEntrenadores.item(i);
                if (nodoEntrenador != null && nodoEntrenador.getNodeType() == Node.ELEMENT_NODE) {
                    Element eEntrenador = (Element) nodoEntrenador;
                    
                    id = Integer.parseInt(eEntrenador.getElementsByTagName(LecturaXmlDOM.IDENTRENADOR).item(0).getTextContent());
                    nombre = eEntrenador.getElementsByTagName(LecturaXmlDOM.NOMBREENTRENADOR).item(0).getTextContent();
                    edad = Integer.parseInt (eEntrenador.getElementsByTagName(LecturaXmlDOM.EDAD).item(0).getTextContent());

                    Entrenador entrenador = new Entrenador (id, nombre, edad);
                    System.out.println(entrenador);
                    }
                }
        } catch (IOException e) {
            System.out.println("No se pueden cargar la inicialización del programa. Saliendo...");
            System.exit(100);
             
        }
        
        String fichero_original = LecturaXmlDOM.myProperties.getProperty("xml_path.old");
           
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
        String ciudad, ruta;
        String rutaO = "", rutaD = "";

        do {

            System.out.println("Selecciona una opcion");
            System.out.println("1. ver Equipos");
            System.out.println("2. Ver Jugadores");
            System.out.println("3. Ver Entrenadores");
            System.out.println("4. Crear Equipo");
            System.out.println("5. Crear fichero aleatorio");
            System.out.println("6. Convertir XML a HTML");
            System.out.println("7. Crear documento DOM");
            System.out.println("8. Lectura XML DOM");
            System.out.println("9. Mover fichero");
            System.out.println("10. Copiar fichero");
            System.out.println("11. Eliminar fichero");
            System.out.println("12. Salir");
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
                    helper.crearDocumentoDOM();
                    break;
                case 8:
                    helper.lecturaXMLDOM();
                    break;
                case 9:

                    System.out.println("Introduce la ruta de origen del fichero que deseas mover");
                    rutaO = sc.nextLine();
                     System.out.println("Introduce la ruta de destino del fichero que deseas mover");
                    rutaD = sc.nextLine();
                    helper.moverFichero(rutaO,rutaD);
                    break;
                case 10:
                    System.out.println("Introduce la ruta de origen del fichero que deseas copiar");
                    rutaO = sc.nextLine();
                     System.out.println("Introduce la ruta de destino del fichero que deseas copiar");
                    rutaD = sc.nextLine();
                    helper.copiarFichero(rutaO,rutaD);
                    break;
                case 11:
                    System.out.println("Introduce la ruta del fichero que deseas eliminar");
                    ruta = sc.nextLine();
                    helper.eliminarFichero("Equipos\\Betis.txt");
                    break;
                case 12:
                    salir = true;
                    break;
            }
        } while (salir != true);
    }
    /**
     * Miguel Herreros
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
    /**
     * Miguel Herreros
     * @param rutaArchivo 
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
    /**
     * Miguel Herreros
     * @param rutaArchivo 
     */
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