/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.losdeatras.laliga;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class FicheroDat {

public static void main(String[] args) {
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
}
