package dao;

import Conexión.Conexión;
import Modelo.ViajeProgramado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViajeProgramadoDAO {
    private final Connection conexion;

    public ViajeProgramadoDAO() {
        this.conexion = Conexión.conectar();
    }

    public boolean insertar(ViajeProgramado viaje) throws SQLException {
        String sql = "INSERT INTO viajeprogramado (ID_Bus, ID_Origen, ID_DestinoFinal, "
                   + "FechaHoraSalida, FechaHoraLlegadaEstimada, EstadoViaje) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            configurarStatement(stmt, viaje);
            
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
                   + "FechaHoraSalida = ?, FechaHoraLlegadaEstimada = ?, EstadoViaje = ? "
                   + "WHERE ID_ViajeProgramado = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            configurarStatement(stmt, viaje);
            stmt.setInt(7, viaje.getIdViajeProgramado());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idViaje) throws SQLException {
        String sql = "DELETE FROM viajeprogramado WHERE ID_ViajeProgramado = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idViaje);
            return stmt.executeUpdate() > 0;
        }
    }

    private void configurarStatement(PreparedStatement stmt, ViajeProgramado viaje) throws SQLException {
        validarReferencias(viaje);
        
        stmt.setInt(1, viaje.getIdBus());
        stmt.setInt(2, viaje.getIdOrigen());
        stmt.setInt(3, viaje.getIdDestinoFinal());
        stmt.setTimestamp(4, Timestamp.valueOf(viaje.getFechaHoraSalida()));
        stmt.setTimestamp(5, Timestamp.valueOf(viaje.getFechaHoraLlegadaEstimada()));
        stmt.setString(6, viaje.getEstadoViaje());
    }

    public ViajeProgramado obtenerPorId(int idViaje) throws SQLException {
        String sql = "SELECT * FROM viajeprogramado WHERE ID_ViajeProgramado = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idViaje);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearViaje(rs);
                }
            }
        }
        return null;
    }

    public List<ViajeProgramado> obtenerTodos() throws SQLException {
        List<ViajeProgramado> viajes = new ArrayList<>();
        String sql = "SELECT * FROM viajeprogramado";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                viajes.add(mapearViaje(rs));
            }
        }
        return viajes;
    }

    private ViajeProgramado mapearViaje(ResultSet rs) throws SQLException {
        ViajeProgramado viaje = new ViajeProgramado();
        viaje.setIdViajeProgramado(rs.getInt("ID_ViajeProgramado"));
        viaje.setIdBus(rs.getInt("ID_Bus"));
        viaje.setIdOrigen(rs.getInt("ID_Origen"));
        viaje.setIdDestinoFinal(rs.getInt("ID_DestinoFinal"));
        viaje.setFechaHoraSalida(rs.getTimestamp("FechaHoraSalida").toLocalDateTime());
        viaje.setFechaHoraLlegadaEstimada(rs.getTimestamp("FechaHoraLlegadaEstimada").toLocalDateTime());
        viaje.setEstadoViaje(rs.getString("EstadoViaje"));
        return viaje;
    }

    private void validarReferencias(ViajeProgramado viaje) throws SQLException {
        if (!existeEnTabla("bus", viaje.getIdBus())) {
            throw new SQLException("No existe un bus con ID: " + viaje.getIdBus());
        }
        if (!existeEnTabla("destino", viaje.getIdOrigen())) {
            throw new SQLException("No existe un destino con ID: " + viaje.getIdOrigen());
        }
        if (!existeEnTabla("destino", viaje.getIdDestinoFinal())) {
            throw new SQLException("No existe un destino con ID: " + viaje.getIdDestinoFinal());
        }
    }

    private boolean existeEnTabla(String tabla, int id) throws SQLException {
        String sql = "SELECT 1 FROM " + tabla + " WHERE ID_" + tabla + " = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}