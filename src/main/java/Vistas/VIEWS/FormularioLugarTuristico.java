package Vistas.VIEWS;

import Modelo.Destino;
import Modelo.LugarTuristico;
import Modelo.Proveedor;
import dao.LugarTuristicoDAO;
import dao.DestinoDAO;
import dao.ProveedorDAO;
import Modelo.Usuario;

import javax.swing.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioLugarTuristico extends javax.swing.JDialog {
       
    private LugarTuristico lugarTuristicoAEditar; // Objeto si estamos editando
    private LugarTuristicoDAO lugarTuristicoDAO;
    private DestinoDAO destinoDAO;
    private ProveedorDAO proveedorDAO;
    private Usuario usuarioActual; 
    private GestionLugarTuristico padreGetionLugarTuristico;

    public FormularioLugarTuristico(JFrame padre, GestionLugarTuristico gestionLugarTuristico) {
        super(padre,true);
        initComponents();
        
        this.padreGetionLugarTuristico= gestionLugarTuristico;
        
        this.proveedorDAO = new ProveedorDAO();
        this.destinoDAO= new DestinoDAO();
        
        this.lugarTuristicoDAO = new LugarTuristicoDAO(this.destinoDAO,this.proveedorDAO);
        
        this.setLocationRelativeTo(padre);
        this.setLocationRelativeTo(null);
        setTitle("Crear Nuevo Lugar Turístico");

                        
        ToggleButtonActivo.setSelected(true); 
        updateToggleButtonText(true);
        cargarDatosInicialesParaNuevo();
        addToggleButtonActionListener();
        
    }
    
    public FormularioLugarTuristico(JFrame padre, GestionLugarTuristico gestionLugarTuristicoPadre, LugarTuristico lugarTuristico){
        this(padre, gestionLugarTuristicoPadre);
        this.lugarTuristicoAEditar = lugarTuristico;
                
        addToggleButtonActionListener(); // Configura el listener del toggle        
        updateToggleButtonText(ToggleButtonActivo.isSelected()); // Actualiza el texto inicial del toggle

        this.setLocationRelativeTo(padre);
        
        setTitle("Modificar LugarTuristico #" + lugarTuristico.getIdLugarTuristico());
        cargarDatosParaEdicion(lugarTuristico);
    
    }
    private void cargarDatosParaEdicion(LugarTuristico lugarTuristico) {
        if (lugarTuristico != null) {
            txtNombre.setText(lugarTuristico.getNombreLugar());
            txtDescripción.setText(lugarTuristico.getDescripcion());
            cbTipo.setSelectedItem(lugarTuristico.getTipoLugar());
            ToggleButtonActivo.setSelected(lugarTuristico.getEstado()); // Establece el estado del toggle
            updateToggleButtonText(lugarTuristico.getEstado()); // Actualiza el texto del toggle

            cargarComboBoxes(); // Carga los ComboBox de Destino y Proveedor

            // Seleccionar el Destino y Proveedor correctos en los ComboBox
            if (lugarTuristico.getDestino() != null) {
                cmbProveedor.setSelectedItem(lugarTuristico.getDestino());
            }
            if (lugarTuristico.getProveedor() != null) {
                cmbDestino.setSelectedItem(lugarTuristico.getProveedor()); // Asumo que cmbDestino es para Proveedor
            }
        }
    }
    
    private void cargarDatosInicialesParaNuevo() {
        // Establecer valores por defecto para un nuevo lugar turístico
        txtNombre.setText("");
        txtDescripción.setText("");
        cbTipo.setSelectedIndex(0); // Selecciona el primer tipo por defecto
        ToggleButtonActivo.setSelected(true); // Por defecto activo
        updateToggleButtonText(true); // Actualiza el texto del botón al estado inicial
        cargarComboBoxes(); // Carga los ComboBox de Destino y Proveedor
    }
        
    private void cargarComboBoxes() {
        cbTipo.setModel(new DefaultComboBoxModel<>(new String[]{"Histórico", "Natural", "Recreativo"}));

        // Cargar ComboBox de Destinos
        DefaultComboBoxModel<Destino> destinoModel = new DefaultComboBoxModel<>();
        List<Destino> destinos = destinoDAO.obtenerTodosLosDestinos(); // Asumo un método para obtener todos los destinos
        for (Destino d : destinos) {
            destinoModel.addElement(d);
        }
        cmbProveedor.setModel(destinoModel);

        // Cargar ComboBox de Proveedores
        DefaultComboBoxModel<Proveedor> proveedorModel = new DefaultComboBoxModel<>();
        List<Proveedor> proveedores = proveedorDAO.obtenerTodosLosProveedores(); // Asumo un método para obtener todos los proveedores
        for (Proveedor p : proveedores) {
            proveedorModel.addElement(p);
        }
        cmbDestino.setModel(proveedorModel); // Asumo que cmbDestino es para Proveedor
    }
        
           
    private void updateToggleButtonText(boolean isActive) {
        if (ToggleButtonActivo != null) { // Asegurarse de que no sea null
            if (isActive) {
                ToggleButtonActivo.setText("Activo");
            } else {
                ToggleButtonActivo.setText("Inactivo");
            }
        }
    }


    private void addToggleButtonActionListener() {
        if (ToggleButtonActivo != null) {
            ToggleButtonActivo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    boolean estaActivo = ToggleButtonActivo.isSelected();
                    updateToggleButtonText(estaActivo); // Actualiza el texto inmediatamente
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbDestino = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ToggleButtonActivo = new javax.swing.JToggleButton();
        txtDescripción = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 84, 200, -1));

        jLabel1.setText("Nombre");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 74, 120, 42));

        jLabel2.setText("Descripcion");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 311, 120, 38));

        jLabel3.setText("Tipo");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 134, 70, 22));

        jLabel6.setText("Estado");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 168, 80, 32));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 120, 43));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 120, 40));

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Historico", "Natural", "Recreativo" }));
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        getContentPane().add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 134, 200, -1));

        jLabel4.setText("Destino ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 80, 40));

        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(cmbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 272, 200, -1));

        jLabel5.setText("Proveedor");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 80, -1));

        cmbDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDestinoActionPerformed(evt);
            }
        });
        getContentPane().add(cmbDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 221, 200, -1));

        btnCancelar.setText("Cancelar");
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 420, 130, 43));

        jLabel7.setText("Formulario para Lugar Turisitico");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 220, 37));

        ToggleButtonActivo.setText("Activo");
        getContentPane().add(ToggleButtonActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 173, 130, -1));
        getContentPane().add(txtDescripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 319, 200, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // 1. Recopilar datos del formulario
        String nombre = txtNombre.getText().trim();
        String tipo = (String) cbTipo.getSelectedItem();
        String descripcion = txtDescripción.getText().trim();
        Destino destinoSeleccionado = (Destino) cmbProveedor.getSelectedItem();
        Proveedor proveedorSeleccionado = (Proveedor) cmbDestino.getSelectedItem(); // Asumo cmbDestino es para Proveedor
        boolean estado = ToggleButtonActivo.isSelected();

        // 2. Validaciones básicas
        if (nombre.isEmpty() || descripcion.isEmpty() || tipo == null || destinoSeleccionado == null || proveedorSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos y seleccione un Destino y Proveedor.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }


        // Si estamos creando un nuevo lugar turístico
        if (lugarTuristicoAEditar == null) {
                // Crear un nuevo objeto LugarTuristico
                LugarTuristico nuevoLugar = new LugarTuristico();
                nuevoLugar.setNombreLugar(nombre);
                nuevoLugar.setTipoLugar(tipo);
                nuevoLugar.setDescripcion(descripcion);
                nuevoLugar.setDestino(destinoSeleccionado);
                nuevoLugar.setProveedor(proveedorSeleccionado);
                nuevoLugar.setEstado(estado);

                // Guardar en la base de datos
                if (lugarTuristicoDAO.insertarLugarTuristico(nuevoLugar)) {
                    JOptionPane.showMessageDialog(this, "Lugar turístico agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    padreGetionLugarTuristico.cargarLugaresTuristicosActivos(); // Refrescar la tabla en la ventana padre
                    dispose(); // Cerrar el diálogo
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar el lugar turístico.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                // Actualizar el objeto existente con los nuevos datos
                lugarTuristicoAEditar.setNombreLugar(nombre);
                lugarTuristicoAEditar.setTipoLugar(tipo);
                lugarTuristicoAEditar.setDescripcion(descripcion);
                lugarTuristicoAEditar.setDestino(destinoSeleccionado);
                lugarTuristicoAEditar.setProveedor(proveedorSeleccionado);
                lugarTuristicoAEditar.setEstado(estado); // El estado ya se actualiza con el toggle

                // Actualizar en la base de datos
                if (lugarTuristicoDAO.actualizarLugarTuristico(lugarTuristicoAEditar)) {
                    JOptionPane.showMessageDialog(this, "Lugar turístico modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    padreGetionLugarTuristico.cargarLugaresTuristicosActivos(); // Refrescar la tabla en la ventana padre
                    dispose(); // Cerrar el diálogo
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar el lugar turístico.", "Error", JOptionPane.ERROR_MESSAGE);
                }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNombre.setText("");
        txtDescripción.setText("");
        cbTipo.setSelectedIndex(0);  // selecciona el primer valor del combo (ej: "Histórico")
        cmbDestino.setSelectedIndex(0);
        cmbProveedor.setSelectedIndex(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cmbDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDestinoActionPerformed

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProveedorActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoActionPerformed


//    cbEstado.setSelectedIndex(0);

//    }                                          

    /*
    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void cbDestinoActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void cbIDProveedorActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             
*/
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ToggleButtonActivo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<Proveedor> cmbDestino;
    private javax.swing.JComboBox<Destino> cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtDescripción;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
