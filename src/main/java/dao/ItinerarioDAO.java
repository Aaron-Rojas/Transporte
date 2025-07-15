
package dao;
import Conexión.Conexión;
import Modelo.Cliente;
import Modelo.Usuario;
import Modelo.Itinerario;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItinerarioDAO {

    private ClienteDAO clienteDAO;
    private UsuarioDAO usuarioDAO;

    public ItinerarioDAO(ClienteDAO clienteDAO, UsuarioDAO usuarioDAO) {
        this.clienteDAO = clienteDAO;
        this.usuarioDAO = usuarioDAO;
    }
    
    public boolean insertarItinerario(Itinerario itinerario) {
        String sql = "INSERT INTO itinerario (ID_Cliente, ID_UsuarioCreacion, FechaCreacion, FechaInicioViaje, FechaFinViaje, Costo, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexión.conectar(); // Usar tu método de conexión
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Obtener los IDs de los objetos Cliente y Usuario
            stmt.setInt(1, itinerario.getCliente().getIdCliente());
            stmt.setInt(2, itinerario.getUsuarioCreacion().getIdUsuario());
            
            // Convertir LocalDate a java.sql.Date para la base de datos
            stmt.setDate(3, java.sql.Date.valueOf(itinerario.getFechaCreacion()));
            stmt.setDate(4, java.sql.Date.valueOf(itinerario.getFechaInicioViaje()));
            stmt.setDate(5, java.sql.Date.valueOf(itinerario.getFechaFinViaje()));
            
            stmt.setBigDecimal(6, itinerario.getCosto());
            stmt.setBoolean(7, itinerario.isEstado());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        itinerario.setID_Itinerario(rs.getInt(1)); // Asignar el ID generado al objeto
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar itinerario: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

  public Itinerario obtenerItinerarioPorId(int idItinerario) {
        Itinerario itinerario = null;
        String sql = "SELECT ID_Itinerario, ID_Cliente, ID_UsuarioCreacion, FechaCreacion, FechaInicioViaje, FechaFinViaje, Costo, Estado FROM itinerario WHERE ID_Itinerario = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idItinerario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Obtener los IDs de las claves foráneas
                    int idCliente = rs.getInt("ID_Cliente");
                    int idUsuarioCreacion = rs.getInt("ID_UsuarioCreacion");

                    // Usar los DAOs para obtener los objetos Cliente y Usuario completos
                    Cliente cliente = clienteDAO.obtenerClientePorId(idCliente);
                    Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuarioCreacion);

                    // Construir el objeto Itinerario
                    itinerario = new Itinerario(
                        rs.getInt("ID_Itinerario"),
                        cliente,
                        usuario,
                        rs.getDate("FechaCreacion").toLocalDate(), // Convertir java.sql.Date a LocalDate
                        rs.getDate("FechaInicioViaje").toLocalDate(),
                        rs.getDate("FechaFinViaje").toLocalDate(),
                        rs.getBigDecimal("Costo"),
                        rs.getBoolean("Estado")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener itinerario por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return itinerario;
    }
    
   
   public List<Itinerario> obtenerTodosLosItinerariosActivos() {
        List<Itinerario> itinerarios = new ArrayList<>();
        String sql = "SELECT ID_Itinerario, ID_Cliente, ID_UsuarioCreacion, FechaCreacion, FechaInicioViaje, FechaFinViaje, Costo, Estado FROM itinerario WHERE Estado = 1 "; // Filtra por Estado = TRUE
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idCliente = rs.getInt("ID_Cliente");
                int idUsuarioCreacion = rs.getInt("ID_UsuarioCreacion");

                Cliente cliente = clienteDAO.obtenerClientePorId(idCliente);
                Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuarioCreacion);

                Itinerario itinerario = new Itinerario(
                    rs.getInt("ID_Itinerario"),
                    cliente,
                    usuario,
                    rs.getDate("FechaCreacion").toLocalDate(),
                    rs.getDate("FechaInicioViaje").toLocalDate(),
                    rs.getDate("FechaFinViaje").toLocalDate(),
                    rs.getBigDecimal("Costo"),
                    rs.getBoolean("Estado")
                );
                itinerarios.add(itinerario);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los itinerarios activos: " + e.getMessage());
            e.printStackTrace();
        }
        return itinerarios;
    }   
  
   public boolean actualizarItinerario(Itinerario itinerario) {
        String sql = "UPDATE itinerario SET ID_Cliente = ?, ID_UsuarioCreacion = ?, FechaCreacion = ?, FechaInicioViaje = ?, FechaFinViaje = ?, Costo = ?, Estado = ? WHERE ID_Itinerario = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itinerario.getCliente().getIdCliente());
            stmt.setInt(2, itinerario.getUsuarioCreacion().getIdUsuario());
            stmt.setDate(3, java.sql.Date.valueOf(itinerario.getFechaCreacion()));
            stmt.setDate(4, java.sql.Date.valueOf(itinerario.getFechaInicioViaje()));
            stmt.setDate(5, java.sql.Date.valueOf(itinerario.getFechaFinViaje()));
            stmt.setBigDecimal(6, itinerario.getCosto());
            stmt.setBoolean(7, itinerario.isEstado());
            stmt.setInt(8, itinerario.getID_Itinerario()); // El ID para identificar el registro a actualizar

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar itinerario: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

   public boolean actualizarEstadoItinerario(int idItinerario, boolean nuevoEstado) {
        String sql = "UPDATE itinerario SET Estado = ? WHERE ID_Itinerario = ?";
        try (Connection conn = Conexión.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, nuevoEstado);
            stmt.setInt(2, idItinerario);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar estado del itinerario: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarItinerario(int idItinerario) {
        String sql = "DELETE FROM itinerario WHERE ID_Itinerario = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idItinerario);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar itinerario: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
  
}
