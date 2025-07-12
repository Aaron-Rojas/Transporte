package dao;

import Modelo.Actividad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Conexión.Conexión; // Mantén esto si estás usando el paquete con tilde

public class ActividadDAO {

    private final Connection conexion;

    public ActividadDAO() {
        this.conexion = Conexión.conectar();
    }

    // INSERTAR
    public boolean insertarActividad(Actividad actividad) {
        String sql = "INSERT INTO actividad (NombreActividad, Descripcion, Duracion, ID_LugarTuristico, ID_Proveedor, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, actividad.getNombre());
            stmt.setString(2, actividad.getDescripcion());
            stmt.setInt(3, actividad.getDuracion());
            stmt.setInt(4, actividad.getIdLugarTuristico());
            stmt.setInt(5, actividad.getIdProveedor());
            stmt.setString(6, actividad.getEstado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // LISTAR TODO
    public List<Actividad> listarActividades() {
        List<Actividad> lista = new ArrayList<>();
        String sql = "SELECT * FROM actividad";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Actividad actividad = new Actividad(
                    rs.getInt("ID_Actividad"),
                    rs.getString("NombreActividad"),
                    rs.getString("Descripcion"),
                    rs.getInt("Duracion"),
                    rs.getInt("ID_LugarTuristico"),
                    rs.getInt("ID_Proveedor"),
                    rs.getString("Estado")
                );
                lista.add(actividad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // OBTENER POR ID
public Actividad obtenerActividadPorId(int id) {
    Actividad actividad = null;
    String sql = "SELECT * FROM actividad WHERE ID_Actividad = ?";

    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            actividad = new Actividad(
                rs.getInt("ID_Actividad"),
                rs.getString("NombreActividad"),
                    rs.getString("Descripcion"),
                rs.getInt("Duracion"),
                rs.getInt("ID_LugarTuristico"),
                rs.getInt("ID_Proveedor"),
                rs.getString("Estado")
            );
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return actividad;
}

    // ACTUALIZAR
    public boolean actualizarActividad(Actividad actividad) {
        String sql = "UPDATE actividad SET NombreActividad=?, Descripcion=?, Duracion=?, ID_LugarTuristico=?, ID_Proveedor=?, Estado=? WHERE ID_Actividad=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, actividad.getNombre());
            stmt.setString(2, actividad.getDescripcion());
            stmt.setInt(3, actividad.getDuracion());
            stmt.setInt(4, actividad.getIdLugarTuristico());
            stmt.setInt(5, actividad.getIdProveedor());
            stmt.setString(6, actividad.getEstado());
            stmt.setInt(7, actividad.getIdActividad());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminarActividad(int id) {
        String sql = "DELETE FROM actividad WHERE ID_Actividad = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
