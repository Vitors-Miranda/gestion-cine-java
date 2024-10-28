import java.time.*;
import java.util.ArrayList;
public class Sesion {
    private int id;
    private float precio;
    private LocalTime horaInicio = LocalTime.now();
    private float recaudacion;

    public Entrada reservarEntrada(int fila, int butaca){
        return new Entrada();
    };

    public ArrayList<Entrada> reservarEntradas(int cantidad){
        return new ArrayList<Entrada>();
    };

    public String obtenerEstadoSesion(){
        return "";
    };
    public int obtenerAsientosLibres(){
        return 0;
    };
    public float obtenerPorcentajeOcupacion(){
        return 0;
    };
}
