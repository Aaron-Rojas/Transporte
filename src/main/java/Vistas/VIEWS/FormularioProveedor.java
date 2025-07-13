package Vistas.VIEWS;

import Modelo.Proveedor;
import dao.ProveedorDAO;

import javax.swing.JOptionPane;
import java.awt.Frame;

import Controlador.NavegacionController;

public class FormularioProveedor extends javax.swing.JFrame {
    
    private ProveedorDAO proveedorDAO;
    private Proveedor proveedorEditando;
    private boolean esNuevo;
    
    public FormularioProveedor(java.awt.Frame parent, boolean modal) {
        initComponents();
/*
        NavegacionController.configurarBotones(
            btnHome, 
            btnClientes, 
            btnReservas, 
            btnProveedores, 
            btnReportes, 
            btnConfiguracion, 
            this
        );
        */

       this.setLocationRelativeTo(parent); 
        proveedorDAO = new ProveedorDAO();
    
    }

        //metodo para habilitar el formulario
        public void setModoEdicion(boolean esEdicion, Proveedor proveedor) {
        this.esNuevo = !esEdicion;
        this.proveedorEditando = proveedor;

        if (esNuevo) {
            this.setTitle("Añadir Nuevo Proveedor");
            limpiarCampos();
        } else {
            this.setTitle("Modificar Proveedor");
            // Cargar los datos del proveedor en los campos de texto
            // Asumiendo que tienes txtNombreProveedor y txtContacto
            txtNombreProveedor.setText(proveedor.getNombreProveedor());
            txtContacto.setText(proveedor.getContacto());
        }
    }
        
    
    private void limpiarCampos() {
        txtNombreProveedor.setText("");
        txtContacto.setText("");
    }    
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnGuardarProveedor = new javax.swing.JButton();
        txtContacto = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Formulario de Reserva");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(250, 130, 220, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Ubicación:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(560, 220, 90, 20);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(380, 173, 190, 30);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Tipo Proveedor: ");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(250, 180, 130, 20);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Servicio:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(560, 270, 130, 20);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("ID:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(610, 170, 40, 20);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Contacto:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(250, 270, 130, 20);

        btnGuardarProveedor.setBackground(new java.awt.Color(102, 153, 255));
        btnGuardarProveedor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardarProveedor.setText("Guardar Proveedor");
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarProveedor);
        btnGuardarProveedor.setBounds(630, 370, 180, 30);
        jPanel1.add(txtContacto);
        txtContacto.setBounds(330, 270, 170, 20);
        jPanel1.add(jTextField2);
        jTextField2.setBounds(660, 270, 170, 20);
        jPanel1.add(txtNombreProveedor);
        txtNombreProveedor.setBounds(330, 220, 170, 20);
        jPanel1.add(jTextField4);
        jTextField4.setBounds(660, 170, 170, 20);

        btnCancelar.setBackground(new java.awt.Color(102, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(280, 380, 130, 23);

        btnConfiguracion.setBackground(new java.awt.Color(8, 8, 100));
        btnConfiguracion.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setText("CONFIGURACIÓN");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        jPanel1.add(btnConfiguracion);
        btnConfiguracion.setBounds(0, 370, 200, 50);

        btnReportes.setBackground(new java.awt.Color(8, 8, 100));
        btnReportes.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("REPORTES");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jPanel1.add(btnReportes);
        btnReportes.setBounds(0, 320, 200, 50);

        btnProveedores.setBackground(new java.awt.Color(8, 8, 100));
        btnProveedores.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedores.setText("PROVEEDORES");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jPanel1.add(btnProveedores);
        btnProveedores.setBounds(0, 270, 200, 50);

        btnReservas.setBackground(new java.awt.Color(8, 8, 100));
        btnReservas.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnReservas.setForeground(new java.awt.Color(255, 255, 255));
        btnReservas.setText("RESERVAS");
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });
        jPanel1.add(btnReservas);
        btnReservas.setBounds(0, 220, 200, 50);

        btnClientes.setBackground(new java.awt.Color(8, 8, 100));
        btnClientes.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setText("CLIENTES");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btnClientes);
        btnClientes.setBounds(0, 170, 200, 50);

        btnHome.setBackground(new java.awt.Color(8, 8, 100));
        btnHome.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome);
        btnHome.setBounds(0, 120, 200, 50);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Nombre:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(250, 220, 70, 20);
        jPanel1.add(jTextField5);
        jTextField5.setBounds(660, 220, 170, 20);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar);
        btnLimpiar.setBounds(460, 410, 90, 30);

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

        jLabel1.setBackground(new java.awt.Color(8, 8, 100));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-bus-58.png"))); // NOI18N
        jLabel1.setText("  Expreso los Chankas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 890, 90);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed

    String nombre = txtNombreProveedor.getText().trim();
    String contacto = txtContacto.getText().trim();
    
    if (nombre.isEmpty() || contacto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    boolean exito = false;
    
   if (esNuevo) {
        // Lógica para añadir nuevo proveedor (debería funcionar si el botón añadir funciona)
        Proveedor nuevoProveedor = new Proveedor(nombre, contacto);
        exito = proveedorDAO.guardarProveedor(nuevoProveedor);
        } else {
        // Lógica para actualizar proveedor existente
        proveedorEditando.setNombreProveedor(nombre);
        proveedorEditando.setContacto(contacto);
        exito = proveedorDAO.actualizarProveedor(proveedorEditando);
    }
   if (exito) {
        JOptionPane.showMessageDialog(this, "Operación exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE); // Mensaje más genérico
        this.dispose(); // Cierra el formulario
    } else {
        JOptionPane.showMessageDialog(this, "Error al realizar la operación con el proveedor.", "Error", JOptionPane.ERROR_MESSAGE); // Mensaje más genérico
    }
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

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

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNombreProveedor.setText("");
        txtContacto.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
            this.dispose(); 
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtNombreProveedor;
    // End of variables declaration//GEN-END:variables
}
