/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trabajois2;

import java.util.ArrayList;
import java.util.List;

interface ObservadorReseñas {
    void actualizar(Reseña reseña);
}

interface SujetoReseñas {
    void registrarObservador(ObservadorReseñas o);
    void eliminarObservador(ObservadorReseñas o);
    void notificarObservadores(Reseña reseña);
}
