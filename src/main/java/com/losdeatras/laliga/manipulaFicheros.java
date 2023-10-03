package com.losdeatras.laliga;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Miguel Herreros Pinilla
 */
public class manipulaFicheros {
    String nombre;
    
    /**
     * @author Miguel Herreros Pinilla
     * @param nombre
     * @param tipo
     * @return
     * @throws IOException 
     */
    public boolean crearFichero(String nombre, int tipo)throws IOException{
        File f;
        switch (tipo) {
            case 1:
                f = new File(nombre+".txt");
                break;
            case 2:
                f = new File(nombre+".dat");
            default:
                throw new AssertionError();
        }
                    return f.createNewFile();

               
    }
    
    public void moverFichero(File f, File ruta){
        if(f.exists()){
            File fAux = f;
        if(ruta.exists()){
            String route = f.getAbsolutePath();
            f = new File(f,route);
        }else{
            System.out.println("La ruta indicada no existe");
        }
        }
        
    }
    
    
    
    
}
