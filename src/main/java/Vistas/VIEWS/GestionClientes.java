
package Vistas.VIEWS;

import Controlador.NavegacionController;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import Vistas.VIEWS.CreacionCliente;
import java.util.List;
import java.util.ArrayList;
//importaciones
import dao.ClienteDAO;
import Modelo.Cliente;
import Modelo.Usuario;
import Vistas.VIEWS.ResultadoBusqueda;

public class GestionClientes extends javax.swing.JFrame {
    private DefaultTableModel modeloTabla;
    private ClienteDAO clienteDAO;
    private Usuario usuarioActual;
    private Cliente clienteSeleccionado;
    
    public GestionClientes(Usuario usuarioLogeado) {
        this.usuarioActual=usuarioLogeado;
        initComponents();
 
        
    this.setLocationRelativeTo(null);
    tbClientes.setDefaultEditor(Object.class, null); //Quitar que la tabla sea editable
    clienteDAO = new ClienteDAO();
    
    
     if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();
            setTitle("Sistema para el usuario " + usuarioActual.getNombreCompleto());
        }else{
            setTitle("Sistema de User");
        }
    configurarTablaClientes();
    cargarClientesEnTabla();
    }

 private void configurarTablaClientes() {
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas de la tabla no sean editables
            }
        };
        // Define las columnas de tu tabla (deben coincidir con tu BD y la clase Cliente)
        modeloTabla.addColumn("ID"); // ID_Cliente
        modeloTabla.addColumn("DNI/Pasaporte"); // DNI_Documento
        modeloTabla.addColumn("Nombres"); // NombreCompleto
        modeloTabla.addColumn("Teléfono"); // Telefono
        modeloTabla.addColumn("E-mail"); // Email
        modeloTabla.addColumn("Preferencias"); // Preferencias
        modeloTabla.addColumn("Estado");
        tbClientes.setModel(modeloTabla); // Asigna el modelo a tu JTable
    }    
    
    
      // Asegúrate de que este método sea PUBLIC para que CreacionCliente pueda llamarlo
    public void cargarClientesEnTabla() {
        modeloTabla.setRowCount(0); // Limpiar filas existentes
        List<Cliente> clientes = clienteDAO.obtenerClientesActivos();
        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getIdCliente(),      // Columna 0: ID del cliente
                cliente.getDni(),
                cliente.getNombreCompleto(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getPreferencias(),
                cliente.isActivo() ? "Activo": "Inactivo"
            };
            modeloTabla.addRow(fila);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("GESTIONAR CLIENTES");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(240, 120, 200, 20);

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
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(640, 480, 130, 30);

        tbClientes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "NOMBRE", "DNI/PASAPORTE", "E-MAIL", "TELEFONO"
            }
        ));
        tbClientes.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tbClientes);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(240, 230, 610, 180);

        btnAgregar.setBackground(new java.awt.Color(40, 167, 69));
        btnAgregar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-plus-sign-21.png"))); // NOI18N
        btnAgregar.setText(" Agregar cliente");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar);
        btnAgregar.setBounds(260, 480, 180, 30);

        btnEditar.setBackground(new java.awt.Color(255, 171, 33));
        btnEditar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-edit-21.png"))); // NOI18N
        btnEditar.setText(" Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar);
        btnEditar.setBounds(470, 480, 130, 30);
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(240, 170, 240, 30);

        btnBuscar.setBackground(new java.awt.Color(0, 123, 255));
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-search-more-23.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar);
        btnBuscar.setBounds(490, 170, 120, 30);

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
        jPanel1.add(btnConfiguracion);
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
        jPanel1.add(btnHome);
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
        jPanel1.add(btnClientes);
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
        jPanel1.add(btnReservas);
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
        jPanel1.add(btnProveedores);
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
        jPanel1.add(btnReportes);
        btnReportes.setBounds(-20, 290, 210, 50);

        jTextField1.setBackground(new java.awt.Color(0, 46, 121));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(-20, 90, 210, 460);

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
     
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tbClientes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idCliente = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            String nombreCliente = (String) modeloTabla.getValueAt(filaSeleccionada, 2);

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea ELIMINAR LÓGICAMENTE a " + nombreCliente + " (ID: " + idCliente + ")?",
                    "Confirmar Eliminación Lógica", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                // Llama al nuevo método de eliminación lógica del DAO
                boolean exito = clienteDAO.eliminarClienteLogico(idCliente); 
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado lógicamente exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarClientesEnTabla(); // Refrescar la tabla para mostrar solo los activos
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar lógicamente el cliente.", "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }  
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //Abrir el formulario de CrearCliente

        CreacionCliente cl = new CreacionCliente(this,this);

        cl.setVisible(true);
        // Despues de que CreacionCliente se cierre, la tabla se refresca
        // El refrescado debe hacerse dentro del CreacionCliente o en un WindowListener si es modal
        // Por ahora, lo mantenemos aquí si el diálogo no es modal
        cargarClientesEnTabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = tbClientes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idCliente = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            Cliente clienteParaEditar = clienteDAO.obtenerClientePorId(idCliente);
            if (clienteParaEditar != null) {
                CreacionCliente edicionClienteFrame = new CreacionCliente(this, this,clienteParaEditar);
                edicionClienteFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cargar los datos del cliente para editar. El cliente podría no existir o estar inactivo.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente de la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        cargarClientesEnTabla();
    }//GEN-LAST:event_btnEditarActionPerformed


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
                this.dispose(); 
                System.out.println("Admin redirigiendo a Gestión de Itinerario.");
                return;
            }
            if ("usuario".equalsIgnoreCase(nombreRol)) {
                GestionItinerario GI = new  GestionItinerario (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose();                 
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



        
    }//GEN-LAST:event_btnHomeActionPerformed


    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String textoBusqueda = txtBuscar.getText().trim();
        if (textoBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un texto para buscar (DNI, Nombre, Teléfono o Email).", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
            
        List<Cliente> clientesEncontrados = clienteDAO.buscarClientes(textoBusqueda);
        
        if (clientesEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron clientes que coincidan con la búsqueda de clientes activos.", "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Asumiendo que ResultadoBusqueda es un JDialog y es modal
            ResultadoBusqueda resultadosFrame = new ResultadoBusqueda(this, true, clientesEncontrados);
            resultadosFrame.setVisible(true);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    private void habilitarEdicionTabla(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean eliminarClienteDeBD(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

