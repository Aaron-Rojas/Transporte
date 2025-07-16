package dao;

import Conexión.Conexión;
import Modelo.Destino;
import Modelo.Hotel;
import Modelo.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Necesario para Statement.RETURN_GENERATED_KEYS
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    private DestinoDAO destinoDAO;
    private ProveedorDAO proveedorDAO;

    public HotelDAO(DestinoDAO destinoDAO, ProveedorDAO proveedorDAO) {
        this.destinoDAO = destinoDAO;
        this.proveedorDAO = proveedorDAO;
    }

    // INSERTAR HOTEL COMPLETO
    public boolean insertarHotel(Hotel hotel) {
        String sql = "INSERT INTO hotel (NombreHotel, Direccion, Categoria, ID_Destino, ID_Proveedor, Estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // Añadir RETURN_GENERATED_KEYS aquí

            stmt.setString(1, hotel.getNombreHotel());
            stmt.setString(2, hotel.getDireccion());
            stmt.setString(3, hotel.getCategoria());

            // Es importante que Destino y Proveedor no sean null antes de intentar acceder a sus IDs
            if (hotel.getDestino() != null) {
                stmt.setInt(4, hotel.getDestino().getIdDestino());
            } else {
                // Manejar el caso donde Destino es null, por ejemplo, lanzar una excepción
                // o setear un valor NULL si la columna lo permite y es apropiado
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            if (hotel.getProveedor() != null) {
                stmt.setInt(5, hotel.getProveedor().getIdProveedor());
            } else {
                // Manejar el caso donde Proveedor es null
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            stmt.setBoolean(6, hotel.isEstado());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        hotel.setIdHotel(rs.getInt(1)); // Asignar el ID autogenerado
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar hotel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // ACTUALIZAR HOTEL COMPLETO
    public boolean actualizarHotel(Hotel hotel) {
        String sql = "UPDATE hotel SET NombreHotel = ?, Direccion = ?, Categoria = ?, ID_Destino = ?, ID_Proveedor = ?, Estado = ? WHERE ID_Hotel = ?";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Eliminar Statement.RETURN_GENERATED_KEYS aquí

            stmt.setString(1, hotel.getNombreHotel());
            stmt.setString(2, hotel.getDireccion());
            stmt.setString(3, hotel.getCategoria());

            if (hotel.getDestino() != null) {
                stmt.setInt(4, hotel.getDestino().getIdDestino());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            if (hotel.getProveedor() != null) {
                stmt.setInt(5, hotel.getProveedor().getIdProveedor());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            stmt.setBoolean(6, hotel.isEstado());
            stmt.setInt(7, hotel.getIdHotel());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Solo devuelve si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al actualizar hotel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // ELIMINAR HOTEL POR ID (Eliminación lógica o física, si es física este nombre es correcto)
    public boolean eliminarHotel(int idHotel) {
        String sql = "DELETE FROM hotel WHERE ID_Hotel = ?"; // Este es un borrado físico

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHotel);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar hotel: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // OBTENER UN HOTEL POR SU ID (TODOS LOS CAMPOS)
    public Hotel obtenerHotelPorId(int idHotel) {
        Hotel hotel = null;
        String sql = "SELECT ID_Hotel, NombreHotel, Direccion, Categoria, ID_Destino, ID_Proveedor, Estado FROM hotel WHERE ID_Hotel = ? ";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHotel);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Recuperar IDs de claves foráneas
                int idDestino = rs.getInt("ID_Destino");
                int idProveedor = rs.getInt("ID_Proveedor");

                // Obtener objetos relacionados usando los DAOs inyectados
                Destino destino = destinoDAO.obtenerDestinoPorId(idDestino);
                Proveedor proveedor = proveedorDAO.obtenerProveedorPorId(idProveedor);

                // Crear y poblar el objeto Hotel
                hotel = new Hotel();
                hotel.setIdHotel(rs.getInt("ID_Hotel"));
                hotel.setNombreHotel(rs.getString("NombreHotel")); // Asegurarse de recuperar NombreHotel
                hotel.setDireccion(rs.getString("Direccion"));
                hotel.setCategoria(rs.getString("Categoria"));
                hotel.setDestino(destino);
                hotel.setProveedor(proveedor);
                hotel.setEstado(rs.getBoolean("Estado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener hotel por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return hotel;
    }

    // LISTAR HOTELES PARA TABLA (ID, Nombre, Dirección, Categoría, Destino, Proveedor, Estado)
    public List<Hotel> obtenerTodosLosHoteles() {
        List<Hotel> hotell = new ArrayList<>();
        String sql = "SELECT ID_Hotel, NombreHotel, Direccion, Categoria, ID_Destino, ID_Proveedor, Estado FROM hotel";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idDestino = rs.getInt("ID_Destino");
                int idProveedor = rs.getInt("ID_Proveedor");

                Destino destino = destinoDAO.obtenerDestinoPorId(idDestino);
                Proveedor proveedor = proveedorDAO.obtenerProveedorPorId(idProveedor);

                Hotel hotel = new Hotel();
                hotel.setIdHotel(rs.getInt("ID_Hotel"));
                hotel.setNombreHotel(rs.getString("NombreHotel"));
                hotel.setDireccion(rs.getString("Direccion"));
                hotel.setCategoria(rs.getString("Categoria"));
                hotel.setDestino(destino);
                hotel.setProveedor(proveedor);
                hotel.setEstado(rs.getBoolean("Estado"));

                hotell.add(hotel);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener hoteles: " + e.getMessage());
            e.printStackTrace();
        }
        return hotell;
    }

    // ELIMINAR HOTEL FÍSICO (Si deseas una eliminación física, este método está bien)
    public boolean eliminarHotelFisico(int idHotel) {
        String sql = "DELETE FROM hotel WHERE ID_Hotel = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHotel);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar Hotel físicamente: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DESACTIVAR HOTEL (Eliminación lógica, si ID_LugarTuristico en tu SQL es ID_Hotel)
    public boolean desactivarHotel(int idHotel) { // El parámetro es idHotel
        // Asegúrate de que la columna sea ID_Hotel si estás trabajando con la tabla hotel.
        // Si tu tabla hotel usa 'ID_LugarTuristico' como FK para lugares, entonces déjalo,
        // pero lo normal sería 'ID_Hotel' para desactivar el hotel directamente.
        String sql = "UPDATE hotel SET Estado = 0 WHERE ID_Hotel = ?"; // 0 para "desactivo"
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHotel);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al desactivar Hotel: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // OBTENER HOTELES ACTIVOS
    public List<Hotel> obtenerTodosLosHotelesActivos() {
        List<Hotel> hotell = new ArrayList<>();
        String sql = "SELECT ID_Hotel, NombreHotel, Direccion, Categoria, ID_Destino, ID_Proveedor, Estado FROM hotel WHERE Estado = 1";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idDestino = rs.getInt("ID_Destino");
                int idProveedor = rs.getInt("ID_Proveedor");

                Destino destino = destinoDAO.obtenerDestinoPorId(idDestino);
                Proveedor proveedor = proveedorDAO.obtenerProveedorPorId(idProveedor);

                Hotel hotel = new Hotel();
                hotel.setIdHotel(rs.getInt("ID_Hotel"));
                hotel.setNombreHotel(rs.getString("NombreHotel")); // Asegúrate de recuperar NombreHotel
                hotel.setDireccion(rs.getString("Direccion"));
                hotel.setCategoria(rs.getString("Categoria"));
                hotel.setDestino(destino);
                hotel.setProveedor(proveedor);
                hotel.setEstado(rs.getBoolean("Estado"));

                hotell.add(hotel);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener hoteles activos: " + e.getMessage()); // Mensaje más específico
            e.printStackTrace();
        }
        return hotell;
    }
}