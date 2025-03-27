package trabajois2;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements Sujeto, Persona {

    private static Usuario instancia;
    private String nombre;
    private String email;
    private String tlf;
    private boolean esClienteFrecuente;
    private List<Entrada> historialCompras;
    private List<Observador> observadores = new ArrayList<>();

    private Usuario(String nombre, String email, String tlf) {
        this.nombre = nombre;
        this.email = email;
        this.tlf = tlf;
        this.esClienteFrecuente = false; // Por defecto, no es cliente frecuente
        this.historialCompras = new ArrayList<>();
    }

    public static Usuario getInstancia(String nombre, String email, String tlf) {
        if (instancia == null) {
            instancia = new Usuario(nombre, email, tlf);
        }
        return instancia;
    }

    public static Usuario getInstancia() {
        return instancia;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getTlf() {
        return tlf;
    }

    @Override
    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public boolean isClienteFrecuente() {
        return esClienteFrecuente;
    }

    public void setClienteFrecuente(boolean esClienteFrecuente) {
        this.esClienteFrecuente = esClienteFrecuente;
    }

    public void agregarCompra(Entrada e) {
        historialCompras.add(e);
        notifyObservers();
    }

    @Override
    public void registerObserver(Observador o) {
        observadores.add(o);
    }

    @Override
    public void removeObserver(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notifyObservers() { //Cada vez que se compra una entrada se notifica
        for (Observador o : observadores) {
            o.update(historialCompras.get(historialCompras.size() - 1));
        }
    }

}
