
package Conexión; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Conexión { 

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/los_chankas_db";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";

    // Constructor privado para evitar instanciación, ya que la clase es solo para métodos estáticos.
    private Conexión() {
    
    }

    // Método estático para obtener una conexión
    public static Connection conectar() { 
        Connection conn = null; 

        try {
            Class.forName(DRIVER); // Cargar el driver
            conn = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CLAVE);
            System.out.println("Conexión exitosa a la base de datos."); 
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el JDBC (Driver): " + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión de la BD: " + e.getMessage() + "\nCódigo SQL: " + e.getErrorCode(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return conn; 
    }

    // Método para cerrar recursos, que podrías usar si abres 
    //PreparedStatement/ResultSet
    
    public static void cerrar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada."); 
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Puedes tener métodos sobrecargados para cerrar PreparedStatement y ResultSet
    public static void cerrar(PreparedStatement ps) { 
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) { 
            System.err.println("Error al cerrar PreparedStatement: " + e.getMessage()); e.printStackTrace(); }
    }
    public static void cerrar(ResultSet rs) { 
        try {
            if (rs != null) rs.close();
        } 
        catch (SQLException e) { 
            System.err.println("Error al cerrar ResultSet: " + e.getMessage()); e.printStackTrace(); 
        }
    }
    
}