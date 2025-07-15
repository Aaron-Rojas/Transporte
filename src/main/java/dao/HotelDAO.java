package dao;

import Modelo.Hotel;
import Conexión.Conexión;
import Modelo.Destino;
import Modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    // SQL para las operaciones CRUD
    private static final String SQL_INSERT = "INSERT INTO hotel (NombreHotel, Direccion, Categoria, ID_Destino, ID_Proveedor, Estado) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM hotel";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM hotel WHERE ID_Hotel = ?";
    private static final String SQL_UPDATE = "UPDATE hotel SET NombreHotel = ?, Direccion = ?, Categoria = ?, ID_Destino = ?, ID_Proveedor = ?, Estado = ? WHERE ID_Hotel = ?";
    private static final String SQL_DELETE = "UPDATE hotel SET Estado = 0 WHERE ID_Hotel = ?"; // Eliminación lógica

    // 1. CREATE - Insertar nuevo hotel
    public boolean insertar(Hotel hotel) throws SQLException {
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, hotel.getNombreHotel());
            stmt.setString(2, hotel.getDireccion());
            stmt.setString(3, hotel.getCategoria());
            stmt.setInt(4, hotel.getDestino().getIdDestino());
            stmt.setInt(5, hotel.getProveedor().getIdProveedor());
            stmt.setBoolean(6, hotel.isEstado());

            int filasAfectadas = stmt.executeUpdate();
            
            // Obtener el ID generado
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        hotel.setIdHotel(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        
        }
    
    }

    // 2. READ - Obtener todos los hoteles activos
    public List<Hotel> seleccionarTodos() throws SQLException {
        List<Hotel> hoteles = new ArrayList<>();
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT + " WHERE Estado = 1");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                hoteles.add(crearHotelDesdeResultSet(rs));
            }
        }
        return hoteles;
    }

    // 3. READ - Obtener hotel por ID
    public Hotel seleccionarPorId(int id) throws SQLException {
        Hotel hotel = null;
        
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    hotel = crearHotelDesdeResultSet(rs);
                }
            }
        }
        return hotel;
    }

    // 4. UPDATE - Actualizar hotel
    public boolean actualizar(Hotel hotel) throws SQLException {
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            
            stmt.setString(1, hotel.getNombreHotel());
            stmt.setString(2, hotel.getDireccion());
            stmt.setString(3, hotel.getCategoria());
            stmt.setInt(4, hotel.getDestino().getIdDestino());
            stmt.setInt(5, hotel.getProveedor().getIdProveedor());
            stmt.setBoolean(6, hotel.isEstado());
            stmt.setInt(7, hotel.getIdHotel());

            return stmt.executeUpdate() > 0;
        }
    }

    // 5. DELETE - Eliminación lógica
    public boolean eliminar(int id) throws SQLException {
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Método auxiliar para crear objeto Hotel desde ResultSet
    private Hotel crearHotelDesdeResultSet(ResultSet rs) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setIdHotel(rs.getInt("ID_Hotel"));
        hotel.setNombreHotel(rs.getString("NombreHotel"));
        hotel.setDireccion(rs.getString("Direccion"));
        hotel.setCategoria(rs.getString("Categoria"));
        
        // Aquí deberías cargar los objetos completos Destino y Proveedor
        // Esto depende de cómo tengas implementados sus DAOs
        // Ejemplo simplificado:
        // Destino destino = destinoDAO.seleccionarPorId(rs.getInt("ID_Destino"));
        // hotel.setDestino(destino);
        
        hotel.setEstado(rs.getBoolean("Estado"));
        return hotel;
    }
    
    
   //Método para listar
    
    public List<Hotel> listarHoteles() throws SQLException {
    List<Hotel> hoteles = new ArrayList<>();
    String sql = "SELECT h.*, p.NombreProveedor, d.NombreDestino " +
                 "FROM hotel h " +
                 "JOIN proveedor p ON h.ID_Proveedor = p.ID_Proveedor " +
                 "JOIN destino d ON h.ID_Destino = d.ID_Destino " +
                 "WHERE h.Estado = 1"; // Solo hoteles activos

    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Hotel hotel = new Hotel();
            // Datos básicos del hotel
            hotel.setIdHotel(rs.getInt("ID_Hotel"));
            hotel.setNombreHotel(rs.getString("NombreHotel"));
            hotel.setDireccion(rs.getString("Direccion"));
            hotel.setCategoria(rs.getString("Categoria"));
            //hotel.setDescripcion(rs.getString("Descripcion"));
            //hotel.setActivo(rs.getBoolean("Estado"));

            // Datos del proveedor (objeto simplificado)
            Proveedor proveedor = new Proveedor();
            proveedor.setIdProveedor(rs.getInt("ID_Proveedor"));
            proveedor.setNombreProveedor(rs.getString("NombreProveedor"));
            hotel.setProveedor(proveedor);

            // Datos del destino (objeto simplificado)
            Destino destino = new Destino();
            destino.setIdDestino(rs.getInt("ID_Destino"));
            destino.setNombreDestino(rs.getString("NombreDestino"));
            hotel.setDestino(destino);

            hoteles.add(hotel);
        }
    }
    return hoteles;
}
}