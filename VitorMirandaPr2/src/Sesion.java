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

    public ArrayList<Entrada> reservarEntradas(int cantidad){
        ArrayList<Entrada> entradas = new ArrayList<>();
        int filas = asientos.length;
        int columnas = asientos[0].length;
        //Busco la fila más central con asientos disponibles consecutivos
        for (int i = filas/2; i >=0 ; i--) {
            entradas = reservarConsecutivos(i,cantidad);
            if (!entradas.isEmpty()){
                return entradas;
            }
        }
        for (int i = (filas/2)+1; i < filas ;i++) {
            entradas = reservarConsecutivos(i,cantidad);
            if (!entradas.isEmpty()){
                return entradas;
            }
        }

        //Si no, no hay asientos disponibles
        return new ArrayList<Entrada>();
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
