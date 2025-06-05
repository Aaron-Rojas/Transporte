
package DASH;

import Conexión.Conexión;
import VIEWS.Login;
import java.sql.Connection;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        new Login().setVisible(true); // o el primer frame que quieras mostrar
            
        //Conexion a la BD
        Conexión dbConnect = new Conexión();
        Connection connection = dbConnect.Conectar();
       
        if (connection !=null) {
            System.out.println("Conexión correctamente con la BD");
           dbConnect.CerrarConexion();
        }else{
            System.out.println("No se puedo establecer la conexión");
        }
    
        
        
        });
       
    }
    
}   
