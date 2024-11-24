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
        for (Sesion sesion:sesiones){
            if (sesion.getSala().equals(idSesion)){

                //Intento reservar la entrada en la sesión
                Entrada entrada = sesion.reservarEntrada(fila,butaca);
                if (entrada!=null){
                    System.out.println("Entrada comprada correctamente.");
                    return entrada;
                }else {
                    System.out.println("El asiento está ocupado.");
                    return null;
                }
            }

        }

        System.out.println("Sesión no encontrada.");
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
            System.out.println("Sesión no encontrada.");
            return entradasCompradas; // Si no se encuentra la sesión, regresamos una lista vacía
        }

        // Intentamos comprar las entradas
        if (cantidad == 1) {
            // Si solo se compra una entrada, pedimos fila y butaca
            System.out.println("Comprando una entrada...");
            // Lógica para reservar una entrada
            Entrada entrada = sesion.reservarEntrada(0, 0); // Suponiendo que el método `reservarEntrada` funciona con índices
            if (entrada != null) {
                entradasCompradas.add(entrada);
                System.out.println("Entrada comprada correctamente.");
            } else {
                System.out.println("No se pudo comprar la entrada. El asiento está ocupado.");
            }
        } else {
            // Si se desean comprar múltiples entradas, intentamos reservar asientos consecutivos
            System.out.println("Comprando " + cantidad + " entradas...");
            entradasCompradas = sesion.reservarEntradas(cantidad);
            if (entradasCompradas.isEmpty()) {
                System.out.println("No hay suficientes asientos disponibles.");
            } else {
                System.out.println("Entradas compradas correctamente.");
            }
        }

        return entradasCompradas;
    }
    public  String verEstadoSesion( int idSesion){
        return "hello world";
    }
    public  float obtenerRecaudacion(){
        return 1;
    }
    }







