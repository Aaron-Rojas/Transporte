package dao;

import Modelo.TipoServicio;
import Conexión.Conexión;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoServicioDAO {
    // Método para guardar un tipo de servicio
    public boolean guardarTipoServicio(TipoServicio tipoServicio) {
        String sql = "INSERT INTO tiposervicio (NombreTipo, Descripcion) VALUES (?, ?)";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, tipoServicio.getNombreTipo());
            stmt.setString(2, tipoServicio.getDescripcion());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        tipoServicio.setIdTipoServicio(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al guardar tipo de servicio: " + e.getMessage());
            return false;
        }
    }
    
    // Método para obtener la lista de todos los tipos de servicio
    public List<TipoServicio> obtenerTodosLosTiposServicio() {
        List<TipoServicio> tiposServicio = new ArrayList<>();
        String sql = "SELECT ID_TipoServicio, NombreTipo, Descripcion FROM tiposervicio";
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idTipoServicio = rs.getInt("ID_TipoServicio");
                String nombreTipo = rs.getString("NombreTipo");
                String descripcion = rs.getString("Descripcion");
                
                tiposServicio.add(new TipoServicio(idTipoServicio, nombreTipo, descripcion));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipos de servicio: " + e.getMessage());
            e.printStackTrace();
        }
        return tiposServicio;
    }
    
    // Método para obtener un tipo de servicio por su ID
    public TipoServicio obtenerTipoServicioPorId(int idTipoServicio) {
        TipoServicio tipoServicio = null;
        String sql = "SELECT ID_TipoServicio, NombreTipo, Descripcion FROM tiposervicio WHERE ID_TipoServicio = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTipoServicio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tipoServicio = new TipoServicio();
                    tipoServicio.setIdTipoServicio(rs.getInt("ID_TipoServicio"));
                    tipoServicio.setNombreTipo(rs.getString("NombreTipo"));
                    tipoServicio.setDescripcion(rs.getString("Descripcion"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipo de servicio por ID: " + e.getMessage());
        }
        return tipoServicio;
    }

    // Método para actualizar un tipo de servicio
    public boolean actualizarTipoServicio(TipoServicio tipoServicio) {
        String sql = "UPDATE tiposervicio SET NombreTipo=?, Descripcion=? WHERE ID_TipoServicio=?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipoServicio.getNombreTipo());
            stmt.setString(2, tipoServicio.getDescripcion());
            stmt.setInt(3, tipoServicio.getIdTipoServicio());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar tipo de servicio: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un tipo de servicio
    public boolean eliminarTipoServicio(int idTipoServicio) {
        String sql = "DELETE FROM tiposervicio WHERE ID_TipoServicio=?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTipoServicio);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar tipo de servicio: " + e.getMessage());
            return false;
        }
    }

    // Método adicional: Obtener tipo de servicio por nombre
    public TipoServicio obtenerTipoServicioPorNombre(String nombreTipo) {
        TipoServicio tipoServicio = null;
        String sql = "SELECT ID_TipoServicio, NombreTipo, Descripcion FROM tiposervicio WHERE NombreTipo = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreTipo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tipoServicio = new TipoServicio();
                    tipoServicio.setIdTipoServicio(rs.getInt("ID_TipoServicio"));
                    tipoServicio.setNombreTipo(rs.getString("NombreTipo"));
                    tipoServicio.setDescripcion(rs.getString("Descripcion"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipo de servicio por nombre: " + e.getMessage());
        }
        return tipoServicio;
    }
}
