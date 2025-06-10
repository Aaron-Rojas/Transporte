package dao;

import Modelo.Bus;
import Conexión.Conexión;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    // Guardar nuevo bus (solo placa y estado)
    public boolean guardarBus(Bus bus) {
        String sql = "INSERT INTO bus (Placa, EstadoBus) VALUES (?, ?)";

        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bus.getPlaca());
            ps.setString(2, bus.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar el bus: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los buses (solo id, placa y estado)
    public List<Bus> obtenerTodosLosBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT id_Bus, Placa, EstadoBus FROM bus";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bus bus = new Bus();
                bus.setIdBus(rs.getInt("id_Bus"));
                bus.setPlaca(rs.getString("Placa"));
                bus.setEstado(rs.getString("EstadoBus"));
                buses.add(bus);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener buses: " + e.getMessage());
        }

        return buses;
    }

    // Actualizar bus (placa y estado)
    public boolean actualizarBus(Bus bus) {
        String sql = "UPDATE bus SET Placa = ?, EstadoBus = ? WHERE id_Bus = ?";

        try (Connection conn = Conexión.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bus.getPlaca());
            ps.setString(2, bus.getEstado());
            ps.setInt(3, bus.getIdBus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar bus: " + e.getMessage());
            return false;
        }
    }

    // Eliminar bus por ID
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

    // Verificar si una placa ya existe (opcional)
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
}
