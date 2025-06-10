
package Controlador;
import Vistas.VIEWS.Configuración;
import Vistas.VIEWS.GestionClientes;
import Vistas.VIEWS.GestionItinerario;
import Vistas.VIEWS.GestionProveedores;
import Vistas.VIEWS.Principal;
import Vistas.VIEWS.Reportes;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavegacionController {
    public static void configurarBotones(JButton btnHome, JButton btnClientes, 
                                      JButton btnReservas, JButton btnProveedores, 
                                      JButton btnReportes, JButton btnConfiguracion, 
                                      JFrame frameActual) {
        
        // Configura un ActionListener común para todos los botones
        ActionListener listener = (ActionEvent e) -> {
            // Cierra el frame actual
            frameActual.dispose();
            
            // Abre el nuevo frame según el botón presionado
            if (e.getSource() == btnHome) {
                new Principal().setVisible(true);
            } else if (e.getSource() == btnClientes) {
                new GestionClientes().setVisible(true);
            } else if (e.getSource() == btnReservas) {
                new GestionItinerario().setVisible(true);
            } else if (e.getSource() == btnProveedores) {
                new GestionProveedores().setVisible(true);
            } else if (e.getSource() == btnReportes) {
                new Reportes().setVisible(true);
            } else if (e.getSource() == btnConfiguracion) {
                System.out.println("Intentando abrir ConfiguracionFrame"); // Mensaje de depuración
    Configuración configFrame = new Configuración();
    configFrame.setVisible(true);
    System.out.println("Frame debería estar visible ahora");
            }
        };
        
        // Asigna el mismo ActionListener a todos los botones
        btnHome.addActionListener(listener);
        btnClientes.addActionListener(listener);
        btnReservas.addActionListener(listener);
        btnProveedores.addActionListener(listener);
        btnReportes.addActionListener(listener);
        btnConfiguracion.addActionListener(listener);
    }
}
