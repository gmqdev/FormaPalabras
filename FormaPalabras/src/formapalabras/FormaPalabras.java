package formapalabras;

import java.util.List;

public class FormaPalabras {

    public static void main(String[] args) {
        Buscador b = new Buscador(false, 0, 100, 7); //Usar diccionario grande, Faltan 0 Sobran 0
        
        //List<Palabra> coincidencias = b.similares("TTDONILUEIHA"); // Solución: INÉDITO
        List<Palabra> coincidencias = b.similares("TTDONILUEIHA", "***D**O"); // Solución: INÉDITO
        
        coincidencias.forEach((coincidencia) -> {
            System.out.println("PALABRA: "+coincidencia.getPalabra()+"\tSOBRAN: "+coincidencia.getSobran()+"\tFALTAN: "+coincidencia.getFaltan());
        });
    }
}
