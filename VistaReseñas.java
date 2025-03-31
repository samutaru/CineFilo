/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajois2;

public class VistaReseñas implements ObservadorReseñas {
    @Override
    public void actualizar(Reseña reseña) {
        System.out.println("\n📢 Nueva Reseña para " + reseña.getPelicula() + ":");
        System.out.println(reseña);
    }
}
