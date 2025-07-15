
package dao;

import Modelo.LugarTuristico;
import Modelo.Proveedor;
import Modelo.Destino;

import Conexión.Conexión;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LugarTuristicoDAO {

    private DestinoDAO destinoDAO;
    private ProveedorDAO proveedorDAO;

    public LugarTuristicoDAO(DestinoDAO destinoDAO, ProveedorDAO proveedorDAO) {
        this.destinoDAO = destinoDAO;
        this.proveedorDAO = proveedorDAO;
    }
    
    public boolean insertarLugarTuristico(LugarTuristico lugarTuristico) {
        String sql = "INSERT INTO lugarturistico (NombreLugar, TipoLugar, Descripcion, ID_Destino, ID_Proveedor, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexión.conectar(); // Usar tu método de conexión
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, lugarTuristico.getNombreLugar());
            stmt.setString(2, lugarTuristico.getTipoLugar());
            stmt.setString(3, lugarTuristico.getDescripcion());
            // Obtener los IDs de los objetos Destino y Proveedor
            stmt.setInt(4, lugarTuristico.getDestino().getIdDestino()); // Asumo getIdDestino() existe
            stmt.setInt(5, lugarTuristico.getProveedor().getIdProveedor()); // Asumo getIdProveedor() existe
            // Convertir el String "activo"/"desactivo" a boolean para el campo BIT(1)
            stmt.setBoolean(6, lugarTuristico.getEstado());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        lugarTuristico.setIdLugarTuristico(rs.getInt(1)); // Asignar el ID generado
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar Lugar Turístico: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
     public LugarTuristico obtenerLugarTuristicoPorId(int idLugarTuristico) {
        LugarTuristico lugarTuristico = null;
        String sql = "SELECT ID_LugarTuristico, NombreLugar, TipoLugar, Descripcion, ID_Destino, ID_Proveedor, Estado FROM lugarturistico WHERE ID_LugarTuristico = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLugarTuristico);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Obtener los IDs de las claves foráneas
                    int idDestino = rs.getInt("ID_Destino");
                    int idProveedor = rs.getInt("ID_Proveedor");

                    // Usar los DAOs para obtener los objetos Destino y Proveedor completos
                    Destino destino = destinoDAO.obtenerDestinoPorId(idDestino); // Asumo este método existe
                    Proveedor proveedor = proveedorDAO.obtenerProveedorPorId(idProveedor); // Asumo este método existe

                    // Construir el objeto LugarTuristico
                    lugarTuristico = new LugarTuristico();
                    lugarTuristico.setIdLugarTuristico(rs.getInt("ID_LugarTuristico"));
                    lugarTuristico.setNombreLugar(rs.getString("NombreLugar"));
                    lugarTuristico.setTipoLugar(rs.getString("TipoLugar"));
                    lugarTuristico.setDescripcion(rs.getString("Descripcion"));
                    lugarTuristico.setDestino(destino);
                    lugarTuristico.setProveedor(proveedor);
                    // Convertir boolean de DB a String "activo"/"desactivo"
                    lugarTuristico.setEstado(rs.getBoolean("Estado"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener Lugar Turístico por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return lugarTuristico;
    }

    public boolean actualizarLugarTuristico(LugarTuristico lugarTuristico) {
        String sql = "UPDATE lugarturistico SET NombreLugar = ?, TipoLugar = ?, Descripcion = ?, ID_Destino = ?, ID_Proveedor = ?, Estado = ? WHERE ID_LugarTuristico = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lugarTuristico.getNombreLugar());
            stmt.setString(2, lugarTuristico.getTipoLugar());
            stmt.setString(3, lugarTuristico.getDescripcion());
            stmt.setInt(4, lugarTuristico.getDestino().getIdDestino());
            stmt.setInt(5, lugarTuristico.getProveedor().getIdProveedor());
            stmt.setBoolean(6, lugarTuristico.getEstado());
            stmt.setInt(7, lugarTuristico.getIdLugarTuristico()); // El ID para identificar el registro a actualizar

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar Lugar Turístico: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }



    public List<LugarTuristico> obtenerTodosLosLugaresTuristicos() {
           List<LugarTuristico> lugares = new ArrayList<>();
           String sql = "SELECT ID_LugarTuristico, NombreLugar, TipoLugar, Descripcion, ID_Destino, ID_Proveedor, Estado FROM lugarturistico";
           try (Connection conn = Conexión.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

               while (rs.next()) {
                   // Obtener los IDs de las claves foráneas
                   int idDestino = rs.getInt("ID_Destino");
                   int idProveedor = rs.getInt("ID_Proveedor");

                   // Usar los DAOs para obtener los objetos Destino y Proveedor completos
                   Destino destino = destinoDAO.obtenerDestinoPorId(idDestino);
                   Proveedor proveedor = proveedorDAO.obtenerProveedorPorId(idProveedor);

                   LugarTuristico lugarTuristico = new LugarTuristico();
                   lugarTuristico.setIdLugarTuristico(rs.getInt("ID_LugarTuristico"));
                   lugarTuristico.setNombreLugar(rs.getString("NombreLugar"));
                   lugarTuristico.setTipoLugar(rs.getString("TipoLugar"));
                   lugarTuristico.setDescripcion(rs.getString("Descripcion"));
                   lugarTuristico.setDestino(destino);
                   lugarTuristico.setProveedor(proveedor);
                   lugarTuristico.setEstado(rs.getBoolean("Estado")); // Mapear a String

                   lugares.add(lugarTuristico);
               }
           } catch (SQLException e) {
               System.err.println("Error al obtener todos los Lugares Turísticos: " + e.getMessage());
               e.printStackTrace();
           }
           return lugares;
    }

    public List<LugarTuristico> obtenerTodosLosLugaresTuristicosActivos() {
        List<LugarTuristico> lugares = new ArrayList<>();
        String sql = "SELECT ID_LugarTuristico, NombreLugar, TipoLugar, Descripcion, ID_Destino, ID_Proveedor, Estado FROM lugarturistico WHERE Estado = 1"; // 1 para "activo"
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Obtener los IDs de las claves foráneas
                int idDestino = rs.getInt("ID_Destino");
                int idProveedor = rs.getInt("ID_Proveedor");

                // Usar los DAOs para obtener los objetos Destino y Proveedor completos
                Destino destino = destinoDAO.obtenerDestinoPorId(idDestino);
                Proveedor proveedor = proveedorDAO.obtenerProveedorPorId(idProveedor);

                LugarTuristico lugarTuristico = new LugarTuristico();
                lugarTuristico.setIdLugarTuristico(rs.getInt("ID_LugarTuristico"));
                lugarTuristico.setNombreLugar(rs.getString("NombreLugar"));
                lugarTuristico.setTipoLugar(rs.getString("TipoLugar"));
                lugarTuristico.setDescripcion(rs.getString("Descripcion"));
                lugarTuristico.setDestino(destino);
                lugarTuristico.setProveedor(proveedor);
                lugarTuristico.setEstado(rs.getBoolean("Estado")); // Mapear a String
                
                lugares.add(lugarTuristico);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener Lugares Turísticos activos: " + e.getMessage());
            e.printStackTrace();
        }
        return lugares;
    }

 public boolean eliminarLugarTuristicoFisico(int idLugarTuristico) {
        String sql = "DELETE FROM lugarturistico WHERE ID_LugarTuristico = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLugarTuristico);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar Lugar Turístico físicamente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
 
        
 public boolean desactivarLugarTuristico(int idLugarTuristico) { // Renombrado para mayor claridad
        String sql = "UPDATE lugarturistico SET Estado = 0 WHERE ID_LugarTuristico = ?"; // 0 para "desactivo"
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