package dao;

import Modelo.Bus; // Asegúrate de que esta ruta sea correcta para tu clase Bus
import Conexión.Conexión; // Asegúrate de que esta ruta sea correcta para tu clase Conexión
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    // Guardar nuevo bus (con estado por defecto 'operativo')
    public boolean guardarBus(Bus bus) {
        String sql = "INSERT INTO bus (Placa, Capacidad, EstadoBus) VALUES (?, ?, ?)";
        
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, bus.getPlaca());
            ps.setInt(2, bus.getCapacidad());
            ps.setString(3, "operativo"); // Estado por defecto para un bus nuevo
            
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

    // Obtener todos los buses (sin filtro de estado, útil para administración completa)
    public List<Bus> obtenerTodosLosBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT ID_Bus, Placa, Capacidad, EstadoBus FROM bus"; 
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                buses.add(new Bus(
                    rs.getInt("ID_Bus"), 
                    rs.getString("Placa"),
                    rs.getInt("Capacidad"),
                    rs.getString("EstadoBus")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los buses: " + e.getMessage());
        }
        return buses;
    }

    /**
     * Obtiene una lista de buses que están en estado 'operativo' o 'mantenimiento'.
     * Este método es ideal para la visualización principal donde no se quieren mostrar
     * buses 'inactivos' (eliminados lógicamente).
     * @return Una lista de objetos Bus con estado 'operativo' o 'mantenimiento'.
     */
    public List<Bus> obtenerBusesOperativosODisponibles() {
        List<Bus> buses = new ArrayList<>();
        // Se utiliza 'OR' para incluir ambos estados: 'operativo' y 'mantenimiento'
        String sql = "SELECT ID_Bus, Placa, Capacidad, EstadoBus FROM bus WHERE EstadoBus = 'operativo' OR EstadoBus = 'mantenimiento'";
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setIdBus(rs.getInt("ID_Bus"));
                bus.setPlaca(rs.getString("Placa"));
                bus.setCapacidad(rs.getInt("Capacidad"));
                bus.setEstadoBus(rs.getString("EstadoBus"));
                buses.add(bus);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener buses operativos o disponibles: " + e.getMessage());
        }
        
        return buses;
    }

    // Obtener solo buses operativos (si necesitas este filtro específico por separado)
    public List<Bus> obtenerBusesOperativos() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT ID_Bus, Placa, Capacidad, EstadoBus FROM bus WHERE EstadoBus = 'operativo'";
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                buses.add(new Bus(
                    rs.getInt("ID_Bus"), 
                    rs.getString("Placa"),
                    rs.getInt("Capacidad"),
                    rs.getString("EstadoBus")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener buses operativos: " + e.getMessage());
        }
        return buses;
    }

    // Obtener buses en mantenimiento
    public List<Bus> obtenerBusesEnMantenimiento() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT ID_Bus, Placa, Capacidad, EstadoBus FROM bus WHERE EstadoBus = 'mantenimiento'";
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                buses.add(new Bus(
                    rs.getInt("ID_Bus"), 
                    rs.getString("Placa"),
                    rs.getInt("Capacidad"),
                    rs.getString("EstadoBus")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener buses en mantenimiento: " + e.getMessage());
        }
        return buses;
    }

    // Obtener buses inactivos (eliminados lógicamente)
    public List<Bus> obtenerBusesInactivos() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT ID_Bus, Placa, Capacidad, EstadoBus FROM bus WHERE EstadoBus = 'inactivo'";
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                buses.add(new Bus(
                    rs.getInt("ID_Bus"), 
                    rs.getString("Placa"),
                    rs.getInt("Capacidad"),
                    rs.getString("EstadoBus")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener buses inactivos: " + e.getMessage());
        }
        return buses;
    }

    // Actualizar información del bus (incluyendo estado)
    public boolean actualizarBus(Bus bus) {
        String sql = "UPDATE bus SET Placa = ?, Capacidad = ?, EstadoBus = ? WHERE ID_Bus = ?";
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bus.getPlaca());
            ps.setInt(2, bus.getCapacidad());
            ps.setString(3, bus.getEstadoBus());
            ps.setInt(4, bus.getIdBus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar bus: " + e.getMessage());
            return false;
        }
    }

    // Eliminación lógica (marcar como 'inactivo')
    public boolean eliminarBusLogico(int idBus) {
        String sql = "UPDATE bus SET EstadoBus = 'inactivo' WHERE ID_Bus = ?";
        
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idBus);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar (lógico) bus: " + e.getMessage());
            return false;
        }
    }

    // Reactivar bus (marcar como 'operativo')
    public boolean reactivarBus(int idBus) {
        String sql = "UPDATE bus SET EstadoBus = 'operativo' WHERE ID_Bus = ?";
        
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idBus);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al reactivar bus: " + e.getMessage());
            return false;
        }
    }

    // Marcar bus como en mantenimiento
    public boolean enviarAMantenimiento(int idBus) {
        String sql = "UPDATE bus SET EstadoBus = 'mantenimiento' WHERE ID_Bus = ?";
        
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idBus);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al enviar bus a mantenimiento: " + e.getMessage());
            return false;
        }
    }

    // Obtener bus por ID
    public Bus obtenerBusPorId(int idBus) {
        String sql = "SELECT ID_Bus, Placa, Capacidad, EstadoBus FROM bus WHERE ID_Bus = ?";
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idBus);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Bus(
                        rs.getInt("ID_Bus"), 
                        rs.getString("Placa"),
                        rs.getInt("Capacidad"),
                        rs.getString("EstadoBus")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener bus por ID: " + e.getMessage());
        }
        return null;
    }

    // Verificar si una placa existe (para validaciones, excluyendo el ID actual si es una actualización)
    public boolean existePlaca(String placa, int idBusActual) {
        // idBusActual se usa para excluir el propio bus que se está modificando
        String sql = "SELECT COUNT(*) FROM bus WHERE Placa = ? AND ID_Bus <> ?"; 

        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ps.setInt(2, idBusActual);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al validar placa: " + e.getMessage());
        }

        return false;
    }
    
    // Sobrecarga para cuando no se tiene un idBusActual (ej. al agregar un nuevo bus)
    public boolean existePlaca(String placa) {
        String sql = "SELECT COUNT(*) FROM bus WHERE Placa = ?";

        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al validar placa (sin ID actual): " + e.getMessage());
        }

        return false;
    }


    // Verificar si un ID de bus existe
    public boolean existeIdBus(int idBus) {
        String sql = "SELECT COUNT(*) FROM bus WHERE ID_Bus = ?";
        
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idBus);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de ID de bus: " + e.getMessage());
        }
        
        return false;
    }

    // Eliminación física (solo para administración avanzada, úsala con precaución)
    public boolean eliminarBusFisico(int idBus) {
        String sql = "DELETE FROM bus WHERE ID_Bus = ?";
        
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idBus);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar bus físicamente: " + e.getMessage());
            return false;
        }
    }
    
}