/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DASH;

import VIEWS.Login;
import javax.swing.SwingUtilities;

/**
 *
 * @author lv248
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true); // o el primer frame que quieras mostrar
        });
        // TODO code application logic here
    }
    
}
