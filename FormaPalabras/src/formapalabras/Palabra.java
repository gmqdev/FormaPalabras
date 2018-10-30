package formapalabras;

public class Palabra {
    public String palabra;
    public int sobran;
    public int faltan;
    
    public Palabra(String palabra, int sobran, int faltan){
        this.palabra = palabra;
        this.sobran = sobran;
        this.faltan = faltan;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getSobran() {
        return sobran;
    }

    public int getFaltan() {
        return faltan;
    }
    
    
}
