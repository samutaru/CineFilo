/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajois2;

public class Reseña {
    private String usuario;
    private String pelicula;
    private String comentario;
    private int calificacion; // de 1 a 5 estrellas

    public Reseña(String usuario, String pelicula, String comentario, int calificacion) {
        this.usuario = (usuario == null || usuario.isEmpty()) ? "Anónimo" : usuario;
        this.pelicula = pelicula;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public String getPelicula() {
        return pelicula;
    }

    public String getComentario() {
        return comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return usuario + " ⭐" + calificacion + "/5\n" + comentario + "\n";
    }
}
