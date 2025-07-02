package dao;

import Modelo.Bus;
import Conexión.Conexión;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    // Guardar nuevo bus (con ID proporcionado)
    public boolean guardarBus(Bus bus) {
        // We now include id_Bus in the INSERT statement
        String sql = "INSERT INTO bus (id_Bus, Placa, Capacidad, EstadoBus) VALUES (?, ?, ?, ?)"; 

        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bus.getIdBus());     // Set ID manually
            ps.setString(2, bus.getPlaca());
            ps.setInt(3, bus.getCapacidad()); 
            ps.setString(4, bus.getEstado()); 

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar el bus: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los buses (id, placa, capacidad y estado) - No change needed
    public List<Bus> obtenerTodosLosBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT id_Bus, Placa, Capacidad, EstadoBus FROM bus"; 

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bus bus = new Bus();
                bus.setIdBus(rs.getInt("id_Bus"));
                bus.setPlaca(rs.getString("Placa"));
                bus.setCapacidad(rs.getInt("Capacidad"));
                bus.setEstado(rs.getString("EstadoBus"));
                buses.add(bus);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener buses: " + e.getMessage());
        }

        return buses;
    }

    // Actualizar bus (placa, capacidad y estado) - No change needed, it already uses WHERE id_Bus = ?
    public boolean actualizarBus(Bus bus) {
        String sql = "UPDATE bus SET Placa = ?, Capacidad = ?, EstadoBus = ? WHERE id_Bus = ?"; 

        try (Connection conn = Conexión.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bus.getPlaca());
            ps.setInt(2, bus.getCapacidad()); 
            ps.setString(3, bus.getEstado()); 
            ps.setInt(4, bus.getIdBus()); 

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar bus: " + e.getMessage());
            return false;
        }
    }

    // Eliminar bus por ID - No change needed
    public boolean eliminarBus(int idBus) {
        String sql = "DELETE FROM bus WHERE id_Bus = ?";

        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idBus);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar bus: " + e.getMessage());
            return false;
        }
    }

    // Verify if a plate exists (optional) - No change needed
    public boolean existePlaca(String placa, int idBusActual) {
        String sql = "SELECT COUNT(*) FROM bus WHERE Placa = ? AND id_Bus <> ?";

        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ps.setInt(2, idBusActual);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al validar placa: " + e.getMessage());
        }

        return false;
    }
    
    // NEW: Check if a bus ID already exists
    public boolean existeIdBus(int idBus) {
        String sql = "SELECT COUNT(*) FROM bus WHERE id_Bus = ?";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idBus);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar existencia de ID de bus: " + e.getMessage());
        }
        return false;
    }
}