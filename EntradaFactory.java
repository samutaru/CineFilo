/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajois2;

/**
 *
 * @author RAIDE
 */
public class EntradaFactory {

    public static Entrada createEntrada(String tipoEntrada) {
        switch (tipoEntrada.toLowerCase()) {
            case "premium":
                return new EntradaPremium();
            case "estandar":
            default:
                return new EntradaEstandar();
        }

    }
}
