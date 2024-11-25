import java.time.LocalTime;

import java.util.ArrayList;
public class Sesion {
    private int id;
    private float precio;
    private LocalTime horaSesion;
    private float recaudacion = 0;
    private Sala sala;
    private boolean[][] asientos;

    public Sesion(float precio, LocalTime hora, Sala sala, int id){
        this.precio = precio;
        this.horaSesion = hora;
        this.sala = sala;
        this.id = id;
        asientos = new boolean[sala.getFila()][sala.getButaca()];
    }

    public Sala getSala() {
        return this.sala;
    }
    public int getId(){
        return this.id;
    }
    public Entrada reservarEntrada(int fila, int butaca){
        //Validando si la fila y la butaca están dentro de los límites
        if (fila<0||fila>=asientos.length||butaca<0||butaca>=asientos[fila].length){
            System.out.println("Asiento fuera de rango.");
            return null;
        }
        //Verificando si el asiento está disponible
        if (!asientos[fila][butaca]){
            asientos[fila][butaca] = true; //Reservar asiento
            recaudacion += precio; //Actualizar recaudación
            return new Entrada(this,fila,butaca);
        }else {
            System.out.println("El asiento ya está ocupado.");
            return null;
        }
    };

    private int distribuirButacas(int fila, int columnas, int asientosReservados, int cantidad, ArrayList<Entrada> entradas) {
        int centro = columnas / 2; // Índice central de la fila

        // Buscar desde el centro hacia los extremos
        for (int despl = 0; despl <= centro && asientosReservados < cantidad; despl++) {
            int izquierda = centro - despl;
            int derecha = centro + despl;

            if (izquierda >= 0 && !asientos[fila][izquierda]) { // Reservar en el lado izquierdo
                asientos[fila][izquierda] = true;
                entradas.add(new Entrada(this, fila, izquierda));
                asientosReservados++;
            }
            if (derecha < columnas && izquierda != derecha && !asientos[fila][derecha]) { // Reservar en el lado derecho
                asientos[fila][derecha] = true;
                entradas.add(new Entrada(this, fila, derecha));
                asientosReservados++;
            }
        }

        return asientosReservados;
    }

    public ArrayList<Entrada> reservarEntradas(int cantidad){
        ArrayList<Entrada> entradas = new ArrayList<>();
        int filas = asientos.length;
        int columnas = asientos[0].length;

        //Primero intento reservar bloques consecutivos priorizando filas y butacas centrales
        for (int i = filas / 2; i >= 0; i--) { // Buscar en filas cercanas al centro hacia arriba
            ArrayList<Entrada> consecutivos = reservarConsecutivos(i, cantidad);
            if (!consecutivos.isEmpty()) {
                return consecutivos; // Si encontramos un bloque consecutivo, devolvemos las entradas
            }
        }
        for (int i = (filas / 2) + 1; i < filas; i++) { // Buscar en filas cercanas al centro hacia abajo
            ArrayList<Entrada> consecutivos = reservarConsecutivos(i, cantidad);
            if (!consecutivos.isEmpty()) {
                return consecutivos; // Si encontramos un bloque consecutivo, devolvemos las entradas
            }
        }
        // Luego, procedo a asignar asientos distribuidos priorizando butacas centrales
        int asientosReservados = 0;
        for (int i = filas / 2; i >= 0 && asientosReservados < cantidad; i--) { // Filas desde el centro hacia arriba
            asientosReservados = distribuirButacas(i, columnas, asientosReservados, cantidad, entradas);

        }
        for (int i = (filas / 2) + 1; i < filas && asientosReservados < cantidad; i++) { // Filas desde el centro hacia abajo
            asientosReservados = distribuirButacas(i, columnas, asientosReservados, cantidad, entradas);

        }
        // Verificar si se pudieron reservar todas las entradas solicitadas
        if (asientosReservados < cantidad) {
            System.out.println("No se pudieron reservar todas las entradas solicitadas.");
            return new ArrayList<>(); // Devolver lista vacía si no se completó la reserva
        }

        recaudacion += asientosReservados * precio; // Actualizar recaudación
        return entradas;

    };

    public float getPrecio(){
        return this.precio;
    }
    public LocalTime getHora(){
        return this.horaSesion;
    }
    public ArrayList<Entrada> reservarConsecutivos(int fila, int cantidad) {
        ArrayList<Entrada> entradas = new ArrayList<>();
        int columnas = asientos[fila].length;
        for (int inicio = 0; inicio <= columnas - cantidad; inicio++) {
            boolean disponibles = true;
            //Verifico si los asientos están libres
            for (int j = 0; j < cantidad; j++) {
                if (asientos[fila][inicio + j]) {
                    disponibles = false;
                    break;
                }
            }
            //Reservo si están disponibles
            if (disponibles) {
                for (int j = 0; j < cantidad; j++) {
                    asientos[fila][inicio + j] = true;
                    entradas.add(new Entrada(this, fila, inicio + j));
                }
                recaudacion += cantidad * precio;
                return entradas;
            }
        }
        return new ArrayList<>();
    }
    public String[][] obtenerEstadoSesion(){
        int fila = this.sala.getFila();
        int butaca = this.sala.getButaca();
        String[][] asientos = new String[fila][butaca];

        //devuelvendo una matriz grafica
        for (int i = 0; i < fila; i++){
            for (int j = 0; j < butaca; j++){
                asientos[i][j] = !this.asientos[i][j] ? " - " : " # ";
            }
        }

        return asientos;
    };
    public int obtenerAsientosLibres() {
        int totalAsientos = this.sala.getFila() * this.sala.getButaca();
        int ocupadas = (int) (this.recaudacion / this.precio);
        return totalAsientos - ocupadas; //libres = total - ocupadas
    };

    public float obtenerPorcentajeOcupacion(){
        int porcentajeTotal = 100;
        int totalAsientos = this.sala.getFila() * this.sala.getButaca();
        float porcentajeLibres = (float) (obtenerAsientosLibres() * porcentajeTotal) / totalAsientos; // regla de 3
        return porcentajeTotal -  porcentajeLibres; // porcentaje ocupacion = 100 - porcentajelibres
    };
    public float getRecaudacion(){
        return this.recaudacion;
    }
}
