package Vistas.VIEWS;

import Modelo.Destino;
import Modelo.LugarTuristico;
import Modelo.Proveedor;
import dao.LugarTuristicoDAO;
import dao.DestinoDAO;
import dao.ProveedorDAO;
import Modelo.Usuario;

import javax.swing.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioLugarTuristico extends javax.swing.JDialog {
       
    private LugarTuristico lugarTuristicoAEditar; // Objeto si estamos editando
    private LugarTuristicoDAO lugarTuristicoDAO;
    private DestinoDAO destinoDAO;
    private ProveedorDAO proveedorDAO;
    private Usuario usuarioActual; 
    private GestionLugarTuristico padreGetionLugarTuristico;

    public FormularioLugarTuristico(JFrame padre, GestionLugarTuristico gestionLugarTuristico) {
        super(padre,true);
        initComponents();
        
        this.padreGetionLugarTuristico= gestionLugarTuristico;
        
        this.proveedorDAO = new ProveedorDAO();
        this.destinoDAO= new DestinoDAO();
        
        this.lugarTuristicoDAO = new LugarTuristicoDAO(this.destinoDAO,this.proveedorDAO);
        
        this.setLocationRelativeTo(padre);
        this.setLocationRelativeTo(null);
        setTitle("Crear Nuevo Lugar Turístico");

                        
        ToggleButtonActivo.setSelected(true); 
        updateToggleButtonText(true);
        cargarDatosInicialesParaNuevo();
        addToggleButtonActionListener();
        
    }
    
    public FormularioLugarTuristico(JFrame padre, GestionLugarTuristico gestionLugarTuristicoPadre, LugarTuristico lugarTuristico){
        this(padre, gestionLugarTuristicoPadre);
        this.lugarTuristicoAEditar = lugarTuristico;
                
        addToggleButtonActionListener(); // Configura el listener del toggle        
        updateToggleButtonText(ToggleButtonActivo.isSelected()); // Actualiza el texto inicial del toggle

        this.setLocationRelativeTo(padre);
        
        setTitle("Modificar LugarTuristico #" + lugarTuristico.getIdLugarTuristico());
        cargarDatosParaEdicion(lugarTuristico);
    
    }
    private void cargarDatosParaEdicion(LugarTuristico lugarTuristico) {
        if (lugarTuristico != null) {
            txtNombre.setText(lugarTuristico.getNombreLugar());
            txtDescripción.setText(lugarTuristico.getDescripcion());
            cbTipo.setSelectedItem(lugarTuristico.getTipoLugar());
            ToggleButtonActivo.setSelected(lugarTuristico.getEstado()); // Establece el estado del toggle
            updateToggleButtonText(lugarTuristico.getEstado()); // Actualiza el texto del toggle

            cargarComboBoxes(); // Carga los ComboBox de Destino y Proveedor

            // Seleccionar el Destino y Proveedor correctos en los ComboBox
            if (lugarTuristico.getDestino() != null) {
                cmbProveedor.setSelectedItem(lugarTuristico.getDestino());
            }
            if (lugarTuristico.getProveedor() != null) {
                cmbDestino.setSelectedItem(lugarTuristico.getProveedor()); // Asumo que cmbDestino es para Proveedor
            }
        }
    }
    
    private void cargarDatosInicialesParaNuevo() {
        // Establecer valores por defecto para un nuevo lugar turístico
        txtNombre.setText("");
        txtDescripción.setText("");
        cbTipo.setSelectedIndex(0); // Selecciona el primer tipo por defecto
        ToggleButtonActivo.setSelected(true); // Por defecto activo
        updateToggleButtonText(true); // Actualiza el texto del botón al estado inicial
        cargarComboBoxes(); // Carga los ComboBox de Destino y Proveedor
    }
        
    private void cargarComboBoxes() {
        cbTipo.setModel(new DefaultComboBoxModel<>(new String[]{"Histórico", "Natural", "Recreativo"}));

        // Cargar ComboBox de Destinos
        DefaultComboBoxModel<Destino> destinoModel = new DefaultComboBoxModel<>();
        List<Destino> destinos = destinoDAO.obtenerTodosLosDestinos(); // Asumo un método para obtener todos los destinos
        for (Destino d : destinos) {
            destinoModel.addElement(d);
        }
        cmbProveedor.setModel(destinoModel);

        // Cargar ComboBox de Proveedores
        DefaultComboBoxModel<Proveedor> proveedorModel = new DefaultComboBoxModel<>();
        List<Proveedor> proveedores = proveedorDAO.obtenerTodosLosProveedores(); // Asumo un método para obtener todos los proveedores
        for (Proveedor p : proveedores) {
            proveedorModel.addElement(p);
        }
        cmbDestino.setModel(proveedorModel); // Asumo que cmbDestino es para Proveedor
    }
        
           
    private void updateToggleButtonText(boolean isActive) {
        if (ToggleButtonActivo != null) { // Asegurarse de que no sea null
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
                    updateToggleButtonText(estaActivo); // Actualiza el texto inmediatamente
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnConfiguracion1 = new javax.swing.JButton();
        btnHome1 = new javax.swing.JButton();
        btnClientes1 = new javax.swing.JButton();
        btnReservas1 = new javax.swing.JButton();
        btnProveedores1 = new javax.swing.JButton();
        btnReportes1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbDestino = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        ToggleButtonActivo = new javax.swing.JToggleButton();
        txtDescripción = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

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

        jLabel8.setBackground(new java.awt.Color(8, 8, 100));
        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 25)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 890, 90);

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
        jPanel1.add(btnHome);
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
        jPanel1.add(btnClientes);
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
        jPanel1.add(btnReservas);
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
        jPanel1.add(btnProveedores);
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
        jPanel1.add(btnReportes);
        btnReportes.setBounds(0, 290, 200, 50);

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(0, 46, 121));

        jButton8.setBackground(new java.awt.Color(179, 23, 23));
        jButton8.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Cerrar sesión");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(8, 8, 100));
        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 25)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("EXPRESO LOS CHANKAS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5);
        jPanel5.setBounds(0, 0, 890, 90);

        btnConfiguracion1.setBackground(new java.awt.Color(0, 46, 121));
        btnConfiguracion1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnConfiguracion1.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion1.setText("CONFIGURACIÓN");
        btnConfiguracion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracion1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnConfiguracion1);
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
        jPanel4.add(btnHome1);
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
        jPanel4.add(btnClientes1);
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
        jPanel4.add(btnReservas1);
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
        jPanel4.add(btnProveedores1);
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
        jPanel4.add(btnReportes1);
        btnReportes1.setBounds(0, 290, 200, 50);

        jPanel6.setBackground(new java.awt.Color(0, 46, 121));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6);
        jPanel6.setBounds(0, 380, 200, 170);
        jPanel4.add(txtNombre);
        txtNombre.setBounds(260, 210, 200, 22);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nombre");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(260, 180, 50, 42);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Descripcion");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(540, 320, 100, 38);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tipo");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(260, 250, 50, 22);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Estado");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(260, 320, 60, 32);

        btnGuardar.setBackground(new java.awt.Color(40, 167, 69));
        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar);
        btnGuardar.setBounds(620, 440, 110, 30);

        btnLimpiar.setBackground(new java.awt.Color(102, 102, 102));
        btnLimpiar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel4.add(btnLimpiar);
        btnLimpiar.setBounds(490, 440, 110, 30);

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Historico", "Natural", "Recreativo" }));
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        jPanel4.add(cbTipo);
        cbTipo.setBounds(260, 280, 200, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Destino ");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(540, 250, 60, 40);

        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(cmbProveedor);
        cmbProveedor.setBounds(540, 280, 200, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Proveedor");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(540, 190, 70, 17);

        cmbDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDestinoActionPerformed(evt);
            }
        });
        jPanel4.add(cmbDestino);
        cmbDestino.setBounds(540, 210, 200, 22);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setText("FORMULARIO PARA LUGAR TURISTICO");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(230, 120, 420, 37);

        btnCancelar.setBackground(new java.awt.Color(179, 23, 23));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        jPanel4.add(btnCancelar);
        btnCancelar.setBounds(350, 440, 120, 30);

        ToggleButtonActivo.setText("Activo");
        jPanel4.add(ToggleButtonActivo);
        ToggleButtonActivo.setBounds(260, 350, 130, 23);
        jPanel4.add(txtDescripción);
        txtDescripción.setBounds(540, 350, 200, 22);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // 1. Recopilar datos del formulario
        String nombre = txtNombre.getText().trim();
        String tipo = (String) cbTipo.getSelectedItem();
        String descripcion = txtDescripción.getText().trim();
        Destino destinoSeleccionado = (Destino) cmbProveedor.getSelectedItem();
        Proveedor proveedorSeleccionado = (Proveedor) cmbDestino.getSelectedItem(); // Asumo cmbDestino es para Proveedor
        boolean estado = ToggleButtonActivo.isSelected();

        // 2. Validaciones básicas
        if (nombre.isEmpty() || descripcion.isEmpty() || tipo == null || destinoSeleccionado == null || proveedorSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos y seleccione un Destino y Proveedor.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }


        // Si estamos creando un nuevo lugar turístico
        if (lugarTuristicoAEditar == null) {
                // Crear un nuevo objeto LugarTuristico
                LugarTuristico nuevoLugar = new LugarTuristico();
                nuevoLugar.setNombreLugar(nombre);
                nuevoLugar.setTipoLugar(tipo);
                nuevoLugar.setDescripcion(descripcion);
                nuevoLugar.setDestino(destinoSeleccionado);
                nuevoLugar.setProveedor(proveedorSeleccionado);
                nuevoLugar.setEstado(estado);

                // Guardar en la base de datos
                if (lugarTuristicoDAO.insertarLugarTuristico(nuevoLugar)) {
                    JOptionPane.showMessageDialog(this, "Lugar turístico agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    padreGetionLugarTuristico.cargarLugaresTuristicosActivos(); // Refrescar la tabla en la ventana padre
                    dispose(); // Cerrar el diálogo
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar el lugar turístico.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                // Actualizar el objeto existente con los nuevos datos
                lugarTuristicoAEditar.setNombreLugar(nombre);
                lugarTuristicoAEditar.setTipoLugar(tipo);
                lugarTuristicoAEditar.setDescripcion(descripcion);
                lugarTuristicoAEditar.setDestino(destinoSeleccionado);
                lugarTuristicoAEditar.setProveedor(proveedorSeleccionado);
                lugarTuristicoAEditar.setEstado(estado); // El estado ya se actualiza con el toggle

                // Actualizar en la base de datos
                if (lugarTuristicoDAO.actualizarLugarTuristico(lugarTuristicoAEditar)) {
                    JOptionPane.showMessageDialog(this, "Lugar turístico modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    padreGetionLugarTuristico.cargarLugaresTuristicosActivos(); // Refrescar la tabla en la ventana padre
                    dispose(); // Cerrar el diálogo
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar el lugar turístico.", "Error", JOptionPane.ERROR_MESSAGE);
                }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNombre.setText("");
        txtDescripción.setText("");
        cbTipo.setSelectedIndex(0);  // selecciona el primer valor del combo (ej: "Histórico")
        cmbDestino.setSelectedIndex(0);
        cmbProveedor.setSelectedIndex(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cmbDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDestinoActionPerformed

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProveedorActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoActionPerformed

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


//    cbEstado.setSelectedIndex(0);

//    }                                          

    /*
    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void cbDestinoActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void cbIDProveedorActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             
*/
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ToggleButtonActivo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnClientes1;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnConfiguracion1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnHome1;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnProveedores1;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReportes1;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btnReservas1;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<Proveedor> cmbDestino;
    private javax.swing.JComboBox<Destino> cmbProveedor;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField txtDescripción;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
