
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbClientes = new javax.swing.JComboBox<Cliente>();
        jLabel3 = new javax.swing.JLabel();
        DateChooserInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DateChooserFin = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        ToggleButtonActivo = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();
        btnCrearItinerario = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Formulario de Itinerario");

        jLabel2.setText("Clientes");

        cmbClientes.setModel(new javax.swing.DefaultComboBoxModel<Cliente>());

        jLabel3.setText("Fecha Inicio de Viaje: ");

        jLabel4.setText("Usuario");

        txtUsuario.setText("AUTOMATICO");

        jLabel5.setText("Fecha Fin de Viaje:");

        jLabel6.setText("Costo");

        ToggleButtonActivo.setText("Activo");

        jLabel7.setText("Estado");

        btnCrearItinerario.setText("Crear");
        btnCrearItinerario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearItinerarioActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Fecha de Creación");

        txtFechaCreacion.setText("AUTOMATICO");
        txtFechaCreacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaCreacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(ToggleButtonActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(btnCrearItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(DateChooserInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(DateChooserFin, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DateChooserInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateChooserFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ToggleButtonActivo)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooserFin;
    private com.toedter.calendar.JDateChooser DateChooserInicio;
    private javax.swing.JToggleButton ToggleButtonActivo;
    private javax.swing.JButton btnCrearItinerario;
    private javax.swing.JComboBox<Cliente> cmbClientes;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtFechaCreacion;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
