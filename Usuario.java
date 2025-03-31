package trabajois2;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String email;
    private String tlf;
    private boolean esClienteFrecuente;


    private List<Entrada> historialCompras;

    public Usuario(String nombre, String email, String tlf) {
        this.nombre = nombre;
        this.email = email;
        this.tlf = tlf;
        this.esClienteFrecuente = false;
        this.historialCompras = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTlf() {
        return tlf;
    }

    public List<Entrada> getHistorialCompras() {
        return historialCompras;
    }
        public boolean EsClienteFrecuente() {
        return esClienteFrecuente;
    }

    public void setEsClienteFrecuente(boolean esClienteFrecuente) {
        this.esClienteFrecuente = esClienteFrecuente;
    }

    public void agregarCompra(Entrada e) {
        historialCompras.add(e);
        // Comprobar si el usuario se convierte en cliente frecuente
        if (historialCompras.size() >= 3) {
            esClienteFrecuente = true;
        }

        System.out.println("Entrada comprada: " + e.getClass().getSimpleName() + " por " + e.getPrecio() + "€");
    }

    public void mostrarHistorial() {
        System.out.println("\nHistorial de compras de " + nombre + ":");
        if (historialCompras.isEmpty()) {
            System.out.println("No hay compras registradas.");
        } else {
            for (Entrada e : historialCompras) {
                System.out.println("- " + e.getClass().getSimpleName() + " | Precio: " + e.getPrecio() + "€");
            }
        }
    }
}
