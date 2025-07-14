
package Vistas.VIEWS;

import Modelo.Destino;
import dao.DestinoDAO;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Added for ToggleButton

public class FormularioDestino extends javax.swing.JDialog {
   
    private DestinoDAO destinoDAO; // Declarar la instancia del DAO
    private boolean esNuevo; // Bandera para saber si es nuevo o edición
    private Destino destinoEditando; // Objeto para mantener el destino que se está editando

      public FormularioDestino(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        destinoDAO = new DestinoDAO();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        // Initialize the toggle button text
        updateToggleButtonText(ToggleButtonEstado.isSelected());
        addToggleButtonActionListener(); // Add action listener for the toggle button
    }

    // Method to configure the form in edit or creation mode
    public void setModoEdicion(boolean esNuevo, Destino destino) {
        this.esNuevo = esNuevo;
        this.destinoEditando = destino;
        if (esNuevo) {
            setTitle("Agregar Nuevo Destino");
            txtNombreDestino.setText("");
            txtDescripcion.setText("");
            ToggleButtonEstado.setSelected(true); // Default to active for new destination
            updateToggleButtonText(true);
        } else {
            setTitle("Modificar Destino");
            if (destino != null) {
                txtNombreDestino.setText(destino.getNombreDestino());
                txtDescripcion.setText(destino.getDescripcion());
                ToggleButtonEstado.setSelected(destino.isEstado()); // Set state from object
                updateToggleButtonText(destino.isEstado());
            }
        }
    }
    
    // Method to update the toggle button text based on its state
    
    private void updateToggleButtonText(boolean isActive) {
        if (isActive) {
            ToggleButtonEstado.setText("Activo");
        } else {
            ToggleButtonEstado.setText("Inactivo");
        }
    }

    // THIS IS THE MODIFIED PART
    private void addToggleButtonActionListener() {
        if (ToggleButtonEstado != null) {
            ToggleButtonEstado.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    boolean estaActivo = ToggleButtonEstado.isSelected();
                    updateToggleButtonText(estaActivo); // Update the button text immediately

                    if (destinoEditando != null) {
                        destinoEditando.setEstado(estaActivo); // Update the Destino object
                        
                        // Attempt to update the database directly
                        boolean exitoActualizacion = destinoDAO.actualizarDestino(destinoEditando); 
                        if (exitoActualizacion) {
                            JOptionPane.showMessageDialog(null, "Estado del destino actualizado a: " + (estaActivo ? "Activo" : "Inactivo"), "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al actualizar el estado del destino en la base de datos.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
                            // Optionally, revert the toggle button state if update fails
                            ToggleButtonEstado.setSelected(!estaActivo);
                            updateToggleButtonText(!estaActivo);
                        }
                    } else {
                        // If it's a new destination, just reflect the state in the message
                        JOptionPane.showMessageDialog(null, "El estado de 'Activo' para el nuevo destino es: " + (estaActivo ? "Activo" : "Inactivo"), "Estado Nuevo Destino", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        ToggleButtonEstado = new javax.swing.JToggleButton();
        txtDescripcion = new javax.swing.JTextField();
        txtNombreDestino = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(0, 46, 121));

        jButton7.setBackground(new java.awt.Color(179, 23, 23));
        jButton7.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Cerrar sesión");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(8, 8, 100));
        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 25)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4);
        jPanel4.setBounds(0, 0, 890, 90);

        btnConfiguracion.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setText("CONFIGURACIÓN");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        jPanel3.add(btnConfiguracion);
        btnConfiguracion.setBounds(0, 340, 200, 50);

        btnHome.setBackground(new java.awt.Color(0, 46, 121));
        btnHome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel3.add(btnHome);
        btnHome.setBounds(0, 90, 200, 50);

        btnClientes.setBackground(new java.awt.Color(0, 46, 121));
        btnClientes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setText("CLIENTES");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel3.add(btnClientes);
        btnClientes.setBounds(0, 140, 200, 50);

        btnReservas.setBackground(new java.awt.Color(0, 46, 121));
        btnReservas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReservas.setForeground(new java.awt.Color(255, 255, 255));
        btnReservas.setText("RESERVAS");
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });
        jPanel3.add(btnReservas);
        btnReservas.setBounds(0, 190, 200, 50);

        btnProveedores.setBackground(new java.awt.Color(0, 46, 121));
        btnProveedores.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedores.setText("PROVEEDORES");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jPanel3.add(btnProveedores);
        btnProveedores.setBounds(0, 240, 200, 50);

        btnReportes.setBackground(new java.awt.Color(0, 46, 121));
        btnReportes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("REPORTES");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jPanel3.add(btnReportes);
        btnReportes.setBounds(0, 290, 200, 50);

        jPanel5.setBackground(new java.awt.Color(0, 46, 121));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5);
        jPanel5.setBounds(0, 380, 200, 170);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORMULARIO DE DESTINOS");
        jLabel1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jLabel1);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(230, 110, 303, 39);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(240, 190, 84, 38);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Descripción:");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(240, 250, 105, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Estado:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(240, 300, 88, 28);

        btnGuardar.setBackground(new java.awt.Color(40, 167, 69));
        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar);
        btnGuardar.setBounds(240, 370, 110, 40);

        btnLimpiar.setBackground(new java.awt.Color(102, 102, 102));
        btnLimpiar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel3.add(btnLimpiar);
        btnLimpiar.setBounds(370, 370, 120, 40);

        ToggleButtonEstado.setText("Activo");
        ToggleButtonEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToggleButtonEstadoActionPerformed(evt);
            }
        });
        jPanel3.add(ToggleButtonEstado);
        ToggleButtonEstado.setBounds(390, 300, 130, 23);
        jPanel3.add(txtDescripcion);
        txtDescripcion.setBounds(390, 250, 222, 22);
        jPanel3.add(txtNombreDestino);
        txtNombreDestino.setBounds(390, 190, 222, 23);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nombre = txtNombreDestino.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        boolean estado = ToggleButtonEstado.isSelected(); // Get state from ToggleButton

        if (nombre.isEmpty() || descripcion.isEmpty()) { // No need to check state for emptiness
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean exito;
        if (esNuevo) {
            Destino nuevoDestino = new Destino(nombre, descripcion, estado); // Pass boolean state
            exito = destinoDAO.agregarDestino(nuevoDestino);
        } else {
            if (destinoEditando != null) {
                destinoEditando.setNombreDestino(nombre);
                destinoEditando.setDescripcion(descripcion);
                destinoEditando.setEstado(estado); // Set boolean state
                exito = destinoDAO.actualizarDestino(destinoEditando);
            } else {
                JOptionPane.showMessageDialog(this, "Error: No se encontró el destino a modificar.", "Error de Edición", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (exito) {
            JOptionPane.showMessageDialog(this, (esNuevo ? "Destino creado" : "Destino actualizado") + " exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al " + (esNuevo ? "crear" : "actualizar") + " el destino. Consulte la consola para más detalles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
         txtNombreDestino.setText("");
        txtDescripcion.setText("");
        ToggleButtonEstado.setSelected(true); // Reset to active when cleaning
        updateToggleButtonText(true);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void ToggleButtonEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToggleButtonEstadoActionPerformed
        if (ToggleButtonEstado != null) {
            ToggleButtonEstado.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    boolean estaActivo = ToggleButtonEstado.isSelected();
                    updateToggleButtonText(estaActivo);
                    // If in edit mode, update the destinoEditando object
                    if (destinoEditando != null) {
                        destinoEditando.setEstado(estaActivo);
                    }
                }
            });
        }
    }//GEN-LAST:event_ToggleButtonEstadoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioDestino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioDestino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioDestino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioDestino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioDestino dialog = new FormularioDestino(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ToggleButtonEstado;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombreDestino;
    // End of variables declaration//GEN-END:variables
}
