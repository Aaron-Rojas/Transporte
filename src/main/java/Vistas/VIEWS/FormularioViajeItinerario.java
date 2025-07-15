
package Vistas.VIEWS;

import Modelo.Cliente;
import Modelo.Itinerario;
import Modelo.Usuario;
import dao.ClienteDAO;
import dao.ItinerarioDAO;
import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JFrame;

//conversion
import java.time.LocalDate;
import java.time.ZoneId; 
import java.util.Date;

public class FormularioViajeItinerario extends javax.swing.JDialog {
    
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private Usuario usuarioActual;
    private UsuarioDAO usuarioDAO;
    private Itinerario itinerarioSeleccionado;
    private ItinerarioDAO itinerarioDAO;
    
    private GestionViajeItinerario padreGestionViajeItinerario;
    
    public FormularioViajeItinerario(JFrame padre, GestionViajeItinerario gestionViajeItinerarioPadre, Usuario  usuarioLogeado) {
        super(padre, true);        
        this.usuarioActual= usuarioLogeado;
        initComponents();
        this.padreGestionViajeItinerario= gestionViajeItinerarioPadre;
        this.clienteDAO = new ClienteDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.itinerarioDAO = new ItinerarioDAO(this.clienteDAO,this.usuarioDAO);
        this.setLocationRelativeTo(padre);
        this.setLocationRelativeTo(null);
                
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();
            setTitle("Sistema para el usuario " + usuarioActual.getNombreCompleto());
        } else {
            setTitle("Sistema de User");
        }
               
        if (usuarioActual != null) {
            txtUsuario.setText(usuarioActual.getNombreCompleto());
        } else {
            txtUsuario.setText("Invitado"); // O el valor por defecto
        }
        txtUsuario.setEditable(false);
        addToggleButtonActionListener();
                
        btnCrearItinerario.setText("Crear Itinerario");

        txtFechaCreacion.setText("Automática al Guardar"); 
        txtFechaCreacion.setEditable(false);

        
        ToggleButtonActivo.setSelected(true); 
        updateToggleButtonText(true);

        addToggleButtonActionListener();
        cargarClientesEnComboBox();
        
    }
    public FormularioViajeItinerario(JFrame padre, GestionViajeItinerario gestionViajeItinerarioPadre, Itinerario itinerario, Usuario usuarioLogeado){
       this(padre, gestionViajeItinerarioPadre, usuarioLogeado);
       this.itinerarioSeleccionado = itinerario;
       btnCrearItinerario.setText("Guardar cambios");
       setTitle("Modificar Itinerario #" + itinerario.getID_Itinerario());
       // RELLENAR LOS COMPONENTES coen los datos del intinerario}
       cargarDatosItinerario(itinerario);
       
    }

    private Date convertToDateViaInstant(LocalDate localDate) {
    if (localDate == null) {
        return null;
    }
        // Convertir LocalDate a Instant y luego a Date
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
  
    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    if (dateToConvert == null) {
        return null;
    }
    // Convertir Date a Instant y luego a LocalDate
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
     private void cargarClientesEnComboBox() {
        DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<>();
                      
        List<Cliente> clientes = this.clienteDAO.obtenerClientesActivos();

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron clientes activos en la BD.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {            
            for (Cliente cliente : clientes) {
                model.addElement(cliente);
            }
        }

        cmbClientes.setModel(model); 
        
        if (itinerarioSeleccionado != null && itinerarioSeleccionado.getCliente() != null) {
            cmbClientes.setSelectedItem(itinerarioSeleccionado.getCliente());
        }
    }
        // Método para cargar los datos del itinerario en los componentes (modo edición)
    private void cargarDatosItinerario(Itinerario itinerario) {
        if (itinerario != null) {
            if (itinerario.getCliente() != null) {
                cmbClientes.setSelectedItem(itinerario.getCliente());
            }

            
            if (itinerario.getUsuarioCreacion() != null) {
                txtUsuario.setText(itinerario.getUsuarioCreacion().getNombreCompleto());
            } else {
                 txtUsuario.setText("N/A"); // O algún valor por defecto
            }
            txtUsuario.setEditable(false);

            
            if (itinerario.getFechaCreacion() != null) {
                // Formatear la fecha para mostrarla
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                txtFechaCreacion.setText(itinerario.getFechaCreacion().toString());
            } else {
                txtFechaCreacion.setText("N/A");
            }
            txtFechaCreacion.setEditable(false);

            // Fechas de Viaje
            DateChooserInicio.setDate(convertToDateViaInstant(itinerario.getFechaInicioViaje()));
            DateChooserFin.setDate(convertToDateViaInstant(itinerario.getFechaFinViaje()));
        
            // Costo
            // Asumiendo jTextField2 para Costo
            if (itinerario.getCosto() != null) {
                txtCosto.setText(itinerario.getCosto().toPlainString()); // toPlainString() evita notación científica
            }

            // Estado (JToggleButton)
            ToggleButtonActivo.setSelected(itinerario.isEstado()); // Asume que 'isEstado()' devuelve un boolean
            updateToggleButtonText(itinerario.isEstado()); // Actualiza el texto del botón al cargar
        }
    }
    private void updateToggleButtonText(boolean isActive) {
        if (isActive) {
            ToggleButtonActivo.setText("Activo");
        } else {
            ToggleButtonActivo.setText("Inactivo");
        }
        
    }
    private void addToggleButtonActionListener() {
        if (ToggleButtonActivo != null) {
            ToggleButtonActivo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    boolean estaActivo = ToggleButtonActivo.isSelected();
                    // Update the text immediately when the state changes
                    updateToggleButtonText(estaActivo);

                    if (itinerarioSeleccionado != null) {
                        itinerarioSeleccionado.setEstado(estaActivo);
                        // If you want to update the DB immediately on toggle
                       
                        JOptionPane.showMessageDialog(null, "Estado del Itinerario: " + (estaActivo ? "Activo" : "Inactivo"));
                    } else {
                        // Handle case for new client if you want immediate DB update
                        // (Though usually, new clients are saved via the "Crear Cliente" button)
                        // For a new client, this state will be used when btn_crearCliente is clicked.
                    }
                }
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbClientes = new javax.swing.JComboBox<Cliente>();
        jLabel8 = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DateChooserInicio = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DateChooserFin = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        ToggleButtonActivo = new javax.swing.JToggleButton();
        btnCrearItinerario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(0, 46, 121));

        jButton7.setBackground(new java.awt.Color(179, 23, 23));
        jButton7.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Cerrar sesión");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(8, 8, 100));
        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 25)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(0, 0, 890, 90);

        btnConfiguracion.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setText("CONFIGURACIÓN");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        jPanel2.add(btnConfiguracion);
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
        jPanel2.add(btnHome);
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
        jPanel2.add(btnClientes);
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
        jPanel2.add(btnReservas);
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
        jPanel2.add(btnProveedores);
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
        jPanel2.add(btnReportes);
        btnReportes.setBounds(0, 290, 200, 50);

        jPanel4.setBackground(new java.awt.Color(0, 46, 121));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(0, 380, 200, 170);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("FORMULARIO DE ITINERARIO");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(250, 130, 320, 25);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Clientes");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(280, 170, 68, 32);

        cmbClientes.setModel(new javax.swing.DefaultComboBoxModel<Cliente>());
        jPanel2.add(cmbClientes);
        cmbClientes.setBounds(270, 200, 161, 22);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Fecha de Creación");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(280, 250, 117, 28);

        txtFechaCreacion.setText("AUTOMATICO");
        txtFechaCreacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaCreacionActionPerformed(evt);
            }
        });
        jPanel2.add(txtFechaCreacion);
        txtFechaCreacion.setBounds(270, 280, 193, 22);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Fecha Inicio de Viaje: ");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(270, 340, 126, 27);
        jPanel2.add(DateChooserInicio);
        DateChooserInicio.setBounds(260, 370, 152, 22);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Costo");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(260, 440, 50, 26);
        jPanel2.add(txtCosto);
        txtCosto.setBounds(250, 470, 111, 22);

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(550, 470, 92, 42);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Usuario");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(500, 160, 67, 32);

        txtUsuario.setText("AUTOMATICO");
        jPanel2.add(txtUsuario);
        txtUsuario.setBounds(490, 190, 137, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Fecha Fin de Viaje:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(510, 260, 113, 17);
        jPanel2.add(DateChooserFin);
        DateChooserFin.setBounds(460, 280, 162, 22);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Estado");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(440, 330, 43, 26);

        ToggleButtonActivo.setText("Activo");
        jPanel2.add(ToggleButtonActivo);
        ToggleButtonActivo.setBounds(430, 370, 117, 23);

        btnCrearItinerario.setText("Crear");
        btnCrearItinerario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearItinerarioActionPerformed(evt);
            }
        });
        jPanel2.add(btnCrearItinerario);
        btnCrearItinerario.setBounds(650, 470, 173, 48);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaCreacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaCreacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaCreacionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnCrearItinerarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearItinerarioActionPerformed
        // TODO add your handling code here:
               
        // 1. Obtener datos de la UI y realizar conversiones
        Cliente cliente = (Cliente) cmbClientes.getSelectedItem();
        Date fechaInicioUtil = DateChooserInicio.getDate();
        Date fechaFinUtil = DateChooserFin.getDate();
        String costoText = txtCosto.getText().trim();
        boolean estado = ToggleButtonActivo.isSelected();

        // 2. Validaciones
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (fechaInicioUtil == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de inicio de viaje.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (fechaFinUtil == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de fin de viaje.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertir java.util.Date a LocalDate
        LocalDate fechaInicioViaje = convertToLocalDateViaInstant(fechaInicioUtil);
        LocalDate fechaFinViaje = convertToLocalDateViaInstant(fechaFinUtil);

        // Validar que la fecha de inicio no sea posterior a la fecha de fin
        if (fechaInicioViaje.isAfter(fechaFinViaje)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio de viaje no puede ser posterior a la fecha de fin de viaje.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar y parsear el costo
        BigDecimal costo;
        try {
            costo = new BigDecimal(costoText);
            if (costo.compareTo(BigDecimal.ZERO) < 0) { // Validar que el costo no sea negativo
                JOptionPane.showMessageDialog(this, "El costo no puede ser negativo.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El costo debe ser un número válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Crear/Actualizar el objeto Itinerario
        boolean operacionExitosa = false;
        
        
 
        if (itinerarioSeleccionado == null) { // Modo CREAR nuevo itinerario
            // La fecha de creación es la fecha actual del sistema
            LocalDate fechaCreacion = LocalDate.now();
            // El usuario de creación es el usuario logeado actual
            Usuario usuarioCreacion = this.usuarioActual; // Asegúrate de que usuarioActual no sea null

            Itinerario nuevoItinerario = new Itinerario(
                cliente,
                usuarioCreacion,
                fechaCreacion,
                fechaInicioViaje,
                fechaFinViaje,
                costo,
                estado
            );

            operacionExitosa = itinerarioDAO.insertarItinerario(nuevoItinerario);
            if (operacionExitosa) {
                JOptionPane.showMessageDialog(this, "Itinerario creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear el itinerario.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else { // Modo MODIFICAR itinerario existente
            // Actualizar los datos del itinerarioSeleccionado
            itinerarioSeleccionado.setCliente(cliente);
            // El usuario de creación y la fecha de creación no cambian en la edición,
            // ya se cargaron al inicio en cargarDatosItinerario()
            itinerarioSeleccionado.setFechaInicioViaje(fechaInicioViaje);
            itinerarioSeleccionado.setFechaFinViaje(fechaFinViaje);
            itinerarioSeleccionado.setCosto(costo);
            itinerarioSeleccionado.setEstado(estado);

            operacionExitosa = itinerarioDAO.actualizarItinerario(itinerarioSeleccionado);
            if (operacionExitosa) {
                JOptionPane.showMessageDialog(this, "Itinerario actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el itinerario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // 4. Cerrar el diálogo y refrescar tabla si la operación fue exitosa
        if (operacionExitosa) {
            if (padreGestionViajeItinerario != null) {
                padreGestionViajeItinerario.cargarItinerariosActivos();// Llama al método para refrescar la tabla en el padre
            }
            this.dispose(); // Cierra el formulario
        }
    }//GEN-LAST:event_btnCrearItinerarioActionPerformed

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
    private com.toedter.calendar.JDateChooser DateChooserFin;
    private com.toedter.calendar.JDateChooser DateChooserInicio;
    private javax.swing.JToggleButton ToggleButtonActivo;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnCrearItinerario;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JComboBox<Cliente> cmbClientes;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtFechaCreacion;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
