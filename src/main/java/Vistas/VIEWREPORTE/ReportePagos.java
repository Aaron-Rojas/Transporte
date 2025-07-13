/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.VIEWREPORTE;

import Controlador.NavegacionController;
import Modelo.Usuario;
import Vistas.VIEWS.Reportes;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB-USR-LCENTRO
 */
public class ReportePagos extends javax.swing.JFrame {
    
    private Usuario usuarioActual;
    
    public ReportePagos(Usuario usuarioLogeado) {
        this.usuarioActual= usuarioActual;
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
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();
            setTitle("Sistema para el usuario " + usuarioActual.getNombreCompleto());
        }else{
            setTitle("Sistema de User");
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setText("Muestra Pagos Realizados");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(220, 130, 470, 40);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cliente ", "Fecha", "Medio de Pago", "Total Reacaudado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(220, 240, 650, 210);

        jLabel5.setText("Cliente:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(230, 180, 70, 16);

        jTextField2.setText("Nombre o telefono");
        jPanel1.add(jTextField2);
        jTextField2.setBounds(230, 200, 470, 22);

        jButton13.setBackground(new java.awt.Color(255, 171, 33));
        jButton13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Generar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13);
        jButton13.setBounds(760, 180, 110, 40);

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

        jButton12.setBackground(new java.awt.Color(255, 171, 33));
        jButton12.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Cerrar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12);
        jButton12.setBounds(80, 420, 110, 40);

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

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
// TODO add your handling code here:
               
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                Reportes GI = new  Reportes (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose(); 
                System.out.println("Admin redirigiendo a Reportes.");
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la gestión de clientes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Reportes por rol: " + nombreRol);
            }
            }else{
             JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
                  
            }
        
         
    }//GEN-LAST:event_jButton12ActionPerformed

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

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
