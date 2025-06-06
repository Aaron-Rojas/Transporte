
package Vistas.VIEWS;

import Controlador.NavegacionController;
import javax.swing.JOptionPane;
import Modelo.Cliente;
import dao.ClienteDAO;


public class CreacionCliente extends javax.swing.JFrame {

    private ClienteDAO clienteDAO;
    private GestionClientes padreGestionClientes;
            
    public CreacionCliente(GestionClientes padre) {
    initComponents();
    
    this.padreGestionClientes = padre;
    this.clienteDAO = new ClienteDAO();
    this.setLocationRelativeTo(null);
    /*
    NavegacionController.configurarBotones(
    btnHome,       // Botón Home
    btnClientes,   // Botón Clientes
    btnReservas,   // Botón Reservas
    btnProveedores,// Botón Proveedores
    btnReportes,   // Botón Reportes
    btnConfiguracion, // Botón Configuración
    this          // Referencia al frame actual (this)
    );
*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_preferencias = new javax.swing.JTextField();
        txt_dni = new javax.swing.JTextField();
        btn_crearCliente = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(8, 8, 100));

        jButton7.setBackground(new java.awt.Color(179, 23, 23));
        jButton7.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Cerrar sesión");

        jLabel1.setBackground(new java.awt.Color(8, 8, 100));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Expreso");

        jLabel2.setBackground(new java.awt.Color(8, 8, 100));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Los Chankas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(318, 318, 318)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 890, 120);

        jPanel3.setBackground(new java.awt.Color(8, 8, 100));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 460, 890, 90);

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel3.setText("Crear clientes");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(220, 130, 260, 40);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Nombres:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(250, 190, 120, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("DNI/Pasaporte");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(570, 180, 140, 40);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel6.setText("Preferencia:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(250, 400, 120, 27);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel7.setText("Telefono:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(570, 280, 80, 27);

        txt_telefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_telefono);
        txt_telefono.setBounds(570, 330, 240, 30);

        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_nombre);
        txt_nombre.setBounds(250, 230, 240, 30);

        txt_preferencias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_preferencias);
        txt_preferencias.setBounds(370, 400, 280, 30);

        txt_dni.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_dni);
        txt_dni.setBounds(570, 230, 240, 30);

        btn_crearCliente.setBackground(new java.awt.Color(255, 171, 33));
        btn_crearCliente.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btn_crearCliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_crearCliente.setText("Crear cliente");
        btn_crearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_crearCliente);
        btn_crearCliente.setBounds(710, 420, 150, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel8.setText("E-mail:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(250, 280, 59, 27);

        txt_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_email);
        txt_email.setBounds(250, 330, 240, 30);

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

    private void btn_crearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearClienteActionPerformed
    // 1. Obtener datos del formulario
    String dni = txt_dni.getText();
    String nombreCompleto = txt_nombre.getText();
    String telefono = txt_telefono.getText();
    String email = txt_email.getText();
    String preferencias = txt_preferencias.getText();
    
    //Validaciones básicas
    if (dni.isEmpty() || nombreCompleto.isEmpty() || telefono.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete los campos obligatorios (DNI, Nombre, Teléfono).", "Error de validación", JOptionPane.WARNING_MESSAGE);
        return; // Detiene la ejecución si faltan datos
    }
    //Inicializamos el objeto clientes, con los datos del formulario    
    Cliente nuevoCliente = new Cliente (0, dni, nombreCompleto, telefono, email, preferencias);
    //llamamos al DAO para guardar el cliente
    boolean exito = clienteDAO.guardarCliente(nuevoCliente);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); //Cierra el formulario
            //refrescar la tabla 
             if (padreGestionClientes != null) {
                padreGestionClientes.cargarClientesEnTabla();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el cliente. Verifique la base de datos.", "Error de Guardado", JOptionPane.ERROR_MESSAGE);
        }
           
    }//GEN-LAST:event_btn_crearClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btn_crearCliente;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txt_dni;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_preferencias;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
