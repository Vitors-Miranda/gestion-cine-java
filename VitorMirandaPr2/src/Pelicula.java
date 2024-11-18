public class Pelicula {
    private String titulo;
    private int duration;//minutes
    // [“Drama”, “Terror”, “Comedia”, “Ciencia Ficción”]
    private String genero;

    public Pelicula (String titulo, String genero, int duration) {
        this.titulo = titulo;
        this.duration = duration;
        this.genero = genero;
    }
}
