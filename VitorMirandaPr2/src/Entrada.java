public class Entrada {
    private int fila;
    private int butaca;
    private String sala;
    private String pelicula;
    private Sesion sesion;
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
        return "Entrada - Sesión "+sesion.getSala().getNumero()+", Fila: "+fila+", Butaca: "+butaca;
    }
}
