package trabajois2;

public class CashStrategy implements CobroStrategy {

    public CashStrategy() {
    }

    @Override
     public void cobrar() {
        System.out.println("Pago en efectivo: " + precio + "€");
    }

}
