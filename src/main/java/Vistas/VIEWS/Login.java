
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
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtContrasena = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 46, 121));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Cumplimos con todos los protocolos");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(320, 510, 245, 20);

        jTextField1.setBackground(new java.awt.Color(255, 204, 0));
        jPanel1.add(jTextField1);
        jTextField1.setBounds(0, 500, 888, 40);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Usuario:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Contraseña:");

        btnLogin.setBackground(new java.awt.Color(0, 60, 241));
        btnLogin.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Ingresar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        jLabel5.setText("INICIAR SESIÓN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtContrasena)
                                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addGap(106, 106, 106))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(100, 110, 320, 240);

        jTextField5.setBackground(new java.awt.Color(255, 204, 0));
        jTextField5.setMinimumSize(new java.awt.Dimension(32, 22));
        jTextField5.setSelectionColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField5);
        jTextField5.setBounds(-10, 80, 888, 10);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tipo de Rol: ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Usuario:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Correo:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Contraseña:");

        btnGuardarUsuario.setBackground(new java.awt.Color(0, 60, 241));
        btnGuardarUsuario.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnGuardarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarUsuario.setText("Guardar");
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(470, 110, 320, 360);

        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 55)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("LOS CHANKAS");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(300, 20, 290, 50);

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
                                       true,       // estado (String)
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
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
