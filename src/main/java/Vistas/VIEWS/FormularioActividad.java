package Vistas.VIEWS;

import Modelo.Actividad;
import Conexión.Conexión;
import Modelo.LugarTuristico;
import Modelo.Proveedor;
import dao.ActividadDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
/**
 *
 * @author pambi
 */
public class FormularioActividad extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioActividad.class.getName());

    private Actividad actividadEnEdicion;
    private final ActividadDAO actividadDAO = new ActividadDAO();
    private final GestionActividad padre;
    /**
     * Creates new form FormularioActividad
     */
     public FormularioActividad(GestionActividad padre, Actividad actividadEnEdicion) {
        super(padre, true); // modal
        this.padre = padre;
        this.actividadEnEdicion = actividadEnEdicion;
        initComponents();
        setLocationRelativeTo(null); // Centrar el formulario
        cargarCombos(); // Cargar combos Lugar Turístico y Proveedor
        if (actividadEnEdicion != null) {
            cargarDatosEnFormulario();
        }
    }
    
    private void cargarCombos() {
        cargarComboLugaresTuristicos();
        cargarComboProveedores();

        cbEstado.removeAllItems();
        cbEstado.addItem("activo");
        cbEstado.addItem("desactivo");
    }

       
    private void cargarDatosEnFormulario() {
        txtNombre.setText(actividadEnEdicion.getNombre());
        txtDescripcion.setText(actividadEnEdicion.getDescripcion());
        txtDuracion.setText(actividadEnEdicion.getDuracion()); // ¡AHORA ES STRING!

        // Seleccionar Lugar Turístico en el ComboBox
        for (int i = 0; i < cbIDLugarT.getItemCount(); i++) {
            LugarTuristico lt = cbIDLugarT.getItemAt(i);
            if (lt.getIdLugarTuristico() == actividadEnEdicion.getIdLugarTuristico()) {
                cbIDLugarT.setSelectedItem(lt);
                break;
            }
        }
        // Seleccionar Proveedor en el ComboBox
        for (int i = 0; i < cbIDProveedor.getItemCount(); i++) {
            Proveedor prov = cbIDProveedor.getItemAt(i);
            if (prov.getIdProveedor() == actividadEnEdicion.getIdProveedor()) {
                cbIDProveedor.setSelectedItem(prov);
                break;
            }
        }
        
        cbEstado.setSelectedItem(actividadEnEdicion.getEstado());
    }

    private void cargarComboLugaresTuristicos() {
        cbIDLugarT.removeAllItems();
        try (Connection conn = Conexión.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT ID_LugarTuristico, NombreLugar FROM lugarturistico WHERE Estado = 'Activo'");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_LugarTuristico");
                String nombre = rs.getString("NombreLugar");
               //  cbIDLugarT.addItem(new LugarTuristico(id, nombre)); // Esto ya debe funcionar
            }

        } catch (SQLException ex) {
            logger.severe("Error al cargar lugares turísticos: " + ex.getMessage());
        }
    }


   private void cargarComboProveedores() {
        cbIDProveedor.removeAllItems();
        try (Connection conn = Conexión.conectar();
             // La consulta para Proveedor usa 'Estado = 1' (asumiendo BOOLEAN/TINYINT(1) en DB)
             PreparedStatement stmt = conn.prepareStatement("SELECT ID_Proveedor, NombreProveedor, Contacto, Estado FROM proveedor WHERE Estado = 1"); // Filtrado por 1 (activo)
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_Proveedor");
                String nombre = rs.getString("NombreProveedor");
                String contacto = rs.getString("Contacto");
                // LEER EL ESTADO DIRECTAMENTE COMO BOOLEAN
                boolean estadoBoolean = rs.getBoolean("Estado"); 

                // Crear el objeto Proveedor con el estado boolean
                Proveedor proveedor = new Proveedor(id, nombre, contacto, estadoBoolean); 
                cbIDProveedor.addItem(proveedor);
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al cargar proveedores: {0}", ex.getMessage());
        }
    }

    private void guardarActividad() {
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String duracion = txtDuracion.getText().trim(); // ¡AHORA ES STRING!

        if (nombre.isEmpty() || descripcion.isEmpty() || duracion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar que la duración sea un número (opcional, pero buena práctica si esperas números)
        try {
            // Intenta convertir a int solo para validar si es un número válido.
            // No guardaremos como int, solo comprobamos formato.
            Integer.parseInt(duracion); 
            if (Integer.parseInt(duracion) < 0) { // Opcional: si esperas solo duraciones positivas
                JOptionPane.showMessageDialog(this, "La duración debe ser un número positivo.", "Error de validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número entero válido (aunque se guarde como texto).", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }


        if (cbIDLugarT.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Lugar Turístico válido.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cbIDProveedor.getSelectedItem() == null) {
             JOptionPane.showMessageDialog(this, "Debes seleccionar un Proveedor válido.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener ID del lugar turístico desde el objeto seleccionado
        LugarTuristico lugarSeleccionado = (LugarTuristico) cbIDLugarT.getSelectedItem();
        int idLugar = lugarSeleccionado.getIdLugarTuristico();

        // Obtener ID del proveedor desde el objeto seleccionado
        Proveedor proveedorSeleccionado = (Proveedor) cbIDProveedor.getSelectedItem();
        int idProveedor = proveedorSeleccionado.getIdProveedor();

        String estado = (String) cbEstado.getSelectedItem();

        if (actividadEnEdicion == null) {
            // Insertar nueva actividad
            Actividad nueva = new Actividad(nombre, descripcion, duracion, estado, idLugar, idProveedor);
            if (actividadDAO.insertarActividad(nueva)) {
                JOptionPane.showMessageDialog(this, "✅ Actividad registrada correctamente.");
                padre.refrescarTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al registrar la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Actualizar actividad existente
            actividadEnEdicion.setNombre(nombre);
            actividadEnEdicion.setDescripcion(descripcion);
            actividadEnEdicion.setDuracion(duracion); // ¡AHORA ES STRING!
            actividadEnEdicion.setIdLugarTuristico(idLugar);
            actividadEnEdicion.setIdProveedor(idProveedor);
            actividadEnEdicion.setEstado(estado);

            if (actividadDAO.actualizarActividad(actividadEnEdicion)) {
                JOptionPane.showMessageDialog(this, "✅ Actividad actualizada correctamente.");
                padre.refrescarTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al actualizar la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtDuracion.setText("");
        if (cbIDLugarT.getItemCount() > 0) {
            cbIDLugarT.setSelectedIndex(0);
        }
        if (cbIDProveedor.getItemCount() > 0) {
            cbIDProveedor.setSelectedIndex(0);
        }
        cbEstado.setSelectedItem("activo"); // Reiniciar a "Activo"
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        cbIDProveedor = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbIDLugarT = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(0, 46, 121));

        jButton7.setBackground(new java.awt.Color(179, 23, 23));
        jButton7.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Cerrar sesión");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(8, 8, 100));
        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 25)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5);
        jPanel5.setBounds(0, 0, 890, 90);

        btnConfiguracion.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setText("CONFIGURACIÓN");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        jPanel4.add(btnConfiguracion);
        btnConfiguracion.setBounds(0, 340, 200, 50);

        btnHome.setBackground(new java.awt.Color(0, 46, 121));
        btnHome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel4.add(btnHome);
        btnHome.setBounds(0, 90, 200, 50);

        btnClientes.setBackground(new java.awt.Color(0, 46, 121));
        btnClientes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setText("CLIENTES");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel4.add(btnClientes);
        btnClientes.setBounds(0, 140, 200, 50);

        btnReservas.setBackground(new java.awt.Color(0, 46, 121));
        btnReservas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReservas.setForeground(new java.awt.Color(255, 255, 255));
        btnReservas.setText("RESERVAS");
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });
        jPanel4.add(btnReservas);
        btnReservas.setBounds(0, 190, 200, 50);

        btnProveedores.setBackground(new java.awt.Color(0, 46, 121));
        btnProveedores.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedores.setText("PROVEEDORES");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jPanel4.add(btnProveedores);
        btnProveedores.setBounds(0, 240, 200, 50);

        btnReportes.setBackground(new java.awt.Color(0, 46, 121));
        btnReportes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("REPORTES");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jPanel4.add(btnReportes);
        btnReportes.setBounds(0, 290, 200, 50);

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

        jPanel4.add(jPanel6);
        jPanel6.setBounds(0, 380, 200, 170);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("FORMULARIO DE ACTIVIDAD");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(230, 110, 330, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Descripcion:");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(580, 180, 75, 17);
        jPanel4.add(txtDescripcion);
        txtDescripcion.setBounds(580, 200, 210, 60);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Estado:");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(680, 340, 47, 17);

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jPanel4.add(cbEstado);
        cbEstado.setBounds(680, 360, 76, 22);

        btnLimpiar.setBackground(new java.awt.Color(102, 102, 102));
        btnLimpiar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel4.add(btnLimpiar);
        btnLimpiar.setBounds(430, 440, 90, 30);

        btnGuardar.setBackground(new java.awt.Color(40, 167, 69));
        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar);
        btnGuardar.setBounds(530, 440, 113, 30);

        jPanel4.add(cbIDProveedor);
        cbIDProveedor.setBounds(490, 360, 117, 22);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("ID Proveedor :");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(490, 340, 91, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(300, 180, 54, 17);

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel4.add(txtNombre);
        txtNombre.setBounds(300, 200, 179, 35);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Duracion:");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(300, 260, 60, 17);
        jPanel4.add(txtDuracion);
        txtDuracion.setBounds(300, 280, 180, 28);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("ID Lugar T. :");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(310, 340, 78, 17);

        jPanel4.add(cbIDLugarT);
        cbIDLugarT.setBounds(310, 360, 118, 22);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    guardarActividad();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
    limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
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

    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<LugarTuristico> cbIDLugarT;
    private javax.swing.JComboBox<Proveedor> cbIDProveedor;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
