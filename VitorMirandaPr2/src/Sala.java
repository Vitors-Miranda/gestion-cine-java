public class Sala {
    private String numero;
    private int fila;
    private int butaca;
    private Cine cine;
    private Pelicula pelicula;

    public Sala(String numero, int fila, int butaca) {
        this.numero = numero;
        this.fila = fila;
        this.butaca = butaca;
    }

    public int getButaca() {
        return butaca;
    }

    public int getFila() {
        return fila;
    }

    public String getNumero(){
        return this.numero;
    }
    public Pelicula getPelicula(){
        return this.pelicula;
    }
    public void setPelicula(Pelicula pelicula){
       this.pelicula = pelicula;
    }
    public boolean estaVacia(){
        return this.pelicula == null;
    }

}
