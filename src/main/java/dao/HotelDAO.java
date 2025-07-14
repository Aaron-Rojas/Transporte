package dao;

import Conexión.Conexión;
import Modelo.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    private final Connection conexion;

    public HotelDAO() {
        this.conexion = Conexión.conectar();
    }

    // INSERTAR HOTEL COMPLETO
    public boolean insertarHotel(Hotel hotel) {
        String sql = "INSERT INTO hotel (NombreHotel, descripcion, Direccion, Categoria, Estado, ID_Destino, ID_Proveedor) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hotel.getNombreHotel());
            stmt.setString(2, hotel.getDescripcion());
            stmt.setString(3, hotel.getDireccion());
            stmt.setString(4, hotel.getCategoria());
            stmt.setString(5, hotel.getEstado());
            stmt.setInt(6, hotel.getIdDestino());
            stmt.setInt(7, hotel.getIdProveedor());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar hotel: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ACTUALIZAR HOTEL COMPLETO
    public boolean actualizarHotel(Hotel hotel) {
        String sql = "UPDATE hotel SET NombreHotel = ?, descripcion = ?, Direccion = ?, Categoria = ?, Estado = ?, ID_Destino = ?, ID_Proveedor = ? WHERE ID_Hotel = ?";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hotel.getNombreHotel());
            stmt.setString(2, hotel.getDescripcion());
            stmt.setString(3, hotel.getDireccion());
            stmt.setString(4, hotel.getCategoria());
            stmt.setString(5, hotel.getEstado());
            stmt.setInt(6, hotel.getIdDestino());
            stmt.setInt(7, hotel.getIdProveedor());
            stmt.setInt(8, hotel.getIdHotel());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar hotel: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR HOTEL POR ID
    public boolean eliminarHotel(int idHotel) {
        String sql = "DELETE FROM hotel WHERE ID_Hotel = ?";

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
        String sql = "SELECT * FROM hotel WHERE ID_Hotel = ?";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHotel);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Hotel(
                    rs.getInt("ID_Hotel"),
                    rs.getString("NombreHotel"),
                    rs.getString("descripcion"),
                    rs.getString("Direccion"),
                    rs.getString("Categoria"),
                    rs.getString("Estado"),
                    rs.getInt("ID_Destino"),
                    rs.getInt("ID_Proveedor")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener hotel por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // LISTAR HOTELES PARA TABLA (ID, Nombre, Descripción)
    public List<Hotel> listarHoteles() {
        List<Hotel> lista = new ArrayList<>();
        String sql = "SELECT ID_Hotel, NombreHotel, descripcion FROM hotel";

        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Hotel hotel = new Hotel(
                    rs.getInt("ID_Hotel"),
                    rs.getString("NombreHotel"),
                    rs.getString("descripcion")
                );
                lista.add(hotel);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar hoteles: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // Método de plantilla (no implementado)
    public List<Hotel> obtenerHoteles() {
        throw new UnsupportedOperationException("Método obtenerHoteles() no implementado.");
    }
}
