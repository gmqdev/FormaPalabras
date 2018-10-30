package formapalabras;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Diccionario {
    
    List<String> lineas;
    int linea;
    
    public Diccionario(String direccion){
        linea = 0;
        
        //Leer todas las lineas. Puede que JVM se quede sin memoria.
        try{
            lineas = Files.readAllLines(Paths.get(direccion));
        }catch(IOException e){
            System.out.println("Diccionario no encontrado, o no cabe en memoria.");
            e.printStackTrace();
        }
    }
    
    public boolean existeSiguiente(){   //Para el uso de esta clase en bucles
        return linea < lineas.size();
    }
    
    public String siguiente(){
        return lineas.get(linea++);
    }
}
