package dao;

import Conexión.Conexión;
import Modelo.Destino;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DestinoDAO {

    public boolean agregarDestino(Destino destino) {
        String sql = "INSERT INTO destino (NombreDestino, Descripcion, Estado) VALUES (?, ?, ?)";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, destino.getNombreDestino());
            pstmt.setString(2, destino.getDescripcion());
            pstmt.setBoolean(3, destino.isEstado()); // Use setBoolean for boolean state
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar destino: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarDestino(Destino destino) {
        String sql = "UPDATE destino SET NombreDestino = ?, Descripcion = ?, Estado = ? WHERE ID_Destino = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, destino.getNombreDestino());
            pstmt.setString(2, destino.getDescripcion());
            pstmt.setBoolean(3, destino.isEstado()); // Use setBoolean for boolean state
            pstmt.setInt(4, destino.getIdDestino());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar destino: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Destino obtenerDestinoPorId(int idDestino) {
        Destino destino = null;
        String sql = "SELECT ID_Destino, NombreDestino, Descripcion, Estado FROM destino WHERE ID_Destino = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDestino);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    destino = new Destino(
                            rs.getInt("ID_Destino"),
                            rs.getString("NombreDestino"),
                            rs.getString("Descripcion"),
                            rs.getBoolean("Estado") // Use getBoolean for boolean state
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener destino por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return destino;
    }

    public List<Destino> obtenerTodosLosDestinos() {
        List<Destino> destinos = new ArrayList<>();
        String sql = "SELECT ID_Destino, NombreDestino, Descripcion, Estado FROM destino";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                destinos.add(new Destino(
                        rs.getInt("ID_Destino"),
                        rs.getString("NombreDestino"),
                        rs.getString("Descripcion"),
                        rs.getBoolean("Estado") // Use getBoolean for boolean state
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener todos los destinos: " + e.getMessage());
            e.printStackTrace();
        }
        return destinos;
    }

    public boolean eliminarDestino(int idDestino) {
        String sql = "DELETE FROM destino WHERE ID_Destino = ?";
        try (Connection conn = Conexión.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDestino);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar destino: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}