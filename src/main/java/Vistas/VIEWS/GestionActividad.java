
package Vistas.VIEWS;

import Controlador.NavegacionController;
import Modelo.ActividadTuristica;
import Modelo.Usuario;
import dao.LugarTuristicoDAO;
import dao.ActividadTuristicaDAO;
import dao.DestinoDAO;
import dao.ProveedorDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


public class GestionActividad extends javax.swing.JFrame {
    private DestinoDAO destinoDAO;
    private Usuario usuarioActual;
    private LugarTuristicoDAO lugarTuristicoDAO;
    private ProveedorDAO proveedorDAO;
    private DefaultTableModel modeloTabla;
    private ActividadTuristicaDAO actividadTuristicaDAO;
            
            
    public GestionActividad(Usuario usuarioLogeado) {
        this.usuarioActual = usuarioLogeado;
        
        initComponents();
        this.setLocationRelativeTo(null);
                
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            setTitle("Gestión de Lugares Turísticos - Usuario: " + usuarioActual.getNombreCompleto() + " (" + usuarioActual.getRol().getNombreRol() + ")");
        } else {
            setTitle("Gestión de Lugares Turísticos");
        }
        this.destinoDAO= new DestinoDAO();
        this.proveedorDAO = new ProveedorDAO ();
        this.lugarTuristicoDAO= new LugarTuristicoDAO(destinoDAO, proveedorDAO);
        
           
        this.actividadTuristicaDAO = new ActividadTuristicaDAO(proveedorDAO, lugarTuristicoDAO);

        configurarTablaActivadesTuristicas();
        cargarActividadesTuristicasActivas();

    }

               
   
    public void cargarActividadesTuristicasActivas(){
      if (modeloTabla == null) {
            return;
        }
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        List<ActividadTuristica> actividad = actividadTuristicaDAO.obtenerTodasLasActividadesTuristicasActivas();

        if (actividad.isEmpty()) {
            System.out.println("DEBUG: No hay lugares turísticos activos para mostrar.");
        }

        for (ActividadTuristica lt : actividad) {
            // Para mostrar Destino y Proveedor, accedemos a sus nombres.
            String nombreLugar = (lt.getLugarTuristico()!= null) ? lt.getLugarTuristico().getNombreLugar() : "N/A"; // Asumo getNombre() en Destino
            String nombreProveedor = (lt.getProveedor() != null) ? lt.getProveedor().getNombreProveedor() : "N/A"; // Asumo getNombre() en Proveedor

            modeloTabla.addRow(new Object[]{
                lt.getID_Actividad(),
                lt.getNombreActividad(),
                lt.getDescripcion(),
                lt.getDuracion(),
                nombreLugar,
                nombreProveedor,
                lt.isEstado() ? "Activo" : "Inactivo" // Muestra "Activo" o "Inactivo"
            });
        }
    }
    
        public void configurarTablaActivadesTuristicas(){
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas de la tabla no sean editables
            }};
              
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Duracion");
        modeloTabla.addColumn("Lugar");
        modeloTabla.addColumn("Proveedor");
        modeloTabla.addColumn("Estado");
         
        if(tbActividadTuristica !=null){
            tbActividadTuristica.setModel(modeloTabla);
            tbActividadTuristica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tbActividadTuristica.setDefaultEditor(Object.class, null);
            
    }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbActividadTuristica = new javax.swing.JTable();
        btnCrear = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel3.setText("Itinerario de Alojamiento");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(220, 100, 470, 70);

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

        tbActividadTuristica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Actividad", "Nombre Actividad", "Descripcion", "Duracion", "ID Lugar Turistico", "ID Proveedor", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tbActividadTuristica);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(210, 170, 650, 200);

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear);
        btnCrear.setBounds(240, 390, 130, 50);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar);
        btnEditar.setBounds(450, 390, 150, 50);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(680, 390, 140, 50);

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

        jPanel1.add(jPanel2);
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

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 380, 200, 170);

        btnVolver.setText("Volver");
        jPanel1.add(btnVolver);
        btnVolver.setBounds(730, 110, 120, 30);

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

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();
            // Asumo que "admin" y "usuario" pueden agregar, ajusta según tus reglas de negocio
            if ("admin".equalsIgnoreCase(nombreRol) || "proveedor".equalsIgnoreCase(nombreRol)) {
                // Constructor para agregar (sin LugarTuristico)
                FormularioActividad formulario = new FormularioActividad(this, this);
                formulario.setVisible(true);
                // Después de cerrar el formulario, recarga la tabla para ver los cambios
                cargarActividadesTuristicasActivas();
            } else {
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para agregar lugares turísticos.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = tbActividadTuristica.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener el ID de la actividad de la fila seleccionada (columna 0)
            int idActividad = (int) tbActividadTuristica.getValueAt(filaSeleccionada, 0); 
            ActividadTuristica actividad = actividadTuristicaDAO.obtenerActividadTuristicaPorId(idActividad); // Llamada al método de instancia

            if (actividad != null) {
                FormularioActividad formulario = new FormularioActividad(this,this, actividad);
                formulario.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la actividad para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una actividad para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tbActividadTuristica.getSelectedRow();

        if (filaSeleccionada != -1) {
            int id = (int) tbActividadTuristica.getValueAt(filaSeleccionada, 0); // Columna 0 = ID
            String nombreRol = usuarioActual.getRol().getNombreRol();

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de 'desactivar' esta actividad? (Eliminación lógica)", // Mensaje de confirmación claro
                    "Confirmar Desactivación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = actividadTuristicaDAO.desactivarActividadTuristica(id);
                if (exito) {
                            JOptionPane.showMessageDialog(this, "Lugar turístico desactivado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                    cargarActividadesTuristicasActivas();// Refrescar la tabla
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al desactivar el lugar turístico.", "Error de Desactivación", JOptionPane.ERROR_MESSAGE);
                        } // Recarga la tabla para que ya no aparezca la actividad desactivada
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Error al realizar la eliminación lógica de la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una fila para 'desactivar'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbActividadTuristica;
    // End of variables declaration//GEN-END:variables
}
