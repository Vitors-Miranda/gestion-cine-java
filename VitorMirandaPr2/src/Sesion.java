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

    public Entrada reservarEntrada(int fila, int butaca){
        return new Entrada();
    };

    public ArrayList<Entrada> reservarEntradas(int cantidad){
        return new ArrayList<Entrada>();
    };

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
