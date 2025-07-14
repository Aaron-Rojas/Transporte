
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreDestino = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ToggleButtonEstado = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Formulario de Destinos");
        jScrollPane1.setViewportView(jLabel1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Descripción:");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Estado:");

        ToggleButtonEstado.setText("Activo");
        ToggleButtonEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToggleButtonEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(txtNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ToggleButtonEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ToggleButtonEstado))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(158, Short.MAX_VALUE))
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
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombreDestino;
    // End of variables declaration//GEN-END:variables
}
