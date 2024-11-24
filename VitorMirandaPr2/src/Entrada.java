public class Entrada {
    private Sesion sesion;
    private int fila;
    private int butaca;
    private String sala;
    private String pelicula;

    public Entrada(Sesion sesion, int fila, int butaca) {
        this.sesion = sesion;
        this.fila = fila;
        this.butaca = butaca;
    }

    public int getFila(){
        return fila;
    }

    public void setFila(int fila){
        this.fila = fila;
    }

    public String obtenerInfo(){
        return "Entrada - Sesión "+sesion.getSala()+", Fila: "+fila+", Butaca: "+butaca;
    }
}
