package trabajois2;

public class EntradaDecorator {
     protected Entrada entrada;
    
    public EntradaDecorator(Entrada entrada) {
        this.entrada = entrada;
    }
    
    public double getPrecio() {
        return entrada.getPrecio();
    }
}

class DescuentoClienteFrecuente extends EntradaDecorator {
    public DescuentoClienteFrecuente(Entrada entrada) {
        super(entrada);
    }
    
    @Override
    public double getPrecio() {
        return entrada.getPrecio() * 0.8; // 20% de descuento
    }
}
