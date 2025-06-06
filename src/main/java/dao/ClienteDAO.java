
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

    
    
}
