package trabajois2;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {
    private static GestorUsuarios gestorUsuarios = GestorUsuarios.getInstancia();
    private static Scanner scanner = new Scanner(System.in);
    private static List<Pelicula> peliculas = new ArrayList<>();

    public static void main(String[] args) {
        peliculas.add(new Pelicula("El Señor de los Anillos"));
        peliculas.add(new Pelicula("El Padrino"));
        peliculas.add(new Pelicula("Interstellar"));
        VistaReseñas vistaReseñas = new VistaReseñas();
        for (Pelicula pelicula : peliculas) {
            pelicula.registrarObservador(vistaReseñas);
        }

        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 1) {
                registrarUsuario();
            } else if (opcion == 2) {
                iniciarSesion();
            } else if (opcion == 3) {
                System.out.println("Saliendo...");
                break;
            }else{
                System.out.println("Opcion incorrecta");
            }
        }
    }

    private static void registrarUsuario() {
        System.out.print("\nIngrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su teléfono: ");
        String tlf = scanner.nextLine();

        Usuario usuario = gestorUsuarios.registrarUsuario(nombre, email, tlf);
        System.out.println("Usuario registrado con éxito.");
    }

    private static void iniciarSesion() {
        System.out.print("\nIngrese su email: ");
        String email = scanner.nextLine();

        Usuario usuario = gestorUsuarios.iniciarSesion(email);
        if (usuario != null) {
            menuUsuario();
        }
    }

    private static void menuUsuario() {
        Usuario usuarioActivo = gestorUsuarios.getUsuarioActual();
        if (usuarioActivo == null) {
            System.out.println("Debe iniciar sesión primero.");
            return;
        }

        while (true) {
    System.out.println("\nMenú:");
    System.out.println("1. Cerrar sesion");
    System.out.println("2. Comprar entrada");
    System.out.println("3. Escribir reseña");
    System.out.println("4. Ver reseñas");
    System.out.print("Seleccione una opcion: ");

    int opcion = scanner.nextInt();
    scanner.nextLine();

    switch (opcion) {
        case 1:
            gestorUsuarios.cerrarSesion();
            return;
        case 2:
            if (usuarioActivo != null) comprarEntrada(usuarioActivo);
            else System.out.println("Debe iniciar sesion.");
            break;
        case 3:
            escribirReseña(usuarioActivo);
            break;
        case 4:
            verReseñas();
            break;
        default:
            System.out.println("Opcion invalida.");
    }
}

    }

private static void comprarEntrada(Usuario usuario) {
    System.out.println("\nSeleccione una película:");
    for (int i = 0; i < peliculas.size(); i++) {
        System.out.println((i + 1) + ". " + peliculas.get(i).getTitulo());
    }
    System.out.print("Seleccione una opción: ");
    int seleccionPelicula = scanner.nextInt();
    scanner.nextLine();

    if (seleccionPelicula < 1 || seleccionPelicula > peliculas.size()) {
        System.out.println("Selección inválida.");
        return;
    }

    Pelicula peliculaSeleccionada = peliculas.get(seleccionPelicula - 1);

    System.out.println("\nTipos de entrada:");
    System.out.println("1. Estándar (€9.99)");
    System.out.println("2. Premium (€15.99)");
    System.out.print("Seleccione una opción: ");
    int tipoEntrada = scanner.nextInt();
    scanner.nextLine();

    Entrada entrada;
    if (tipoEntrada == 1) {
        entrada = new EntradaEstandar();
    } if(tipoEntrada == 2) {
        entrada = new EntradaPremium();
    }else { System.out.println("Opcion incorrecta, procederas con la opcion Estandar:");
            entrada = new EntradaEstandar();

    }

    // Aplicar descuento si es cliente frecuente
    if (usuario.EsClienteFrecuente()) {
        entrada = new DescuentoClienteFrecuente(entrada);
        System.out.println("Descuento aplicado. Nuevo precio: " + entrada.getPrecio() + "€");
    }

    // SELECCIONAR MÉTODO DE PAGO USANDO PATRÓN ESTRATEGIA
    System.out.println("\nMétodo de pago:");
    System.out.println("1. Efectivo");
    System.out.println("2. Tarjeta Visa");
    System.out.println("3. PayPal");
    System.out.print("Seleccione una opción: ");
    int metodoPagoOpcion = scanner.nextInt();
    scanner.nextLine();

    CobroStrategy metodoPago;
    switch (metodoPagoOpcion) {
        case 1:
            metodoPago = new CashStrategy();
            break;
        case 2:
            metodoPago = new VisaStrategy();
            break;
        case 3:
            metodoPago = new PayPalStrategy();
            break;
        default:
            System.out.println("Opción inválida. Se usará pago en efectivo por defecto.");
            metodoPago = new CashStrategy();
            break;
    }

    // COBRAR AL USUARIO
    metodoPago.cobrar(entrada.getPrecio());

    // REGISTRAR LA COMPRA
    peliculaSeleccionada.venderEntrada(entrada);
    usuario.agregarCompra(entrada);

        // SELECCIONAR MÉTODO DE NOTIFICACIÓN
    System.out.println("Seleccione notificación:");
    System.out.println("1. Email");
    System.out.println("2. WhatsApp");
    System.out.print("Opción: ");
    int tipoNotificacionOpcion = scanner.nextInt();
    scanner.nextLine();
    if(tipoNotificacionOpcion<1 ||tipoNotificacionOpcion>2 ){
        System.out.println("Opcion incorrecta, se continuara con la opcion 1");
        tipoNotificacionOpcion = 1;
    }

    String tipoNotificacion = (tipoNotificacionOpcion == 2) ? "Whatsapp" : "Email";
    NotificadorStrategy notificador = NotificadorFactory.createStrategy(tipoNotificacion, usuario);

    String mensajeEntrada = "Película: " + peliculaSeleccionada.getTitulo() + 
                            "\nPrecio: " + entrada.getPrecio() + "€" +
                            "\nTipo: " + (tipoEntrada == 1 ? "Estándar" : "Premium");

    notificador.enviarNotificacion(mensajeEntrada);

}

    private static void escribirReseña(Usuario usuario) {
        System.out.println("\nSeleccione una película para reseñar:");
        for (int i = 0; i < peliculas.size(); i++) {
            System.out.println((i + 1) + ". " + peliculas.get(i).getTitulo());
        }
        System.out.print("Seleccione una opción: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion < 1 || seleccion > peliculas.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Pelicula peliculaSeleccionada = peliculas.get(seleccion - 1);
        boolean exito=false;
        int calificacion=0;
        while(!exito){
        System.out.print("Ingrese su calificación (1-5): ");
        calificacion = scanner.nextInt();
        scanner.nextLine();
        if(calificacion <1 || calificacion >5){
            System.out.println("Nota incorrecta, vuelve a intentarlo");
        }else{
            exito = true;
        }
        }
        System.out.print("Escriba su comentario: ");
        String comentario = scanner.nextLine();

        String nombreUsuario = (usuario != null) ? usuario.getNombre() : null;
        Reseña reseña = ReseñaFactory.crearReseña(nombreUsuario, peliculaSeleccionada.getTitulo(), comentario, calificacion);

        peliculaSeleccionada.agregarReseña(reseña);

        System.out.println("\n Reseña publicada con éxito.");
    }
    private static void verReseñas() {
    System.out.println("\nSeleccione una película para ver reseñas:");
    for (int i = 0; i < peliculas.size(); i++) {
        System.out.println((i + 1) + ". " + peliculas.get(i).getTitulo());
    }
    System.out.print("Seleccione una opción: ");
    int seleccion = scanner.nextInt();
    scanner.nextLine();

    if (seleccion < 1 || seleccion > peliculas.size()) {
        System.out.println("Selección inválida.");
        return;
    }

    Pelicula peliculaSeleccionada = peliculas.get(seleccion - 1);

    System.out.println("\n Reseñas para " + peliculaSeleccionada.getTitulo() + ":");
    for (Reseña r : peliculaSeleccionada.getReseñas()) {
        System.out.println(r);
    }
}

}
