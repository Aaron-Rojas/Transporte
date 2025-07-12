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
        cargarCombos(); // Cargar combos Lugar Turístico y Proveedor
        if (actividadEnEdicion != null) {
            cargarDatosEnFormulario();
        }
    }
    
    private void cargarCombos() {
        cargarComboLugaresTuristicos();
        cargarComboProveedores();

        cbEstado.removeAllItems();
        cbEstado.addItem("Activo");
        cbEstado.addItem("Inactivo");
    }

       
    private void cargarDatosEnFormulario() {
        txtNombre.setText(actividadEnEdicion.getNombre());
        txtDescripcion.setText(actividadEnEdicion.getDescripcion());
        txtDuracion.setText(String.valueOf(actividadEnEdicion.getDuracion()));
        cbIDLugarT.setSelectedItem(actividadEnEdicion.getIdLugarTuristico());
        cbIDProveedor.setSelectedItem(actividadEnEdicion.getIdProveedor());
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
                cbIDLugarT.addItem(new LugarTuristico(id, nombre)); // Esto ya debe funcionar
            }

        } catch (SQLException ex) {
            logger.severe("Error al cargar lugares turísticos: " + ex.getMessage());
        }
    }


private void cargarComboProveedores() {
    cbIDProveedor.removeAllItems();
    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement("SELECT ID_Proveedor, NombreProveedor, Contacto, Estado FROM proveedor WHERE Estado = 'Activo'");
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("ID_Proveedor");
            String nombre = rs.getString("NombreProveedor");
            String contacto = rs.getString("Contacto");
            String estado = rs.getString("Estado");

            Proveedor proveedor = new Proveedor(id, nombre, contacto, estado);
            cbIDProveedor.addItem(proveedor);
        }

    } catch (SQLException ex) {
        logger.severe("Error al cargar proveedores: " + ex.getMessage());
    }
}





private void guardarActividad() {
    String nombre = txtNombre.getText().trim();
    String descripcion = txtDescripcion.getText().trim();
    String duracionStr = txtDuracion.getText().trim();
    int duracion = 0;

    if (nombre.isEmpty() || descripcion.isEmpty() || duracionStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Completa todos los campos.");
        return;
    }

    if (cbIDLugarT.getSelectedItem() == null || cbIDProveedor.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this, "Debes seleccionar un Lugar Turístico y un Proveedor válido.");
        return;
    }

    try {
        duracion = Integer.parseInt(duracionStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Duración debe ser un número.");
        return;
    }

    int idLugar = (int) cbIDLugarT.getSelectedItem();

    // 🟢 Obtener ID del proveedor desde el objeto seleccionado
    Proveedor proveedorSeleccionado = (Proveedor) cbIDProveedor.getSelectedItem();
    int idProveedor = proveedorSeleccionado.getIdProveedor();

    String estado = (String) cbEstado.getSelectedItem();

    if (actividadEnEdicion == null) {
        // Insertar nueva actividad
        Actividad nueva = new Actividad(0, nombre, descripcion, duracion, idLugar, idProveedor, estado);
        if (actividadDAO.insertarActividad(nueva)) {
            JOptionPane.showMessageDialog(this, "Actividad registrada.");
            padre.refrescarTabla();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la actividad.");
        }
    } else {
        // Actualizar actividad existente
        actividadEnEdicion.setNombre(nombre);
        actividadEnEdicion.setDescripcion(descripcion);
        actividadEnEdicion.setDuracion(duracion);
        actividadEnEdicion.setIdLugarTuristico(idLugar);
        actividadEnEdicion.setIdProveedor(idProveedor);
        actividadEnEdicion.setEstado(estado);

        if (actividadDAO.actualizarActividad(actividadEnEdicion)) {
            JOptionPane.showMessageDialog(this, "Actividad actualizada.");
            padre.refrescarTabla();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la actividad.");
        }
    }
}

    private void limpiarFormulario() {
    txtNombre.setText("");
    txtDescripcion.setText("");
    txtDuracion.setText("");
    cbIDLugarT.setSelectedIndex(0);
    cbIDProveedor.setSelectedIndex(0);
    cbEstado.setSelectedIndex(0);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbIDLugarT = new javax.swing.JComboBox<>();
        cbIDProveedor = new javax.swing.JComboBox<>();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtDuracion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cbEstado = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Formulario de Actividad");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Descripcion:");

        jLabel4.setText("Duracion:");

        jLabel5.setText("ID Lugar T. :");

        jLabel6.setText("ID Proveedor :");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel7.setText("Estado:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbIDLugarT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiar)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbIDLugarT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btnLimpiar)
                .addGap(24, 24, 24))
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

    /**
     * @param args the command line arguments
     */



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<LugarTuristico> cbIDLugarT;
    private javax.swing.JComboBox<Proveedor> cbIDProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
