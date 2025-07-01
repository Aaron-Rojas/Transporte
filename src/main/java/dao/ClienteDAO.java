
package dao;
import Modelo.Cliente;  //se importa de la clase cliente, que contiene los atribuos
import Conexión.Conexión; //Se importa la conexión para la BD
import Vistas.VIEWS.GestionClientes;

//importaciones sql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ClienteDAO {
    // Método para guardar un nuevo cliente
    public boolean guardarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (DNI_Documento, NombreCompleto, Telefono, Email, Preferencias) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexión.conectar(); //llama al metodo para conectarse
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombreCompleto());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getPreferencias());

            int filasAfectadas = stmt.executeUpdate();
            
            return filasAfectadas > 0; // Retorna true si se insertó al menos una fila
        } catch (SQLException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            return false;
        }
    }
    //metodo para mostrar los datos en el JTable
    public List<Cliente> obtenerTodosLosClientes() {
      List<Cliente> clientes = new ArrayList<>(); //inicializa un arrayList
      String sql = "SELECT ID_Cliente, DNI_Documento, NombreCompleto, Telefono, Email, Preferencias FROM cliente";
      try (Connection conn = Conexión.conectar(); //variables para la conexión
           PreparedStatement stmt = conn.prepareStatement(sql); //sentencia sql
           ResultSet rs = stmt.executeQuery()) // rs obtiene los datos de la tabla
           {
           //mientras sigue  iterando 
           while (rs.next()) {
               //se completa los datos de la clase Cliente (modelo)
              Cliente cliente = new Cliente();
              cliente.setIdCliente(rs.getInt("ID_Cliente"));
              cliente.setDni(rs.getString("DNI_Documento"));
              cliente.setNombreCompleto(rs.getString("NombreCompleto"));
              cliente.setTelefono(rs.getString("Telefono"));
              cliente.setEmail(rs.getString("Email"));
              cliente.setPreferencias(rs.getString("Preferencias"));
              clientes.add(cliente);
          }
      } catch (SQLException e) {
          System.err.println("Error al obtener clientes: " + e.getMessage());
          e.printStackTrace();
      }
      return clientes;
}  

// Método para obtener un solo cliente por ID (crucial para la edición)
public Cliente obtenerClientePorId(int idCliente) {
    Cliente cliente = null;
    String sql = "SELECT ID_Cliente, DNI_Documento, NombreCompleto, Telefono, Email, Preferencias FROM cliente WHERE ID_Cliente = ?";
    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idCliente);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) { // Si encuentra un registro
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_Cliente"));
                cliente.setDni(rs.getString("DNI_Documento"));
                cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setPreferencias(rs.getString("Preferencias"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener cliente por ID: " + e.getMessage());
        e.printStackTrace(); // Es bueno tener un stack trace para depuración
    }
    return cliente;
}    
 
// Método para actualizar un cliente existente
public boolean actualizarCliente(Cliente cliente) {
    String sql = "UPDATE cliente SET DNI_Documento=?, NombreCompleto=?, Telefono=?, Email=?, Preferencias=? WHERE ID_Cliente=?";
    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cliente.getDni());
        stmt.setString(2, cliente.getNombreCompleto());
        stmt.setString(3, cliente.getTelefono());
        stmt.setString(4, cliente.getEmail());
        stmt.setString(5, cliente.getPreferencias());
        stmt.setInt(6, cliente.getIdCliente()); // El ID es fundamental para WHERE

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

// metodo para para la búsqueda

public List<Cliente> buscarClientes(String textoBusqueda) {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT ID_Cliente, DNI_Documento, NombreCompleto, Telefono, Email, Preferencias FROM cliente " +
                 "WHERE DNI_Documento LIKE ? OR NombreCompleto LIKE ? OR Telefono LIKE ? OR Email LIKE ?"; // Puedes añadir más campos a la búsqueda
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
                clientes.add(cliente);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar clientes: " + e.getMessage());
        e.printStackTrace();
    }
    return clientes;
}


}
