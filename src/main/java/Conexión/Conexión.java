
package Conexión;
import java.sql.*;

import javax.swing.JOptionPane;
public class Conexión {
	public String driver = "com.mysql.cj.jdbc.Driver";
	public String cadena = "jdbc:mysql://localhost:3306/los_chankas_db";
	public String usuario = "root";
	public String clave ="root";
        
	public Connection conn;
	
        public Conexión (){
            
        }
        
	public Connection Conectar() {
            conn = null;
            
                try 
                {
                Class.forName(driver);
                conn=DriverManager.getConnection(cadena,usuario,clave);
                JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos.");
                
                }catch(ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Error al cargar el JDBC"+e+"Error en el Driver");
                        e.printStackTrace();
                }catch(SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error en la conexion de la BD"+e+ "\nCódigo SQL" + e.getErrorCode(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
                return conn;
                
        }
        
        /*
        public void CerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
                JOptionPane.showMessageDialog(null, "Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error al Cerrar", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // Imprime la traza de la pila para depuración
            }
        }
        
        }
*/
}


