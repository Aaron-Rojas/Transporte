
package Vistas.VIEWS;

import Conexión.Conexión;


import Modelo.Itinerario;
import dao.ItinerarioDAO;
import dao.ClienteDAO; 
import dao.UsuarioDAO;

import Modelo.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


import java.sql.Connection;

public class GestionViajeItinerario extends javax.swing.JFrame {

   
    private Usuario usuarioActual;
    private ItinerarioDAO itinerarioDAO;
    private ClienteDAO clienteDAO; 
    private UsuarioDAO usuarioDAO; 
    private DefaultTableModel modeloTabla;
    
    public GestionViajeItinerario(Usuario usuarioLogeado) {
        this.usuarioActual=usuarioLogeado;
    
        initComponents();        
        System.out.println("DEBUG: initComponents() ejecutado. Componentes inicializados.");
  
        this.setLocationRelativeTo(null);
        
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();
            setTitle("Sistema para el usuario " + usuarioActual.getNombreCompleto());
        }else{
            setTitle("Sistema de User");
        }
                
        this.clienteDAO = new ClienteDAO(); 
        this.usuarioDAO = new UsuarioDAO();        
        this.itinerarioDAO = new ItinerarioDAO(clienteDAO, usuarioDAO); 
        System.out.println("DEBUG: DAOs inicializados.");

        configurarTablaItinerarios(); 
        System.out.println("DEBUG: configurarTablaItinerarios() ejecutado. Modelo de tabla configurado.");

        cargarItinerariosActivos();
        System.out.println("DEBUG: cargarItinerariosActivos() ejecutado. Datos cargados (si hay).");

    }
     
    public void configurarTablaItinerarios() {
        System.out.println("DEBUG: Entrando a configurarTablaItinerarios().");
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas de la tabla no sean editables
            }
        };
        // Define las columnas de tu tabla para ITINERARIOS
        modeloTabla.addColumn("ID"); 
        modeloTabla.addColumn("Cliente"); 
        modeloTabla.addColumn("Usuario Creación"); 
        modeloTabla.addColumn("Fecha Creación"); 
        modeloTabla.addColumn("Fecha Inicio"); 
        modeloTabla.addColumn("Fecha Fin"); 
        modeloTabla.addColumn("Costo"); 
        modeloTabla.addColumn("Estado");
             
        if (tbItinerario != null) {
            tbItinerario.setModel(modeloTabla); // <<-- Esta es la línea crucial
            tbItinerario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo permitir una fila seleccionada
            tbItinerario.setDefaultEditor(Object.class, null); // Ahora sí podemos poner esto aquí
            System.out.println("DEBUG: Modelo asignado a tbItinerario.");
        } else {
            System.out.println("ERROR: tbItinerario es null en configurarTablaItinerarios(). No se pudo asignar el modelo.");
        }
        System.out.println("DEBUG: Saliendo de configurarTablaItinerarios().");
    }

    public void cargarItinerariosActivos() {

        System.out.println("DEBUG: Entrando a cargarItinerariosActivos().");
         
        if (modeloTabla == null) {
            System.out.println("ERROR: modeloTabla es null en cargarItinerariosActivos(). No se pueden añadir filas.");
            return;
        }
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos       
        System.out.println("DEBUG: Filas del modelo limpiadas.");
              
        List<Itinerario> itinerarios = itinerarioDAO.obtenerTodosLosItinerariosActivos();
        System.out.println("DEBUG: Se obtuvieron " + itinerarios.size() + " itinerarios de la BD.");
       
        if (itinerarios.isEmpty()) {
            System.out.println("DEBUG: No hay itinerarios activos para mostrar.");
        }

        for (Itinerario it : itinerarios) {
            // Para mostrar Cliente y Usuario, accedemos a sus nombres.
            String nombreCliente = (it.getCliente() != null) ? it.getCliente().getNombreCompleto() : "N/A";
            String nombreUsuario = (it.getUsuarioCreacion() != null) ? it.getUsuarioCreacion().getNombreCompleto() : "N/A";

            modeloTabla.addRow(new Object[]{
                it.getID_Itinerario(),
                nombreCliente,
                nombreUsuario,
                it.getFechaCreacion(),
                it.getFechaInicioViaje(),
                it.getFechaFinViaje(),
                it.getCosto(),
                it.isEstado() ? "Activo" : "Inactivo" // Muestra "Activo" o "Inactivo"
            });
              
            System.out.println("DEBUG: Fila añadida para itinerario ID: " + it.getID_Itinerario());
        }
        System.out.println("DEBUG: Saliendo de cargarItinerariosActivos().");

    }

   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnConfiguracion = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItinerario = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
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
        btnConfiguracion.setBounds(0, 340, 200, 50);

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
        btnReportes.setBounds(0, 290, 200, 50);

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
        btnProveedores.setBounds(0, 240, 200, 50);

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
        btnReservas.setBounds(0, 190, 200, 50);

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
        btnClientes.setBounds(0, 140, 200, 50);

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
        btnHome.setBounds(0, 90, 200, 50);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ITINERARIO DE VIAJES");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(220, 110, 280, 50);

        tbItinerario.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tbItinerario);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(240, 170, 610, 190);

        btnEliminar.setBackground(new java.awt.Color(179, 23, 23));
        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(640, 400, 120, 40);

        btnAgregar.setBackground(new java.awt.Color(40, 167, 69));
        btnAgregar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar);
        btnAgregar.setBounds(340, 400, 120, 40);

        btnModificar.setBackground(new java.awt.Color(255, 171, 33));
        btnModificar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar);
        btnModificar.setBounds(490, 400, 120, 40);

        jButton8.setBackground(new java.awt.Color(185, 42, 42));
        jButton8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Aceptar");
        jButton8.setBorder(null);
        jPanel1.add(jButton8);
        jButton8.setBounds(760, 500, 100, 30);

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
        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 890, 90);

        jPanel6.setBackground(new java.awt.Color(0, 46, 121));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(0, 380, 200, 170);

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
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                GestionItinerario GI = new  GestionItinerario (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                System.out.println("Admin redirigiendo a Gestión de Itinerario.");
                return;
            }
            if ("usuario".equalsIgnoreCase(nombreRol)) {
                GestionItinerario GI = new  GestionItinerario (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                System.out.println("User redirigiendo a Gestión de Itinerario.");
                return; 
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la gestión de clientes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Gestión de Itinerario por rol: " + nombreRol);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
                
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                FormularioViajeItinerario GI = new  FormularioViajeItinerario (this,this,usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                System.out.println("Admin redirigiendo a Formulario de Itinerario.");
                return;
            }
            if ("usuario".equalsIgnoreCase(nombreRol)) {
                FormularioViajeItinerario GI = new  FormularioViajeItinerario (this,this,usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);  
                System.out.println("User redirigiendo a Formulario de Itinerario.");
                return;
            }else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la gestión de clientes.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Gestión de Itinerario por rol: " + nombreRol);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       
        int filaSeleccionada = tbItinerario.getSelectedRow();
        if (filaSeleccionada >= 0) {
            // CORRECCIÓN 1: El ID de Itinerario está en la columna 0.
            int idItinerario = (int) modeloTabla.getValueAt(filaSeleccionada, 0);            
            Itinerario itinerarioParaEditar = itinerarioDAO.obtenerItinerarioPorId(idItinerario);

            if (itinerarioParaEditar != null) {
                // Pasa 'this' para el padre JFrame, 'this' para la instancia de GestionViajeItinerario (para refrescar),
                // el usuarioLogeado actual, y el itinerario a editar.
                FormularioViajeItinerario edicionItinerarioDialog = new FormularioViajeItinerario(
                    this,                     // Padre JFrame
                    this,
            itinerarioParaEditar,                     // Instancia de GestionViajeItinerario para refrescar
                    usuarioActual           // Usuario actualmente logeado
                         // El objeto Itinerario a editar
                );
                edicionItinerarioDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cargar los datos del itinerario para editar. El itinerario podría no existir o hubo un error de acceso a datos.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un itinerario de la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
       
        cargarItinerariosActivos();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tbItinerario.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idItinerario = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            String nombreClienteAsociado = (String) modeloTabla.getValueAt(filaSeleccionada, 2);

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea ELIMINAR LÓGICAMENTE a " + nombreClienteAsociado + " (ID: " + idItinerario + ")?",
                    "Confirmar Eliminación Lógica", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                // Llama al nuevo método de eliminación lógica del DAO
                boolean exito = itinerarioDAO.actualizarEstadoItinerario(idItinerario,false);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado lógicamente exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                     // Refrescar la tabla para mostrar solo los activo
                     cargarItinerariosActivos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar lógicamente el cliente.", "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }  
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbItinerario;
    // End of variables declaration//GEN-END:variables
}






