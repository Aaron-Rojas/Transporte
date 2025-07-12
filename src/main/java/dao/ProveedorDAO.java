package dao;

import Modelo.Proveedor;
import Conexión.Conexión; // Asegúrate de que esta ruta de conexión sea correcta

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Aunque no lo usaremos directamente para RETURN_GENERATED_KEYS en este CRUD, es bueno saber que está disponible.
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    
    public List<Proveedor> listarProveedores() {
    return obtenerProveedoresActivos();
}


    // --- C.R.U.D. para Proveedor (Adaptado con Estado y Eliminación Lógica) ---

    // 1. Crear (Guardar) un nuevo proveedor
    // Ahora incluye el campo 'estado' en la inserción
    public boolean guardarProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO proveedor (nombreProveedor, contacto, estado) VALUES (?, ?, ?)";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proveedor.getNombreProveedor());
            pstmt.setString(2, proveedor.getContacto());
            pstmt.setString(3, proveedor.getEstado()); // Añadido el estado
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al añadir proveedor: " + e.getMessage());
            e.printStackTrace(); // Imprime la traza completa para depuración
            return false;
        }
    }

    // 2. Leer (Obtener) todos los proveedores ACTIVOS
    // Este método solo devolverá los proveedores con Estado = 'activo'
    public List<Proveedor> obtenerProveedoresActivos() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT ID_Proveedor, NombreProveedor, Contacto, Estado FROM proveedor WHERE Estado = 'activo'"; // Filtrado por estado
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("ID_Proveedor"));
                proveedor.setNombreProveedor(rs.getString("NombreProveedor"));
                proveedor.setContacto(rs.getString("Contacto"));
                proveedor.setEstado(rs.getString("Estado")); // Obtener el estado
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedores activos: " + e.getMessage());
            e.printStackTrace(); // Para depuración
        }
        return proveedores;
    }

    // (Opcional) Método para obtener TODOS los proveedores (activos y desactivados)
    // Puede ser útil para una vista de administración o historial
    public List<Proveedor> obtenerTodosLosProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT ID_Proveedor, NombreProveedor, Contacto, Estado FROM proveedor"; // Sin filtro por estado
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("ID_Proveedor"));
                proveedor.setNombreProveedor(rs.getString("NombreProveedor"));
                proveedor.setContacto(rs.getString("Contacto"));
                proveedor.setEstado(rs.getString("Estado"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los proveedores (activos e inactivos): " + e.getMessage());
            e.printStackTrace();
        }
        return proveedores;
    }

    // 3. Leer (Obtener) un proveedor por su ID
    // Ahora incluye el campo 'estado' al recuperar
    public Proveedor obtenerProveedorPorId(int idProveedor) {
        Proveedor proveedor = null;
        String sql = "SELECT ID_Proveedor, NombreProveedor, Contacto, Estado FROM proveedor WHERE ID_Proveedor = ?"; // Incluido Estado
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProveedor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor();
                    proveedor.setIdProveedor(rs.getInt("ID_Proveedor"));
                    proveedor.setNombreProveedor(rs.getString("NombreProveedor"));
                    proveedor.setContacto(rs.getString("Contacto"));
                    proveedor.setEstado(rs.getString("Estado")); // Obtener el estado
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedor por ID: " + e.getMessage());
            e.printStackTrace(); // Para depuración
        }
        return proveedor;
    }

    // 4. Actualizar un proveedor existente
    // Ahora permite actualizar el campo 'estado'
    public boolean actualizarProveedor(Proveedor proveedor) {
        String sql = "UPDATE proveedor SET nombreProveedor = ?, Contacto = ?, Estado = ? WHERE ID_Proveedor = ?"; // Añadido Estado
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proveedor.getNombreProveedor());
            pstmt.setString(2, proveedor.getContacto());
            pstmt.setString(3, proveedor.getEstado()); // Actualizar el estado
            pstmt.setInt(4, proveedor.getIdProveedor());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar proveedor: " + e.getMessage());
            e.printStackTrace(); // Imprime la traza completa para depuración
            return false;
        }
    }

    // 5. Eliminar lógicamente un proveedor (cambiar su estado a 'desactivo')
    // Reemplaza la eliminación física por una actualización de estado
    public boolean eliminarLogicamenteProveedor(int idProveedor) {
        String sql = "UPDATE proveedor SET Estado = 'desactivo' WHERE ID_Proveedor = ?"; // Eliminación lógica
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProveedor);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar proveedor lógicamente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // NOTA: El método 'eliminarProveedor' (físico) ha sido reemplazado por 'eliminarLogicamenteProveedor'.
    // Si realmente necesitas una eliminación física, podrías añadir un método separado para ello,
    // pero para un CRUD típico con eliminación lógica, solo se usa el que actualiza el estado.
}