 
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
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLugar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnActividad = new javax.swing.JButton();
        btnActividad1 = new javax.swing.JButton();
        btnHotel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

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
        jScrollPane1.setBounds(320, 190, 480, 120);

        btnEliminarProveedor.setBackground(new java.awt.Color(179, 23, 23));
        btnEliminarProveedor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarProveedor.setText("Eliminar Proveedor");
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarProveedor);
        btnEliminarProveedor.setBounds(280, 350, 190, 40);

        btnModificarProveedor.setBackground(new java.awt.Color(255, 171, 33));
        btnModificarProveedor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnModificarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarProveedor.setText("Modificar");
        btnModificarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificarProveedor);
        btnModificarProveedor.setBounds(480, 350, 120, 40);

        btnAgregarProveedor.setBackground(new java.awt.Color(40, 167, 69));
        btnAgregarProveedor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAgregarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProveedor.setText("Agregar Proveedor");
        btnAgregarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarProveedor);
        btnAgregarProveedor.setBounds(620, 350, 190, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("GESTIÓN DE PROVEEDORES");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(230, 120, 310, 30);

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

        btnLugar.setBackground(new java.awt.Color(102, 153, 255));
        btnLugar.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        btnLugar.setForeground(new java.awt.Color(255, 255, 255));
        btnLugar.setText("Lugar");
        btnLugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLugarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLugar);
        btnLugar.setBounds(220, 450, 180, 30);

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

        btnActividad.setText("Actividad");
        btnActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActividadActionPerformed(evt);
            }
        });
        jPanel1.add(btnActividad);
        btnActividad.setBounds(440, 440, 200, 50);

        btnActividad1.setText("Actividad");
        btnActividad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActividadActionPerformed(evt);
            }
        });
        jPanel1.add(btnActividad1);
        btnActividad1.setBounds(440, 440, 200, 50);

        btnHotel.setText("Hotel");
        btnHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHotelActionPerformed(evt);
            }
        });
        jPanel1.add(btnHotel);
        btnHotel.setBounds(700, 450, 100, 40);

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

        if (selectedRow != -1) {
        int idProveedor = (int) tbproveedor.getValueAt(selectedRow, 0);
        Proveedor proveedorAEditar = proveedorDAO.obtenerProveedorPorId(idProveedor); // Obtener el objeto Proveedor completo

        if (proveedorAEditar != null) {
            // Se pasa el usuarioActual al constructor de FormularioProveedor
            FormularioProveedor formulario = new FormularioProveedor(this, this,proveedorAEditar); 
            formulario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la información del proveedor seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnLugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLugarActionPerformed
        // TODO add your handling code here:
             
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                GestionLugarTuristico GI = new  GestionLugarTuristico (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose(); 
                System.out.println("Admin redirigiendo a Gestion Lugar Turistico.");

            }
            if ("proveedor".equalsIgnoreCase(nombreRol)) {
                GestionLugarTuristico GI = new  GestionLugarTuristico (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose();                 
                System.out.println("User redirigiendo a Gestion Lugar Turistico .");
            }
            else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la seccion de Proveedores.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Proveedores por rol: " + nombreRol);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnLugarActionPerformed

    private void btnActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActividadActionPerformed
        // TODO add your handling code here:
               
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                GestionActividad GI = new  GestionActividad (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose(); 
                System.out.println("Admin redirigiendo a Gestion Lugar Turistico.");

            }
            if ("proveedor".equalsIgnoreCase(nombreRol)) {
                GestionActividad GI = new  GestionActividad (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose();                 
                System.out.println("User redirigiendo a Gestion Lugar Turistico .");
            }
            else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la seccion de Proveedores.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Proveedores por rol: " + nombreRol);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_btnActividadActionPerformed

    private void btnHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHotelActionPerformed
        // TODO add your handling code here:
                
    if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();

            if ("admin".equalsIgnoreCase(nombreRol)) {
                GestionHotel GI = new  GestionHotel (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose(); 
                System.out.println("Admin redirigiendo a Gestion Hotel.");

            }
            if ("proveedor".equalsIgnoreCase(nombreRol)) {
                GestionHotel GI = new  GestionHotel (usuarioActual);
                GI.setVisible(true);
                GI.setLocationRelativeTo(null);
                this.dispose(); 
                System.out.println("Admin redirigiendo a Gestion Hotel.");
            }
            else{
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para ver la seccion de Proveedores.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                System.out.println("Intento de acceso no autorizado a Proveedores por rol: " + nombreRol);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        
    }//GEN-LAST:event_btnHotelActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActividad;
    private javax.swing.JButton btnActividad1;
    private javax.swing.JButton btnAgregarProveedor;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnHotel;
    private javax.swing.JButton btnLugar;
    private javax.swing.JButton btnModificarProveedor;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbproveedor;
    // End of variables declaration//GEN-END:variables
}
