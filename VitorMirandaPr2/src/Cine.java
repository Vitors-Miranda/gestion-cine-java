import java.util.ArrayList;
import java.util.Objects;

public class Cine {
    private String nombre;
    private String ubicacion;
    private ArrayList<Sala> salas = new ArrayList<Sala>();
    private ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
    private int  lastId = 0;
    
    public Cine(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public  void AgregarSala(Sala sala){
        this.salas.add(sala);
    }
    public ArrayList<Sala> getSalas(){
        return this.salas;
    }
    public  void AgregarPelicula(String nSala, Pelicula pelicula){
        for (Sala sala : this.salas) {
            if (Objects.equals(sala.getNumero(), nSala)) {
                sala.setPelicula(pelicula);
            }
        }

    }
    public  void EliminarPelicula(String nSala){
        for (Sala sala : this.salas) {
            if (Objects.equals(sala.getNumero(), nSala)) {
                sala.setPelicula(null);
            }
        }
    }
    public ArrayList<Sesion> getSesiones(){
        return this.sesiones;
    }

    public  void CrearSession(float precio, String hora,  String nSala){

        for (Sala sala : this.salas) {
            if (Objects.equals(sala.getNumero(), nSala)) {
                
                Sesion sesion = new Sesion(precio, hora, sala, this.lastId);
                this.sesiones.add(sesion);

                this.lastId++;
            }
        }
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
