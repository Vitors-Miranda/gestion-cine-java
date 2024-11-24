import java.time.LocalTime;

import java.util.ArrayList;
public class Sesion {
    private int id;
    private float precio;
    private LocalTime horaSesion;
    private float recaudacion;
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


        for (int i = 0; i < fila; i++){
            for (int j = 0; j < butaca; j++){
                asientos[i][j] = !this.asientos[i][j] ? " - " : " # ";
            }
        }

        return asientos;
    };
    public int obtenerAsientosLibres() {
        return this.sala.getFila() * this.sala.getButaca();
    };

    public float obtenerPorcentajeOcupacion(){
        return 100 - (float) (obtenerAsientosLibres() * 100) / (this.sala.getFila() * this.sala.getButaca());
    };
}
