/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.VIEWS;

import Controlador.NavegacionController;
import Modelo.Usuario;
import Vistas.VIEWREPORTE.ReporteActividades;
import Vistas.VIEWREPORTE.ReporteCliente;
import Vistas.VIEWREPORTE.ReporteHoteles;
import Vistas.VIEWREPORTE.ReportePagos;
import Vistas.VIEWREPORTE.ReporteReserva;
import Vistas.VIEWREPORTE.ReporteViajes;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB-USR-LCENTRO
 */
public class Reportes extends javax.swing.JFrame {

    private Usuario usuarioActual;
    
    public Reportes(Usuario usuarioLogeado) {
       this.usuarioActual= usuarioLogeado;
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
            setTitle("Sistema para el Admin " + usuarioActual.getNombreCompleto());
        }else{
            setTitle("Sistema de User");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jButton10.setBackground(new java.awt.Color(255, 171, 33));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Reporte de Hoteles Usados");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10);
        jButton10.setBounds(400, 430, 290, 40);

        jButton11.setBackground(new java.awt.Color(255, 171, 33));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Reporte de Clientes");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11);
        jButton11.setBounds(400, 180, 290, 40);

        jButton12.setBackground(new java.awt.Color(255, 171, 33));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Reporte Reserva/Itinerarios");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12);
        jButton12.setBounds(400, 230, 290, 40);

        jButton13.setBackground(new java.awt.Color(255, 171, 33));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Reporte de Pagos");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13);
        jButton13.setBounds(400, 280, 290, 40);

        jButton14.setBackground(new java.awt.Color(255, 171, 33));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Reporte de Viajes Programados ");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14);
        jButton14.setBounds(400, 330, 290, 40);

        jButton15.setBackground(new java.awt.Color(255, 171, 33));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Reporte de Actividades Turisticas");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton15);
        jButton15.setBounds(400, 380, 290, 40);

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

        jPanel3.setBackground(new java.awt.Color(0, 46, 121));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 380, 200, 160);

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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
                                    
        
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                ReporteHoteles reporteHoteles = new ReporteHoteles(usuarioActual);
                reporteHoteles.setVisible(true); // mostrar frame ReporteCliente
                this.dispose();        
                System.out.println("Admin redirigiendo a Reportes.");
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la Reportes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Reportes por rol: " + nombreRol);
            }
            }else{
             JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
                  
            }
        
         
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
   // TODO add your handling code here:
                  
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                ReporteCliente reporteCliente = new ReporteCliente(usuarioActual);
                reporteCliente.setVisible(true); // mostrar frame ReporteCliente
                this.dispose();

                System.out.println("Admin redirigiendo a Reportes.");
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la para ver Reportes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Reportes por rol: " + nombreRol);
            }
            }else{
             JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
               
            }
        
         
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        
        
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {       
                ReporteReserva reporteReserva = new ReporteReserva();    
                reporteReserva.setVisible(true); // mostrar frame ReporteCliente
                this.dispose();
                System.out.println("Admin redirigiendo a Reportes.");
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la para ver Reportes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Reportes por rol: " + nombreRol);
            }
            }else{
             JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
               
            }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
// TODO add your handling code here:
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
               ReportePagos reportePagos = new ReportePagos(usuarioActual);
               reportePagos.setVisible(true); // mostrar frame ReporteCliente
               this.dispose();
                System.out.println("Admin redirigiendo a Reportes.");
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la para ver Reportes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Reportes por rol: " + nombreRol);
            }
            }else{
             JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
               
            }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:

        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                ReporteActividades reporteActividades = new ReporteActividades(usuarioActual);
                reporteActividades.setVisible(true); // mostrar frame ReporteCliente
                this.dispose();

                System.out.println("Admin redirigiendo a Reportes.");
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la para ver Reportes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Reportes por rol: " + nombreRol);
            }
            }else{
             JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
               
            }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:

        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
             ReporteCliente reporteCliente = new ReporteCliente(usuarioActual);
             reporteCliente.setVisible(true); // mostrar frame ReporteCliente
             this.dispose();
                System.out.println("Admin redirigiendo a Reportes.");
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la para ver Reportes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Reportes por rol: " + nombreRol);
            }
            }else{
             JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
               
            }

    }//GEN-LAST:event_jButton15ActionPerformed

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
