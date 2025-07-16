package Vistas.VIEWS;

import Modelo.Hotel;
import Modelo.Usuario;
import dao.DestinoDAO;
import dao.HotelDAO;
import dao.ProveedorDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GestionHotel extends javax.swing.JFrame {
    
  
    private Usuario usuarioActual;
    private DestinoDAO destinoDAO;
    private ProveedorDAO proveedorDAO;
    private DefaultTableModel modeloTabla;
    private HotelDAO hotelDAO;
    
    public GestionHotel(Usuario usuarioLogeado) {
    this.usuarioActual= usuarioLogeado;
    initComponents();
       
    this.setLocationRelativeTo(null);
                
        
    if (usuarioActual != null && usuarioActual.getRol() != null) {
            setTitle("Gestión de Lugares Turísticos - Usuario: " + usuarioActual.getNombreCompleto() + " (" + usuarioActual.getRol().getNombreRol() + ")");
    } else {
        setTitle("Gestión de Lugares Turísticos");
    }
    
    this.destinoDAO = new DestinoDAO();
    this.proveedorDAO = new ProveedorDAO();
    this.hotelDAO = new HotelDAO(destinoDAO, proveedorDAO);
    
     
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
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Categoria");
        modeloTabla.addColumn("Destino");
        modeloTabla.addColumn("Proveedor");
        modeloTabla.addColumn("Estado");

        if (tbHotel != null) {
            tbHotel.setModel(modeloTabla);
            tbHotel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo permitir una fila seleccionada
            tbHotel.setDefaultEditor(Object.class, null); // Evitar que se puedan editar las celdas
        } else {
        }
    }

    
    public void cargarLugaresTuristicosActivos() {

        if (modeloTabla == null) {
            return;
        }
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        List<Hotel> hotel = hotelDAO.obtenerTodosLosHotelesActivos();

        if (hotel.isEmpty()) {
            System.out.println("DEBUG: No hay lugares turísticos activos para mostrar.");
        }

        for (Hotel lt : hotel) {
            // Para mostrar Destino y Proveedor, accedemos a sus nombres.
            String nombreDestino = (lt.getDestino() != null) ? lt.getDestino().getNombreDestino() : "N/A"; // Asumo getNombre() en Destino
            String nombreProveedor = (lt.getProveedor() != null) ? lt.getProveedor().getNombreProveedor() : "N/A"; // Asumo getNombre() en Proveedor

            modeloTabla.addRow(new Object[]{
                lt.getIdHotel(),
                lt.getNombreHotel(),
                lt.getDireccion(),
                lt.getCategoria(),
                nombreDestino,
                nombreProveedor,
                lt.isEstado() ? "Activo": "Inactivo"
            });
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHotel = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        btnConfiguracion.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setText(" CONFIGURACIÓN");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        jPanel4.add(btnConfiguracion);
        btnConfiguracion.setBounds(-20, 340, 210, 50);

        btnHome.setBackground(new java.awt.Color(0, 46, 121));
        btnHome.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText(" HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel4.add(btnHome);
        btnHome.setBounds(-20, 90, 210, 50);

        btnClientes.setBackground(new java.awt.Color(0, 46, 121));
        btnClientes.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setText(" CLIENTES");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel4.add(btnClientes);
        btnClientes.setBounds(-20, 140, 210, 50);

        btnReservas.setBackground(new java.awt.Color(0, 46, 121));
        btnReservas.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnReservas.setForeground(new java.awt.Color(255, 255, 255));
        btnReservas.setText(" RESERVAS");
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });
        jPanel4.add(btnReservas);
        btnReservas.setBounds(-20, 190, 210, 50);

        btnProveedores.setBackground(new java.awt.Color(0, 46, 121));
        btnProveedores.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedores.setText(" PROVEEDORES");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jPanel4.add(btnProveedores);
        btnProveedores.setBounds(-20, 240, 210, 50);

        btnReportes.setBackground(new java.awt.Color(0, 46, 121));
        btnReportes.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText(" REPORTES");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jPanel4.add(btnReportes);
        btnReportes.setBounds(-20, 290, 210, 50);

        jTextField1.setBackground(new java.awt.Color(0, 46, 121));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField1);
        jTextField1.setBounds(-20, 90, 210, 460);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("GESTIÓN DE HOTEL");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(240, 130, 170, 20);

        btnEditar.setBackground(new java.awt.Color(0, 123, 255));
        btnEditar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEditar);
        btnEditar.setBounds(460, 420, 130, 40);

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

        jLabel2.setBackground(new java.awt.Color(8, 8, 100));
        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel2);
        jPanel2.setBounds(0, 0, 890, 90);

        tbHotel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbHotel);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(222, 160, 590, 240);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar);
        btnEliminar.setBounds(290, 430, 100, 30);

        btnGuardar.setText("Crear");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar);
        btnGuardar.setBounds(650, 420, 110, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
    int filaSeleccionada = tbHotel.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un hotel para editar.");
        return;
    }

    // Obtener el ID del hotel desde la tabla (columna 0)
    int idHotel = (int) tbHotel.getValueAt(filaSeleccionada, 0);
    
    Hotel hotel = hotelDAO.obtenerHotelPorId(idHotel);
    if (hotel != null) {
        FormularioHotel formulario = new FormularioHotel(this,this, hotel);// Para refrescar la tabla después de editar
        formulario.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "No se encontro el hotel.");
    }
    
    }//GEN-LAST:event_btnEditarActionPerformed

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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.dispose(); // Cierra la ventana actual
            new Login().setVisible(true); // Abre la ventana de login
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
                 
        int filaSeleccionada = tbHotel.getSelectedRow();
        if (filaSeleccionada >= 0) {
             if (usuarioActual != null && usuarioActual.getRol() != null) {
                String nombreRol = usuarioActual.getRol().getNombreRol();
                // Solo admin puede desactivar, ajusta según tus reglas de negocio
                if ("admin".equalsIgnoreCase(nombreRol)|| "proveedor".equalsIgnoreCase(nombreRol)) {
                    int idHotel = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
                    String nombreLugar = (String) modeloTabla.getValueAt(filaSeleccionada, 1); // Asumiendo que el nombre está en la columna 1
                 
                    int confirmacion = JOptionPane.showConfirmDialog(this,
                            "¿Está seguro de que desea DESACTIVAR el lugar turístico '" + nombreLugar + "' (ID: " + idHotel + ")?",
                            "Confirmar Desactivación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        boolean exito = hotelDAO.eliminarHotel(idHotel);
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
         if (usuarioActual != null && usuarioActual.getRol() != null) {
            String nombreRol = usuarioActual.getRol().getNombreRol();
            // Asumo que "admin" y "usuario" pueden agregar, ajusta según tus reglas de negocio
            if ("admin".equalsIgnoreCase(nombreRol) || "proveedor".equalsIgnoreCase(nombreRol)) {
                // Constructor para agregar (sin LugarTuristico)
                FormularioHotel formulario = new FormularioHotel(this, this);
                formulario.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(this, "Acceso denegado. No tienes permisos para agregar lugares turísticos.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error de seguridad. No se pudo verificar su rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbHotel;
    // End of variables declaration//GEN-END:variables
}
