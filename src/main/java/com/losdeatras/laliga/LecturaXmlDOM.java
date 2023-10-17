package com.losdeatras.laliga;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.text.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adrian
 */
public class LecturaXmlDOM {
    
    private static final Properties myProperties = new Properties();

    //Declaro las constantes de mi xlm
    private static final String ENTRENADOR = "ENTRENADOR";
    private static final String IDENTRENADOR = "IDENTRENADOR";
    private static final String NOMBREENTRENADOR = "NOMBRENTREN";
    private static final String EDAD = "EDAD";
    
     public static void main(String[] args) {
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
}   
