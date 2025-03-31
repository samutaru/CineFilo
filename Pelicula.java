
package trabajois2;

import java.util.ArrayList;
import java.util.List;

public class Pelicula implements SujetoReseñas {
    private String titulo;
    private List<Entrada> entradasVendidas;
    private List<Reseña> reseñas;
    private List<ObservadorReseñas> observadores;

    public Pelicula(String titulo) {
        this.titulo = titulo;
        this.entradasVendidas = new ArrayList<>();
        this.reseñas = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void venderEntrada(Entrada entrada) {
        entradasVendidas.add(entrada);
    }

    public void agregarReseña(Reseña reseña) {
        reseñas.add(reseña);
        notificarObservadores(reseña);
    }

    public List<Reseña> getReseñas() {
        return reseñas;
    }

    @Override
    public void registrarObservador(ObservadorReseñas o) {
        observadores.add(o);
    }

    @Override
    public void eliminarObservador(ObservadorReseñas o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores(Reseña reseña) {
        for (ObservadorReseñas observador : observadores) {
            observador.actualizar(reseña);
        }
    }
}
