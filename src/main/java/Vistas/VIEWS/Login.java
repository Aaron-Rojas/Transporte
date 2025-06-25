
package Vistas.VIEWS;

import Modelo.Usuario;
import dao.UsuarioDAO;
import Modelo.Rol;
import dao.RolDAO;
import java.util.List;


import Vistas.VIEWS.Principal;
import Vistas.VIEWCONFIGURACION.GestioUsuarios;
import Vistas.VIEWS.GestionProveedores;
import Vistas.VIEWCONFIGURACION.GestioUsuarios;
        
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


public class Login extends javax.swing.JFrame {
    
    private UsuarioDAO usuarioDAO; 
    private RolDAO rolDAO;
    
    
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        usuarioDAO = new UsuarioDAO();        
        rolDAO = new RolDAO();
        
        CargarRolesComboBox();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtContrasena = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cmbRoles = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnGuardarUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(8, 8, 100));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(550, 10, 197, 32);

        jTextField1.setBackground(new java.awt.Color(255, 182, 18));
        jPanel1.add(jTextField1);
        jTextField1.setBounds(0, 374, 888, 171);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cumplimos con todos los protocolos");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(199, 334, 245, 20);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtContrasena.setBackground(new java.awt.Color(235, 235, 235));
        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });

        jLabel3.setText("Usuario:");

        jLabel4.setText("Contraseña:");

        txtNombreUsuario.setBackground(new java.awt.Color(235, 235, 235));

        btnLogin.setBackground(new java.awt.Color(51, 51, 51));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Ingresar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreUsuario)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(40, 80, 270, 200);

        jTextField5.setBackground(new java.awt.Color(255, 212, 31));
        jPanel1.add(jTextField5);
        jTextField5.setBounds(0, 360, 888, 8);

        jTextField6.setBackground(new java.awt.Color(255, 212, 31));
        jPanel1.add(jTextField6);
        jTextField6.setBounds(0, 360, 888, 8);

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("INICIAR SESIÓN");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(90, 30, 197, 32);

        jLabel6.setText("Tipo de Rol: ");

        jLabel7.setText("Usuario:");

        jLabel8.setText("Correo:");

        jLabel9.setText("Contraseña:");

        btnGuardarUsuario.setText("Guardar");
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbRoles, 0, 157, Short.MAX_VALUE)
                    .addComponent(txtUsername)
                    .addComponent(txtCorreo)
                    .addComponent(txtPassword))
                .addGap(43, 43, 43))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(btnGuardarUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnGuardarUsuario)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(460, 60, 380, 280);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CargarRolesComboBox (){
    
    DefaultComboBoxModel<Rol> model = new DefaultComboBoxModel <>();
    List<Rol> roles = rolDAO.obtenerTodosLosRoles();
    
       if (roles.isEmpty()) {
            //En caso no se encuentre Roles en la BD
           JOptionPane.showMessageDialog(this,"No se encontraron roles en la BD","Error de configuración", JOptionPane.WARNING_MESSAGE);
           //Deshabilitar el JComboBox
           cmbRoles.setEnabled(false);
       }else{
           //Iterando dentro de la lista
         for(Rol rol: roles){
            model.addElement(rol);
         }
         cmbRoles.setModel(model);
       }
    
    }
    
   private void redireccionarSegunRol(Usuario usuarioLogeado){
       if (usuarioLogeado == null || usuarioLogeado.getRol() == null) {
           JOptionPane.showMessageDialog(this,"Error: No se pudo determinar el rol","Error de Redirección", JOptionPane.ERROR_MESSAGE);
           return ;
       }
       
       String nombreRol = usuarioLogeado.getRol().getNombreRol();
       javax.swing.JFrame ventanaDestino = null;
       
       if ("admin".equalsIgnoreCase(nombreRol)) {
           ventanaDestino = new GestioUsuarios (usuarioLogeado);
           System.out.println("Redirigiendo al Panel De Admin con el rol: "+ nombreRol);
       }else if ("usuario".equalsIgnoreCase(nombreRol)) {
           ventanaDestino = new Principal(usuarioLogeado);
           System.out.println("Redirifgiendo al Panel de Usuario "+ nombreRol);
       }else if("proveedor".equalsIgnoreCase(nombreRol)){
           ventanaDestino = new GestionProveedores(usuarioLogeado);
           System.out.println("Redirigiendo al Panel de Proveedores"+ nombreRol);
       }else{
           JOptionPane.showMessageDialog(this,"Rol no reconocido","Acceso Denegado",JOptionPane.ERROR_MESSAGE);
            return;
       }
       
       //determinar la ventana destino
       if (ventanaDestino !=null) {
           ventanaDestino.setVisible(true);
           ventanaDestino.setLocationRelativeTo(null);
           this.dispose();
       }
   }
    
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
    String nombreUsuarioIngresado = txtNombreUsuario.getText().trim(); 
    String contrasenaIngresada = new String(txtContrasena.getPassword());

    // **PASO 1: Validar que los campos no estén vacíos**
    if (nombreUsuarioIngresado.isEmpty()) { 
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre de usuario.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (contrasenaIngresada.isEmpty()) { 
        JOptionPane.showMessageDialog(this, "Por favor, ingrese la contraseña.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // **PASO 2: Llamar al DAO para validar credenciales**
    // El método en UsuarioDAO se llama validarLogin
    Usuario usuarioLogeado = usuarioDAO.validarLogin(nombreUsuarioIngresado, contrasenaIngresada); 
        if (usuarioLogeado != null) {
            JOptionPane.showMessageDialog(this, "Login Exitoso","Bienvenido",JOptionPane.INFORMATION_MESSAGE);
            redireccionarSegunRol(usuarioLogeado);
        }else{
            JOptionPane.showMessageDialog(this,"Credenciales inválidas o Usuario Inactivo","Error de Login",JOptionPane.ERROR_MESSAGE);
        }
  
 
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed
        String nombreCompleto = txtUsername.getText().trim();
        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword());
        Rol rolSeleccionado = (Rol) cmbRoles.getSelectedItem();
        
        if (nombreCompleto.isEmpty() || correo.isEmpty() || password.isEmpty() || rolSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", "Campos Vacio", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
    Usuario nuevoUsuario = new Usuario( 0,                                // idUsuario (int)
                                        nombreCompleto,      // nombreCompleto (String)
                                        correo,              // correo (String)
                                       password,    // contraseña (String)
                                       "activo",       // estado (String)
                                       rolSeleccionado.getIdRol(),       // idRol (int)
                                       rolSeleccionado                   // rol (Rol) <-- Añadir este argumento
);        
        boolean exito = usuarioDAO.agregarUsuario(nuevoUsuario); // Necesitarás un método agregarUsuario en UsuarioDAO
        if (exito) {
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Limpiar los campos después del registro exitoso
            txtUsername.setText("");
            txtCorreo.setText("");
            txtPassword.setText(""); // Para JPasswordField
            cmbRoles.setSelectedIndex(0); // O restablecer a un valor por defecto
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario. Verifique el nombre de usuario o correo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox<Rol> cmbRoles;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
