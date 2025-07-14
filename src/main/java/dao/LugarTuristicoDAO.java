package dao;

import Modelo.LugarTuristico;
import Conexión.Conexión;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LugarTuristicoDAO {
    // No es necesario declarar Connection, PreparedStatement, ResultSet como atributos de clase
    // si se usan en bloques try-with-resources. Esto ayuda a evitar fugas de recursos.
    private static final Logger logger = Logger.getLogger(LugarTuristicoDAO.class.getName());

    public boolean insertarLugarTuristico(LugarTuristico lugar) {
        String sql = "INSERT INTO lugarturistico (NombreLugar, TipoLugar, Descripcion, ID_Destino, ID_Proveedor, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lugar.getNombre());
            ps.setString(2, lugar.getTipo());
            ps.setString(3, lugar.getDescripcion());
            ps.setInt(4, lugar.getIdDestino());
            ps.setInt(5, lugar.getIdProveedor());
            ps.setString(6, lugar.getEstado());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar lugar turístico: {0}", e.getMessage());
            return false;
        }
    }

    public boolean actualizarLugarTuristico(LugarTuristico lugar) {
        String sql = "UPDATE lugarturistico SET NombreLugar=?, TipoLugar=?, Descripcion=?, ID_Destino=?, ID_Proveedor=?, Estado=? WHERE ID_LugarTuristico=?";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lugar.getNombre());
            ps.setString(2, lugar.getTipo());
            ps.setString(3, lugar.getDescripcion());
            ps.setInt(4, lugar.getIdDestino());
            ps.setInt(5, lugar.getIdProveedor());
            ps.setString(6, lugar.getEstado());
            ps.setInt(7, lugar.getIdLugarTuristico());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar lugar turístico: {0}", e.getMessage());
            return false;
        }
    }

    public LugarTuristico obtenerLugarPorId(int id) {
        LugarTuristico lugar = null;
        String sql = "SELECT ID_LugarTuristico, NombreLugar, TipoLugar, Descripcion, ID_Destino, ID_Proveedor, Estado FROM lugarturistico WHERE ID_LugarTuristico=?";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lugar = new LugarTuristico(
                        rs.getInt("ID_LugarTuristico"),
                        rs.getString("NombreLugar"),
                        rs.getString("TipoLugar"),
                        rs.getString("Descripcion"),
                        rs.getInt("ID_Destino"),
                        rs.getInt("ID_Proveedor"),
                        rs.getString("Estado")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener lugar turístico por ID: {0}", e.getMessage());
        }
        return lugar;
    }

    // LISTAR SOLO LUGARES TURÍSTICOS ACTIVOS (Eliminación Lógica)
    public List<LugarTuristico> listarLugaresTuristicos() {
        List<LugarTuristico> lista = new ArrayList<>();
        // Filtra por 'Estado' = 'Activo'
        String sql = "SELECT ID_LugarTuristico, NombreLugar, TipoLugar, Descripcion, ID_Destino, ID_Proveedor, Estado FROM lugarturistico WHERE Estado = 'Activo'";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LugarTuristico lugar = new LugarTuristico(
                    rs.getInt("ID_LugarTuristico"),
                    rs.getString("NombreLugar"),
                    rs.getString("TipoLugar"),
                    rs.getString("Descripcion"),
                    rs.getInt("ID_Destino"),
                    rs.getInt("ID_Proveedor"),
                    rs.getString("Estado")
                );
                lista.add(lugar);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al listar lugares turísticos activos: {0}", e.getMessage());
        }
        return lista;
    }

    // (Opcional) Método para listar TODOS los lugares turísticos (activos e inactivos)
    public List<LugarTuristico> obtenerTodosLosLugaresTuristicos() {
        List<LugarTuristico> lista = new ArrayList<>();
        String sql = "SELECT ID_LugarTuristico, NombreLugar, TipoLugar, Descripcion, ID_Destino, ID_Proveedor, Estado FROM lugarturistico";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LugarTuristico lugar = new LugarTuristico(
                    rs.getInt("ID_LugarTuristico"),
                    rs.getString("NombreLugar"),
                    rs.getString("TipoLugar"),
                    rs.getString("Descripcion"),
                    rs.getInt("ID_Destino"),
                    rs.getInt("ID_Proveedor"),
                    rs.getString("Estado")
                );
                lista.add(lugar);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al listar todos los lugares turísticos: {0}", e.getMessage());
        }
        return lista;
    }

    // ELIMINACIÓN LÓGICA: Cambia el estado a 'Inactivo'
   public boolean eliminarLugarTuristico(int id) {
        // La sentencia SQL se cambia a UPDATE para la eliminación lógica
        String sql = "UPDATE lugarturistico SET Estado = 'Inactivo' WHERE ID_LugarTuristico=?";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al desactivar lugar turístico: {0}", e.getMessage());
            return false;
        }
    }
    
    // (Opcional) Método para restaurar un lugar turístico (cambiar su estado a 'Activo')
    public boolean restaurarLugarTuristico(int id) {
        String sql = "UPDATE lugarturistico SET Estado = 'Activo' WHERE ID_LugarTuristico=?";
        try (Connection con = Conexión.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al restaurar lugar turístico: {0}", e.getMessage());
            return false;
        }
    }
}