/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajois2;

public class EmailStrategy implements NotificadorStrategy {
    private String email;

    public EmailStrategy(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando entrada por Email a " + getEmail());
        System.out.println("Detalles de la entrada: \n" + mensaje);
    }
}
