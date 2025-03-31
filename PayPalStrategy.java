package trabajois2;

public class PayPalStrategy implements CobroStrategy {


    public PayPalStrategy() {
    }

    @Override
    public void cobrar(double precio) {
        System.out.println("Pago con PayPal: " + precio + "€");
    }

}
