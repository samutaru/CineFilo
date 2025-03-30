package trabajois2;

import java.util.Scanner;

public class Aplicacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Cerrar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (Usuario.getInstancia() == null) {
                        System.out.print("Ingrese su nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese su email: ");
                        String email = scanner.nextLine();
                        System.out.print("Ingrese su teléfono: ");
                        String telefono = scanner.nextLine();

                        Usuario.iniciarSesion(nombre, email, telefono);
                        menuUsuario(scanner);
                    } else {
                        System.out.println("Ya hay una sesión activa. Cierre sesión primero.");
                    }
                    break;

                case 2:
                    Usuario.cerrarSesion();
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void menuUsuario(Scanner scanner) {
        Usuario usuario = Usuario.getInstancia();
        if (usuario == null) return;

        while (true) {
            System.out.println("\nMenú de Usuario:");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Ver historial de compras");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    comprarEntrada(scanner, usuario);
                    break;

                case 2:
                    verHistorial(usuario);
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void comprarEntrada(Scanner scanner, Usuario usuario) {
        System.out.println("Seleccione tipo de entrada:");
        System.out.println("1. Estándar");
        System.out.println("2. Premium");
        System.out.print("Opción: ");
        int tipoEntradaOpcion = scanner.nextInt();
        scanner.nextLine();

        String tipoEntrada = (tipoEntradaOpcion == 2) ? "premium" : "estandar";
        Entrada entrada = EntradaFactory.createEntrada(tipoEntrada);

        if (usuario.isClienteFrecuente()) {
            entrada = new DescuentoClienteFrecuente(entrada);
        }

        System.out.println("Seleccione método de pago:");
        System.out.println("1. Tarjeta");
        System.out.println("2. PayPal");
        System.out.println("3. Efectivo");
        System.out.print("Opción: ");
        int metodoPagoOpcion = scanner.nextInt();
        scanner.nextLine();

        CobroStrategy pago;
        switch (metodoPagoOpcion) {
            case 1:
                pago = new VisaStrategy();
                break;
            case 2:
                pago = new PayPalStrategy();
                break;
            case 3:
                pago = new CashStrategy();
                break;
            default:
                System.out.println("Método de pago no válido. Cancelando compra.");
                return;
        }

        pago.cobrar(entrada.getPrecio());
        usuario.agregarCompra(entrada);

        System.out.println("Seleccione notificación:");
        System.out.println("1. Email");
        System.out.println("2. WhatsApp");
        System.out.print("Opción: ");
        int tipoNotificacionOpcion = scanner.nextInt();
        scanner.nextLine();

        String tipoNotificacion = (tipoNotificacionOpcion == 2) ? "Whatsapp" : "Email";
        NotificadorStrategy notificador = NotificadorFactory.createStrategy(tipoNotificacion, usuario);
        notificador.enviarNotificacion("Compra realizada con éxito. Precio: " + entrada.getPrecio() + "€");
    }

    public static void verHistorial(Usuario usuario) {
        System.out.println("Historial de compras:");
        for (Entrada entrada : usuario.getHistorialCompras()) {
            System.out.println("- " + entrada.getClass().getSimpleName() + " - Precio: " + entrada.getPrecio() + "€");
        }
    }
}
