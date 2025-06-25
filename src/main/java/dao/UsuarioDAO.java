
package dao;

import Modelo.Usuario;
import Modelo.Rol;
import Conexión.Conexión;
/*
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
/*
    private String hashContrasena(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasena.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error al hashear contraseña: " + e.getMessage());
            throw new RuntimeException("Error en el algoritmo de hashing.", e);
        }
    }
*/
    /*
    public Usuario validarCredenciales(String nombreCompleto, String contrasena) { 
        Usuario usuario = null;
        // La columna para el login es 'NombreCompleto' en tu BD
        String sql = "SELECT ID_Usuario, NombreCompleto, Correo, Contraseña, Estado, ID_Rol FROM usuario WHERE NombreCompleto = ? AND Contraseña = ? "; 

        //String contrasenaHasheada = hashContrasena(contrasena);

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreCompleto); 
            stmt.setString(2, contrasena);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("ID_Usuario"));
                    usuario.setNombreCompleto(rs.getString("NombreCompleto")); // <-- Mapeando a NombreCompleto
                    usuario.setCorreo(rs.getString("Correo"));
                    usuario.setContraseña(rs.getString("Contraseña"));
                    usuario.setEstado(rs.getString("Estado"));
                    usuario.setIdRol(rs.getInt("ID_Rol"));

                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
        }
        return usuario;
    }
*/
    public boolean guardarUsuario(Usuario usuario) {
        // Ajusta el INSERT para usar 'NombreCompleto'
        String sql = "INSERT INTO Usuario (NombreCompleto, Correo, Contrasena, Estado, ID_Rol) VALUES (?, ?, ?, ?, ?)";
    //    String contrasenaHasheada = hashContrasena(usuario.getContrasena());

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNombreCompleto()); // <-- Usando NombreCompleto
            stmt.setString(2, usuario.getCorreo());
   //         stmt.setString(3, contrasenaHasheada);
            stmt.setString(4, usuario.getEstado());
            stmt.setInt(5, usuario.getIdRol());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setIdUsuario(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        // Ajusta el SELECT para usar 'NombreCompleto'
        String sql = "SELECT u.ID_Usuario, u.NombreCompleto, u.Correo, u.Contrasena, u.Estado, u.ID_Rol, r.NombreRol, r.Descripcion " +
                     "FROM Usuario u JOIN Rol r ON u.ID_Rol = r.ID_Rol";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("ID_Usuario"));
                usuario.setNombreCompleto(rs.getString("NombreCompleto")); // <-- Mapeando a NombreCompleto
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setContraseña(rs.getString("Contrasena"));
                usuario.setEstado(rs.getString("Estado"));
                usuario.setIdRol(rs.getInt("ID_Rol"));

                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("ID_Rol"));
                rol.setNombreRol(rs.getString("NombreRol"));
                rol.setDescripcion(rs.getString("Descripcion"));
                usuario.setRol(rol);

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        // Ajusta el SELECT para usar 'NombreCompleto'
        String sql = "SELECT u.ID_Usuario, u.NombreCompleto, u.Correo, u.Contrasena, u.Estado, u.ID_Rol, r.NombreRol, r.Descripcion " +
                     "FROM Usuario u JOIN Rol r ON u.ID_Rol = r.ID_Rol " +
                     "WHERE u.ID_Usuario = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("ID_Usuario"));
                    usuario.setNombreCompleto(rs.getString("NombreCompleto")); // <-- Mapeando a NombreCompleto
                    usuario.setCorreo(rs.getString("Correo"));
                    usuario.setContraseña(rs.getString("Contrasena"));
                    usuario.setEstado(rs.getString("Estado"));
                    usuario.setIdRol(rs.getInt("ID_Rol"));

                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("ID_Rol"));
                    rol.setNombreRol(rs.getString("NombreRol"));
                    rol.setDescripcion(rs.getString("Descripcion"));
                    usuario.setRol(rol);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por ID: " + e.getMessage());
        }
        return usuario;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        // Ajusta el UPDATE para usar 'NombreCompleto'
        String sql = "UPDATE Usuario SET NombreCompleto=?, Correo=?, Contrasena=?, Estado=?, ID_Rol=? WHERE ID_Usuario=?";

        String contrasenaAUsar;
        if (usuario.getContraseña() != null && !usuario.getContraseña().isEmpty()) {
   //         contrasenaAUsar = hashContrasena(usuario.getContrasena());
        } else {
            Usuario usuarioExistente = obtenerUsuarioPorId(usuario.getIdUsuario());
            if (usuarioExistente != null) {
                contrasenaAUsar = usuarioExistente.getContraseña();
            } else {
                System.err.println("Error: No se encontró el usuario para actualizar la contraseña existente.");
                return false;
            }
        }

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreCompleto()); // <-- Usando NombreCompleto
            stmt.setString(2, usuario.getCorreo());
 //           stmt.setString(3, contrasenaAUsar);
            stmt.setString(4, usuario.getEstado());
            stmt.setInt(5, usuario.getIdRol());
            stmt.setInt(6, usuario.getIdUsuario());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE ID_Usuario=?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
    
    
   public boolean agregarUsuario(Usuario usuario){
       String sql = "INSERT INTO usuario (NombreCompleto ,Correo, Contraseña, Estado, ID_Rol) values (?,?,?,?,?)";
       try (Connection conn = Conexión.conectar()){
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setString(1,usuario.getNombreCompleto());
           pstmt.setString(2, usuario.getCorreo());
           pstmt.setString(3, usuario.getContraseña());
           pstmt.setString(4, usuario.getEstado());
           pstmt.setInt(5,usuario.getIdRol());
           int rowsAffected = pstmt.executeUpdate();
           return rowsAffected > 0;
       }catch(SQLException e){
            // Manejo de errores más específico, ej. si el username o email ya existen (UNIQUE)
            if (e.getSQLState().startsWith("23")) { // SQLState para violación de restricción de integridad (ej. UNIQUE)
                System.err.println("Error: El nombre de usuario o correo electrónico ya existen.");
            } else {
                System.err.println("Error SQL al agregar usuario: " + e.getMessage());
            }
            e.printStackTrace();
            return false;   
       }   
   }
   
       // Método para validar el login y obtener el usuario (incluyendo su rol)
    public Usuario validarLogin(String nombreCompleto, String contraseña) {
        String sql = "SELECT u.ID_Usuario, u.NombreCompleto, u.Correo, u.Contraseña, u.ID_Rol, u.Estado, r.NombreRol, r.Descripcion " +
                     "FROM Usuario u JOIN Rol r ON u.ID_Rol = r.ID_Rol " +
                     "WHERE u.NombreCompleto = ? AND u.Contraseña = ? AND u.Estado = 'activo'"; // Solo usuarios activos

        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreCompleto);
            pstmt.setString(2, contraseña); // Recuerda: Validar con el hash de la contraseña
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Si se encuentra un usuario, lo retornamos
                    int idRol = rs.getInt("ID_Rol");
                    String nombreRol =rs.getString("NombreRol");
                    String descripcion = rs.getString("Descripcion");
                    
                    Rol rol = new Rol(idRol,nombreRol,descripcion); //Crear el objeto Rol
                    
               
                   Modelo.Usuario usuario = new Modelo.Usuario(
                    rs.getInt("ID_Usuario"),
                    rs.getString("NombreCompleto"), // Asegúrate de usar "NombreCompleto" como en la DB
                    rs.getString("Correo"),
                    rs.getString("Contraseña"),
                    rs.getString("Estado"),
                    idRol,        
                    rol
                );
                return usuario;
            }
        }
    } catch (SQLException e) {
        System.err.println("Error SQL al validar login: " + e.getMessage());
        e.printStackTrace();
    }
    return null; // Credenciales inválidas o error
    }
}