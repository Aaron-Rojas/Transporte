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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbLugaresTuristicos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel1.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel1.setText("Lugar Turisitico");

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(7, 7, 7)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
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

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbLugaresTuristicos;
    // End of variables declaration//GEN-END:variables
}
