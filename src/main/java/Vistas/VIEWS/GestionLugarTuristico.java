package Vistas.VIEWS;

import Modelo.LugarTuristico;
import Modelo.Usuario;
import dao.DestinoDAO;
import dao.LugarTuristicoDAO;
import dao.ProveedorDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GestionLugarTuristico extends javax.swing.JFrame {
 
    private Usuario usuarioActual;
    private DestinoDAO destinoDAO;
    private LugarTuristicoDAO lugarTuristicoDAO;
    private ProveedorDAO proveedorDAO;
    private DefaultTableModel modeloTabla;
        
    public GestionLugarTuristico(Usuario usuarioLogeado) {
        this.usuarioActual = usuarioLogeado;
        initComponents();
        this.setLocationRelativeTo(null);
                
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            setTitle("Gestión de Lugares Turísticos - Usuario: " + usuarioActual.getNombreCompleto() + " (" + usuarioActual.getRol().getNombreRol() + ")");
        } else {
            setTitle("Gestión de Lugares Turísticos");
        }
                
        this.destinoDAO = new DestinoDAO(); 
        this.proveedorDAO = new ProveedorDAO(); 
        this.lugarTuristicoDAO = new LugarTuristicoDAO(destinoDAO, proveedorDAO);
        
        
        configurarTablaLugaresTuristicos();

        cargarLugaresTuristicosActivos();
    }
    
 
    public void configurarTablaLugaresTuristicos() {
    
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas de la tabla no sean editables
            }
        };
        // Define las columnas para la tabla de Lugares Turísticos
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Destino");
        modeloTabla.addColumn("Proveedor");
        modeloTabla.addColumn("Estado");

        if (tbLugaresTuristicos != null) {
            tbLugaresTuristicos.setModel(modeloTabla);
            tbLugaresTuristicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo permitir una fila seleccionada
            tbLugaresTuristicos.setDefaultEditor(Object.class, null); // Evitar que se puedan editar las celdas
        } else {
        }
    }

    
    public void cargarLugaresTuristicosActivos() {

        if (modeloTabla == null) {
            return;
        }
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        List<LugarTuristico> lugares = lugarTuristicoDAO.obtenerTodosLosLugaresTuristicosActivos();

        if (lugares.isEmpty()) {
            System.out.println("DEBUG: No hay lugares turísticos activos para mostrar.");
        }

        for (LugarTuristico lt : lugares) {
            // Para mostrar Destino y Proveedor, accedemos a sus nombres.
            String nombreDestino = (lt.getDestino() != null) ? lt.getDestino().getNombreDestino() : "N/A"; // Asumo getNombre() en Destino
            String nombreProveedor = (lt.getProveedor() != null) ? lt.getProveedor().getNombreProveedor() : "N/A"; // Asumo getNombre() en Proveedor

            modeloTabla.addRow(new Object[]{
                lt.getIdLugarTuristico(),
                lt.getNombreLugar(),
                lt.getTipoLugar(),
                lt.getDescripcion(),
                nombreDestino,
                nombreProveedor,
                lt.getEstado() ? "Activo" : "Inactivo" // Muestra "Activo" o "Inactivo"
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnConfiguracion1 = new javax.swing.JButton();
        btnHome1 = new javax.swing.JButton();
        btnClientes1 = new javax.swing.JButton();
        btnReservas1 = new javax.swing.JButton();
        btnProveedores1 = new javax.swing.JButton();
        btnReportes1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLugaresTuristicos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(0, 46, 121));

        jButton8.setBackground(new java.awt.Color(179, 23, 23));
        jButton8.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Cerrar sesión");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(8, 8, 100));
        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 0, 890, 90);

        btnConfiguracion1.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnConfiguracion1.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion1.setText("CONFIGURACIÓN");
        btnConfiguracion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracion1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnConfiguracion1);
        btnConfiguracion1.setBounds(0, 340, 200, 50);

        btnHome1.setBackground(new java.awt.Color(0, 46, 121));
        btnHome1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHome1.setForeground(new java.awt.Color(255, 255, 255));
        btnHome1.setText("HOME");
        btnHome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome1);
        btnHome1.setBounds(0, 90, 200, 50);

        btnClientes1.setBackground(new java.awt.Color(0, 46, 121));
        btnClientes1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClientes1.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes1.setText("CLIENTES");
        btnClientes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientes1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnClientes1);
        btnClientes1.setBounds(0, 140, 200, 50);

        btnReservas1.setBackground(new java.awt.Color(0, 46, 121));
        btnReservas1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReservas1.setForeground(new java.awt.Color(255, 255, 255));
        btnReservas1.setText("RESERVAS");
        btnReservas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservas1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnReservas1);
        btnReservas1.setBounds(0, 190, 200, 50);

        btnProveedores1.setBackground(new java.awt.Color(0, 46, 121));
        btnProveedores1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnProveedores1.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedores1.setText("PROVEEDORES");
        btnProveedores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedores1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnProveedores1);
        btnProveedores1.setBounds(0, 240, 200, 50);

        btnReportes1.setBackground(new java.awt.Color(0, 46, 121));
        btnReportes1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReportes1.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes1.setText("REPORTES");
        btnReportes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportes1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnReportes1);
        btnReportes1.setBounds(0, 290, 200, 50);

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

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 380, 200, 170);

        btnVolver.setBackground(new java.awt.Color(102, 102, 102));
        btnVolver.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver);
        btnVolver.setBounds(740, 120, 89, 34);

        btnEliminar.setBackground(new java.awt.Color(179, 23, 23));
        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(390, 360, 104, 40);

        btnEditar.setBackground(new java.awt.Color(255, 171, 33));
        btnEditar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar);
        btnEditar.setBounds(500, 360, 100, 38);

        btnCrear.setBackground(new java.awt.Color(40, 167, 69));
        btnCrear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear);
        btnCrear.setBounds(630, 360, 99, 38);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("LUGAR TURISTICO");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(220, 120, 191, 25);

        tbLugaresTuristicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_LugarTuristico", "Nombre del Lugar", "TIpo", "Descripcion", "ID_Destino", "ID_Proveedor", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tbLugaresTuristicos);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(310, 190, 476, 135);

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 414, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
      
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();
            // Asumo que "admin" y "usuario" pueden agregar, ajusta según tus reglas de negocio
            if ("admin".equalsIgnoreCase(nombreRol) || "proveedor".equalsIgnoreCase(nombreRol)) {
                // Constructor para agregar (sin LugarTuristico)
                FormularioLugarTuristico formulario = new FormularioLugarTuristico(this, this);
                formulario.setVisible(true);
                // Después de cerrar el formulario, recarga la tabla para ver los cambios
                cargarLugaresTuristicosActivos();
            } else {
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para agregar lugares turísticos.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = tbLugaresTuristicos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            if (usuarioActual != null && usuarioActual.getRol() != null) {
                String nombreRol = usuarioActual.getRol().getNombreRol();
                // Asumo que "admin" y "usuario" pueden modificar, ajusta según tus reglas de negocio
                if ("admin".equalsIgnoreCase(nombreRol) || "proveedor".equalsIgnoreCase(nombreRol)) {
                    int idLugarTuristico = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
                    LugarTuristico lugarParaEditar = lugarTuristicoDAO.obtenerLugarTuristicoPorId(idLugarTuristico);

                    if (lugarParaEditar != null) {
                        // Constructor para modificar (con LugarTuristico existente)
                        FormularioLugarTuristico formulario = new FormularioLugarTuristico(this, this,lugarParaEditar);
                        formulario.setVisible(true);
                        cargarLugaresTuristicosActivos(); // Recarga la tabla después de la edición
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo cargar los datos del lugar turístico para editar. Podría no existir o hubo un error de acceso a datos.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para modificar lugares turísticos.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un lugar turístico de la tabla para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
         int filaSeleccionada = tbLugaresTuristicos.getSelectedRow();
        if (filaSeleccionada >= 0) {
             if (usuarioActual != null && usuarioActual.getRol() != null) {
                String nombreRol = usuarioActual.getRol().getNombreRol();
                // Solo admin puede desactivar, ajusta según tus reglas de negocio
                if ("admin".equalsIgnoreCase(nombreRol)|| "proveedor".equalsIgnoreCase(nombreRol)) {
                    int idLugarTuristico = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
                    String nombreLugar = (String) modeloTabla.getValueAt(filaSeleccionada, 1); // Asumiendo que el nombre está en la columna 1
                 
                    int confirmacion = JOptionPane.showConfirmDialog(this,
                            "¿Está seguro de que desea DESACTIVAR el lugar turístico '" + nombreLugar + "' (ID: " + idLugarTuristico + ")?",
                            "Confirmar Desactivación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        boolean exito = lugarTuristicoDAO.desactivarLugarTuristico(idLugarTuristico);
                        if (exito) {
                            JOptionPane.showMessageDialog(this, "Lugar turístico desactivado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            cargarLugaresTuristicosActivos(); // Refrescar la tabla
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al desactivar el lugar turístico.", "Error de Desactivación", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Acceso denegado. Solo administradores pueden desactivar lugares turísticos.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un lugar turístico de la tabla para desactivar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:        
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea volver?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new GestionProveedores(usuarioActual).setVisible(true);
        }
    }//GEN-LAST:event_btnVolverActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnConfiguracion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracion1ActionPerformed

    private void btnHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome1ActionPerformed

    }//GEN-LAST:event_btnHome1ActionPerformed

    private void btnClientes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientes1ActionPerformed

    private void btnReservas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservas1ActionPerformed

    private void btnProveedores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedores1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedores1ActionPerformed

    private void btnReportes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportes1ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes1;
    private javax.swing.JButton btnConfiguracion1;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHome1;
    private javax.swing.JButton btnProveedores1;
    private javax.swing.JButton btnReportes1;
    private javax.swing.JButton btnReservas1;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbLugaresTuristicos;
    // End of variables declaration//GEN-END:variables
}
