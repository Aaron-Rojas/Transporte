
package dao;
import Modelo.Cliente;  //se importa de la clase cliente, que contiene los atribuos
import Conexión.Conexión; //Se importa la conexión para la BD

//importaciones sql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
    // Método para guardar un nuevo cliente
    public boolean guardarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (DNI_Documento, NombreCompleto, Telefono, Email, Preferencias, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombreCompleto());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getPreferencias());
            stmt.setBoolean(6, cliente.isActivo()); // Asigna el valor del nuevo campo 'activo' (que se mapea a 'Estado' BIT)

            int filasAfectadas = stmt.executeUpdate();
            
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            e.printStackTrace(); // Agregado para mejor depuración
            return false;
        }
    }
    //metodo para mostrar los datos en el JTable
    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT ID_Cliente, DNI_Documento, NombreCompleto, Telefono, Email, Preferencias, Estado FROM cliente"; // Selecciona 'Estado'
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_Cliente"));
                cliente.setDni(rs.getString("DNI_Documento"));
                cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setPreferencias(rs.getString("Preferencias"));
                cliente.setActivo(rs.getBoolean("Estado")); // Lee 'Estado' de la BD y lo asigna a 'activo' en el objeto
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }
   // Método para obtener SOLO los clientes activos
    public List<Cliente> obtenerClientesActivos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT ID_Cliente, DNI_Documento, NombreCompleto, Telefono, Email, Preferencias, Estado FROM cliente WHERE Estado = 1"; // Filtra por 'Estado' = 1
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_Cliente"));
                cliente.setDni(rs.getString("DNI_Documento"));
                cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setPreferencias(rs.getString("Preferencias"));
                cliente.setActivo(rs.getBoolean("Estado")); // Lee 'Estado' de la BD y lo asigna a 'activo'
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes activos: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    // Método para actualizar un cliente existente
    
 // Método para actualizar un cliente existente (incluyendo el estado 'Estado')
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET DNI_Documento=?, NombreCompleto=?, Telefono=?, Email=?, Preferencias=?, Estado=? WHERE ID_Cliente=?"; // Actualiza 'Estado'
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombreCompleto());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getPreferencias());
            stmt.setBoolean(6, cliente.isActivo()); // Asigna el valor de 'activo' al campo 'Estado'
            stmt.setInt(7, cliente.getIdCliente());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    

// Método para eliminar un cliente por su ID
public boolean eliminarCliente(int idCliente) {
    String sql = "DELETE FROM cliente WHERE ID_Cliente=?";
    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idCliente);

        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException e) {
        System.err.println("Error al eliminar cliente: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
 // Método para ELIMINAR LÓGICAMENTE un cliente por su ID
    public boolean eliminarClienteLogico(int idCliente) {
        String sql = "UPDATE cliente SET Estado = 0 WHERE ID_Cliente=?"; // Establece 'Estado' a 0 (desactivo)
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar lógicamente el cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

     // Opcional: Método para 'restaurar' un cliente (volverlo activo)
    public boolean restaurarCliente(int idCliente) {
        String sql = "UPDATE cliente SET Estado = 1 WHERE ID_Cliente=?"; // Establece 'Estado' a 1 (activo)
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al restaurar el cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
  
    // Método para búsqueda de clientes (filtra solo activos por defecto)
    public List<Cliente> buscarClientes(String textoBusqueda) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT ID_Cliente, DNI_Documento, NombreCompleto, Telefono, Email, Preferencias, Estado FROM cliente " +
                     "WHERE Estado = 1 AND (DNI_Documento LIKE ? OR NombreCompleto LIKE ? OR Telefono LIKE ? OR Email LIKE ?)";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + textoBusqueda + "%");
            stmt.setString(2, "%" + textoBusqueda + "%");
            stmt.setString(3, "%" + textoBusqueda + "%");
            stmt.setString(4, "%" + textoBusqueda + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("ID_Cliente"));
                    cliente.setDni(rs.getString("DNI_Documento"));
                    cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                    cliente.setTelefono(rs.getString("Telefono"));
                    cliente.setEmail(rs.getString("Email"));
                    cliente.setPreferencias(rs.getString("Preferencias"));
                    cliente.setActivo(rs.getBoolean("Estado")); // Lee 'Estado' de la BD y lo asigna a 'activo'
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }
    

 // Método para obtener un solo cliente por ID
    public Cliente obtenerClientePorId(int idCliente) {
        Cliente cliente = null;
        String sql = "SELECT ID_Cliente, DNI_Documento, NombreCompleto, Telefono, Email, Preferencias, Estado FROM cliente WHERE ID_Cliente = ?"; // Selecciona 'Estado'
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("ID_Cliente"));
                    cliente.setDni(rs.getString("DNI_Documento"));
                    cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                    cliente.setTelefono(rs.getString("Telefono"));
                    cliente.setEmail(rs.getString("Email"));
                    cliente.setPreferencias(rs.getString("Preferencias"));
                    cliente.setActivo(rs.getBoolean("Estado")); // Lee 'Estado' de la BD y lo asigna a 'activo'
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return cliente;
    }

}
