package dao;

import Modelo.Destino;
import Conexión.Conexión;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DestinoDAO {
    
    public List<Destino> listarDestinos() {
    return obtenerTodosLosDestinos();
}


    // Método para agregar un nuevo destino
    public boolean agregarDestino(Destino destino) {
        String sql = "INSERT INTO destino (NombreDestino, Descripcion, Estado) VALUES (?, ?, ?)"; // <--- Added Estado
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, destino.getNombreDestino());
            pstmt.setString(2, destino.getDescripcion());
            pstmt.setString(3, destino.getEstado()); // <--- Set Estado
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("DEBUG DAO: Filas afectadas al agregar: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL al añadir destino: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado al añadir destino: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un destino existente
    public boolean actualizarDestino(Destino destino) {
        String sql = "UPDATE destino SET NombreDestino = ?, Descripcion = ?, Estado = ? WHERE ID_Destino = ?"; // <--- Added Estado
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, destino.getNombreDestino());
            pstmt.setString(2, destino.getDescripcion());
            pstmt.setString(3, destino.getEstado()); // <--- Set Estado
            pstmt.setInt(4, destino.getIdDestino());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("DEBUG DAO: Filas afectadas al actualizar: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL al actualizar destino: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado al actualizar destino: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un destino por su ID (no cambia, no usa estado)
    public boolean eliminarDestino(int idDestino) {
        String sql = "DELETE FROM destino WHERE ID_Destino = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDestino);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("DEBUG DAO: Filas afectadas al eliminar: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL al eliminar destino: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado al eliminar destino: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener un destino por su ID
    public Destino obtenerDestinoPorId(int idDestino) {
        String sql = "SELECT ID_Destino, NombreDestino, Descripcion, Estado FROM destino WHERE ID_Destino = ?"; // <--- Added Estado
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDestino);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Destino(
                            rs.getInt("ID_Destino"),
                            rs.getString("NombreDestino"),
                            rs.getString("Descripcion"),
                            rs.getString("Estado") // <--- Get Estado
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener destino por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Método para obtener todos los destinos
    public List<Destino> obtenerTodosLosDestinos() {
        List<Destino> destinos = new ArrayList<>();
        String sql = "SELECT ID_Destino, NombreDestino, Descripcion, Estado FROM destino"; // <--- Added Estado
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                destinos.add(new Destino(
                        rs.getInt("ID_Destino"),
                        rs.getString("NombreDestino"),
                        rs.getString("Descripcion"),
                        rs.getString("Estado") // <--- Get Estado
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener todos los destinos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener todos los destinos: " + e.getMessage());
            e.printStackTrace();
        }
        return destinos;
    }
}
    
