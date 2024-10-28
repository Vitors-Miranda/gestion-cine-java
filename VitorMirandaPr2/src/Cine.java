import java.util.ArrayList;

public class Cine {
    private String nombre;
    private String ubicacion;

    public  void AgregarSala(Sala sala){

    }
    public  void AgregarPelicula(String nSala, Pelicula pelicula){

    }
    public  void EliminarPelicula(String nSala, Pelicula pelicula){

    }
    public  void CrearSession(float precio, String hora,  String nSala){

    }
    public  Entrada comprarEntrada(int fila,int butaca, int idSesion){
        return new Entrada();
    }
    public  ArrayList<Entrada> comprarEntradas(int cantidade, int idSesion){
        return new ArrayList<Entrada>();
    }
    public  String verEstadoSesion( int idSesion){
        return "hello world";
    }
    public  float obtenerRecaudacion(){
        return 1;
    }
}
