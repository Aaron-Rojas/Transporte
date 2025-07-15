package Vistas.VIEWS;

import Conexión.Conexión;
import Modelo.ActividadTuristica;
import Modelo.LugarTuristico;
import Modelo.Proveedor;
import dao.ActividadTuristicaDAO;
import dao.DestinoDAO; // Necesario para inicializar LugarTuristicoDAO
import dao.LugarTuristicoDAO;
import dao.ProveedorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
public class FormularioActividad extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioActividad.class.getName());

    private ActividadTuristica actividadAEditar;
    private  ActividadTuristicaDAO actividadTuristicaDAO;
    private LugarTuristicoDAO lugarTuristicoDAO;
    private ProveedorDAO proveedorDAO;
    private GestionActividad padreGestionActividad;

     public FormularioActividad(JFrame padre,GestionActividad gestionActividad) {
        super(padre, true); // Modal
        initComponents();
        this.setLocationRelativeTo(padre);
        setTitle("Crear Nueva Actividad Turística");

        this.padreGestionActividad = gestionActividad;

        // Inicialización de DAOs
        // Se asume que DestinoDAO es necesario para instanciar LugarTuristicoDAO
        DestinoDAO destinoDAO = new DestinoDAO();
        this.proveedorDAO = new ProveedorDAO();
        this.lugarTuristicoDAO = new LugarTuristicoDAO(destinoDAO, this.proveedorDAO);
        this.actividadTuristicaDAO = new ActividadTuristicaDAO(this.proveedorDAO, this.lugarTuristicoDAO);

        // Configuración inicial del formulario
        addToggleButtonActionListener();
        cargarDatosIniciales();
    }
        
     public FormularioActividad(JFrame padre, GestionActividad gestionActividad, ActividadTuristica actividad) {
        this(padre, gestionActividad); // Llama al constructor de creación para inicializar todo
        this.actividadAEditar = actividad;
        setTitle("Modificar Actividad #" + actividad.getID_Actividad());
        cargarDatosParaEdicion();
    }
     
    private void cargarComboBoxes() {
        // Cargar ComboBox de Lugares Turísticos
        DefaultComboBoxModel<LugarTuristico> lugarModel = new DefaultComboBoxModel<>();
        // Es buena práctica obtener solo los lugares activos para asociar
        List<LugarTuristico> lugares = lugarTuristicoDAO.obtenerTodosLosLugaresTuristicosActivos();
        for (LugarTuristico lugar : lugares) {
            lugarModel.addElement(lugar);
        }
        cbIDLugarT.setModel(lugarModel);

        // Cargar ComboBox de Proveedores
        DefaultComboBoxModel<Proveedor> proveedorModel = new DefaultComboBoxModel<>();
        List<Proveedor> proveedores = proveedorDAO.obtenerProveedoresActivos(); // Igualmente, solo activos
        for (Proveedor prov : proveedores) {
            proveedorModel.addElement(prov);
        }
        cbIDProveedor.setModel(proveedorModel);
    }
    

    private void cargarDatosIniciales() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtDuracion.setText("");
        
        ToggleButtonActivo.setSelected(true);
        updateToggleButtonText(true);
        
        cargarComboBoxes();
    }
        
    private void cargarDatosParaEdicion() {
        if (actividadAEditar != null) {
            txtNombre.setText(actividadAEditar.getNombreActividad());
            txtDescripcion.setText(actividadAEditar.getDescripcion());
            txtDuracion.setText(actividadAEditar.getDuracion());

            ToggleButtonActivo.setSelected(actividadAEditar.isEstado());
            updateToggleButtonText(actividadAEditar.isEstado());

            cargarComboBoxes(); // Carga los datos primero

            // Selecciona los elementos correctos en los ComboBox
            cbIDLugarT.setSelectedItem(actividadAEditar.getLugarTuristico());
            cbIDProveedor.setSelectedItem(actividadAEditar.getProveedor());
        }
    }
    
        private void updateToggleButtonText(boolean isActive) {
        if (ToggleButtonActivo != null) {
            ToggleButtonActivo.setText(isActive ? "Activo" : "Inactivo");
        }
    }

    /**
     * Añade el listener para que el botón cambie de texto al ser presionado.
     */
    private void addToggleButtonActionListener() {
        if (ToggleButtonActivo != null) {
            ToggleButtonActivo.addActionListener(e -> {
                boolean estaActivo = ToggleButtonActivo.isSelected();
                updateToggleButtonText(estaActivo);
            });
        }
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
        ToggleButtonActivo = new javax.swing.JToggleButton();
        btnCancelar = new javax.swing.JButton();

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

        ToggleButtonActivo.setText("Activo");
        jPanel4.add(ToggleButtonActivo);
        ToggleButtonActivo.setBounds(660, 360, 120, 23);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar);
        btnCancelar.setBounds(320, 440, 90, 30);

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
            // 1. Validar datos
        if (txtNombre.getText().trim().isEmpty() || txtDuracion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre y la duración son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LugarTuristico lugarSeleccionado = (LugarTuristico) cbIDLugarT.getSelectedItem();
        Proveedor proveedorSeleccionado = (Proveedor) cbIDProveedor.getSelectedItem();

        if (lugarSeleccionado == null || proveedorSeleccionado == null) {
             JOptionPane.showMessageDialog(this, "Debe seleccionar un lugar y un proveedor.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Recolectar datos del formulario
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String duracion = txtDuracion.getText().trim();
        boolean estado = ToggleButtonActivo.isSelected();

        // 3. Crear o actualizar el objeto
        if (actividadAEditar == null) { // Modo CREAR
            ActividadTuristica nuevaActividad = new ActividadTuristica(0, nombre, descripcion, duracion, lugarSeleccionado, proveedorSeleccionado, estado);
            if (actividadTuristicaDAO.insertarActividadTuristica(nuevaActividad)) {
                JOptionPane.showMessageDialog(this, "Actividad creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else { // Modo EDITAR
            actividadAEditar.setNombreActividad(nombre);
            actividadAEditar.setDescripcion(descripcion);
            actividadAEditar.setDuracion(duracion);
            actividadAEditar.setLugarTuristico(lugarSeleccionado);
            actividadAEditar.setProveedor(proveedorSeleccionado);
            actividadAEditar.setEstado(estado);

            if (actividadTuristicaDAO.actualizarActividadTuristica(actividadAEditar)) {
                JOptionPane.showMessageDialog(this, "Actividad actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // 4. Refrescar tabla y cerrar
        padreGestionActividad.cargarActividadesTuristicasActivas();
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        cargarDatosIniciales();
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
         this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ToggleButtonActivo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
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
