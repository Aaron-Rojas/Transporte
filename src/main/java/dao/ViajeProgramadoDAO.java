package dao;

import Modelo.ViajeProgramado;
import Conexión.Conexión;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViajeProgramadoDAO {
    private final Connection conexion;
    private final BusDAO busDAO;
    private final DestinoDAO destinoDAO;

    public ViajeProgramadoDAO(BusDAO busDAO, DestinoDAO destinoDAO) {
        this.conexion = Conexión.conectar();
        this.busDAO = busDAO;
        this.destinoDAO = destinoDAO;
    }

   
public boolean guardarViaje(ViajeProgramado viaje) throws SQLException {
        String sql = "INSERT INTO viajeprogramado (ID_Bus, ID_Origen, ID_DestinoFinal, "
                   + "FechaHoraSalida, FechaHoraLlegadaEstimada, Estadoviaje) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, viaje.getIdBus());
            stmt.setInt(2, viaje.getIdOrigen());
            stmt.setInt(3, viaje.getIdDestinoFinal());
            stmt.setTimestamp(4, Timestamp.valueOf(viaje.getFechaHoraSalida()));
            stmt.setTimestamp(5, Timestamp.valueOf(viaje.getFechaHoraLlegadaEstimada()));
            stmt.setBoolean(6, viaje.isActivo());
            
            if (stmt.executeUpdate() > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        viaje.setIdViajeProgramado(rs.getInt(1));
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public boolean actualizar(ViajeProgramado viaje) throws SQLException {
        String sql = "UPDATE viajeprogramado SET "
                   + "ID_Bus = ?, ID_Origen = ?, ID_DestinoFinal = ?, "
                   + "FechaHoraSalida = ?, FechaHoraLlegadaEstimada = ?, Estadoviaje = ? "
                   + "WHERE ID_ViajeProgramado = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, viaje.getIdBus());
            stmt.setInt(2, viaje.getIdOrigen());
            stmt.setInt(3, viaje.getIdDestinoFinal());
            stmt.setTimestamp(4, Timestamp.valueOf(viaje.getFechaHoraSalida()));
            stmt.setTimestamp(5, Timestamp.valueOf(viaje.getFechaHoraLlegadaEstimada()));
            stmt.setBoolean(6, viaje.isActivo());
            stmt.setInt(7, viaje.getIdViajeProgramado());
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean cambiarEstado(int idViaje, boolean activo) throws SQLException {
        String sql = "UPDATE viajeprogramado SET Estadoviaje = ? WHERE ID_ViajeProgramado = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setBoolean(1, activo);
            stmt.setInt(2, idViaje);
            return stmt.executeUpdate() > 0;
        }
    }

    public ViajeProgramado obtenerPorId(int idViaje) throws SQLException {
        String sql = "SELECT * FROM viajeprogramado WHERE ID_ViajeProgramado = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idViaje);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ViajeProgramado viaje = new ViajeProgramado();
                    viaje.setIdViajeProgramado(rs.getInt("ID_ViajeProgramado"));
                    viaje.setIdBus(rs.getInt("ID_Bus"));
                    viaje.setIdOrigen(rs.getInt("ID_Origen"));
                    viaje.setIdDestinoFinal(rs.getInt("ID_DestinoFinal"));
                    viaje.setFechaHoraSalida(rs.getTimestamp("FechaHoraSalida").toLocalDateTime());
                    viaje.setFechaHoraLlegadaEstimada(rs.getTimestamp("FechaHoraLlegadaEstimada").toLocalDateTime());
                    viaje.setActivo(rs.getBoolean("Estadoviaje"));
                    return viaje;
                }
            }
        }
        return null;
    }

    public List<ViajeProgramado> obtenerViajesActivos() throws SQLException {
        List<ViajeProgramado> viajes = new ArrayList<>();
        String sql = "SELECT * FROM viajeprogramado WHERE Estadoviaje = true";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ViajeProgramado viaje = new ViajeProgramado();
                viaje.setIdViajeProgramado(rs.getInt("ID_ViajeProgramado"));
                viaje.setIdBus(rs.getInt("ID_Bus"));
                viaje.setIdOrigen(rs.getInt("ID_Origen"));
                viaje.setIdDestinoFinal(rs.getInt("ID_DestinoFinal"));
                viaje.setFechaHoraSalida(rs.getTimestamp("FechaHoraSalida").toLocalDateTime());
                viaje.setFechaHoraLlegadaEstimada(rs.getTimestamp("FechaHoraLlegadaEstimada").toLocalDateTime());
                viaje.setActivo(rs.getBoolean("Estadoviaje"));
                viajes.add(viaje);
            }
        }
        return viajes;
    }
   

    public boolean eliminarLogico(int idViaje) throws SQLException {
        String sql = "UPDATE viajeprogramado SET Estado=false WHERE ID_ViajeProgramado=?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idViaje);
            return stmt.executeUpdate() > 0;
        }
    }
}

   

  