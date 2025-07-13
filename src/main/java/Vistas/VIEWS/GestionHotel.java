package Vistas.VIEWS;

import Modelo.Hotel;
import dao.HotelDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.logging.Logger;

public class GestionHotel extends javax.swing.JFrame {
    
   private final HotelDAO hotelDAO = new HotelDAO();
    private DefaultTableModel modeloTablaHoteles;
    private static final Logger logger = Logger.getLogger(GestionHotel.class.getName());
    
public GestionHotel() {
    initComponents();
    modeloTablaHoteles = (DefaultTableModel) tbHotel.getModel();
    cargarHotelesEnTabla();
}


public void cargarHotelesEnTabla() {
    modeloTablaHoteles.setRowCount(0); // Limpiar tabla

    List<Hotel> lista = hotelDAO.listarHoteles();
    for (Hotel hotel : lista) {
        modeloTablaHoteles.addRow(new Object[]{
            hotel.getIdHotel(),
            hotel.getNombreHotel(),
            hotel.getDescripcion()
    });
}
}




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCrear = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHotel = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCrear.setBackground(new java.awt.Color(40, 167, 69));
        btnCrear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-plus-sign-21.png"))); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnVolver.setBackground(new java.awt.Color(153, 153, 153));
        btnVolver.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-left-arrow-21.png"))); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        tbHotel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbHotel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID_hotel", "Nombre Hotel", "Descripción"
            }
        ));
        jScrollPane2.setViewportView(tbHotel);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        btnConfiguracion.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-config-23 (1).png"))); // NOI18N
        btnConfiguracion.setText(" CONFIGURACIÓN");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        jPanel4.add(btnConfiguracion);
        btnConfiguracion.setBounds(-20, 340, 210, 50);

        btnHome.setBackground(new java.awt.Color(0, 46, 121));
        btnHome.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-house-23.png"))); // NOI18N
        btnHome.setText(" HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel4.add(btnHome);
        btnHome.setBounds(-20, 90, 210, 50);

        btnClientes.setBackground(new java.awt.Color(0, 46, 121));
        btnClientes.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-customers-23.png"))); // NOI18N
        btnClientes.setText(" CLIENTES");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel4.add(btnClientes);
        btnClientes.setBounds(-20, 140, 210, 50);

        btnReservas.setBackground(new java.awt.Color(0, 46, 121));
        btnReservas.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnReservas.setForeground(new java.awt.Color(255, 255, 255));
        btnReservas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-calendar-23.png"))); // NOI18N
        btnReservas.setText(" RESERVAS");
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });
        jPanel4.add(btnReservas);
        btnReservas.setBounds(-20, 190, 210, 50);

        btnProveedores.setBackground(new java.awt.Color(0, 46, 121));
        btnProveedores.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-supplier-23.png"))); // NOI18N
        btnProveedores.setText(" PROVEEDORES");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jPanel4.add(btnProveedores);
        btnProveedores.setBounds(-20, 240, 210, 50);

        btnReportes.setBackground(new java.awt.Color(0, 46, 121));
        btnReportes.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-report-23 (1).png"))); // NOI18N
        btnReportes.setText(" REPORTES");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jPanel4.add(btnReportes);
        btnReportes.setBounds(-20, 290, 210, 50);

        jPanel2.setBackground(new java.awt.Color(0, 46, 121));

        jButton7.setBackground(new java.awt.Color(179, 23, 23));
        jButton7.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-lock-30.png"))); // NOI18N
        jButton7.setText("Cerrar sesión");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(8, 8, 100));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-bus-58.png"))); // NOI18N
        jLabel3.setText("  Expreso los Chankas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel2);
        jPanel2.setBounds(0, 0, 890, 90);

        jTextField1.setBackground(new java.awt.Color(0, 46, 121));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField1);
        jTextField1.setBounds(-20, 90, 210, 460);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("GESTIÓ DE HOTEL");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(240, 130, 170, 20);

        btnEditar.setBackground(new java.awt.Color(0, 123, 255));
        btnEditar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-edit-21 (1).png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEditar);
        btnEditar.setBounds(500, 420, 130, 40);

        btnEliminar.setBackground(new java.awt.Color(179, 23, 23));
        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-trash-can-21.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addGap(73, 73, 73))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(350, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181)
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(111, 111, 111))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnVolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    int selectedRow = tbHotel.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un hotel de la tabla para eliminar.", "Ningún Hotel Seleccionado", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int idHotel = (int) modeloTablaHoteles.getValueAt(selectedRow, 0); // Columna 0: ID
    String nombreHotel = (String) modeloTablaHoteles.getValueAt(selectedRow, 1); // Columna 1: Nombre

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "¿Está seguro de que desea eliminar el hotel: " + nombreHotel + " (ID: " + idHotel + ")?",
        "Confirmar Eliminación",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );

    if (confirm == JOptionPane.YES_OPTION) {
        boolean exito = hotelDAO.eliminarHotel(idHotel);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Hotel eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarHotelesEnTabla(); // Recarga la tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el hotel. Consulte la consola para más detalles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
    int filaSeleccionada = tbHotel.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un hotel para editar.");
        return;
    }

    // Obtener el ID del hotel desde la tabla (columna 0)
    int idHotel = (int) tbHotel.getValueAt(filaSeleccionada, 0);

    HotelDAO hotelDAO = new HotelDAO();
    Hotel hotelSeleccionado = hotelDAO.obtenerHotelPorId(idHotel);

    if (hotelSeleccionado != null) {
        FormularioHotel formulario = new FormularioHotel(this, true);
        formulario.setGestionHotel(this); // Para refrescar la tabla después de editar
        formulario.setModoEdicion(true, hotelSeleccionado);
        formulario.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "No se pudo cargar el hotel.");
    }
    
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed

    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        FormularioHotel formulario = new FormularioHotel(this, true);
        formulario.setVisible(true);
        cargarHotelesEnTabla(); // Refrescar tabla después de cerrar el formulario
    }//GEN-LAST:event_btnCrearActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GestionHotel().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbHotel;
    // End of variables declaration//GEN-END:variables
}
