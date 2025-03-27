package trabajois2;

public class CashStrategy implements CobroStrategy {

    public CashStrategy(float precio) {
    }

    @Override
     public void cobrar(double precio) {
        System.out.println("Pago en efectivo: " + precio + "€");
    }

}
