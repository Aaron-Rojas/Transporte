package dao;

import Modelo.Proveedor;
import Conexión.Conexión; // Asegúrate de que esta ruta de conexión sea correcta

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Necesario para Statement.RETURN_GENERATED_KEYS
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    // --- C.R.U.D. para Proveedor ---
    // 1. Crear (Guardar) un nuevo proveedor
    public boolean guardarProveedor(Proveedor proveedor) {
    String sql = "INSERT INTO proveedor (nombreProveedor, contacto) VALUES (?, ?)";
    try (Connection conn = Conexión.conectar(); // Asegúrate de que getConnection() funcione
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, proveedor.getNombreProveedor());
        pstmt.setString(2, proveedor.getContacto());
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.err.println("Error al añadir proveedor: " + e.getMessage());
        e.printStackTrace(); // Imprime la traza completa para depuración
        return false;
    }
    }

    // 2. Leer (Obtener) todos los proveedores
    public List<Proveedor> obtenerTodosLosProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT ID_Proveedor, NombreProveedor, Contacto FROM proveedor";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("ID_Proveedor"));
                proveedor.setNombreProveedor(rs.getString("NombreProveedor"));
                proveedor.setContacto(rs.getString("Contacto"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los proveedores: " + e.getMessage());
        }
        return proveedores;
    }

    // 3. Leer (Obtener) un proveedor por su ID
    public Proveedor obtenerProveedorPorId(int idProveedor) {
        Proveedor proveedor = null;
        String sql = "SELECT ID_Proveedor, NombreProveedor, Contacto FROM proveedor WHERE ID_Proveedor = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProveedor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor();
                    proveedor.setIdProveedor(rs.getInt("ID_Proveedor"));
                    proveedor.setNombreProveedor(rs.getString("NombreProveedor"));
                    proveedor.setContacto(rs.getString("Contacto"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedor por ID: " + e.getMessage());
        }
        return proveedor;
    }

    // 4. Actualizar un proveedor existente
    public boolean actualizarProveedor(Proveedor proveedor) {
    String sql = "UPDATE proveedor SET nombreProveedor = ?, Contacto = ? WHERE ID_Proveedor = ?";
    try (Connection conn = Conexión.conectar(); // Asegúrate de que getConnection() funcione
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, proveedor.getNombreProveedor());
        pstmt.setString(2, proveedor.getContacto());
        pstmt.setInt(3, proveedor.getIdProveedor()); // Asegúrate de que el ID se usa para la cláusula WHERE
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.err.println("Error al actualizar proveedor: " + e.getMessage());
        e.printStackTrace(); // Imprime la traza completa para depuración
        return false;
    }
    }

    // 5. Eliminar un proveedor
    public boolean eliminarProveedor(int idProveedor) {
        String sql = "DELETE FROM proveedor WHERE ID_Proveedor = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProveedor);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar proveedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}