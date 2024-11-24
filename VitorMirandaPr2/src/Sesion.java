import java.util.ArrayList;
public class Sesion {
    private int id;
    private float precio;
    private String horaSesion;
    private float recaudacion;
    private Sala sala;
    private boolean[][] asientoS;

    public Sesion(float precio, String hora, Sala sala, int id){
        this.precio = precio;
        this.horaSesion = hora;
        this.sala = sala;
        this.id = id;
        this.asientoS = new boolean[sala.getFila()][sala.getButaca()];
    }

    public String getSala() {
        return sala.getNumero();
    }
    public int getId(){
        return this.id;
    }
    public Entrada reservarEntrada(int fila, int butaca){
        //Validando si la fila y la butaca están dentro de los límites
        if (fila<0||fila>=asientoS.length||butaca<0||butaca>=asientoS[fila].length){
            System.out.println("Asiento fuera de rango.");
            return null;
        }

        //Verificando si el asiento está disponible
        if (!asientoS[fila][butaca]){
            asientoS[fila][butaca] = true; //Reservar asiento
            recaudacion += precio; //Actualizar recaudación
            return new Entrada(this,fila,butaca);
        }else {
            System.out.println("El asiento ya está ocupado.");
            return null;
        }
    };

    public ArrayList<Entrada> reservarEntradas(int cantidad){
        ArrayList<Entrada> entradas = new ArrayList<>();
        int filas = asientoS.length;
        int columnas = asientoS[0].length;

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

    private ArrayList<Entrada> reservarConsecutivos(int fila, int cantidad){
        ArrayList<Entrada> entradas = new ArrayList<>();
        int columnas = asientoS[fila].length;

        for (int inicio = 0; inicio <= columnas - cantidad; inicio++) {
            boolean disponibles = true;

            //Verifico si los asientos están libres
            for (int j = 0; j < cantidad; j++) {
               if (asientoS[fila][inicio + j]){
                   disponibles = false;
                   break;
               }
            }

            //Reservo si están disponibles
            if (disponibles){
                for (int j = 0; j <cantidad; j++) {
                    asientoS[fila][inicio + j]=true;
                    entradas.add(new Entrada(this,fila,inicio+j));
                }
                recaudacion += cantidad*precio;
                return entradas;

            }

        }

        return new ArrayList<>();
    }


    public String obtenerEstadoSesion(){
       int fila = this.sala.getFila();
        int butaca = this.sala.getButaca();

        String resultado = "";

        for (int i = 0; i < asientoS.length; i++){
            for (int j = 0; j < asientoS[i].length; j++) {
                if (asientoS[i][j]) {
                    resultado += "*";//asiento ocupado
                } else {
                    resultado += "-";//asiento libre
                }
            }
            resultado += "\n";

            }
        return resultado;
        }

    public int obtenerAsientosLibres() {
        int libres =0;

        for (int i = 0; i < asientoS.length; i++) {
            for (int j = 0; j < asientoS[i].length; j++) {
                if (!asientoS[i][j]){
                    libres++;
                }
            }
        }
        return libres;
    };

    public float obtenerPorcentajeOcupacion(){
        return 100 - (float) (obtenerAsientosLibres() * 100) / (this.sala.getFila() * this.sala.getButaca());
    };

    }


