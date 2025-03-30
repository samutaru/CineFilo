package trabajois2;


import java.util.Scanner;

public class Aplicacion {
    private static GestorUsuarios gestorUsuarios = GestorUsuarios.getInstancia();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
        Usuario usuario = gestorUsuarios.getUsuarioActual();
        if (usuario == null) {
            System.out.println("Debe iniciar sesión primero.");
            return;
        }

        while (true) {
            System.out.println("\nMenú de Usuario:");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Ver historial de compras");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 1) {
                comprarEntrada(usuario);
            } else if (opcion == 2) {
                usuario.mostrarHistorial();
            } else if (opcion == 3) {
                gestorUsuarios.cerrarSesion();
                break;
            }
        }
    }

    private static void comprarEntrada(Usuario usuario) {
        System.out.println("\nTipos de entrada:");
        System.out.println("1. Estándar (€9.99)");
        System.out.println("2. Premium (€15.99)");
        System.out.print("Seleccione una opción: ");
        int tipoEntrada = scanner.nextInt();
        scanner.nextLine(); 

        Entrada entrada;
        if (tipoEntrada == 1) {
            entrada = new EntradaEstandar();
        } else {
            entrada = new EntradaPremium();
        }

        usuario.agregarCompra(entrada);
    }
}
