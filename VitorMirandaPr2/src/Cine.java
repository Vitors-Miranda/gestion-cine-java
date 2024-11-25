import java.util.ArrayList;
import java.util.Objects;
import java.time.LocalTime;

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
    public ArrayList<Sala> getSalas(boolean vazia){
        ArrayList<Sala> salas = new ArrayList<>();

            for (Sala sala : this.salas){
                if (vazia && sala.estaVacia()) {
                    salas.add(sala);
                } else if (!vazia && !sala.estaVacia()){
                    salas.add(sala);
                }
            }
        return salas;
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

    public  void CrearSession(float precio, LocalTime hora,  String nSala){

        for (Sala sala : this.salas) {
            if (Objects.equals(sala.getNumero(), nSala)) {
                
                Sesion sesion = new Sesion(precio, hora, sala, this.lastId);
                this.sesiones.add(sesion);

                this.lastId++;
            }
        }
    }
    public  Entrada comprarEntrada(int fila,int butaca, int idSesion){
        for (Sesion sesion:sesiones){
            if (sesion.getSala().equals(idSesion)){

                //Intento reservar la entrada en la sesión
                Entrada entrada = sesion.reservarEntrada(fila,butaca);
                if (entrada!=null){
                    return entrada;
                }
            }
        }
        return null;
    }
    public  ArrayList<Entrada> comprarEntradas(int cantidad, int idSesion){
        ArrayList<Entrada> entradasCompradas = new ArrayList<>();
        // Buscar la sesión por su ID
        Sesion sesion = null;

        for (Sesion s : sesiones) {
            if (s.getId() == idSesion) {
                sesion = s;
                break; // Se encontró la sesión
            }
        }
        if (sesion == null) {
            return entradasCompradas; // Si no se encuentra la sesión, regresamos una lista vacía
        }
        // Intentamos comprar las entradas
        if (cantidad == 1) {
            // Lógica para reservar una entrada
            Entrada entrada = sesion.reservarEntrada(0, 0); // Suponiendo que el método `reservarEntrada` funciona con índices
            if (entrada != null) {
                entradasCompradas.add(entrada);
            }

        } else {
            // Si se desean comprar múltiples entradas, intentamos reservar asientos consecutivos
            entradasCompradas = sesion.reservarEntradas(cantidad);
        }
        return entradasCompradas;
    }

    public  float obtenerRecaudacion(){
        float recaudacionTotal = 0;
        for (Sesion sesion : this.sesiones) {
            recaudacionTotal += sesion.getRecaudacion();
        }
        return recaudacionTotal;
    }
}
