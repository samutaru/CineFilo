package trabajois2;


public class WhatsappStrategy implements NotificadorStrategy {
    private String tlf;

    public WhatsappStrategy(String tlf) {
        this.tlf = tlf;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando entrada por WhatsApp al número " + getTlf());
        System.out.println("Detalles: " + mensaje);
    }
}
