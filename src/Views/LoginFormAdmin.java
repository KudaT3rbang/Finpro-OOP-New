package Views;

import Services.AuthServiceAdmin;
import Models.AdminSession;
import com.formdev.flatlaf.IntelliJTheme;
import javax.swing.JOptionPane;

/**
 *
 * @author natha
 */
public class LoginFormAdmin extends javax.swing.JFrame {

    /**
     * Creates new form LoginFormAdmin
     */
    public LoginFormAdmin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OptionPane2 = new javax.swing.JOptionPane();
        adminLoginPanel = new javax.swing.JPanel();
        usernameField = new javax.swing.JLabel();
        adminUsernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JLabel();
        adminPasswordField = new javax.swing.JPasswordField();
        toggleAdminPassword = new javax.swing.JCheckBox();
        loginButtonAdmin = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Page Admin");
        setResizable(false);

        adminLoginPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login Form Admin", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        adminLoginPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        usernameField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        usernameField.setText("Username");

        adminUsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminUsernameFieldActionPerformed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        passwordField.setText("Password");

        adminPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminPasswordFieldActionPerformed(evt);
            }
        });

        toggleAdminPassword.setText("Show Password");
        toggleAdminPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleAdminPasswordActionPerformed(evt);
            }
        });

        loginButtonAdmin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        loginButtonAdmin.setText("Login");
        loginButtonAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminLoginPanelLayout = new javax.swing.GroupLayout(adminLoginPanel);
        adminLoginPanel.setLayout(adminLoginPanelLayout);
        adminLoginPanelLayout.setHorizontalGroup(
            adminLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLoginPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(adminLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminLoginPanelLayout.createSequentialGroup()
                        .addComponent(passwordField)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminLoginPanelLayout.createSequentialGroup()
                        .addGroup(adminLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(adminLoginPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(toggleAdminPassword))
                            .addComponent(adminPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adminUsernameField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, adminLoginPanelLayout.createSequentialGroup()
                                .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(164, 164, 164))
                            .addComponent(loginButtonAdmin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        adminLoginPanelLayout.setVerticalGroup(
            adminLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLoginPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(usernameField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toggleAdminPassword)
                .addGap(30, 30, 30)
                .addComponent(loginButtonAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(adminLoginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(250, 250, 250))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(adminLoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void adminUsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminUsernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminUsernameFieldActionPerformed

    /**
     * Checkbox yang berguna untuk menampilkan atau menyembunyikan password.
     *
     * @param evt Ketika checkbox di klik.
     */
    private void toggleAdminPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleAdminPasswordActionPerformed
        if (toggleAdminPassword.isSelected()) {
            adminPasswordField.setEchoChar((char) 0);
        } else {
            adminPasswordField.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_toggleAdminPasswordActionPerformed

    /**
     * Event handler untuk tombol login admin. Fungsi ini memeriksa apakah
     * username dan password tidak kosong, lalu melakukan autentikasi terhadap
     * database. Jika autentikasi berhasil, sesi admin akan diatur dan dashboard
     * admin akan ditampilkan. Jika gagal, pesan kesalahan akan muncul.
     *
     * @param evt ActionEvent yang dipicu saat tombol login diklik.
     */
    private void loginButtonAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonAdminActionPerformed
        // Mengambil input username dan password dari field
        String username = adminUsernameField.getText();
        String password = String.valueOf(adminPasswordField.getPassword());

        // Memastikan input tidak kosong
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Membuat instance AuthServiceAdmin untuk autentikasi
        AuthServiceAdmin authServiceAdmin = new AuthServiceAdmin();
        
        // Melakukan autentikasi dengan username dan password
        if (authServiceAdmin.authenticateAdmin(username, password)) {
            // Jika autentikasi berhasil, ambil admin ID dari database
            int adminId = authServiceAdmin.getAdminId(username);

            // Mengatur detail sesi admin
            AdminSession adminSession = AdminSession.getInstance();
            adminSession.setAdminDetails(adminId, username);

            // Menampilkan pesan berhasil login
            JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + username);

            // Membuka halaman dashboard admin
            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loginButtonAdminActionPerformed

    private void adminPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminPasswordFieldActionPerformed

    }//GEN-LAST:event_adminPasswordFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        IntelliJTheme.setup(LoginFormStudent.class.getResourceAsStream("/Light.theme.json"));

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFormAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JOptionPane OptionPane2;
    private javax.swing.JPanel adminLoginPanel;
    private javax.swing.JPasswordField adminPasswordField;
    private javax.swing.JTextField adminUsernameField;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton loginButtonAdmin;
    private javax.swing.JLabel passwordField;
    private javax.swing.JCheckBox toggleAdminPassword;
    private javax.swing.JLabel usernameField;
    // End of variables declaration//GEN-END:variables
}
