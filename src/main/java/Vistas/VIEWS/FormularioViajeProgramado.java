package Vistas.VIEWS;

import Modelo.ViajeProgramado;
import Modelo.Bus;
import Modelo.Destino;
import Vistas.VIEWS.GestionViajeProgramado;
import dao.ViajeProgramadoDAO;
import dao.BusDAO;
import dao.DestinoDAO;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class FormularioViajeProgramado extends javax.swing.JDialog {
    private ViajeProgramado viaje;
    private boolean esModificacion;
    private GestionViajeProgramado padreGestionViajes;

    public FormularioViajeProgramado(java.awt.Frame parent, boolean modal,
                                   GestionViajeProgramado gestionPadre,
                                   ViajeProgramado viaje, boolean esModificacion) {
        super(parent, modal);
        initComponents();
        
        this.padreGestionViajes = gestionPadre;
        this.viaje = (viaje != null) ? viaje : new ViajeProgramado();
        this.esModificacion = esModificacion;

        cargarComboboxes();
        configurarToggleButton();
        
        if (esModificacion) {
            cargarDatosViaje();
            btnCrear.setText("Actualizar");
        }

        setLocationRelativeTo(parent);
    }

    private void cargarComboboxes() {
        try {
            // Cargar buses activos
            BusDAO busDAO = new BusDAO();
            cmbbus.removeAllItems();
            cmbbus.addItem("Seleccione bus");
            busDAO.obtenerBusesActivos().forEach(bus -> {
                cmbbus.addItem(bus.getIdBus() + " - " + bus.getPlaca());
            });

            // Cargar destinos disponibles
            DestinoDAO destinoDAO = new DestinoDAO();
            
            // Origen
            cmborigen.removeAllItems();
            cmborigen.addItem("Seleccione origen");
            destinoDAO.obtenerTodosLosDestinos().forEach(destino -> {
                cmborigen.addItem(destino.getIdDestino() + " - " + destino.getNombreDestino());
            });

            // Destino Final
            cmbdestino.removeAllItems();
            cmbdestino.addItem("Seleccione destino");
            destinoDAO.obtenerTodosLosDestinos().forEach(destino -> {
                cmbdestino.addItem(destino.getIdDestino() + " - " + destino.getNombreDestino());
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar datos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void configurarToggleButton() {
        ToggleEstado.setText(viaje.isActivo() ? "ACTIVO" : "INACTIVO");
        ToggleEstado.setSelected(viaje.isActivo());
        
        ToggleEstado.addActionListener((ActionEvent e) -> {
            boolean seleccionado = ToggleEstado.isSelected();
            ToggleEstado.setText(seleccionado ? "ACTIVO" : "INACTIVO");
        });
    }

    private int extraerIdDeComboBox(javax.swing.JComboBox<String> comboBox) {
        String seleccion = (String) comboBox.getSelectedItem();
        if (seleccion == null || seleccion.startsWith("Seleccione")) {
            return -1;
        }
        return Integer.parseInt(seleccion.split(" - ")[0]);
    }

    private void cargarDatosViaje() {
        txtViaje.setText(String.valueOf(viaje.getIdViajeProgramado()));
        
        // Buscar y seleccionar el bus correspondiente
        for (int i = 0; i < cmbbus.getItemCount(); i++) {
            String item = cmbbus.getItemAt(i);
            if (item.startsWith(viaje.getIdBus() + " - ")) {
                cmbbus.setSelectedIndex(i);
                break;
            }
        }
        
        // Buscar y seleccionar el origen
        for (int i = 0; i < cmborigen.getItemCount(); i++) {
            String item = cmborigen.getItemAt(i);
            if (item.startsWith(viaje.getIdOrigen() + " - ")) {
                cmborigen.setSelectedIndex(i);
                break;
            }
        }
        
        // Buscar y seleccionar el destino
        for (int i = 0; i < cmbdestino.getItemCount(); i++) {
            String item = cmbdestino.getItemAt(i);
            if (item.startsWith(viaje.getIdDestinoFinal() + " - ")) {
                cmbdestino.setSelectedIndex(i);
                break;
            }
        }
        
        // Configurar fechas
        if (viaje.getFechaHoraSalida() != null) {
            dateSalida.setDate(Date.from(viaje.getFechaHoraSalida().atZone(ZoneId.systemDefault()).toInstant()));
        }
        if (viaje.getFechaHoraLlegadaEstimada() != null) {
            dateLlegada.setDate(Date.from(viaje.getFechaHoraLlegadaEstimada().atZone(ZoneId.systemDefault()).toInstant()));
        }
        
        // Configurar estado
        ToggleEstado.setSelected(viaje.isActivo());
        ToggleEstado.setText(viaje.isActivo() ? "ACTIVO" : "INACTIVO");
    }


 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ToggleEstado = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        DateChooserInicio = new com.toedter.calendar.JDateChooser();
        DateChooserFin = new com.toedter.calendar.JDateChooser();
        cmbbus = new javax.swing.JComboBox<>();
        cmborigen = new javax.swing.JComboBox<>();
        cmbdestino = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        btnCrear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        dateSalida = new com.toedter.calendar.JDateChooser();
        dateLlegada = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtViaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        btnConfiguracion.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setText(" CONFIGURACIÓN");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        jPanel2.add(btnConfiguracion);
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
        jPanel2.add(btnHome);
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
        jPanel2.add(btnClientes);
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
        jPanel2.add(btnReservas);
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
        jPanel2.add(btnProveedores);
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
        jPanel2.add(btnReportes);
        btnReportes.setBounds(-20, 290, 210, 50);

        jTextField1.setBackground(new java.awt.Color(0, 46, 121));
        jPanel2.add(jTextField1);
        jTextField1.setBounds(0, 90, 190, 460);

        btnLimpiar.setBackground(new java.awt.Color(153, 153, 153));
        btnLimpiar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(btnLimpiar);
        btnLimpiar.setBounds(410, 470, 120, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("FORMULARIO-VIAJE PROGRAMADO");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(410, 150, 292, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("ID Viaje programado:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(320, 200, 140, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("ID Bus:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(320, 260, 50, 17);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("ID Origen:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(320, 320, 70, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("ID Destino Final:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(320, 380, 110, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fecha de Salida:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(620, 200, 100, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Fecha de Lllegada:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(620, 260, 120, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Estado de Viaje");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(620, 320, 97, 17);

        ToggleEstado.setText("Activo");
        ToggleEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToggleEstadoActionPerformed(evt);
            }
        });
        jPanel2.add(ToggleEstado);
        ToggleEstado.setBounds(620, 350, 140, 30);

        jPanel4.setBackground(new java.awt.Color(0, 46, 121));

        jButton8.setBackground(new java.awt.Color(179, 23, 23));
        jButton8.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Cerrar sesión");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(8, 8, 100));
        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 25)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(0, 0, 890, 90);
        jPanel2.add(DateChooserInicio);
        DateChooserInicio.setBounds(0, 0, 85, 20);
        jPanel2.add(DateChooserFin);
        DateChooserFin.setBounds(0, 0, 85, 22);

        cmbbus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cmbbus);
        cmbbus.setBounds(320, 290, 130, 22);

        cmborigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cmborigen);
        cmborigen.setBounds(320, 350, 130, 22);

        cmbdestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cmbdestino);
        cmbdestino.setBounds(320, 410, 130, 22);
        jPanel2.add(jTextField2);
        jTextField2.setBounds(320, 230, 130, 22);

        btnCrear.setBackground(new java.awt.Color(40, 167, 69));
        btnCrear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

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
        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("  Expreso los Chankas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 448, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dateSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(dateLlegada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(176, 176, 176))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtViaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addComponent(dateLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
       try {
        // Validar selecciones
        int idBus = extraerIdDeComboBox(cmbbus);
        int idOrigen = extraerIdDeComboBox(cmborigen);
        int idDestino = extraerIdDeComboBox(cmbdestino);
        
        if (idBus == -1 || idOrigen == -1 || idDestino == -1) {
            JOptionPane.showMessageDialog(this, 
                "Debe seleccionar bus, origen y destino", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que origen y destino sean diferentes
        if (idOrigen == idDestino) {
            JOptionPane.showMessageDialog(this, 
                "El origen y destino no pueden ser iguales", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar fechas
        if (dateSalida.getDate() == null || dateLlegada.getDate() == null) {
            JOptionPane.showMessageDialog(this,
                "Seleccione ambas fechas",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Configurar el objeto viaje
        viaje.setIdBus(idBus);
        viaje.setIdOrigen(idOrigen);
        viaje.setIdDestinoFinal(idDestino);
        
        // Convertir Date a LocalDateTime
        LocalDateTime salida = dateSalida.getDate().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime llegada = dateLlegada.getDate().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDateTime();
        
        viaje.setFechaHoraSalida(salida);
        viaje.setFechaHoraLlegadaEstimada(llegada);
        
        // Configurar estado
        viaje.setActivo(ToggleEstado.isSelected());

        // Crear DAOs necesarios
        BusDAO busDAO = new BusDAO();
        DestinoDAO destinoDAO = new DestinoDAO();
        ViajeProgramadoDAO dao = new ViajeProgramadoDAO(busDAO, destinoDAO);

        boolean exito;

        if (esModificacion) {
            exito = dao.actualizar(viaje);
        } else {
            exito = dao.guardarViaje(viaje);
        }

        if (exito) {
            JOptionPane.showMessageDialog(this,
                esModificacion ? "Viaje actualizado correctamente" : "Viaje creado correctamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            
            if (padreGestionViajes != null && padreGestionViajes.isVisible()) {
                padreGestionViajes.cargarViajesEnTabla();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Error al guardar el viaje",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
            "Error: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void ToggleEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToggleEstadoActionPerformed

    }//GEN-LAST:event_ToggleEstadoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        cmbbus.setSelectedIndex(0);
        cmborigen.setSelectedIndex(0);
        cmbdestino.setSelectedIndex(0);
        dateSalida.setDate(null);
        dateLlegada.setDate(null);
        ToggleEstado.setSelected(true);
    }//GEN-LAST:event_btnLimpiarActionPerformed

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

    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooserFin;
    private com.toedter.calendar.JDateChooser DateChooserInicio;
    private javax.swing.JToggleButton ToggleEstado;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JComboBox<String> cmbbus;
    private javax.swing.JComboBox<String> cmbdestino;
    private javax.swing.JComboBox<String> cmborigen;
    private com.toedter.calendar.JDateChooser dateLlegada;
    private com.toedter.calendar.JDateChooser dateSalida;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel txtViaje;
    // End of variables declaration//GEN-END:variables
}
