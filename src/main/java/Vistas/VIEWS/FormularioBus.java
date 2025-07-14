/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.VIEWS;

import Modelo.Bus;
import dao.BusDAO;
import Controlador.NavegacionController;
import Vistas.VIEWS.ListaBuses;
import javax.swing.JOptionPane;

public class FormularioBus extends javax.swing.JFrame {

     private boolean esNuevoBus = true;
     private int originalIdBus; 
     
    public FormularioBus() {
        initComponents();
        NavegacionController.configurarBotones(
            btnHome, 
            btnClientes, 
            btnReservas, 
            btnProveedores, 
            btnReportes, 
            btnConfiguracion, 
            this
        );
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[] { "operativo", "mantenimiento", "inactivo" }));

// Inicializar para nuevo bus
    inicializarParaNuevoBus();
    }
private void inicializarParaNuevoBus() {
        esNuevoBus = true;
        originalIdBus = 0;
        txtIdBus.setText(""); // Indicate auto-generation
        txtIdBus.setEnabled(true); // Disable ID field for new buses
        txtPlaca.setText("");
        txtCapacidad.setText("");
        cbEstado.setSelectedIndex(0);
        btnGuardarBus.setText("Guardar Nuevo Bus"); // Change button text
    }

    // Method to set data when editing an existing bus
   public void setDatosBus(int idBus, String placa, int capacidad, String estado) {
        esNuevoBus = false; 
        originalIdBus = idBus; // Store the original ID
        txtIdBus.setText(String.valueOf(idBus));
        txtIdBus.setEnabled(true); // Keep ID field enabled for modification
        txtPlaca.setText(placa);
        txtCapacidad.setText(String.valueOf(capacidad));
        cbEstado.setSelectedItem(estado);
        btnGuardarBus.setText("Actualizar Bus");
    }

    // Method to get data from the form
    public Bus getDatosBus() throws NumberFormatException {
        Bus bus = new Bus();
        
        // Get ID from the text field for both new and existing buses
        String idText = txtIdBus.getText().trim();
        if (idText.isEmpty()) {
            throw new NumberFormatException("El ID del bus no puede estar vacío.");
        }
        try {
            bus.setIdBus(Integer.parseInt(idText));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El ID debe ser un número entero válido", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
            throw e; 
        }
        
        bus.setPlaca(txtPlaca.getText().trim());
        
        try {
            bus.setCapacidad(Integer.parseInt(txtCapacidad.getText().trim()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "La capacidad debe ser un número entero válido", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
            throw e; 
        }
        
        bus.setEstado(cbEstado.getSelectedItem().toString().toLowerCase());
        
        return bus;
    }
  
    private boolean validarCampos() {
        String idText = txtIdBus.getText().trim();
        if (idText.isEmpty()) {
            mostrarError("El ID del bus es obligatorio");
            txtIdBus.requestFocus();
            return false;
        }
        
        try {
            int id = Integer.parseInt(idText);
            if (id <= 0) {
                mostrarError("El ID debe ser un número positivo");
                txtIdBus.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser un número válido");
            txtIdBus.requestFocus();
            return false;
        }
        
        String placa = txtPlaca.getText().trim();
        if (placa.isEmpty()) {
            mostrarError("La placa es obligatoria");
            txtPlaca.requestFocus();
            return false;
        }
        if (!placa.matches("^[A-Za-z0-9-]{3,10}$")) { 
            mostrarError("Formato de placa inválido. Use letras, números y guiones (ej: ABC-123). Longitud entre 3 y 10 caracteres.");
            txtPlaca.requestFocus();
            return false;
        }

        String capacidadText = txtCapacidad.getText().trim();
        if (capacidadText.isEmpty()) {
            mostrarError("La capacidad es obligatoria");
            txtCapacidad.requestFocus();
            return false;
        }
        try {
            int capacidad = Integer.parseInt(capacidadText);
            if (capacidad <= 0 || capacidad > 100) {
                mostrarError("La capacidad debe estar entre 1 y 100 pasajeros");
                txtCapacidad.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("La capacidad debe ser un número válido");
            txtCapacidad.requestFocus();
            return false;
        }

        String estado = cbEstado.getSelectedItem().toString().toLowerCase();
        if (!estado.matches("operativo|mantenimiento|inactivo")) {
            mostrarError("Estado inválido. Seleccione: Operativo, Mantenimiento o Inactivo");
            cbEstado.requestFocus();
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, 
            mensaje, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }

 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdBus = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        cbEstado = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnGuardarBus = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Formulario de Buses");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(250, 130, 220, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel4.setText("Estado:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(260, 320, 120, 18);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setText("Nro de Bus:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(260, 190, 100, 18);

        txtIdBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBusActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdBus);
        txtIdBus.setBounds(380, 190, 140, 22);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel7.setText("Nro de placa:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(260, 230, 120, 18);
        jPanel1.add(txtCapacidad);
        txtCapacidad.setBounds(380, 280, 140, 22);

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Operativo", "Mantenimiento", "Inactivo", " " }));
        jPanel1.add(cbEstado);
        cbEstado.setBounds(380, 320, 140, 22);

        btnCancelar.setBackground(new java.awt.Color(102, 153, 255));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setText("cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(540, 380, 180, 30);

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

        btnGuardarBus.setBackground(new java.awt.Color(102, 153, 255));
        btnGuardarBus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardarBus.setText("Guardar Bus");
        btnGuardarBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarBusActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarBus);
        btnGuardarBus.setBounds(290, 380, 180, 30);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel6.setText("Capacidad:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(260, 280, 120, 20);
        jPanel1.add(txtPlaca);
        txtPlaca.setBounds(380, 230, 140, 22);

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

        jTextField1.setBackground(new java.awt.Color(0, 46, 121));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(0, 340, 200, 210);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Confirmar si realmente quiere cancelar
    int confirmacion = JOptionPane.showConfirmDialog(
        this, 
        "¿Está seguro que desea cancelar? Los datos no guardados se perderán.", 
        "Confirmar cancelación", 
        JOptionPane.YES_NO_OPTION
    );
    
    if (confirmacion == JOptionPane.YES_OPTION) {
        ListaBuses lista = new ListaBuses();
        lista.setVisible(true);
        this.dispose();
    }
 
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnGuardarBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarBusActionPerformed
      if (!validarCampos()) {
            return;
        }

        try {
            Bus bus = getDatosBus(); // Get bus object with the manually entered ID
            BusDAO busDAO = new BusDAO();
            boolean exito = false;
            String mensaje = "";

            if (esNuevoBus) {
                // For a new bus, check if ID already exists
                if (busDAO.existeIdBus(bus.getIdBus())) {
                    JOptionPane.showMessageDialog(this, 
                        "El ID de bus '" + bus.getIdBus() + "' ya existe. Por favor, ingrese un ID diferente.", 
                        "Error de ID Duplicado", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Also check for duplicate plate for new buses
                if (busDAO.existePlaca(bus.getPlaca(), 0)) { 
                     JOptionPane.showMessageDialog(this, 
                        "La placa '" + bus.getPlaca() + "' ya está registrada en otro bus. No se puede crear un bus con una placa duplicada.", 
                        "Error de Duplicidad", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                exito = busDAO.guardarBus(bus); // Calls the modified guardarBus that takes ID
                mensaje = exito ? "Bus guardado correctamente" : "No se pudo guardar el bus";
            } else {
                // If ID was changed during modification, delete old record and insert new one
                if (bus.getIdBus() != originalIdBus) {
                    // Check if the new ID already exists
                    if (busDAO.existeIdBus(bus.getIdBus())) {
                        JOptionPane.showMessageDialog(this, 
                            "El nuevo ID de bus '" + bus.getIdBus() + "' ya existe. Por favor, ingrese un ID diferente o mantenga el original.", 
                            "Error de ID Duplicado", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // Delete the old record and insert a new one with the updated ID
                    if (busDAO.eliminarBus(originalIdBus)) {
                        exito = busDAO.guardarBus(bus); // Insert as new with the new ID
                        mensaje = exito ? "Bus actualizado correctamente (ID modificado)" : "No se pudo actualizar el bus (ID modificado)";
                    } else {
                        JOptionPane.showMessageDialog(this, 
                            "No se pudo eliminar el bus original para actualizar el ID. Operación cancelada.", 
                            "Error de Actualización", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    // If ID was NOT changed, proceed with normal update
                    // Check for duplicate plate for existing bus (excluding its own original plate)
                    if (busDAO.existePlaca(bus.getPlaca(), bus.getIdBus())) {
                        JOptionPane.showMessageDialog(this, 
                            "La placa '" + bus.getPlaca() + "' ya está registrada en otro bus. Por favor, use una placa diferente.", 
                            "Error de Duplicidad", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    exito = busDAO.actualizarBus(bus); // Calls the regular actualizarBus
                    mensaje = exito ? "Bus actualizado correctamente" : "No se pudo actualizar el bus";
                }
            }
            
            if (exito) {
                JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                ListaBuses lista = new ListaBuses(); 
                lista.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            // Error message already shown in getDatosBus() or validarCampos()
            System.err.println("Error de formato de número: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error inesperado al procesar el bus: " + e.getMessage(), 
                "Error General", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_btnGuardarBusActionPerformed

    private void txtIdBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBusActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioBus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnGuardarBus;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtIdBus;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
