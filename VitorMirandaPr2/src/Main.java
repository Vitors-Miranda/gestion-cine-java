import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

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
        System.out.println("Beienvenido al Cine Vitor!");
        System.out.println("---------------------------");



        Sala sala1 = new Sala("Sala Comun 5", 5, 5); //creating sala 1
        Sala sala2 = new Sala("Sala VIP 10", 7, 4); //creating sala 2

        Cine cine1 = new Cine("Cine Vitor", "Linares"); //creating cine 1
        cine1.AgregarSala(sala1); //adding "sala 1" to cine 1
        cine1.AgregarSala(sala2); //adding "sala 2" to cine 1

        String title, gender;
        int duration, nSala;
        ArrayList<Sala> salas = new ArrayList<Sala>();

        do{
            //user method choice
            System.out.println("Qué quiere hacer hoy?");
            System.out.println("1. anadir pelicula");
            System.out.println("2. eliminar pelicula");
            System.out.println("3. crear sesion");
            System.out.println("4. mostrar estado de sesion");
            System.out.println("5. comprar entrada");
            System.out.println("6. ver recaudacion");
            System.out.println("0. salir");

            option = scanner.nextInt();
            String[] movieGender = {"Drama", "Terror", "Comédia", "Ficción"};

            switch (option) {
                case ANADIR:

                    System.out.println("Escriba el titulo de la pelicula:"); //title
                    title = scanner.next();

                    System.out.println("Escriba el genero de la pelicula:"); //gender
                    System.out.println("1. Drama");
                    System.out.println("2. Terror");
                    System.out.println("3. Comédia");
                    System.out.println("4. Ficción");
                    gender = movieGender[(scanner.nextInt()-1)];

                    System.out.println(gender);

                    System.out.println("Escriba la duraccion de la pelicula (minutos):"); //time
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
                    break;
                case CREAR:
                    break;
                case MOSTRAR:
                    break;
                case COMPRAR:
                    break;
                case RECAUDACION:
                    break;
                case SALIR:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while(option != 0);
    }
}