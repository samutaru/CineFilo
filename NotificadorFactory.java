
package trabajois2;

public class NotificadorFactory {
    public static NotificadorStrategy createStrategy(String tipoStrategia,Usuario u){
        NotificadorStrategy strategy=null;
        if(tipoStrategia.equals("Whatsapp"))
            strategy=new WhatsappStrategy(u.getTlf());
        else if (tipoStrategia.equals("Email"))
            strategy=new EmailStrategy(u.getEmail());
        
        return strategy;
            
    }
}
