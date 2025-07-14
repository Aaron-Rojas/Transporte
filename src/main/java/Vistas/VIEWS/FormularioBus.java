/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.VIEWS;

import Modelo.Bus;
import dao.BusDAO;
import Controlador.NavegacionController;
import Modelo.Usuario;
import Vistas.VIEWS.ListaBuses;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormularioBus extends javax.swing.JFrame {

    private ListaBuses listaBuses;
    private Usuario usuarioActual;
    private boolean esNuevoBus = true;
    private int originalIdBus;
    Usuario usuarioLogeado;

    public FormularioBus(Usuario usuarioLogeado) {
        this.listaBuses = listaBuses;
        this.usuarioActual = usuarioLogeado;
        initComponents();

        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();
            setTitle("Sistema para el usuario " + usuarioActual.getNombreCompleto());
        } else {
            setTitle("Sistema de User");
        }
        
        
// Inicializar para nuevo bus
        inicializarParaNuevoBus();
    }
    
    private void addToggleButtonActionListener() {
    ToggleButtonEstado.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            boolean estaActivo = ToggleButtonEstado.isSelected();
            updateToggleButtonText(estaActivo);
        }
    });
}



    public FormularioBus(Usuario usuarioLogeado, ListaBuses listaBuses) {
        this(usuarioLogeado); // Llama al constructor anterior
        this.listaBuses = listaBuses; // Asigna la referencia a ListaBuses
    }

    
    
    private void inicializarParaNuevoBus() {
    esNuevoBus = true;
    originalIdBus = 0;
    txtIdBus.setText(""); // Auto-generado
    txtPlaca.setText("");
    txtCapacidad.setText("");
    ToggleButtonEstado.setSelected(true); // Activo por defecto
    updateToggleButtonText(true);
}

// Método para actualizar texto del toggle
private void updateToggleButtonText(boolean isActive) {
    ToggleButtonEstado.setText(isActive ? "Operativo" : "Inactivo");
}

    // Method to set data when editing an existing bus
    public void setDatosBus(int idBus, String placa, int capacidad, String estado) {
        esNuevoBus = false;
        originalIdBus = idBus; // Store the original ID
        txtIdBus.setText(String.valueOf(idBus));
        txtIdBus.setEnabled(true); // Keep ID field enabled for modification
        txtPlaca.setText(placa);
        txtCapacidad.setText(String.valueOf(capacidad));
        btnGuardarBus.setText("Actualizar Bus");
    }

    // Method to get data from the form
    public Bus getDatosBus() throws NumberFormatException {
        Bus bus = new Bus();

        // Get ID from the text field for both new and existing buses
        String idText = txtIdBus.getText().trim();
        if (idText.isEmpty()) {
            throw new NumberFormatException("El ID del bus no puede estar vacío.");
        }
        try {
            bus.setIdBus(Integer.parseInt(idText));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número entero válido",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }

        bus.setPlaca(txtPlaca.getText().trim());

        try {
            bus.setCapacidad(Integer.parseInt(txtCapacidad.getText().trim()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "La capacidad debe ser un número entero válido",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
        if (esNuevoBus) {
            bus.setEstado(true); // Un nuevo bus se crea como activo
        } else {
            Bus busActual = new BusDAO().obtenerBusPorId(bus.getIdBus());
            if (busActual != null) {
                bus.setEstado(busActual.isEstado());
            } else {
                bus.setEstado(false); // Default to false if not found (shouldn't happen for existing bus)
            }
        }

        return bus;
    }

    private boolean validarCampos() {
        String idText = txtIdBus.getText().trim();
        if (idText.isEmpty()) {
            mostrarError("El ID del bus es obligatorio");
            txtIdBus.requestFocus();
            return false;
        }

        try {
            int id = Integer.parseInt(idText);
            if (id <= 0) {
                mostrarError("El ID debe ser un número positivo");
                txtIdBus.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser un número válido");
            txtIdBus.requestFocus();
            return false;
        }

        String placa = txtPlaca.getText().trim();
        if (placa.isEmpty()) {
            mostrarError("La placa es obligatoria");
            txtPlaca.requestFocus();
            return false;
        }
        if (!placa.matches("^[A-Za-z0-9-]{3,10}$")) {
            mostrarError("Formato de placa inválido. Use letras, números y guiones (ej: ABC-123). Longitud entre 3 y 10 caracteres.");
            txtPlaca.requestFocus();
            return false;
        }

        String capacidadText = txtCapacidad.getText().trim();
        if (capacidadText.isEmpty()) {
            mostrarError("La capacidad es obligatoria");
            txtCapacidad.requestFocus();
            return false;
        }
        try {
            int capacidad = Integer.parseInt(capacidadText);
            if (capacidad <= 0 || capacidad > 100) {
                mostrarError("La capacidad debe estar entre 1 y 100 pasajeros");
                txtCapacidad.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("La capacidad debe ser un número válido");
            txtCapacidad.requestFocus();
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Método para establecer la referencia
    public void setListaBuses(ListaBuses listaBuses) {
        this.listaBuses = listaBuses;
    }

    private void volverAListaBuses() {
        if (listaBuses != null) {
            listaBuses.actualizarTabla(); // Call the method to refresh the table in ListaBuses
            listaBuses.setVisible(true); // Make the ListaBuses window visible again
        }
        this.dispose(); // Close the current FormularioBus window
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdBus = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnGuardarBus = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        ToggleButtonEstado = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(8, 8, 100));

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
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Expreso");

        jLabel2.setBackground(new java.awt.Color(8, 8, 100));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Los Chankas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(318, 318, 318)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
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

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Formulario de Buses");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(250, 130, 220, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel4.setText("Estado:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(260, 320, 120, 18);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setText("Nro de Bus:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(260, 190, 100, 18);

        txtIdBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBusActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdBus);
        txtIdBus.setBounds(380, 190, 140, 22);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel7.setText("Nro de placa:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(260, 230, 120, 18);
        jPanel1.add(txtCapacidad);
        txtCapacidad.setBounds(380, 280, 140, 22);

        btnCancelar.setBackground(new java.awt.Color(102, 153, 255));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setText("cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(540, 380, 180, 30);

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

        btnGuardarBus.setBackground(new java.awt.Color(102, 153, 255));
        btnGuardarBus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardarBus.setText("Guardar Bus");
        btnGuardarBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarBusActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarBus);
        btnGuardarBus.setBounds(290, 380, 180, 30);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel6.setText("Capacidad:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(260, 280, 120, 20);
        jPanel1.add(txtPlaca);
        txtPlaca.setBounds(380, 230, 140, 22);

        ToggleButtonEstado.setText("Activo");
        ToggleButtonEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToggleButtonEstadoActionPerformed(evt);
            }
        });
        jPanel1.add(ToggleButtonEstado);
        ToggleButtonEstado.setBounds(380, 320, 140, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Confirmar si realmente quiere cancelar
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea cancelar? Los datos no guardados se perderán.",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION
        );

//    if (confirmacion == JOptionPane.YES_OPTION) {
//        ListaBuses lista = new ListaBuses();
//        lista.setVisible(true);
//        this.dispose();
//    }

    }//GEN-LAST:event_btnCancelarActionPerformed

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

    private void btnGuardarBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarBusActionPerformed
        if (!validarCampos()) return;
    
    try {
        Bus bus = new Bus();
        bus.setIdBus(Integer.parseInt(txtIdBus.getText().trim()));
        bus.setPlaca(txtPlaca.getText().trim());
        bus.setCapacidad(Integer.parseInt(txtCapacidad.getText().trim()));
        bus.setEstado(ToggleButtonEstado.isSelected());
        
        BusDAO busDAO = new BusDAO();
        boolean exito = esNuevoBus ? busDAO.guardarBus(bus) : busDAO.actualizarBus(bus);
        
        if (exito) {
            JOptionPane.showMessageDialog(this, "Operación exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            volverAListaBuses();
        } else {
            JOptionPane.showMessageDialog(this, "Error en la operación", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnGuardarBusActionPerformed

    private void txtIdBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBusActionPerformed

    private void ToggleButtonEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToggleButtonEstadoActionPerformed
        // Asegúrate de que el bus sea existente (no un nuevo bus)
        if (esNuevoBus) {
            mostrarError("Primero debe guardar el bus para poder cambiar su estado.");
            return;
        }

        int idBus;
        try {
            idBus = Integer.parseInt(txtIdBus.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("No se pudo obtener el ID del bus. Asegúrese de que sea un número válido.");
            return;
        }

        BusDAO busDAO = new BusDAO();
        Bus busActual = busDAO.obtenerBusPorId(idBus);

        if (busActual == null) {
            mostrarError("No se encontró el bus con ID: " + idBus + " en la base de datos.");
            return;
        }

        boolean estadoActual = busActual.isEstado();
        boolean nuevoEstado = !estadoActual; // Toggle (invertir) el estado actual
        String mensajeConfirmacion = "¿Está seguro de que desea " + (nuevoEstado ? "ACTIVAR" : "DESACTIVAR") + " el bus?";
        String operacion = nuevoEstado ? "activar" : "desactivar";

        int confirmacion = JOptionPane.showConfirmDialog(this, mensajeConfirmacion, "Confirmar Cambio de Estado", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean exito = busDAO.cambiarEstadoBus(idBus, nuevoEstado);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Bus " + operacion + " exitosamente.");
                // Actualizar el texto del botón y la tabla en ListaBuses
                ToggleButtonEstado.setText("Estado: " + (nuevoEstado ? "Activo" : "Inactivo"));
                if (listaBuses != null) {
                    listaBuses.actualizarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo " + operacion + " el bus.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_ToggleButtonEstadoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (listaBuses != null) {
            listaBuses.actualizarTabla();
            listaBuses.setVisible(true);
        } else {
            // Si no hay referencia, crea una nueva instancia de ListaBuses.
            // Asegúrate de que ListaBuses también tenga un constructor que acepte Usuario
            new ListaBuses(usuarioActual).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ToggleButtonEstado;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnGuardarBus;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtIdBus;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
