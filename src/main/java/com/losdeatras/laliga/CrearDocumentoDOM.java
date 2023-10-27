package com.losdeatras.laliga;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adrian
 */
public class CrearDocumentoDOM {
    
    static final Properties myProperties = new Properties();
    
    private static final String ENTRENADOR = "ENTRENADOR";
    private static final String IDENTRENADOR = "IDENTRENADOR";
    private static final String NOMBREENTRENADOR = "NOMBRENTREN";
    private static final String EDAD = "EDAD";
    
        public static void main(String[] args) throws Exception {

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
            throw e;
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
}
