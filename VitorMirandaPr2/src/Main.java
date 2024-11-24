import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
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
        System.out.println("Bienvenido al Cine Vitor!");
        System.out.println("---------------------------");



        Sala sala1 = new Sala("Sala Comun 5", 5, 5); //creating sala 1
        Sala sala2 = new Sala("Sala VIP 10", 7, 4); //creating sala 2

        Cine cine1 = new Cine("Cine Vitor y Pedro", "Linares"); //creating cine 1
        cine1.AgregarSala(sala1); //adding "sala 1" to cine 1
        cine1.AgregarSala(sala2); //adding "sala 2" to cine 1

        String title, gender;
        int duration, nSala, nSesion, asientosLibres, nEntradas;
        float precio, ocupacion;
        String horaSesion;

        ArrayList<Sala> salas;
        ArrayList<Sesion> sesiones;

        do{
            //user method choice
            System.out.println("Qué quiere hacer hoy?");
            System.out.println("1. Anadir película");
            System.out.println("2. Eliminar película");
            System.out.println("3. Crear sesión");
            System.out.println("4. Mostrar estado de sesión");
            System.out.println("5. Comprar entrada");
            System.out.println("6. Ver recaudación");
            System.out.println("0. Salir");

            option = scanner.nextInt();
            String[] movieGender = {"Drama", "Terror", "Comédia", "Ciencia Ficción"};

            switch (option) {
                case ANADIR:

                    System.out.println("Escriba el titulo de la pelicula:"); //title
                    title = scanner.next();

                    System.out.println("Escriba el género:"); //gender
                    System.out.println("1. Drama");
                    System.out.println("2. Terror");
                    System.out.println("3. Comédia");
                    System.out.println("4. Ciencia Ficción");
                    gender = movieGender[(scanner.nextInt()-1)];


                    System.out.println(gender);

                    System.out.println("Escriba la duración en minutos:"); //time
                    duration = scanner.nextInt();

                    Pelicula pelicula = new Pelicula(title, gender, duration);//Creating the movie

                    //show disponible rooms
                    System.out.println("Salas disponibles:"); 
                    salas = cine1.getSalas();

                    for (int i = 0; i < salas.size(); i++) {
                        if (salas.get(i).getPelicula() == null) { //if the room has not a movie
                            System.out.println((i+1) + "." + salas.get(i).getNumero());
                        }
                    }

                    System.out.println("Cual sala le gustaria anadir la pelicula? ");
                    nSala = scanner.nextInt();

                    cine1.AgregarPelicula(salas.get(nSala-1).getNumero(), pelicula);

                    break;
                case ELIMINAR:
                    System.out.println("Películas disponibles: ");
                    salas = cine1.getSalas();
                    for (int i = 0; i < salas.size(); i++) {
                        if (salas.get(i).getPelicula() != null) { //if the room has a movie
                            System.out.println((i+1)+". Película: " + salas.get(i).getPelicula().getTitulo() + " (" + salas.get(i).getNumero()  + ")"  );
                        }
                    }
                    System.out.println("\nCuál película deseas eliminar?");
                    nSala = scanner.nextInt();
                    cine1.EliminarPelicula(salas.get(nSala-1).getNumero());

                    break;
                case CREAR:
                    //show disponible rooms
                    System.out.println("Salas disponibles:");
                    salas = cine1.getSalas();

                    for (int i = 0; i < salas.size(); i++) {
                        if (salas.get(i).getPelicula() != null) { //if the room has not a movie
                            System.out.println((i+1) + "." + salas.get(i).getNumero());
                        }
                    }

                    System.out.println("En cuál sala le gustaria crear sesión? ");
                    nSala = scanner.nextInt();

                    System.out.println("Cuál precio de la sesión? ");
                    precio = scanner.nextFloat();

                    System.out.println("Cuál hora de la sesion? ");
                    horaSesion = scanner.next();

                    System.out.println(salas.get(nSala-1).getNumero());

                    cine1.CrearSession(precio, horaSesion, salas.get(nSala-1).getNumero());

                    break;
                case MOSTRAR:
                    //show disponible sessions
                    System.out.println("Sesiones disponibles:");
                    sesiones = cine1.getSesiones();

                    for (int i = 0; i < sesiones.size(); i++) {
                            System.out.println((i+1) + "." + sesiones.get(i).getSala());
                    }

                    System.out.println("Selecione una sesión: ");
                    nSesion = scanner.nextInt();

                    //mostrar estado de la sesión seleccionada
                    System.out.println("Estado de la sesión:");
                    System.out.println(sesiones.get(nSesion-1).obtenerEstadoSesion());


                    asientosLibres = sesiones.get(nSesion-1).obtenerAsientosLibres();
                    ocupacion = sesiones.get(nSesion-1).obtenerPorcentajeOcupacion();

                    System.out.println("Asientos Libres: " + asientosLibres);
                    System.out.println("Porcentaje de Ocupacion: " + ocupacion);

                    break;
                case COMPRAR:


                    //show disponible sessions
                    System.out.println("Sesiones disponibles:");
                    sesiones = cine1.getSesiones();

                    for (int i = 0; i < sesiones.size(); i++) {
                        System.out.println((i+1) + "." + sesiones.get(i).getSala());
                    }

                    System.out.print("Selecione una sesión: ");
                    nSesion = scanner.nextInt();

                    //mostrar estado de la sesión seleccionada
                    System.out.println("Estado de la sesión:");
                    System.out.println(sesiones.get(nSesion-1).obtenerEstadoSesion());


                    //mostrar asientos libres y porcentaje de ocupación
                    asientosLibres = sesiones.get(nSesion-1).obtenerAsientosLibres();
                    ocupacion = sesiones.get(nSesion-1).obtenerPorcentajeOcupacion();

                    System.out.println("Asientos Libres: " + asientosLibres);
                    System.out.println("Porcentaje de Ocupacion: " + ocupacion);

                    System.out.print("Ingrese el número de entradas a comprar (máximo 5): ");
                    nEntradas = scanner.nextInt();

                    do {//Entradas != 1
                        if (nEntradas>5){
                            System.out.println("No se pueden comprar más de cinco entradas.");
                        }

                        if(nEntradas<5 && nEntradas!=1){

                        ArrayList<Entrada> entradas = cine1.comprarEntradas(nEntradas, nSesion-1);

                        if (entradas.isEmpty()){
                            System.out.println("No hay asientos suficientes disponibles");
                        }else {
                            System.out.println("Entradas compradas correctamente: ");
                            for (Entrada entrada : entradas){
                                System.out.println(entrada.obtenerInfo());
                            }
                        }
                        }

                        //Comprar única entrada  (nEntradas==1)
                        if (nEntradas==1){
                            System.out.print("Ingrese la fila del asiento: ");
                            int fila = scanner.nextInt();
                            System.out.print("Ingrese el número de la butaca: ");
                            int butaca = scanner.nextInt();

                            Entrada entrada = cine1.comprarEntrada(fila-1,butaca-1,nSesion-1);

                            if(entrada != null){
                                System.out.println("Información de la entrada comprada: ");
                                System.out.println(entrada.obtenerInfo());
                            }else {
                                System.out.println("No se pudo comprar la entrada.");
                            }

                        }

                    }while(nEntradas>5);


                    break;
                case RECAUDACION:

                    break;
                case SALIR:

                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(option != 0);
    }
}