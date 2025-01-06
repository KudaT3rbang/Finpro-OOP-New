package Views;


import Services.AuthService;
import Models.UserSession;
import com.formdev.flatlaf.IntelliJTheme;
import javax.swing.JOptionPane;

/**
 *
 * @author natha
 */
public class LoginFormStudent extends javax.swing.JFrame {

    public LoginFormStudent() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optionPane = new javax.swing.JOptionPane();
        loginPanel = new javax.swing.JPanel();
        labelNim = new javax.swing.JLabel();
        nimField = new javax.swing.JTextField();
        labelPassword = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        togglePassword = new javax.swing.JCheckBox();
        passwordField = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Page");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        loginPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login Form Student", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        labelNim.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNim.setLabelFor(nimField);
        labelNim.setText("NIM");

        nimField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nimFieldActionPerformed(evt);
            }
        });

        labelPassword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelPassword.setText("Password");

        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        togglePassword.setText("Show Password");
        togglePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglePasswordActionPerformed(evt);
            }
        });

        passwordField.setToolTipText("");
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(togglePassword))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nimField)
                            .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(loginPanelLayout.createSequentialGroup()
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNim)
                                    .addComponent(labelPassword))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(19, 19, 19))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(labelNim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nimField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(togglePassword)
                .addGap(30, 30, 30)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(250, 250, 250))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(94, 94, 94))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nimFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nimFieldActionPerformed

    }//GEN-LAST:event_nimFieldActionPerformed

    /**
     * Checkbox yang berguna untuk menampilkan atau menyembunyikan password.
     *
     * @param evt Ketika checkbox di klik.
     */
    private void togglePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglePasswordActionPerformed
        if (togglePassword.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_togglePasswordActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed

    }//GEN-LAST:event_passwordFieldActionPerformed

    /**
     * Menghandle action login untuk student authentication. Method ini
     * menvalidasi user input (NIM dan password), mengautentikasi user
     * menggunakan class AuthService, lalu inisiasi UserSession ketika login
     * berhasil. Jika login gagal tampilkan option pane yang menyatakan bahwa
     * login gagal.
     *
     * @param evt Ketika button di klik
     */
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // Ambil text dan password dari field.
        String nim = nimField.getText();
        String password = String.valueOf(passwordField.getPassword());

        // Cek apabila nim dan password kosong tampilkan dialog untuk mengisi.
        if (nim.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIM and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Autentikasi user menggunakan AuthService
        AuthService authService = new AuthService();
        if (authService.authenticate(nim, password)) {
            // Fetch data username dan masukkan kedalam session details.
            String userName = authService.getUserName(nim);
            UserSession.getInstance().setUserDetails(nim, userName);

            // Display bahwa login berhasil.
            JOptionPane.showMessageDialog(this, "Login Success!");

            // Buka student dashboard dan tutup login form
            StudentDashboard dashboard = new StudentDashboard();
            dashboard.setVisible(true);
            this.dispose();
        } else {
            // Display error jika login gagal.
            JOptionPane.showMessageDialog(this, "Wrong NIM or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    /**
     * Main method untuk membuka window LoginFormStudent. Berguna untuk
     * menampilkan login form ke student agar student bisa login. Method ini
     * berguna untuk menampilkan UI seperti intellijIdea dengan library flatLaf.
     *
     * @param args tidak ada argumen yang digunakan.
     */
    public static void main(String args[]) {
        IntelliJTheme.setup(LoginFormStudent.class.getResourceAsStream("/Light.theme.json"));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFormStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelNim;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField nimField;
    private javax.swing.JOptionPane optionPane;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JCheckBox togglePassword;
    // End of variables declaration//GEN-END:variables
}
