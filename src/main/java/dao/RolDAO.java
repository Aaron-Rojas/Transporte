
package dao;

import Modelo.Rol;
import Conexión.Conexión;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {
    //metodo para guardar
    public boolean guardarRol(Rol rol) {
        String sql = "INSERT INTO Rol (NombreRol, Descripcion) VALUES (?, ?)";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, rol.getNombreRol());
            stmt.setString(2, rol.getDescripcion());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        rol.setIdRol(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al guardar rol: " + e.getMessage());
            return false;
        }
    }
    //metodo para obtener las lissta de roles
    public List<Rol> obtenerTodosLosRoles() {
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT ID_Rol, NombreRol, Descripcion FROM Rol";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("ID_Rol"));
                rol.setNombreRol(rs.getString("NombreRol"));
                rol.setDescripcion(rs.getString("Descripcion"));
                roles.add(rol);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener roles: " + e.getMessage());
        }
        return roles;
    }
    //meotodo para el filtrado por ide
    public Rol obtenerRolPorId(int idRol) {
        Rol rol = null;
        String sql = "SELECT ID_Rol, NombreRol, Descripcion FROM Rol WHERE ID_Rol = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRol);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    rol = new Rol();
                    rol.setIdRol(rs.getInt("ID_Rol"));
                    rol.setNombreRol(rs.getString("NombreRol"));
                    rol.setDescripcion(rs.getString("Descripcion"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener rol por ID: " + e.getMessage());
        }
        return rol;
    }
    //metodo para actualizar Rol
    public boolean actualizarRol(Rol rol) {
        String sql = "UPDATE Rol SET NombreRol=?, Descripcion=? WHERE ID_Rol=?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rol.getNombreRol());
            stmt.setString(2, rol.getDescripcion());
            stmt.setInt(3, rol.getIdRol());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar rol: " + e.getMessage());
            return false;
        }
    }
    //metodo para eliminar rol
    public boolean eliminarRol(int idRol) {
        String sql = "DELETE FROM Rol WHERE ID_Rol=?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRol);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar rol: " + e.getMessage());
            return false;
        }
    }
}