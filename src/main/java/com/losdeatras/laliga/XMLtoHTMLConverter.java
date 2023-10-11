
package com.losdeatras.laliga;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Carlos Ciudad
 */

public class XMLtoHTMLConverter {
    
    private static final String PATH = ".\\jugadores\\013.xml";
    
    public static void main(String[] args) {
                    
        try {
            File xmlFile = new File(PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            FileWriter htmlFile = new FileWriter(".\\jugadores\\013.html");
            
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

    //Convertir elementos XML en HTML
    private static void convertirXMLaHTML(Node node, FileWriter htmlFile) throws IOException {
        
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
    }
}

