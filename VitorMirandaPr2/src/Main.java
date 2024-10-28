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

        //menu
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------");
        System.out.println("Beienvenido al Cine Vitor!");
        System.out.println("---------------------------");

        Sala sala1 = new Sala("5", 5, 5);
        Sala sala2 = new Sala("10", 7, 4);

        do{
            System.out.println("Qué quiere hacer hoy?");
            System.out.println("1. anadir pelicula");
            System.out.println("2. eliminar pelicula");
            System.out.println("3. crear sesion");
            System.out.println("4. mostrar estado de sesion");
            System.out.println("5. comprar entrada");
            System.out.println("6. ver recaudacion");
            System.out.println("0. salir");
            option = scanner.nextInt();

            switch (option) {
                case ANADIR:

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