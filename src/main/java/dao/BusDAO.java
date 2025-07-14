package dao;

import Conexión.Conexión;
import Modelo.Bus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

     public boolean guardarBus(Bus bus) {
        String sql = "INSERT INTO bus (Placa, Capacidad, EstadoBus) VALUES (?, ?, ?)";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, bus.getPlaca());
            ps.setInt(2, bus.getCapacidad());
            ps.setBoolean(3, true); // Activo por defecto (1 en BIT)
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        bus.setIdBus(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al guardar bus: " + e.getMessage());
            return false;
        }
    }

    // Obtener buses activos (Estadibus = 1)
    public List<Bus> obtenerBusesActivos() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT ID_Bus, Placa, Capacidad, EstadoBus FROM bus WHERE EstadoBus = 1";
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setIdBus(rs.getInt("ID_Bus"));
                bus.setPlaca(rs.getString("Placa"));
                bus.setCapacidad(rs.getInt("Capacidad"));
                bus.setEstado(rs.getBoolean("EstadoBus"));
                buses.add(bus);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener buses activos: " + e.getMessage());
        }
        return buses;
    }

    // Eliminación lógica (marcar Estadibus = 0)
    public boolean eliminarBusLogico(int idBus) {
        String sql = "UPDATE bus SET EstadoBus = 0 WHERE ID_Bus = ?";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idBus);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar (lógico) bus: " + e.getMessage());
            return false;
        }
    }
    
    // Actualizar bus
    public boolean actualizarBus(Bus bus) {
        String sql = "UPDATE bus SET Placa = ?, Capacidad = ?, EstadoBus = ? WHERE ID_Bus = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, bus.getPlaca());
            ps.setInt(2, bus.getCapacidad());
            ps.setBoolean(3, bus.isEstado());
            ps.setInt(4, bus.getIdBus());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar bus: " + e.getMessage());
            return false;
        }
    }

    // Modificar un bus existente
    public boolean modificarBus(Bus bus) {
        String sql = "UPDATE bus SET Placa = ?, Capacidad = ?, EstadoBus = ? WHERE ID_Bus = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bus.getPlaca());
            stmt.setInt(2, bus.getCapacidad());
            stmt.setBoolean(3, bus.isEstado()); // Set boolean state
            stmt.setInt(4, bus.getIdBus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar bus: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para cambiar el estado (activo/inactivo) de un bus (soft delete)
    public boolean cambiarEstadoBus(int idBus, boolean nuevoEstado) {
        String sql = "UPDATE bus SET EstadoBus = ? WHERE ID_Bus = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, nuevoEstado); // Use setBoolean for boolean value
            stmt.setInt(2, idBus);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al cambiar el estado del bus: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Obtener un solo bus por ID
    public Bus obtenerBusPorId(int idBus) {
        String sql = "SELECT ID_Bus, Placa, Capacidad, EstadoBus FROM bus WHERE ID_Bus = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idBus);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Bus(
                        rs.getInt("ID_Bus"),
                        rs.getString("Placa"),
                        rs.getInt("Capacidad"),
                        rs.getBoolean("Estado") // Read as boolean
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener bus por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Validar si una placa ya existe (para evitar duplicados al insertar/modificar)
    public boolean validarPlacaExistente(String placa, int idActual) {
        String sql = "SELECT COUNT(*) FROM bus WHERE Placa = ? AND ID_Bus != ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.setInt(2, idActual);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al validar placa: " + e.getMessage());
        }
        return false;
    }

    public boolean validarPlacaExistente(String placa) {
        String sql = "SELECT COUNT(*) FROM bus WHERE Placa = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al validar placa (sin ID actual): " + e.getMessage());
        }
        return false;
    }
}