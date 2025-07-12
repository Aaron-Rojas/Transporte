
package dao;

import Modelo.Usuario;
import Modelo.Rol;
import Conexión.Conexión;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {
    
    private RolDAO rolDAO;
    
    public UsuarioDAO(){
     
        this.rolDAO = rolDAO;
    }
 
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        // Ajusta el SELECT para usar 'NombreCompleto'
        String sql = "SELECT u.ID_Usuario, u.NombreCompleto, u.Correo, u.Contraseña, u.Estado, u.ID_Rol, r.NombreRol, r.Descripcion " +
                     "FROM Usuario u JOIN Rol r ON u.ID_Rol = r.ID_Rol";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("ID_Usuario"));
                usuario.setNombreCompleto(rs.getString("NombreCompleto")); // <-- Mapeando a NombreCompleto
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setContraseña(rs.getString("Contraseña"));
                usuario.setEstado(rs.getBoolean("Estado"));
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
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        // Ajusta el SELECT para usar 'NombreCompleto'
        String sql = "SELECT u.ID_Usuario, u.NombreCompleto, u.Correo, u.Contraseña, u.Estado, u.ID_Rol, r.NombreRol, r.Descripcion " +
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
                    usuario.setContraseña(rs.getString("Contraseña"));
                    usuario.setEstado(rs.getBoolean("Estado"));
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
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        String sql;
        String contrasenaHasheada = null;
        
        if (usuario.getContraseña() != null && !usuario.getContraseña().isEmpty()) {
            // Se ha proporcionado una nueva contraseña, hay que hashearla
            contrasenaHasheada = BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt());
            sql = "UPDATE Usuario SET NombreCompleto=?, Correo=?, Contraseña=?, Estado=?, ID_Rol=? WHERE ID_Usuario=?";
        } else {
            // No se proporcionó una nueva contraseña, no actualizamos el campo Contraseña
            sql = "UPDATE Usuario SET NombreCompleto=?, Correo=?, Estado=?, ID_Rol=? WHERE ID_Usuario=?";
        }

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setString(2, usuario.getCorreo());
            
            int paramIndex = 3; // Índice inicial para los parámetros siguientes
            if (contrasenaHasheada != null) {
                stmt.setString(paramIndex++, contrasenaHasheada);
            }
            
            stmt.setBoolean(paramIndex++, usuario.isEstado()); // Usamos setBoolean
            
            if (usuario.getIdRol() > 0) {
                stmt.setInt(paramIndex++, usuario.getIdRol());
            } else {
                stmt.setNull(paramIndex++, java.sql.Types.INTEGER);
            }
            
            stmt.setInt(paramIndex, usuario.getIdUsuario()); // El último parámetro es el ID

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

     public boolean eliminarUsuario(int idUsuario) {
        String sql = "UPDATE Usuario SET Estado = FALSE WHERE ID_Usuario=?"; // Soft delete
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Usuario con ID " + idUsuario + " deshabilitado (estado inactivo).");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al deshabilitar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    
   public boolean agregarUsuario(Usuario usuario){
       // La columna en la BD es 'Contraseña', 'Correo' y 'Estado'
        String sql = "INSERT INTO Usuario (NombreCompleto, Correo, Contraseña, Estado, ID_Rol) VALUES (?, ?, ?, ?, ?)";

        // 1. Hashear la contraseña antes de guardarla
        String contrasenaPlana = usuario.getContraseña();
        String contrasenaHasheada = org.mindrot.jbcrypt.BCrypt.hashpw(contrasenaPlana, org.mindrot.jbcrypt.BCrypt.gensalt());

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, contrasenaHasheada); // Guardamos la contraseña hasheada
            stmt.setBoolean(4, usuario.isEstado()); // Usamos setBoolean para el tipo BIT(1)
            
            // Manejo del ID_Rol: Puede ser 0 o null si no se asigna un rol al crear.
            // Ajusta según si ID_Rol en DB es NULLable o NOT NULL
            if (usuario.getIdRol() > 0) { // Asume que un ID_Rol > 0 es un rol válido
                stmt.setInt(5, usuario.getIdRol());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER); // Si ID_Rol es NULLable en tu DB
            }

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setIdUsuario(rs.getInt(1)); // Asigna el ID generado
                    }
                }
                System.out.println("Usuario " + usuario.getNombreCompleto() + " agregado con éxito.");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
            // Manejo de errores más específico, ej. si el username o email ya existen (UNIQUE)
            if (e.getSQLState() != null && e.getSQLState().startsWith("23")) { // SQLState para violación de restricción de integridad (ej. UNIQUE)
                System.err.println("Error: El nombre de usuario o correo electrónico ya existen.");
            }
            e.printStackTrace();
            return false;
        }
   }
   
       // Método para validar el login y obtener el usuario (incluyendo su rol)
    
   public Usuario validarLogin(String nombreCompleto, String contraseña) {
    String sql = "SELECT ID_Usuario, Contraseña, Estado FROM Usuario WHERE NombreCompleto = ?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Usuario usuarioAutenticado = null;

    try {
        conn = Conexión.conectar(); // Usando tu método conectar()
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nombreCompleto);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            String hashAlmacenado = rs.getString("Contraseña");
            // Obtener el estado del usuario como boolean desde la columna BIT(1)
            boolean estadoUsuario = rs.getBoolean("Estado"); 

            if (estadoUsuario && org.mindrot.jbcrypt.BCrypt.checkpw(contraseña, hashAlmacenado)) {
                usuarioAutenticado = obtenerUsuarioPorId(rs.getInt("ID_Usuario"));
                System.out.println("Autenticación exitosa para: " + nombreCompleto);
            } else {
                // Si el estado es falso O la contraseña no coincide.
                System.out.println("Autenticación fallida: Contraseña incorrecta o usuario inactivo para: " + nombreCompleto);
            }
        } else {
            // No se encontró ningún usuario con ese nombre completo.
            System.out.println("Autenticación fallida: Usuario no encontrado: " + nombreCompleto);
        }
    }catch(IllegalArgumentException e){
        System.err.println("Error de contraseña (formato inválido) para el usuario " + nombreCompleto + ": " + e.getMessage());
        System.err.println("Probable causa: La contraseña almacenada no es un hash BCrypt válido.");
        usuarioAutenticado = null ;
    } catch (SQLException e) {
        System.err.println("Error SQL al validar login: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Asegurarse de cerrar los recursos
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos en validarLogin: " + e.getMessage());
        }
    }
    return usuarioAutenticado;

    }

    // Método para obtener solo Usuarios Activos (Estado = TRUE)

   public List<Usuario> obtenerUsuariosActivos() {
    List<Usuario> usuariosActivos = new ArrayList<>();
    // Filtramos directamente en la consulta por Estado = TRUE
    String sql = "SELECT u.ID_Usuario, u.NombreCompleto, u.Correo, u.Contraseña, u.Estado, u.ID_Rol, r.NombreRol, r.Descripcion " +
                 "FROM Usuario u LEFT JOIN Rol r ON u.ID_Rol = r.ID_Rol " +
                 "WHERE u.Estado = TRUE"; // Aquí usamos TRUE para el tipo BIT(1)

    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("ID_Usuario"));
            usuario.setNombreCompleto(rs.getString("NombreCompleto"));
            usuario.setCorreo(rs.getString("Correo"));
            usuario.setContraseña(rs.getString("Contraseña"));
            usuario.setEstado(rs.getBoolean("Estado")); // Obtener el boolean
            usuario.setIdRol(rs.getInt("ID_Rol"));

            // Mapeo del Rol
            if (!rs.wasNull() && usuario.getIdRol() > 0) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("ID_Rol"));
                rol.setNombreRol(rs.getString("NombreRol"));
                rol.setDescripcion(rs.getString("Descripcion"));
                usuario.setRol(rol);
            } else {
                usuario.setRol(null);
            }
            usuariosActivos.add(usuario);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener usuarios activos: " + e.getMessage());
        e.printStackTrace();
    }
    return usuariosActivos;
}
   
   
   

}