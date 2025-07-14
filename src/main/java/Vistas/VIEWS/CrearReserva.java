/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.VIEWS;

import Vistas.VIEWS.ConfirmacionReserva;
import Controlador.NavegacionController;
import Modelo.Usuario;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB-USR-LCENTRO
 */
public class CrearReserva extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    
    private Usuario usuarioActual;
    public CrearReserva(Usuario usuarioLogeado) {
        initComponents();
         
        
                NavegacionController.configurarBotones(
    btnHome,       // Botón Home
    btnClientes,   // Botón Clientes
    btnReservas,   // Botón Reservas
    btnProveedores,// Botón Proveedores
    btnReportes,   // Botón Reportes
    btnConfiguracion, // Botón Configuración
    this          // Referencia al frame actual (this)
        );
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAsiento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        btnCalcularMonto = new javax.swing.JButton();
        btnAsiento = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        btnConfiguracion.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setText("CONFIGURACIÓN");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        jPanel1.add(btnConfiguracion);
        btnConfiguracion.setBounds(0, 330, 200, 50);

        btnHome.setBackground(new java.awt.Color(0, 46, 121));
        btnHome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome);
        btnHome.setBounds(0, 80, 200, 50);

        btnClientes.setBackground(new java.awt.Color(0, 46, 121));
        btnClientes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setText("CLIENTES");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btnClientes);
        btnClientes.setBounds(0, 130, 200, 50);

        btnReservas.setBackground(new java.awt.Color(0, 46, 121));
        btnReservas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReservas.setForeground(new java.awt.Color(255, 255, 255));
        btnReservas.setText("RESERVAS");
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });
        jPanel1.add(btnReservas);
        btnReservas.setBounds(0, 180, 200, 50);

        btnProveedores.setBackground(new java.awt.Color(0, 46, 121));
        btnProveedores.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedores.setText("PROVEEDORES");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jPanel1.add(btnProveedores);
        btnProveedores.setBounds(0, 230, 200, 50);

        btnReportes.setBackground(new java.awt.Color(0, 46, 121));
        btnReportes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("REPORTES");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jPanel1.add(btnReportes);
        btnReportes.setBounds(0, 280, 200, 50);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("CREAR RESERVA");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(240, 130, 160, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel4.setText("Cliente:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(250, 180, 60, 18);
        jPanel1.add(jTextField1);
        jTextField1.setBounds(690, 170, 160, 30);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setText("Actividades a realizar:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(570, 260, 160, 20);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(370, 213, 160, 30);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel6.setText("Lugar Turístico:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(250, 220, 120, 20);
        jPanel1.add(jTextField2);
        jTextField2.setBounds(370, 170, 160, 30);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel7.setText("Fecha de viaje:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(570, 170, 110, 20);
        jPanel1.add(txtAsiento);
        txtAsiento.setBounds(330, 360, 160, 30);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel8.setText("Asiento:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(250, 320, 90, 20);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(690, 210, 160, 30);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel9.setText("Presupuesto:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(250, 270, 90, 20);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel10.setText("Hotel:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(570, 210, 90, 20);
        jPanel1.add(jTextField5);
        jTextField5.setBounds(570, 300, 270, 30);

        btnCalcularMonto.setBackground(new java.awt.Color(179, 23, 23));
        btnCalcularMonto.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnCalcularMonto.setForeground(new java.awt.Color(255, 255, 255));
        btnCalcularMonto.setText("Calcular Monto");
        btnCalcularMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularMontoActionPerformed(evt);
            }
        });
        jPanel1.add(btnCalcularMonto);
        btnCalcularMonto.setBounds(680, 400, 169, 42);

        btnAsiento.setText("Asiento");
        btnAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsientoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAsiento);
        btnAsiento.setBounds(330, 320, 100, 30);

        jPanel2.setBackground(new java.awt.Color(0, 46, 121));

        jButton7.setBackground(new java.awt.Color(179, 23, 23));
        jButton7.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Cerrar sesión");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(8, 8, 100));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 890, 80);

        jTextField3.setBackground(new java.awt.Color(0, 46, 121));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField3);
        jTextField3.setBounds(0, 340, 200, 210);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                GestionClientes GI = new  GestionClientes (usuarioActual);
                
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose(); 
                System.out.println("Admin redirigiendo a Gestión Clientes.");

            }
//            if ("usuario".equalsIgnoreCase(nombreRol)) {
//                GestionClientes GI = new  GestionClientes (usuarioActual);
//                GI.setVisible(true);
//                GI.setLocationRelativeTo(null);
//                this.dispose();                 
//                System.out.println("User redirigiendo a Gestión Clientes .");
////            
            else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la gestión de clientes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Gestión de Clientes por rol: " + nombreRol);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasActionPerformed
        if (usuarioActual !=null && usuarioActual.getRol() !=null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();
            if ("admin".equalsIgnoreCase(nombreRol)) {
                GestionItinerario GI = new  GestionItinerario (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose(); 
                System.out.println("Admin redirigiendo a Reservas.");

            }
            if ("usuario".equalsIgnoreCase(nombreRol)) {
                GestionItinerario GI = new  GestionItinerario (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose();                 
                System.out.println("User redirigiendo a Reservas.");
            
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la seccion de Reservas.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a la seccion de Reservas por rol: " + nombreRol);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnCalcularMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularMontoActionPerformed
ConfirmacionReserva confirmacion = new ConfirmacionReserva();
confirmacion.setVisible(true);
        
                dispose(); // Cierra CrearReserva
    }//GEN-LAST:event_btnCalcularMontoActionPerformed

    private void btnAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsientoActionPerformed
      asientos ventanaAsientos = new asientos();
    ventanaAsientos.setVisible(true);
    
    // Esperar a que se cierre la ventana de asientos
    ventanaAsientos.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            int numeroAsiento = asientos.getAsientoSeleccionado();
            if (numeroAsiento > 0) {
                txtAsiento.setText(String.valueOf(numeroAsiento));
                // Opcional: limpiar la selección después de usarla
                asientos.limpiarSeleccion();
            }
        }
    });
    
    }//GEN-LAST:event_btnAsientoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new CrearReserva().setVisible(true);AARON ME DIJO QUE LO IGNORARA
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsiento;
    private javax.swing.JButton btnCalcularMonto;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField txtAsiento;
    // End of variables declaration//GEN-END:variables

    private void abrirFrame(String calcularMonto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
