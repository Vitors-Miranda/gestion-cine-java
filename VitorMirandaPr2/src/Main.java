import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {

    //TRATAMIENTO DE ERRORES

    //Integer Control de errores
    public static Integer checkInteger(Scanner scanner, String text, int lastIndice){

        int number = -1;

        while (number < 0) {
            try{
                System.out.println(text);
                number = scanner.nextInt();

                if (lastIndice > 0){ //Son opiciones limitadas
                    if (number > lastIndice){ //El valor es mayor que el  numero maximo de opiciones
                        number = 0;
                    } else{ //normalizando el vetor
                        number --;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Respuesta inválida!");
                scanner.nextLine();
            }
        }
        return number;
    }

    //Float Control de errores
    public static float checkFloat(Scanner scanner, String text){
        float number = 0;

        while (number < 1) {
            try{
                System.out.println(text);
                number = scanner.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println("Respuesta inválida!");
                scanner.nextLine();
            }
        }
        return number;
    }
    //Genero control de errores
    public static String checkGender(Scanner scanner, String[] movieGender){
        int index = -1;

        while (index == -1) {
            try {
                //Recibindo el genero del usuario
                System.out.println("Escriba el genero de la pelicula:");
                System.out.println("1. Drama");
                System.out.println("2. Terror");
                System.out.println("3. Comédia");
                System.out.println("4. Ciencia Ficción");
                index = (scanner.nextInt() - 1);

                if (index < 0 || index > 3) {
                    index = -1;
                    System.out.println("Respuesta Inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Respuesta inválida!");
                scanner.nextLine(); // Discartar la entrada invalida
            }
        }
        return movieGender[index];
    }
    //String control de errores
    public static String stringCheck(String question, Scanner scanner) {
        String text = null;
        while (text == null) {
                System.out.println(question); //recibindo la pregunta
                text = scanner.nextLine();
                if (text.length() < 3) {
                    text = null;
                    System.out.println("Nombre Inválido! Mínimo de 3 caracteres");
                }
        }

        return text;
    }
    //LocalTime
    public static LocalTime localTimeCheck(LocalTime HoraSesion, Scanner scanner, DateTimeFormatter formatter) {
        while (HoraSesion == null) {
            try {
                System.out.println("Cual hora de la sesion? (HH:mm)");
                String _horaSesion = scanner.nextLine(); //recibindo como una String
                HoraSesion = LocalTime.parse(_horaSesion, formatter); //convertiendo para LocalTime
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido!");
            }
        }

        return HoraSesion;
    }

    public static void main(String[] args) {

        //constantes
        int option;
        final int ANADIR = 1;
        final int ELIMINAR = 2;
        final int CREAR = 3;
        final int MOSTRAR = 4;
        final int COMPRAR = 5;
        final int RECAUDACION = 6;
        final int SALIR = 0;

        Scanner scanner = new Scanner(System.in);

        //menu
        System.out.println("----------------------------");
        System.out.println("Beienvenido al Cine Vitor y Pedro Lima!");
        System.out.println("---------------------------");


        //creando las salas
        Sala sala1 = new Sala("Sala Comun 5", 5, 5);
        Sala sala2 = new Sala("Sala VIP 10", 7, 4);

        //creando el cine y agregando las salas
        Cine cine1 = new Cine("Cine Vitor y Pedro", "Linares");
        cine1.AgregarSala(sala1);
        cine1.AgregarSala(sala2);

        //declaracion de variables
        String title, gender;
        int duration, nSala, nSesion, asientosLibres;
        float precio, ocupacion, recaudaciones;

        //declaracion de listas y arrays
        ArrayList<Sala> salas = new ArrayList<Sala>();
        ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
        String[] movieGender = {"Drama", "Terror", "Comédia", "Ficción"};


        //localTime para la hora de la Sesion
        LocalTime horaSesion = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); //estandar


        do{
            //menu de acciones del usuario
            System.out.println();
            System.out.println("1. anadir pelicula");
            System.out.println("2. eliminar pelicula");
            System.out.println("3. crear sesion");
            System.out.println("4. mostrar estado de sesion");
            System.out.println("5. comprar entrada");
            System.out.println("6. ver recaudacion");
            System.out.println("0. salir");

            //generos de la pelicula
            option = checkInteger(scanner, "Qué quiere hacer hoy?", 0);

            switch (option) {
                case ANADIR:
                    salas = cine1.getSalas(true);

                    if (salas.size() == 0) {// no hay salas libres
                        System.out.println("No hay salas disponibles");
                        break;
                    }
                    //Recibindo el titulo del usuario
                    scanner.nextLine();
                    title = stringCheck("Escriba el titulo de la pelicula:", scanner);

                    gender = checkGender(scanner, movieGender);

                    //Recibindo la duracion
                    duration = checkInteger(scanner, "Escriba la duraccion de la pelicula (minutos):", 0);

                    Pelicula pelicula = new Pelicula(title, gender, duration);//Creando el objeto de la pelicula

                    //Muestrando las salas disponibles
                    System.out.println("Salas disponibles:");

                    for (int i = 0; i < salas.size(); i++) {
                            System.out.println((i+1) + "." + salas.get(i).getNumero());
                    }

                    //Recibindo la sala del usuaio
                    nSala = checkInteger(scanner, "Cual sala le gustaria anadir la pelicula? ", salas.size());

                    cine1.AgregarPelicula(salas.get(nSala).getNumero(), pelicula); //anadindo la pelicula en la sala

                    break;
                case ELIMINAR:
                    salas = cine1.getSalas(false);

                    if (salas.size() == 0) {// no hay peliculas registradas
                        System.out.println("No hay salas con peliculas");
                        break;
                    }

                    //Muestrando las peliculas disponibles
                    System.out.println("Películas disponibles: ");
                    for (int i = 0; i < salas.size(); i++) {
                            System.out.println((i+1)+". Película: " + salas.get(i).getPelicula().getTitulo() + " (" + salas.get(i).getNumero()  + ")"  );
                    }

                    //Recibindo la sala que tiene la pelicula a eliminar
                    nSala = checkInteger(scanner, "Cuál película deseas eliminar?", salas.size());

                    cine1.EliminarPelicula(salas.get(nSala).getNumero());

                    break;
                case CREAR:
                    salas = cine1.getSalas(false);

                    if (salas.size() == 0) { // no hay salas disponibles
                        System.out.println("No hay salas con peliculas.");
                        break;
                    }

                    //muestrar las salas disponibles
                    System.out.println("Salas disponibles:");
                    for (int i = 0; i < salas.size(); i++) {
                            System.out.println((i+1) + "." + salas.get(i).getNumero());
                    }

                    //Recibindo la session del usuario
                    nSala = checkInteger(scanner, "Cual sala le gustaria crear sesion?", salas.size());

                    //Recibindo el precio
                    precio = checkFloat(scanner, "Cual precio de la sesion? ");

                    scanner.nextLine();

                    //Recibindo el horario
                    horaSesion = localTimeCheck(horaSesion, scanner, formatter);

                    System.out.println(salas.get(nSala).getNumero());
                    cine1.CrearSession(precio, horaSesion, salas.get(nSala).getNumero());

                    break;
                case MOSTRAR:
                    sesiones = cine1.getSesiones();
                    if (sesiones.size() == 0) {
                        System.out.println("No hay sesiones disponibles");
                        break;
                    }

                    //mestrando sesiones disponibles
                    System.out.println("Sesiones disponibles:");

                    for (int i = 0; i < sesiones.size(); i++) {
                            System.out.println((i+1) + "." + sesiones.get(i).getSala().getNumero());
                    }

                    //Recibindo la sesion
                    nSesion = checkInteger(scanner, "Selecione una sesion:", salas.size());

                    int fila =  sesiones.get(nSesion).getSala().getFila();
                    int butaca =  sesiones.get(nSesion).getSala().getButaca();

                    //muestrando el estado de la session (grafica)
                    String[][]  sesionGrafica = sesiones.get(nSesion).obtenerEstadoSesion();
                    for (int i = 0; i < fila; i++){
                        for (int j = 0; j < butaca; j++){
                            System.out.print(sesionGrafica[i][j]);
                        }
                        System.out.println();
                    }

                    //asientos libres libres y porcentaje de ocupacion
                    asientosLibres = sesiones.get(nSesion).obtenerAsientosLibres();
                    ocupacion = sesiones.get(nSesion).obtenerPorcentajeOcupacion();
                    System.out.println("Asientos Libres: " + asientosLibres);
                    System.out.println("Porcentaje de Ocupacion: " + ocupacion);
                    scanner.nextLine();
                    scanner.nextLine();
                    break;

                case COMPRAR:
                    break;

                case RECAUDACION:

                    sesiones = cine1.getSesiones();
                    if (sesiones.size() == 0) { //no hay sesiones
                        System.out.println("No hay sesiones disponibles");
                        break;
                    }

                    recaudaciones = cine1.obtenerRecaudacion();
                    System.out.println("Recaudacion total del cine: " + recaudaciones);
                    break;

                case SALIR:
                    System.out.println("Adios!");
                    break;
                    
                default:
                    System.out.println("Opcion no valida");
            }
        } while(option != 0);
    }
}