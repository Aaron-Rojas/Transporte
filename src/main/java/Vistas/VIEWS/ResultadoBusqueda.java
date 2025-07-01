
package Vistas.VIEWS;
import Modelo.Cliente;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;

public class ResultadoBusqueda extends javax.swing.JDialog {
    
    private DefaultTableModel modeloTablaResultados;

    
    public ResultadoBusqueda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        configurarTablaResultados();
        
    }
    //Constructor que recibe la lista de Clientes Encontrados
    public ResultadoBusqueda(java.awt.Frame parent, boolean modal, List<Modelo.Cliente> clientesEncontrados) {
        // Llama al constructor principal de JDialog
        super(parent, modal);
        initComponents(); // Inicializa los componentes visuales
        this.setLocationRelativeTo(null); // Centra la ventana
        configurarTablaResultados(); // Configura el modelo de la tabla

        // Carga los resultados en la tabla después de que el modelo esté configurado
        cargarResultadosEnTabla(clientesEncontrados);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbResultadosClientes = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Resultado de la Busquedad de Clientes");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jScrollPane1.setViewportView(jLabel1);

        tbResultadosClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbResultadosClientes);

        scrollPane1.add(jScrollPane3);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(76, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(221, 221, 221))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
               this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed


    
    private void configurarTablaResultados() {
    modeloTablaResultados = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Las celdas de la tabla no son editables
        }
    };
    // Define las columnas de tu tabla (deben coincidir con tu BD y la clase Cliente)
    modeloTablaResultados.addColumn("ID");
    modeloTablaResultados.addColumn("DNI/Pasaporte");
    modeloTablaResultados.addColumn("Nombres");
    modeloTablaResultados.addColumn("Teléfono");
    modeloTablaResultados.addColumn("E-mail");
    modeloTablaResultados.addColumn("Preferencias");
    tbResultadosClientes.setModel(modeloTablaResultados);
}
// Método para cargar los clientes en la tabla de resultados
    public void cargarResultadosEnTabla(List<Cliente> clientes) {
        modeloTablaResultados.setRowCount(0); // Limpiar filas existentes
        if (clientes != null && !clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                Object[] fila = {
                    cliente.getIdCliente(),
                    cliente.getDni(),
                    cliente.getNombreCompleto(),
                    cliente.getTelefono(),
                    cliente.getEmail(),
                    cliente.getPreferencias()
                };
                modeloTablaResultados.addRow(fila);
            }
        } else {
            
         JOptionPane.showMessageDialog(this, "No se encontraron clientes con el criterio de búsqueda.", "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }   
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ResultadoBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResultadoBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResultadoBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultadoBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ResultadoBusqueda dialog = new ResultadoBusqueda(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JTable tbResultadosClientes;
    // End of variables declaration//GEN-END:variables
}
