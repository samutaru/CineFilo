
    import java.util.*;

/*** 1️⃣ SINGLETON - Gestión de Sesión ***/
class UsuarioSesion {
    private static UsuarioSesion instancia;
    private String usuario;

    private UsuarioSesion() {}

    public static UsuarioSesion getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioSesion();
        }
        return instancia;
    }

    public void iniciarSesion(String usuario) {
        this.usuario = usuario;
        System.out.println("Usuario autenticado: " + usuario);
    }

    public void cerrarSesion() {
        System.out.println("Cierre de sesión: " + usuario);
        usuario = null;
    }
}

/*** 2️⃣ FACTORY METHOD - Creación de Tickets ***/
abstract class Ticket {
    protected double precio;
    abstract void mostrarDetalles();
}

class TicketEstandar extends Ticket {
    public TicketEstandar() { this.precio = 5.0; }
    public void mostrarDetalles() { System.out.println("Ticket estándar: $" + precio); }
}

class TicketPremium extends Ticket {
    public TicketPremium() { this.precio = 15.0; }
    public void mostrarDetalles() { System.out.println("Ticket Premium: $" + precio); }
}

class TicketFactory {
    public static Ticket crearTicket(String tipo) {
        if (tipo.equalsIgnoreCase("premium")) return new TicketPremium();
        return new TicketEstandar();
    }
}

/*** 3️⃣ OBSERVER - Notificaciones ***/
interface Observador {
    void notificar(String mensaje);
}

class WhatsAppNotificacion implements Observador {
    public void notificar(String mensaje) {
        System.out.println("WhatsApp: " + mensaje);
    }
}

class EmailNotificacion implements Observador {
    public void notificar(String mensaje) {
        System.out.println("Email: " + mensaje);
    }
}

class TicketNotifier {
    private List<Observador> observadores = new ArrayList<>();
    public void agregarObservador(Observador obs) { observadores.add(obs); }
    public void notificarUsuarios(String mensaje) {
        for (Observador obs : observadores) obs.notificar(mensaje);
    }
}

/*** 4️⃣ STRATEGY - Métodos de Pago ***/
interface MetodoPago {
    void pagar(double cantidad);
}

class PagoTarjeta implements MetodoPago {
    public void pagar(double cantidad) {
        System.out.println("Pago realizado con tarjeta: $" + cantidad);
    }
}

class PagoPayPal implements MetodoPago {
    public void pagar(double cantidad) {
        System.out.println("Pago realizado con PayPal: $" + cantidad);
    }
}

class PasarelaPago {
    private MetodoPago metodo;
    public PasarelaPago(MetodoPago metodo) { this.metodo = metodo; }
    public void procesarPago(double cantidad) { metodo.pagar(cantidad); }
}

/*** 5️⃣ DECORATOR - Aplicación de Descuentos ***/
interface TicketBase {
    double getPrecio();
}

class TicketSimple implements TicketBase {
    public double getPrecio() { return 10.0; }
}

class DescuentoDecorator implements TicketBase {
    protected TicketBase ticket;
    public DescuentoDecorator(TicketBase ticket) { this.ticket = ticket; }
    public double getPrecio() { return ticket.getPrecio() * 0.8; }
}

/*** 6️⃣ MEMENTO - Sistema de Valoraciones ***/
class ValoracionMemento {
    private final String comentario;
    private final int puntuacion;
    public ValoracionMemento(String comentario, int puntuacion) {
        this.comentario = comentario;
        this.puntuacion = puntuacion;
    }
    public String getComentario() { return comentario; }
    public int getPuntuacion() { return puntuacion; }
}

class Valoracion {
    private String comentario;
    private int puntuacion;

    public void setValoracion(String comentario, int puntuacion) {
        this.comentario = comentario;
        this.puntuacion = puntuacion;
    }

    public ValoracionMemento guardar() {
        return new ValoracionMemento(comentario, puntuacion);
    }

    public void restaurar(ValoracionMemento memento) {
        this.comentario = memento.getComentario();
        this.puntuacion = memento.getPuntuacion();
    }

    public void mostrar() {
        System.out.println("Valoración: " + comentario + " - " + puntuacion + " estrellas");
    }
}

/*** MAIN PARA PRUEBAS ***/
public class Poto {
    public static void main(String[] args) {
        // Singleton - Gestión de Sesión
        UsuarioSesion usuario = UsuarioSesion.getInstancia();
        usuario.iniciarSesion("Carlos");

        // Factory - Creación de Tickets
        Ticket miTicket = TicketFactory.crearTicket("premium");
        miTicket.mostrarDetalles();

        // Observer - Notificaciones
        TicketNotifier notifier = new TicketNotifier();
        notifier.agregarObservador(new WhatsAppNotificacion());
        notifier.agregarObservador(new EmailNotificacion());
        notifier.notificarUsuarios("Tu ticket ha sido generado.");

        // Strategy - Métodos de Pago
        PasarelaPago pago = new PasarelaPago(new PagoTarjeta());
        pago.procesarPago(20.0);

        // Decorator - Descuento aplicado
        TicketBase ticketConDescuento = new DescuentoDecorator(new TicketSimple());
        System.out.println("Precio con descuento: $" + ticketConDescuento.getPrecio());

        // Memento - Sistema de Valoraciones
        Valoracion valoracion = new Valoracion();
        valoracion.setValoracion("Gran película!", 5);
        ValoracionMemento memento = valoracion.guardar();
        valoracion.setValoracion("No estuvo tan buena...", 3);
        valoracion.mostrar();
        valoracion.restaurar(memento);
        valoracion.mostrar();
    }
}

   

