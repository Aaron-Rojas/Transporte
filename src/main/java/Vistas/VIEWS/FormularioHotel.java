package Vistas.VIEWS;
import Conexión.Conexión;
import Modelo.Hotel;
import Modelo.Usuario;
import dao.DestinoDAO;
import dao.HotelDAO;
import dao.ProveedorDAO;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import Modelo.Destino;
import Modelo.Proveedor;

public class FormularioHotel extends javax.swing.JDialog {
    
    private Hotel hotelAEditar;
    private HotelDAO hotelDAO;
    private DestinoDAO destinoDAO;
    private ProveedorDAO proveedorDAO;
    private Usuario usuarioActual;
    private GestionHotel padreGestionHotel;

    public FormularioHotel(JFrame padre, GestionHotel gestionHotel) {
        super(padre, true);
        initComponents();

        this.padreGestionHotel = gestionHotel;

        this.destinoDAO = new DestinoDAO();
        this.proveedorDAO = new ProveedorDAO();
        this.hotelDAO = new HotelDAO(this.destinoDAO, this.proveedorDAO);

        this.setLocationRelativeTo(padre);
        setTitle("Crear Nuevo Hotel");

        ToggleButtonActivo.setSelected(true);
        updateToggleButtonText(true);
        cargarDatosInicialesParaNuevo();
        addToggleButtonActionListener();
    }
        
    public FormularioHotel(JFrame padre, GestionHotel gestionHotelPadre, Hotel hotel) {
        this(padre, gestionHotelPadre);
        this.hotelAEditar = hotel;

        setTitle("Modificar Hotel #" + hotel.getIdHotel());
        cargarDatosParaEdicion(hotel);
    }

    private void cargarDatosParaEdicion(Hotel hotel) {
        if (hotel != null) {
            txtNombreHotel.setText(hotel.getNombreHotel());
            txtDireccionHotel.setText(hotel.getDireccion());
            cmbCategoria.setSelectedItem(hotel.getCategoria());
            ToggleButtonActivo.setSelected(hotel.isEstado());
            updateToggleButtonText(hotel.isEstado());

            cargarComboBoxes();

            if (hotel.getDestino() != null) {
                cmbDestino.setSelectedItem(hotel.getDestino());
            }
            if (hotel.getProveedor() != null) {
                cmbProveedor.setSelectedItem(hotel.getProveedor());
            }
        }
    }

    private void cargarDatosInicialesParaNuevo() {
        txtNombreHotel.setText("");
        txtDireccionHotel.setText("");
        cmbCategoria.setSelectedIndex(0);
        ToggleButtonActivo.setSelected(true);
        updateToggleButtonText(true);
        cargarComboBoxes();
    }

    private void cargarComboBoxes() {
        cmbCategoria.setModel(new DefaultComboBoxModel<>(new String[]{"1 estrella", "2 estrellas", "3 estrellas", "4 estrellas", "5 estrellas"}));

        DefaultComboBoxModel<Destino> destinoModel = new DefaultComboBoxModel<>();
        List<Destino> destinos = destinoDAO.obtenerTodosLosDestinos();
        for (Destino d : destinos) {
            destinoModel.addElement(d);
        }
        cmbDestino.setModel(destinoModel);

        DefaultComboBoxModel<Proveedor> proveedorModel = new DefaultComboBoxModel<>();
        List<Proveedor> proveedores = proveedorDAO.obtenerTodosLosProveedores();
        for (Proveedor p : proveedores) {
            proveedorModel.addElement(p);
        }
        cmbProveedor.setModel(proveedorModel);
    }

    private void updateToggleButtonText(boolean isActive) {
        if (ToggleButtonActivo != null) {
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
                    updateToggleButtonText(estaActivo);
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        btnConfiguracion2 = new javax.swing.JButton();
        btnHome2 = new javax.swing.JButton();
        btnClientes2 = new javax.swing.JButton();
        btnReservas2 = new javax.swing.JButton();
        btnProveedores2 = new javax.swing.JButton();
        btnReportes2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreHotel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDireccionHotel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ToggleButtonActivo = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        cmbDestino = new javax.swing.JComboBox<>();
        cmbProveedor = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        btnConfiguracion2.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnConfiguracion2.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion2.setText(" CONFIGURACIÓN");
        btnConfiguracion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracion2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnConfiguracion2);
        btnConfiguracion2.setBounds(-10, 340, 210, 50);

        btnHome2.setBackground(new java.awt.Color(0, 46, 121));
        btnHome2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHome2.setForeground(new java.awt.Color(255, 255, 255));
        btnHome2.setText(" HOME");
        btnHome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnHome2);
        btnHome2.setBounds(-10, 90, 210, 50);

        btnClientes2.setBackground(new java.awt.Color(0, 46, 121));
        btnClientes2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClientes2.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes2.setText(" CLIENTES");
        btnClientes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientes2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnClientes2);
        btnClientes2.setBounds(-10, 140, 210, 50);

        btnReservas2.setBackground(new java.awt.Color(0, 46, 121));
        btnReservas2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReservas2.setForeground(new java.awt.Color(255, 255, 255));
        btnReservas2.setText(" RESERVAS");
        btnReservas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservas2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnReservas2);
        btnReservas2.setBounds(-10, 190, 210, 50);

        btnProveedores2.setBackground(new java.awt.Color(0, 46, 121));
        btnProveedores2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnProveedores2.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedores2.setText(" PROVEEDORES");
        btnProveedores2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedores2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnProveedores2);
        btnProveedores2.setBounds(-10, 240, 210, 50);

        btnReportes2.setBackground(new java.awt.Color(0, 46, 121));
        btnReportes2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReportes2.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes2.setText(" REPORTES");
        btnReportes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportes2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnReportes2);
        btnReportes2.setBounds(-10, 290, 210, 50);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("FORMULARIO DEL HOTEL");
        jPanel6.add(jLabel8);
        jLabel8.setBounds(220, 120, 280, 25);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel6.add(jLabel2);
        jLabel2.setBounds(240, 180, 60, 17);

        txtNombreHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreHotelActionPerformed(evt);
            }
        });
        jPanel6.add(txtNombreHotel);
        txtNombreHotel.setBounds(240, 200, 222, 38);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Direccion:");
        jPanel6.add(jLabel4);
        jLabel4.setBounds(240, 250, 61, 30);

        txtDireccionHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionHotelActionPerformed(evt);
            }
        });
        jPanel6.add(txtDireccionHotel);
        txtDireccionHotel.setBounds(230, 290, 278, 35);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Categoria:");
        jPanel6.add(jLabel5);
        jLabel5.setBounds(580, 180, 63, 17);

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Estrella", "2  Estrella", "3  Estrella", "4  Estrella", "5  Estrella" }));
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        jPanel6.add(cmbCategoria);
        cmbCategoria.setBounds(580, 200, 97, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Estado:");
        jPanel6.add(jLabel6);
        jLabel6.setBounds(580, 270, 47, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Id Provedor:");
        jPanel6.add(jLabel7);
        jLabel7.setBounds(580, 360, 78, 17);

        btnGuardar.setBackground(new java.awt.Color(40, 167, 69));
        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel6.add(btnGuardar);
        btnGuardar.setBounds(580, 480, 160, 30);

        btnLimpiar.setBackground(new java.awt.Color(153, 153, 153));
        btnLimpiar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel6.add(btnLimpiar);
        btnLimpiar.setBounds(400, 480, 160, 30);

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

        jPanel6.add(jPanel2);
        jPanel2.setBounds(0, 0, 890, 90);

        jPanel3.setBackground(new java.awt.Color(0, 46, 121));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel3);
        jPanel3.setBounds(0, 380, 200, 170);

        ToggleButtonActivo.setText("Activo");
        jPanel6.add(ToggleButtonActivo);
        ToggleButtonActivo.setBounds(570, 300, 130, 23);

        jLabel9.setText("Destino:");
        jPanel6.add(jLabel9);
        jLabel9.setBounds(240, 350, 70, 30);

        btnCancelar.setText("Cancelar");
        jPanel6.add(btnCancelar);
        btnCancelar.setBounds(260, 480, 100, 30);

        cmbDestino.setModel(new javax.swing.DefaultComboBoxModel<>());
        jPanel6.add(cmbDestino);
        cmbDestino.setBounds(240, 380, 190, 40);

        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>());
        jPanel6.add(cmbProveedor);
        cmbProveedor.setBounds(570, 390, 130, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    // 1. Validar datos obligatorios del formulario
    if (txtNombreHotel.getText().trim().isEmpty() || txtDireccionHotel.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "El nombre y la dirección son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 2. Obtener objetos de los ComboBoxes
    Destino destino = (Destino) cmbDestino.getSelectedItem();
    Proveedor proveedor = (Proveedor) cmbProveedor.getSelectedItem();

    // 3. Validar que se hayan seleccionado Destino y Proveedor
    if (destino == null || proveedor == null) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un Destino y un proveedor.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 4. Recolectar datos de los campos de texto y el ToggleButton
    String nombre = txtNombreHotel.getText().trim();
    String direccion = txtDireccionHotel.getText().trim(); // Corregido: "direcciom" a "direccion"
    String categoria = (String) cmbCategoria.getSelectedItem();
    boolean estado = ToggleButtonActivo.isSelected();

    // 5. Lógica para CREAR o ACTUALIZAR Hotel
    if (hotelAEditar == null) { // Modo CREAR nuevo Hotel
        Hotel nuevoHotel = new Hotel(0, nombre, direccion, categoria, destino, proveedor, estado);
        if (hotelDAO.insertarHotel(nuevoHotel)) {
            JOptionPane.showMessageDialog(this, "Hotel creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear el Hotel.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else { // Modo EDITAR Hotel existente
        hotelAEditar.setNombreHotel(nombre);
        hotelAEditar.setDireccion(direccion);
        hotelAEditar.setCategoria(categoria);
        hotelAEditar.setDestino(destino);
        hotelAEditar.setProveedor(proveedor);
        hotelAEditar.setEstado(estado);

        if (hotelDAO.actualizarHotel(hotelAEditar)) {
            JOptionPane.showMessageDialog(this, "Hotel actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el Hotel.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 6. Refrescar la tabla en el padre y cerrar el formulario
    // Asegúrate de que padreGestionHotel tenga un método para recargar hoteles, por ejemplo:
    // padreGestionHotel.cargarHoteles(); o padreGestionHotel.cargarHotelesActivos();
    // NOTA: 'cargarLugaresTuristicosActivos()' es incorrecto para GestionHotel.
    padreGestionHotel.cargarLugaresTuristicosActivos(); // Cambiado a un nombre de método más apropiado para Hoteles
    this.dispose();       
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtDireccionHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionHotelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionHotelActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void txtNombreHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreHotelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreHotelActionPerformed

    private void btnConfiguracion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracion2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracion2ActionPerformed

    private void btnHome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome2ActionPerformed

    }//GEN-LAST:event_btnHome2ActionPerformed

    private void btnClientes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientes2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientes2ActionPerformed

    private void btnReservas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservas2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservas2ActionPerformed

    private void btnProveedores2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedores2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedores2ActionPerformed

    private void btnReportes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportes2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportes2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ToggleButtonActivo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes2;
    private javax.swing.JButton btnConfiguracion2;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHome2;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProveedores2;
    private javax.swing.JButton btnReportes2;
    private javax.swing.JButton btnReservas2;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<Destino> cmbDestino;
    private javax.swing.JComboBox<Proveedor> cmbProveedor;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField txtDireccionHotel;
    private javax.swing.JTextField txtNombreHotel;
    // End of variables declaration//GEN-END:variables

    void setModoEdicion(boolean b, Object object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
