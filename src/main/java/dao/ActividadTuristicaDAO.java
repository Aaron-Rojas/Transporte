package dao;

import Conexión.Conexión;
import Modelo.ActividadTuristica;
import Modelo.LugarTuristico;
import Modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActividadTuristicaDAO {

    private ProveedorDAO proveedorDAO;
    private LugarTuristicoDAO lugarTuristicoDAO;

    public ActividadTuristicaDAO(ProveedorDAO proveedorDAO, LugarTuristicoDAO lugarTuristicoDAO) {
        this.proveedorDAO = proveedorDAO;
        this.lugarTuristicoDAO = lugarTuristicoDAO;
    }

 
    public boolean insertarActividadTuristica(ActividadTuristica actividad) {
        String sql = "INSERT INTO actividad (NombreActividad, Descripcion, Duracion, ID_LugarTuristico, ID_Proveedor, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, actividad.getNombreActividad());
            stmt.setString(2, actividad.getDescripcion());
            stmt.setString(3, actividad.getDuracion());
            stmt.setInt(4, actividad.getLugarTuristico().getIdLugarTuristico());
            stmt.setInt(5, actividad.getProveedor().getIdProveedor());
            stmt.setBoolean(6, actividad.isEstado());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        actividad.setID_Actividad(rs.getInt(1)); // Asignar el ID generado al objeto
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar actividad turística: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    
    public ActividadTuristica obtenerActividadTuristicaPorId(int idActividad) {
        ActividadTuristica actividad = null;
        String sql = "SELECT ID_Actividad, NombreActividad, Descripcion, Duracion, ID_LugarTuristico, ID_Proveedor, Estado FROM actividad WHERE ID_Actividad = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idActividad);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Obtener los IDs de las claves foráneas
                    int idLugar = rs.getInt("ID_LugarTuristico");
                    int idProveedor = rs.getInt("ID_Proveedor");

                    // Usar los DAOs para obtener los objetos completos
                    LugarTuristico lugarTuristico = lugarTuristicoDAO.obtenerLugarTuristicoPorId(idLugar);
                    Proveedor proveedor = proveedorDAO.obtenerProveedorPorId(idProveedor);

                    // Construir el objeto ActividadTuristica
                    actividad = new ActividadTuristica(
                        rs.getInt("ID_Actividad"),
                        rs.getString("NombreActividad"),
                        rs.getString("Descripcion"),
                        rs.getString("Duracion"),
                        lugarTuristico,
                        proveedor,
                        rs.getBoolean("Estado")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener actividad por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return actividad;
    }


    public List<ActividadTuristica> obtenerTodasLasActividadesTuristicasActivas() {
        List<ActividadTuristica> actividades = new ArrayList<>();
        String sql = "SELECT ID_Actividad, NombreActividad, Descripcion, Duracion, ID_LugarTuristico, ID_Proveedor, Estado FROM actividad WHERE Estado = TRUE";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idLugar = rs.getInt("ID_LugarTuristico");
                int idProveedor = rs.getInt("ID_Proveedor");

                LugarTuristico lugarTuristico = lugarTuristicoDAO.obtenerLugarTuristicoPorId(idLugar);
                Proveedor proveedor = proveedorDAO.obtenerProveedorPorId(idProveedor);

                if (lugarTuristico != null && proveedor != null) {
                    ActividadTuristica actividad = new ActividadTuristica(
                        rs.getInt("ID_Actividad"),
                        rs.getString("NombreActividad"),
                        rs.getString("Descripcion"),
                        rs.getString("Duracion"),
                        lugarTuristico,
                        proveedor,
                        rs.getBoolean("Estado")
                    );
                    actividades.add(actividad);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las actividades activas: " + e.getMessage());
            e.printStackTrace();
        }
        return actividades;
    }


    public boolean actualizarActividadTuristica(ActividadTuristica actividad) {
        String sql = "UPDATE actividad SET NombreActividad = ?, Descripcion = ?, Duracion = ?, ID_LugarTuristico = ?, ID_Proveedor = ?, Estado = ? WHERE ID_Actividad = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, actividad.getNombreActividad());
            stmt.setString(2, actividad.getDescripcion());
            stmt.setString(3, actividad.getDuracion());
            stmt.setInt(4, actividad.getLugarTuristico().getIdLugarTuristico());
            stmt.setInt(5, actividad.getProveedor().getIdProveedor());
            stmt.setBoolean(6, actividad.isEstado());
            stmt.setInt(7, actividad.getID_Actividad()); // El ID para identificar el registro a actualizar

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar actividad turística: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    public boolean actualizarEstadoActividadTuristica(int idActividad, boolean nuevoEstado) {
        String sql = "UPDATE actividad SET Estado = ? WHERE ID_Actividad = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, nuevoEstado);
            stmt.setInt(2, idActividad);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar estado de la actividad: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarActividadTuristica(int idActividad) {
        String sql = "DELETE FROM actividad WHERE ID_Actividad = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idActividad);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar actividad: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
     public boolean desactivarActividadTuristica(int idLugarTuristico) { // Renombrado para mayor claridad
        String sql = "UPDATE actividad SET Estado = 0 WHERE ID_LugarTuristico = ?"; // 0 para "desactivo"
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLugarTuristico);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al desactivar Lugar Turístico: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}