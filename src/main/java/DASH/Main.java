
package DASH;

import Conexión.Conexión;
import Vistas.VIEWS.Login;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        new Login().setVisible(true); // o el primer frame ha mostrar
            
        //Conexion a la BD
        Connection connection = null;
        
        try {
                connection = Conexión.conectar(); // Llama al método estático directamente

                if (connection != null) {
                    System.out.println("Conexión exitosa con la BD al iniciar la aplicación.");
                } else {
                    System.err.println("ERROR: No se pudo establecer la conexión con la base de datos al iniciar la aplicación.");
                    JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. La aplicación puede no funcionar correctamente.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                }
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                        System.out.println("Conexión de prueba cerrada."); // Para depuración
                    } catch (SQLException e) {
                        System.err.println("Error al cerrar la conexión de prueba: " + e.getMessage());
                    }
                }
            }
        });
       
    }
    
}   
