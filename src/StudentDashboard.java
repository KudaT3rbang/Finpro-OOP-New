/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author natha
 */
public class StudentDashboard extends javax.swing.JFrame {

    /**
     * Creates new form StudentDashboard
     */
    public StudentDashboard() {
        initComponents();
        loadUserData();
    }
    
    private void loadUserData() {
        UserSession session = UserSession.getInstance();
        usernameLabel.setText(session.getName());
        nimLabel.setText(session.getNim()); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personalDataPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        nimLabel = new javax.swing.JLabel();
        enrollPanel = new javax.swing.JPanel();
        headerEnroll = new javax.swing.JLabel();
        subheadEnroll = new javax.swing.JLabel();
        subheadEnroll2 = new javax.swing.JLabel();
        planButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Dashboard");
        setResizable(false);

        personalDataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Data", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        usernameLabel.setText("User Name");

        nimLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nimLabel.setText("NIM");

        javax.swing.GroupLayout personalDataPanelLayout = new javax.swing.GroupLayout(personalDataPanel);
        personalDataPanel.setLayout(personalDataPanelLayout);
        personalDataPanelLayout.setHorizontalGroup(
            personalDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalDataPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(personalDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalDataPanelLayout.createSequentialGroup()
                        .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(526, 526, 526))
                    .addGroup(personalDataPanelLayout.createSequentialGroup()
                        .addComponent(nimLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        personalDataPanelLayout.setVerticalGroup(
            personalDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nimLabel)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        enrollPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Course Selection Planner"));

        headerEnroll.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        headerEnroll.setText("Plan Your Course Path");

        subheadEnroll.setText("Effortlessly plan and manage your courses to stay on track");

        subheadEnroll2.setText("toward your academic goals.");

        planButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        planButton.setText("Plan Now");
        planButton.setFocusable(false);
        planButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout enrollPanelLayout = new javax.swing.GroupLayout(enrollPanel);
        enrollPanel.setLayout(enrollPanelLayout);
        enrollPanelLayout.setHorizontalGroup(
            enrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enrollPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(enrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerEnroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subheadEnroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(enrollPanelLayout.createSequentialGroup()
                        .addComponent(subheadEnroll2)
                        .addGap(154, 154, 154)))
                .addGap(78, 78, 78)
                .addComponent(planButton, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        enrollPanelLayout.setVerticalGroup(
            enrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enrollPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(headerEnroll, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subheadEnroll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subheadEnroll2)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enrollPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(planButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(personalDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(enrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(personalDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(enrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(295, 295, 295))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void planButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planButtonActionPerformed
        // TODO add your handling code here:
        CoursePlanner coursePlanner = new CoursePlanner();
        coursePlanner.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_planButtonActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel enrollPanel;
    private javax.swing.JLabel headerEnroll;
    private javax.swing.JLabel nimLabel;
    private javax.swing.JPanel personalDataPanel;
    private javax.swing.JButton planButton;
    private javax.swing.JLabel subheadEnroll;
    private javax.swing.JLabel subheadEnroll2;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
