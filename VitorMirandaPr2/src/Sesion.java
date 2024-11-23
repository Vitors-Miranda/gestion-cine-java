import java.time.*;
import java.util.ArrayList;
public class Sesion {
    private int id;
    private float precio;
    private String horaSesion;
    private float recaudacion;
    private Sala sala;

    public Sesion(float precio, String hora, Sala sala, int id){
        this.precio = precio;
        this.horaSesion = hora;
        this.sala = sala;
        this.id = id;
    }

    public String getSala() {
        return sala.getNumero();
    }

    public Entrada reservarEntrada(int fila, int butaca){
        return new Entrada();
    };

    public ArrayList<Entrada> reservarEntradas(int cantidad){
        return new ArrayList<Entrada>();
    };

    public String obtenerEstadoSesion(){
        int fila = this.sala.getFila();
        int butaca = this.sala.getButaca();
        String asientos = "";
        for (int i = 0; i < fila; i++){
            for (int j = 0; j < butaca; j++){
                asientos += " - ";
            }
            asientos += ",";
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
