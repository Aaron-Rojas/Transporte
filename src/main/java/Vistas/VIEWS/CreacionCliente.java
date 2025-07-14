
package Vistas.VIEWS;

import Conexión.Conexión;
import javax.swing.JOptionPane;
import Modelo.Cliente;
import dao.ClienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JFrame;


public class CreacionCliente extends javax.swing.JDialog {

    private ClienteDAO clienteDAO;
    private GestionClientes padreGestionClientes;
    private Cliente clienteAEditar;
    
    
    public CreacionCliente(JFrame padre, GestionClientes gestionClientesPadre) {
    super(padre,true);
    initComponents();
    
    this.padreGestionClientes = gestionClientesPadre;
    this.clienteDAO= new ClienteDAO();
    this.setLocationRelativeTo(padre);
    jLabel3.setText("Crear Cliente");
    btn_crearCliente.setText("Crear Cliente");
    addToggleButtonActionListener();

    }
    
    public CreacionCliente(JFrame padre, GestionClientes gestionClientesPadre, Cliente cliente) {
        this(padre,gestionClientesPadre); // Llama al primer constructor para inicializar componentes, DAO, etc.
        this.clienteAEditar = cliente; // Asigna el cliente que se va a editar
        
        // Rellenar los campos con los datos del cliente
        txt_dni.setText(cliente.getDni());
        txt_nombre.setText(cliente.getNombreCompleto());
        txt_telefono.setText(cliente.getTelefono());
        txt_email.setText(cliente.getEmail());
        txt_preferencias.setText(cliente.getPreferencias());

        // Cambiar el texto del botón y el título para indicar EDICIÓN
        jLabel3.setText("Editar Cliente");
        btn_crearCliente.setText("Guardar Cambios");
         
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

                    if (clienteAEditar != null) {
                        clienteAEditar.setActivo(estaActivo);
                        // If you want to update the DB immediately on toggle
                        clienteDAO.actualizarCliente(clienteAEditar);
                        JOptionPane.showMessageDialog(null, "Estado del cliente actualizado a: " + (estaActivo ? "Activo" : "Inactivo"));
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
        ToggleButtonActivo = new javax.swing.JToggleButton();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_preferencias = new javax.swing.JTextField();
        txt_dni = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        btn_crearCliente = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        ToggleButtonActivo.setText("Activo");
        ToggleButtonActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToggleButtonActivoActionPerformed(evt);
            }
        });
        jPanel1.add(ToggleButtonActivo);
        ToggleButtonActivo.setBounds(560, 410, 130, 23);

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
        btnConfiguracion.setBounds(0, 330, 200, 50);

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
        btnHome.setBounds(0, 80, 200, 50);

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
        btnClientes.setBounds(0, 130, 200, 50);

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
        btnReservas.setBounds(0, 180, 200, 50);

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
        btnProveedores.setBounds(0, 230, 200, 50);

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
        btnReportes.setBounds(0, 280, 200, 50);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel3.setText("Crear clientes");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(220, 130, 260, 40);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Nombres:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(250, 190, 120, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("DNI/Pasaporte");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(570, 180, 140, 40);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel6.setText("Estado:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(490, 400, 120, 27);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel7.setText("Telefono:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(570, 280, 80, 27);

        txt_telefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_telefono);
        txt_telefono.setBounds(570, 330, 240, 30);

        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_nombre);
        txt_nombre.setBounds(250, 230, 240, 30);

        txt_preferencias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_preferencias);
        txt_preferencias.setBounds(320, 400, 150, 30);

        txt_dni.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_dni);
        txt_dni.setBounds(570, 230, 240, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel8.setText("E-mail:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(250, 280, 59, 27);

        txt_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 171, 33), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 171, 33), new java.awt.Color(255, 171, 33)));
        jPanel1.add(txt_email);
        txt_email.setBounds(250, 330, 240, 30);

        btn_crearCliente.setBackground(new java.awt.Color(255, 171, 33));
        btn_crearCliente.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btn_crearCliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_crearCliente.setText("Crear cliente");
        btn_crearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_crearCliente);
        btn_crearCliente.setBounds(710, 410, 170, 40);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel9.setText("Preferencia:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(210, 400, 120, 27);

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
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 890, 80);

        jTextField1.setBackground(new java.awt.Color(0, 46, 121));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(0, 340, 200, 210);

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
    

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
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

    private void btn_crearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearClienteActionPerformed
        // TODO add your handling code here:
        // 1. Obtener datos del formulario
        String dni = txt_dni.getText();
        String nombreCompleto = txt_nombre.getText();
        String telefono = txt_telefono.getText();
        String email = txt_email.getText();
        String preferencias = txt_preferencias.getText();

        // 2. Validaciones básicas
        if (dni.isEmpty() || nombreCompleto.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete los campos obligatorios (DNI, Nombre, Teléfono).", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean exitoOperacion;

        // 3. Lógica para CREAR o ACTUALIZAR <-- Lógica diferenciadora
        if (clienteAEditar == null) { // Si clienteAEditar es null, estamos en modo CREACIÓN
            Cliente nuevoCliente = new Cliente(dni, nombreCompleto, telefono, email, preferencias); // El ID es 0 o un valor placeholder para un nuevo cliente
            exitoOperacion = clienteDAO.guardarCliente(nuevoCliente);
            if (exitoOperacion) {
                JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el cliente. Verifique la base de datos.", "Error de Guardado", JOptionPane.ERROR_MESSAGE);
            }
        } else { // Si clienteAEditar NO es null, estamos en modo EDICIÓN
            // Actualizar el objeto clienteAEditar con los datos del formulario
            // El ID_Cliente ya está en clienteAEditar desde que se pasó al constructor
            clienteAEditar.setDni(dni);
            clienteAEditar.setNombreCompleto(nombreCompleto);
            clienteAEditar.setTelefono(telefono);
            clienteAEditar.setEmail(email);
            clienteAEditar.setPreferencias(preferencias);

            exitoOperacion = clienteDAO.actualizarCliente(clienteAEditar); // Llama al método de actualización del DAO
            if (exitoOperacion) {
                JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el cliente. Verifique la base de datos.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
            }
        }

        // 4. Si la operación fue exitosa, cerrar el formulario y refrescar la tabla padre
        if (exitoOperacion) {
            this.dispose(); // Cierra el formulario actual
            if (padreGestionClientes != null) {
                padreGestionClientes.cargarClientesEnTabla(); // Refresca la tabla en GestionClientes
            }
        }
    }

// Método para obtener un solo cliente por ID (crucial para la edición)
public Cliente obtenerClientePorId(int idCliente) {
    Cliente cliente = null;
    String sql = "SELECT ID_Cliente, DNI_Documento, NombreCompleto, Telefono, Email, Preferencias FROM cliente WHERE ID_Cliente = ?";
    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idCliente);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) { // Si encuentra un registro
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_Cliente"));
                cliente.setDni(rs.getString("DNI_Documento"));
                cliente.setNombreCompleto(rs.getString("NombreCompleto"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setPreferencias(rs.getString("Preferencias"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener cliente por ID: " + e.getMessage());
        e.printStackTrace(); // Es bueno tener un stack trace para depuración
    }
    return cliente;
}    
    

// Método para actualizar un cliente existente
public boolean actualizarCliente(Cliente cliente) {
    String sql = "UPDATE cliente SET DNI_Documento=?, NombreCompleto=?, Telefono=?, Email=?, Preferencias=? WHERE ID_Cliente=?";
    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cliente.getDni());
        stmt.setString(2, cliente.getNombreCompleto());
        stmt.setString(3, cliente.getTelefono());
        stmt.setString(4, cliente.getEmail());
        stmt.setString(5, cliente.getPreferencias());
        stmt.setInt(6, cliente.getIdCliente()); // El ID es fundamental para WHERE

        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException e) {
        System.err.println("Error al actualizar cliente: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}


// Método para eliminar un cliente por su ID
public boolean eliminarCliente(int idCliente) {
    String sql = "DELETE FROM cliente WHERE ID_Cliente=?";
    try (Connection conn = Conexión.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idCliente);

        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException e) {
        System.err.println("Error al eliminar cliente: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}



  /* Variables declaration - do not modify                     
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btn_crearCliente;
    private javax.swing.JButton btn_guradarCliente;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txt_dni;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_preferencias;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration                   

    }//GEN-LAST:event_btn_crearClienteActionPerformed
*/
    private void ToggleButtonActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToggleButtonActivoActionPerformed
        boolean estaActivo = ToggleButtonActivo.isSelected();
        // Update the button text immediately when the state changes
        updateToggleButtonText(estaActivo); // Call the helper method

        if (clienteAEditar != null) {
            clienteAEditar.setActivo(estaActivo);
            // Actualizar el estado del cliente en la base de datos
            clienteDAO.actualizarCliente(clienteAEditar); // O el método correspondiente
            JOptionPane.showMessageDialog(null, "Estado del cliente actualizado a: " + (estaActivo ? "Activo" : "Inactivo"));
        } else {
            // If it's a new client, you might just store the state
            // in a temporary client object or a variable until the "Crear Cliente" button is pressed.
            // For example: this.nuevoClienteActivoEstado = estaActivo;
            JOptionPane.showMessageDialog(null, "El estado de 'Activo' para el nuevo cliente es: " + (estaActivo ? "Activo" : "Inactivo"));
        }
    }//GEN-LAST:event_ToggleButtonActivoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ToggleButtonActivo;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btn_crearCliente;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txt_dni;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_preferencias;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
