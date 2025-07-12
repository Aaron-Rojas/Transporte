package dao;

import Modelo.LugarTuristico;
import Conexión.Conexión;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LugarTuristicoDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean insertarLugarTuristico(LugarTuristico lugar) {
        String sql = "INSERT INTO lugarturistico (NombreLugar, TipoLugar, Descripcion, ID_Destino, ID_Proveedor, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = Conexión.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, lugar.getNombre());
            ps.setString(2, lugar.getTipo());
            ps.setString(3, lugar.getDescripcion());
            ps.setInt(4, lugar.getIdDestino());
            ps.setInt(5, lugar.getIdProveedor());
            ps.setString(6, lugar.getEstado());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar lugar turístico: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarLugarTuristico(LugarTuristico lugar) {
        String sql = "UPDATE lugarturistico SET NombreLugar=?, TipoLugar=?, Descripcion=?, ID_Destino=?, ID_Proveedor=?, Estado=? WHERE ID_LugarTuristico=?";
        try {
            con = Conexión.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, lugar.getNombre());
            ps.setString(2, lugar.getTipo());
            ps.setString(3, lugar.getDescripcion());
            ps.setInt(4, lugar.getIdDestino());
            ps.setInt(5, lugar.getIdProveedor());
            ps.setString(6, lugar.getEstado());
            ps.setInt(7, lugar.getIdLugarTuristico());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar lugar turístico: " + e.getMessage());
            return false;
        }
    }

    public LugarTuristico obtenerLugarPorId(int id) {
        LugarTuristico lugar = null;
        String sql = "SELECT * FROM lugarturistico WHERE ID_LugarTuristico=?";
        try {
            con = Conexión.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
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
        } catch (SQLException e) {
            System.out.println("Error al obtener lugar turístico: " + e.getMessage());
        }
        return lugar;
    }

    public List<LugarTuristico> listarLugaresTuristicos() {
        List<LugarTuristico> lista = new ArrayList<>();
        String sql = "SELECT * FROM lugarturistico";
        try {
            con = Conexión.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
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
            System.out.println("Error al listar lugares turísticos: " + e.getMessage());
        }
        return lista;
    }

    public boolean eliminarLugarTuristico(int id) {
        String sql = "DELETE FROM lugarturistico WHERE ID_LugarTuristico=?";
        try {
            con = Conexión.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar lugar turístico: " + e.getMessage());
            return false;
        }
    }
}

