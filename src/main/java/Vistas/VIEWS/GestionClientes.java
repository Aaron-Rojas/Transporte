
package Vistas.VIEWS;

import Controlador.NavegacionController;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Vistas.VIEWS.CreacionCliente;
import java.util.List;
import java.util.ArrayList;
//importaciones
import dao.ClienteDAO;
import Modelo.Cliente;
import Vistas.VIEWS.ResultadoBusqueda;

public class GestionClientes extends javax.swing.JFrame {
    private DefaultTableModel modeloTabla;
    private ClienteDAO clienteDAO;
    public GestionClientes() {
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
    this.setLocationRelativeTo(null);
    tbClientes.setDefaultEditor(Object.class, null); //Quitar que la tabla sea editable
    clienteDAO = new ClienteDAO();
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
        tbClientes.setModel(modeloTabla); // Asigna el modelo a tu JTable
    }    
    
    
      // Asegúrate de que este método sea PUBLIC para que CreacionCliente pueda llamarlo
    public void cargarClientesEnTabla() {
        modeloTabla.setRowCount(0); // Limpiar filas existentes
        List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();
        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getIdCliente(),      // Columna 0: ID del cliente
                cliente.getDni(),
                cliente.getNombreCompleto(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getPreferencias()
            };
            modeloTabla.addRow(fila);
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
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel3.setText("Gestionar clientes");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(220, 140, 310, 40);

        btnEliminar.setBackground(new java.awt.Color(179, 23, 23));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(680, 410, 110, 40);

        tbClientes.setBackground(new java.awt.Color(204, 204, 204));
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
        jScrollPane1.setViewportView(tbClientes);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(240, 210, 610, 180);

        btnAgregar.setBackground(new java.awt.Color(8, 153, 192));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("+Agregar cliente");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar);
        btnAgregar.setBounds(300, 410, 190, 40);

        btnEditar.setBackground(new java.awt.Color(255, 171, 33));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar);
        btnEditar.setBounds(530, 410, 110, 40);

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
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(680, 150, 190, 30);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar);
        btnBuscar.setBounds(580, 160, 75, 23);

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
/*
    //Perpara la estructura del JTable
    public void configurarTablaClientes() {
    modeloTabla = new DefaultTableModel();
    modeloTabla.addColumn("ID");
    modeloTabla.addColumn("DNI");
    modeloTabla.addColumn("Nombre Completo");
    modeloTabla.addColumn("Teléfono");
    modeloTabla.addColumn("Email");
    modeloTabla.addColumn("Preferencias");
    tbClientes.setModel(modeloTabla);
}
*/    
 

   
 
 
 /*
    //metodo para obtener los datos de tbClientes a JTable
    public void cargarClientesEnTabla() {
    // Limpiar la tabla antes de cargar nuevos 
    modeloTabla.setRowCount(0);
    
    // Obtener todos los clientes desde la base de datos desde tu clase DTO
    List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();

    // Añadir cada cliente como una fila a la tabla
    for (Cliente cliente : clientes) {
        Object[] fila = new Object[6]; // 6 columnas definidas
        fila[0] = cliente.getIdCliente();
        fila[1] = cliente.getDni();
        fila[2] = cliente.getNombreCompleto();
        fila[3] = cliente.getTelefono();
        fila[4] = cliente.getEmail();
        fila[5] = cliente.getPreferencias();
        modeloTabla.addRow(fila);
    }
}
    
    //metodo para refrescar la tabla
    public void refrescarTabla() {
    cargarClientesEnTabla();
    }
  */
    
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int filaSeleccionada = tbClientes.getSelectedRow(); // Obtener la fila seleccionada
        if (filaSeleccionada >= 0) { // Si hay una fila seleccionada
            // Obtener el ID del cliente de la primera columna (columna 0) de la fila seleccionada
            int idCliente = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            String nombreCliente = (String) modeloTabla.getValueAt(filaSeleccionada, 2); // Obtener el nombre para el mensaje de confirmación (columna 2)

            // Confirmación al usuario antes de eliminar
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea eliminar a " + nombreCliente + " (ID: " + idCliente + ")?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = clienteDAO.eliminarCliente(idCliente); // Llama al método de eliminación del DAO
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarClientesEnTabla(); // Refrescar la tabla después de la eliminación
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el cliente. Podría tener dependencias o la base de datos no está accesible.", "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
            
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //Abrir el formulario de CrearCliente
        CreacionCliente cl = new CreacionCliente(this);
        cl.setVisible(true);
        cargarClientesEnTabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
        int filaSeleccionada = tbClientes.getSelectedRow(); // Obtener la fila seleccionada
        if (filaSeleccionada >= 0) { // Si hay una fila seleccionada (índice 0 o mayor)
            // Obtener el ID del cliente de la primera columna (columna 0) de la fila seleccionada
            // ¡CRÍTICO!: Asegúrate de que la columna 0 de tu tabla SIEMPRE sea el ID_Cliente
            int idCliente = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

            // Obtener el cliente completo de la base de datos usando el DAO
            Cliente clienteParaEditar = clienteDAO.obtenerClientePorId(idCliente);

            if (clienteParaEditar != null) {
                // Abrir el formulario CreacionCliente, pasándole el cliente para editar
                CreacionCliente edicionClienteFrame = new CreacionCliente(this, clienteParaEditar);
                edicionClienteFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cargar los datos del cliente para editar. El cliente podría no existir.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente de la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
       
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
    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

      String textoBusqueda = txtBuscar.getText().trim();
        if (textoBusqueda.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un texto para buscar (DNI, Nombre, Teléfono o Email).", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return; // Sale del método si el campo de búsqueda está vacío
    }
        
    List<Cliente> clientesEncontrados = clienteDAO.buscarClientes(textoBusqueda);
    
    if (clientesEncontrados.isEmpty()) {
    JOptionPane.showMessageDialog(this, "No se encontraron clientes que coincidan con la búsqueda.", "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
    } else {
    // Asumiendo que 'this' es el JFrame de GestionClientes
    // Y que quieres que el diálogo sea modal (true)
    ResultadoBusqueda resultadosFrame = new ResultadoBusqueda(this, true, clientesEncontrados);
    resultadosFrame.setVisible(true);
}
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(GestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionClientes().setVisible(true);
            }
        });
    }

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
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

