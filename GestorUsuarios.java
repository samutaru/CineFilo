package trabajois2;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private static GestorUsuarios instancia;
    private List<Usuario> usuarios;
    private Usuario usuarioActual;

    private GestorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public static GestorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }

    public Usuario registrarUsuario(String nombre, String email, String tlf) {
        // Buscar si ya existe un usuario con ese email
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                System.out.println("El usuario ya está registrado.");
                return u;
            }
        }
        // Si no existe, lo crea y lo añade a la lista
        Usuario nuevoUsuario = new Usuario(nombre, email, tlf);
        usuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }

    public Usuario iniciarSesion(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                usuarioActual = u;
                return u;
            }
        }
        System.out.println("Usuario no encontrado.");
        return null;
    }

    public void cerrarSesion() {
        usuarioActual = null;
        System.out.println("Sesión cerrada.");
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
