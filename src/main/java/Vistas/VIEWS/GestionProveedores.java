 
package Vistas.VIEWS;

import Vistas.VIEWS.FormularioProveedor;
import Controlador.NavegacionController;

import Modelo.Proveedor;
import dao.ProveedorDAO;
import Modelo.Usuario;
import dao.UsuarioDAO;
import Modelo.Rol;
import Modelo.Proveedor;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; 
import java.util.List;


public class GestionProveedores extends javax.swing.JFrame {
   
    private ProveedorDAO proveedorDAO;
    private DefaultTableModel tbMproveedor;
    private Usuario usuarioActual;
    private Proveedor proveedorSeleccionado;
            
    public GestionProveedores(Usuario usuarioLogeado) {
        this.usuarioActual=usuarioLogeado;
        initComponents();
        
//        NavegacionController.configurarBotones(
//            btnHome, 
//            btnClientes, 
//            btnReservas, 
//            btnProveedores, 
//            btnReportes, 
//            btnConfiguracion, 
//            this
//        );
        this.setLocationRelativeTo(null);
        proveedorDAO = new ProveedorDAO();
        tbproveedor.setDefaultEditor(Object.class,null);
        setupTableModel();
        
     
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            setTitle("Gestión de Proveedores- " + usuarioActual.getRol().getNombreRol() + ": " + usuarioActual.getNombreCompleto());
        }else{
            setTitle("Gestión de Proveedores");
        }
        cargarProveedoresActivosEnTabla();
    }
    
       public Usuario getUsuarioActual() {
        return usuarioActual;
    }
       
// Método para configurar el JTABLE
    private void setupTableModel() {
        // Define los nombres de las columnas que se mostrarán en el JTable
        // Incluimos el estado para la eliminación lógica
        String[] columnNames = {"ID", "Nombre del Proveedor", "Contacto", "Estado"};
        tbMproveedor = new DefaultTableModel(columnNames, 0) {
            // Opcional: Hace que las celdas no sean editables directamente en la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbproveedor.setModel(tbMproveedor); 
    }// Asigna el modelo a tu JTable
        
     // Método para cargar los proveedores desde la BD y mostrarlos en el JTable
    public void cargarProveedoresActivosEnTabla() {
     tbMproveedor.setRowCount(0); // Limpia todas las filas existentes en la tabla

        // Obtiene la lista de proveedores activos del DAO
        List<Proveedor> proveedores = proveedorDAO.obtenerProveedoresActivos();

        // Itera sobre la lista de proveedores y añade cada uno como una fila en el tableModel
        for (Proveedor proveedor : proveedores) {
            Object[] rowData = {
                proveedor.getIdProveedor(),
                proveedor.getNombreProveedor(),
                proveedor.getContacto(),
                proveedor.IsActivo() ? "Activo" : "Inactivo" // Mostrar el estado
            };
            tbMproveedor.addRow(rowData); // Añade la fila al modelo de la tabla
        }    
    }
    
        public void cargarTodosLosProveedoresEnTabla() {
        tbMproveedor.setRowCount(0); // Limpia todas las filas existentes en la tabla
        List<Proveedor> proveedores = proveedorDAO.obtenerTodosLosProveedores(); // Obtiene todos los proveedores
        for (Proveedor proveedor : proveedores) {
            Object[] rowData = {
                proveedor.getIdProveedor(),
                proveedor.getNombreProveedor(),
                proveedor.getContacto(),
                proveedor.IsActivo()
            };
            tbMproveedor.addRow(rowData);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbproveedor = new javax.swing.JTable();
        btnEliminarProveedor = new javax.swing.JButton();
        btnModificarProveedor = new javax.swing.JButton();
        btnAgregarProveedor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(8, 8, 100));

        jLabel1.setBackground(new java.awt.Color(8, 8, 100));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Expreso");

        jLabel2.setBackground(new java.awt.Color(8, 8, 100));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Los Chankas");

        jButton8.setBackground(new java.awt.Color(179, 23, 23));
        jButton8.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Cerrar sesión");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(519, 519, 519))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(717, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(48, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)))
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

        tbproveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre ", "Contacto"
            }
        ));
        jScrollPane1.setViewportView(tbproveedor);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(240, 170, 610, 200);

        btnEliminarProveedor.setBackground(new java.awt.Color(179, 23, 23));
        btnEliminarProveedor.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnEliminarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarProveedor.setText("Eliminar Proveedor");
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarProveedor);
        btnEliminarProveedor.setBounds(650, 390, 210, 42);

        btnModificarProveedor.setBackground(new java.awt.Color(179, 23, 23));
        btnModificarProveedor.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnModificarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarProveedor.setText("Modificar");
        btnModificarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificarProveedor);
        btnModificarProveedor.setBounds(230, 390, 140, 42);

        btnAgregarProveedor.setBackground(new java.awt.Color(179, 23, 23));
        btnAgregarProveedor.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        btnAgregarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProveedor.setText("Agregar Proveedor");
        btnAgregarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarProveedor);
        btnAgregarProveedor.setBounds(410, 390, 210, 42);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Gestión Proveedores");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(240, 130, 200, 30);

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

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
    // 1. Obtener la fila seleccionada
    int selectedRow = tbproveedor.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proveedor de la tabla para desactivar.", "Ningún Proveedor Seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idProveedor = (int) tbproveedor.getValueAt(selectedRow, 0);
        String nombreProveedor = (String) tbproveedor.getValueAt(selectedRow, 2);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea DESACTIVAR al proveedor: " + nombreProveedor + " (ID: " + idProveedor + ")?\n" +
                "Esto cambiará su estado a 'desactivo' y ya no aparecerá en la lista principal.",
                "Confirmar Desactivación Lógica",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = proveedorDAO.eliminarLogicamenteProveedor(idProveedor); // Asumimos que este método existe en ProveedorDAO
            if (exito) {
                JOptionPane.showMessageDialog(this, "Proveedor desactivado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarProveedoresActivosEnTabla(); // Recargar la tabla para reflejar el cambio (solo activos)
            } else {
                JOptionPane.showMessageDialog(this, "Error al desactivar el proveedor. Verifique la consola para más detalles.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnModificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProveedorActionPerformed
        int selectedRow = tbproveedor.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proveedor de la tabla para modificar.", "Ningún Proveedor Seleccionado", JOptionPane.WARNING_MESSAGE);
            return;  
        }
        int idProveedor = (int) tbproveedor.getValueAt(selectedRow, 0);
        Proveedor proveedorAEditar = proveedorDAO.obtenerProveedorPorId(idProveedor); // Obtener el objeto Proveedor completo

        if (proveedorAEditar != null) {
            // Se pasa el usuarioActual al constructor de FormularioProveedor
            FormularioProveedor formulario = new FormularioProveedor(this, this,proveedorAEditar); 
            formulario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la información del proveedor seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarProveedorActionPerformed

    private void btnAgregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProveedorActionPerformed
    // Se pasa el usuarioActual al constructor de FormularioProveedor
        FormularioProveedor formulario = new FormularioProveedor(this, this);
        formulario.setVisible(true);
        cargarProveedoresActivosEnTabla(); 
        
        
        
    }//GEN-LAST:event_btnAgregarProveedorActionPerformed

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

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProveedor;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnModificarProveedor;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbproveedor;
    // End of variables declaration//GEN-END:variables
}
