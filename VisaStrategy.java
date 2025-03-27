/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajois2;

/**
 *
 * @author RAIDE
 */
public class VisaStrategy implements CobroStrategy {

    public VisaStrategy(float precio) {
    }

    @Override
    public void cobrar(double precio) {
        System.out.println("Pago con tarjeta: " + precio + "€");
    }

}
