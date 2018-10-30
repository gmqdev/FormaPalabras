package formapalabras;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;


public class Buscador {
    
    boolean extendido;
    boolean todasSobran;
    int toleranciaFaltan, toleranciaSobran, tamano;
    
    public Buscador(boolean extendido, int toleranciaFaltan, int toleranciaSobran, int tamano){
        this.extendido = extendido;   //Usar, o no, el diccionario grande.
        this.toleranciaFaltan = toleranciaFaltan;   //Límite máximo de letras añadidas o no usadas para formar la palabra.
        this.toleranciaSobran = toleranciaSobran;
        this.tamano = tamano;
    }
    
    public List<Palabra> similares(String letras){
        letras = letras.toLowerCase();
        //Cargar el diccionario.
        Diccionario d;
        if(extendido){
            d = new Diccionario("src/Dics/extendido.txt");      //Esta linea para user dentro del IDE
            //d = new Diccionario("Dics/extendido.txt");        //Esta línea para usar fuera del IDE
        }else{
            d = new Diccionario("src/Dics/estandar.txt");       //Esta linea para user dentro del IDE
            //d = new Diccionario("Dics/estandar.txt");         //Esta línea para usar fuera del IDE
        }
        
        ArrayList<Palabra> palabras = new ArrayList<>();
        String palabra;
        Palabra resultado;    //variable temporal
        
        //Recorrer todas las palabras del diccionario
        while(d.existeSiguiente()){
           palabra = d.siguiente();
           if(tamano == -1 || palabra.length() == tamano){
               resultado = diferencia(letras,palabra);
                if(resultado.getSobran() <= toleranciaSobran && resultado.getFaltan() <= toleranciaFaltan){  //Si culple los requisitos, crea el objeto y lo devuelve
                    palabras.add(resultado);
                }
           }
        }
        
        return palabras;
    }
    
    public List<Palabra> similares(String letras, String ayuda){
        letras = letras.toLowerCase();
        ayuda = ayuda.toLowerCase();    // letras de ayuda
        //Cargar el diccionario.
        Diccionario d;
        if(extendido){
            d = new Diccionario("src/Dics/extendido.txt");      //Esta linea para user dentro del IDE
            //d = new Diccionario("Dics/extendido.txt");        //Esta línea para usar fuera del IDE
        }else{
            d = new Diccionario("src/Dics/estandar.txt");       //Esta linea para user dentro del IDE
            //d = new Diccionario("Dics/estandar.txt");         //Esta línea para usar fuera del IDE
        }
        
        ArrayList<Palabra> palabras = new ArrayList<>();
        String palabra;
        Palabra resultado;    //variable temporal
        
        //Recorrer todas las palabras del diccionario
        while(d.existeSiguiente()){
           palabra = d.siguiente();
           if(tamano == -1 || palabra.length() == tamano){
               resultado = diferencia(letras, palabra, ayuda);
                if(resultado.getSobran() <= toleranciaSobran && resultado.getFaltan() <= toleranciaFaltan){  //Si culple los requisitos, crea el objeto y lo devuelve
                    palabras.add(resultado);
                }
           }
        }
        
        return palabras;
    }
    
    
    private Palabra diferencia(String letras, String original){
        String adaptada = eliminarTildes(original);    //No distingue entre vocales con tilde
        int j, sobran = 0, faltan = 0;

        //Pasa por todas las letras de la palabra
        for(int i = 0; i < adaptada.length(); i++){
            j = 0;
            //Busca coincidencias entre las letras introducidas
            while(j < letras.length() && j != -1){
                if(adaptada.charAt(i) == letras.charAt(j)){
                    letras = letras.replaceFirst(adaptada.charAt(i)+"",""); //Elimina la letra
                    j = -1; //acaba el bucle con resultado afirmativo
                }else{
                    j++;
                }
            }
            //Si el resultado no es afirmativo, esa letra falta
            if(j != -1)
                faltan++;
        }
        sobran = letras.length();   //Las letras que no hayan sido eliminadas, sobran

        return new Palabra(original,sobran,faltan);
    }
    
    private Palabra diferencia(String letras, String original, String ayuda){
        String adaptada = eliminarTildes(original);    //No distingue entre vocales con tilde
        int j, sobran = 0, faltan = 0;

        //Pasa por todas las letras de la palabra
        for(int i = 0; i < adaptada.length(); i++){
            j = 0;
            //Busca coincidencias entre las letras introducidas
            while(j < letras.length() && j != -1){
                if(adaptada.charAt(i) == letras.charAt(j) && (ayuda.charAt(i) == adaptada.charAt(i) || ayuda.charAt(i) == '*')){
                    letras = letras.replaceFirst(adaptada.charAt(i)+"",""); //Elimina la letra
                    j = -1; //acaba el bucle con resultado afirmativo
                }else{
                    j++;
                }
            }
            //Si el resultado no es afirmativo, esa letra falta
            if(j != -1)
                faltan++;
        }
        sobran = letras.length();   //Las letras que no hayan sido eliminadas, sobran

        return new Palabra(original,sobran,faltan);
    }
    
    private String eliminarTildes(String palabra){
        return palabra.replace('á', 'a')
                .replace('é', 'e')
                .replace('í', 'i')
                .replace('ó', 'o')
                .replace('ú', 'u');
    }
    
}
