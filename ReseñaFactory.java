/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajois2;

public class ReseñaFactory {
    public static Reseña crearReseña(String usuario, String pelicula, String comentario, int calificacion) {
        return new Reseña(usuario, pelicula, comentario, calificacion);
    }
}
