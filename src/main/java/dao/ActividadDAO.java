package dao;

import Modelo.Actividad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Conexión.Conexión; // Asegúrate de que esta ruta de conexión sea correcta

public class ActividadDAO {

    // INSERTAR
    public boolean insertarActividad(Actividad actividad) {
        String sql = "INSERT INTO actividad (NombreActividad, Descripcion, Duracion, ID_LugarTuristico, ID_Proveedor, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, actividad.getNombre());
            pstmt.setString(2, actividad.getDescripcion());
            pstmt.setString(3, actividad.getDuracion());
            pstmt.setInt(4, actividad.getIdLugarTuristico());
            pstmt.setInt(5, actividad.getIdProveedor());
            pstmt.setString(6, actividad.getEstado()); // Guarda el estado (ej. "activo")
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al insertar actividad: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODO (solo activos por defecto)
    public List<Actividad> listarActividades() {
        List<Actividad> lista = new ArrayList<>();
        // <--- FILTRA POR ESTADO 'activo' para la eliminación lógica
        String sql = "SELECT ID_Actividad, NombreActividad, Descripcion, Duracion, ID_LugarTuristico, ID_Proveedor, Estado FROM actividad WHERE Estado = 'activo'";
        try (Connection conn = Conexión.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Actividad actividad = new Actividad(
                    rs.getInt("ID_Actividad"),
                    rs.getString("NombreActividad"),
                    rs.getString("Descripcion"),
                    rs.getString("Duracion"),
                    rs.getInt("ID_LugarTuristico"),
                    rs.getInt("ID_Proveedor"),
                    rs.getString("Estado")
                );
                lista.add(actividad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al listar actividades: " + e.getMessage());
        }
        return lista;
    }
    
    // OBTENER POR ID
    public Actividad obtenerActividadPorId(int id) {
        Actividad actividad = null;
        String sql = "SELECT ID_Actividad, NombreActividad, Descripcion, Duracion, ID_LugarTuristico, ID_Proveedor, Estado FROM actividad WHERE ID_Actividad = ?";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                actividad = new Actividad(
                    rs.getInt("ID_Actividad"),
                    rs.getString("NombreActividad"),
                    rs.getString("Descripcion"),
                    rs.getString("Duracion"),
                    rs.getInt("ID_LugarTuristico"),
                    rs.getInt("ID_Proveedor"),
                    rs.getString("Estado")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error al obtener actividad por ID: " + ex.getMessage());
        }
        return actividad;
    }

    // ACTUALIZAR
    public boolean actualizarActividad(Actividad actividad) {
        String sql = "UPDATE actividad SET NombreActividad=?, Descripcion=?, Duracion=?, ID_LugarTuristico=?, ID_Proveedor=?, Estado=? WHERE ID_Actividad=?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, actividad.getNombre());
            pstmt.setString(2, actividad.getDescripcion());
            pstmt.setString(3, actividad.getDuracion());
            pstmt.setInt(4, actividad.getIdLugarTuristico());
            pstmt.setInt(5, actividad.getIdProveedor());
            pstmt.setString(6, actividad.getEstado()); // Actualiza el estado
            pstmt.setInt(7, actividad.getIdActividad());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al actualizar actividad: " + e.getMessage());
            return false;
        }
    }

    // ELIMINACIÓN LÓGICA (cambia el estado a 'desactivo')
    public boolean eliminarActividad(int id) {
        // <--- ESTA ES LA SENTENCIA PARA LA ELIMINACIÓN LÓGICA
        String sql = "UPDATE actividad SET Estado = 'desactivo' WHERE ID_Actividad = ?"; 
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al cambiar estado de actividad a 'desactivo': " + e.getMessage());
            return false;
        }
    }
}