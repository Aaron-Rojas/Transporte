package dao;

import Conexión.Conexión;
import Modelo.ItinerarioViaje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItinerarioViajeDAO {
    private Connection conexion;

    public ItinerarioViajeDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

    public boolean insertarItinerario(ItinerarioViaje itinerario) throws SQLException {
        String sql = "INSERT INTO itinerario_viajebus (ID_Itinerario, ID_ViajeProgramado, AsientoAsignado, Costo, estado) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Validar que existan los IDs referenciados
            if (!existeIdEnTabla("itinerario", itinerario.getIdItinerario()) || 
                !existeIdEnTabla("viaje_programado", itinerario.getIdViajeProgramado())) {
                throw new SQLException("Los IDs referenciados no existen en las tablas correspondientes");
            }

            stmt.setInt(1, itinerario.getIdItinerario());
            stmt.setInt(2, itinerario.getIdViajeProgramado());
            stmt.setString(3, itinerario.getAsientoAsignado());
            stmt.setDouble(4, itinerario.getCosto());
            stmt.setString(5, itinerario.getEstado());

            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el itinerario");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    itinerario.setIdItinerarioViajeBus(generatedKeys.getInt(1));
                }
            }
            
            return true;
        }
    }

    // Método auxiliar para validar IDs
    private boolean existeIdEnTabla(String tabla, int id) throws SQLException {
        String sql = "SELECT 1 FROM " + tabla + " WHERE ID_" + tabla + " = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Método auxiliar mejorado para verificar existencia de registros
    private boolean existeRegistroEnTabla(String tabla, String columna, int valor) throws SQLException {
        String sql = "SELECT 1 FROM " + tabla + " WHERE " + columna + " = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, valor);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public List<ItinerarioViaje> obtenerItinerariosPorViaje(int idViajeProgramado) {
        List<ItinerarioViaje> itinerarios = new ArrayList<>();
        String sql = "SELECT * FROM itinerario_viajebus WHERE ID_ViajeProgramado = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idViajeProgramado);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItinerarioViaje itinerario = new ItinerarioViaje();
                    itinerario.setIdItinerarioViajeBus(rs.getInt("ID_ItinerarioViajeBus"));
                    itinerario.setIdItinerario(rs.getInt("ID_Itinerario"));
                    itinerario.setIdViajeProgramado(rs.getInt("ID_ViajeProgramado"));
                    itinerario.setAsientoAsignado(rs.getString("AsientoAsignado"));
                    itinerario.setCosto(rs.getDouble("Costo"));
                    itinerario.setEstado(rs.getString("estado"));
                    
                    itinerarios.add(itinerario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener itinerarios: " + e.getMessage());
        }
        return itinerarios;
    }
    
    public boolean actualizarItinerario(ItinerarioViaje itinerario) throws SQLException {
        String sql = "UPDATE itinerario_viajebus SET ID_Itinerario = ?, ID_ViajeProgramado = ?, "
                   + "AsientoAsignado = ?, Costo = ?, estado = ? WHERE ID_ItinerarioViajeBus = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, itinerario.getIdItinerario());
            stmt.setInt(2, itinerario.getIdViajeProgramado());
            stmt.setString(3, itinerario.getAsientoAsignado());
            stmt.setDouble(4, itinerario.getCosto());
            stmt.setString(5, itinerario.getEstado());
            stmt.setInt(6, itinerario.getIdItinerarioViajeBus());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<ItinerarioViaje> obtenerTodosItinerarios() throws SQLException {
        List<ItinerarioViaje> itinerarios = new ArrayList<>();
        String sql = "SELECT * FROM itinerario_viajebus";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ItinerarioViaje itinerario = new ItinerarioViaje();
                itinerario.setIdItinerarioViajeBus(rs.getInt("ID_ItinerarioViajeBus"));
                itinerario.setIdItinerario(rs.getInt("ID_Itinerario"));
                itinerario.setIdViajeProgramado(rs.getInt("ID_ViajeProgramado"));
                itinerario.setAsientoAsignado(rs.getString("AsientoAsignado"));
                itinerario.setCosto(rs.getDouble("Costo"));
                itinerario.setEstado(rs.getString("estado"));
                
                itinerarios.add(itinerario);
            }
        }
        return itinerarios; 
    }

    public ItinerarioViaje obtenerItinerarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM itinerario_viajebus WHERE ID_ItinerarioViajeBus = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ItinerarioViaje itinerario = new ItinerarioViaje();
                    itinerario.setIdItinerarioViajeBus(rs.getInt("ID_ItinerarioViajeBus"));
                    itinerario.setIdItinerario(rs.getInt("ID_Itinerario"));
                    itinerario.setIdViajeProgramado(rs.getInt("ID_ViajeProgramado"));
                    itinerario.setAsientoAsignado(rs.getString("AsientoAsignado"));
                    itinerario.setCosto(rs.getDouble("Costo"));
                    itinerario.setEstado(rs.getString("estado"));
                    return itinerario;
                }
            }
        }
        return null;
    }

    public boolean eliminarItinerario(int id) throws SQLException {
        String sql = "DELETE FROM itinerario_viajebus WHERE ID_ItinerarioViajeBus = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}