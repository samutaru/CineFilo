package trabajois2;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements Sujeto, Persona {
    private static Usuario instancia = null; // Singleton
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
        this.esClienteFrecuente = false;
        this.historialCompras = new ArrayList<>();
    }

    public static Usuario getInstancia() {
        return instancia;
    }

    public static void iniciarSesion(String nombre, String email, String tlf) {
        if (instancia == null) {
            instancia = new Usuario(nombre, email, tlf);
            System.out.println("Sesión iniciada: " + nombre);
        } else {
            System.out.println("Ya hay una sesión activa. Cierre sesión primero.");
        }
    }

    public static void cerrarSesion() {
        if (instancia != null) {
            System.out.println("Cerrando sesión de: " + instancia.getNombre());
            instancia = null;
        } else {
            System.out.println("No hay una sesión activa.");
        }
    }

    public List<Entrada> getHistorialCompras() {
        return historialCompras;
    }

    public void agregarCompra(Entrada e) {
        historialCompras.add(e);
        notifyObservers();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isClienteFrecuente() {
        return esClienteFrecuente;
    }

    public void setClienteFrecuente(boolean esClienteFrecuente) {
        this.esClienteFrecuente = esClienteFrecuente;
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
    public void notifyObservers() {
        for (Observador o : observadores) {
            o.update(historialCompras.get(historialCompras.size() - 1));
        }
    }

    public static void setInstancia(Usuario instancia) {
        Usuario.instancia = instancia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public boolean isEsClienteFrecuente() {
        return esClienteFrecuente;
    }

    public void setEsClienteFrecuente(boolean esClienteFrecuente) {
        this.esClienteFrecuente = esClienteFrecuente;
    }

    public void setHistorialCompras(List<Entrada> historialCompras) {
        this.historialCompras = historialCompras;
    }
}
