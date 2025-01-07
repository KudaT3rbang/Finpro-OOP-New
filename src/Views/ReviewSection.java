package Views;

import Services.ReviewService;
import Models.AdminSession;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReviewSection extends javax.swing.JFrame {

    /**
     * Membuka halaman ReviewSection untuk mengelola pendaftaran krs mahasiswa.
     * Halaman ini menampilkan daftar mahasiswa yang pendaftarannya perlu
     * ditinjau.
     */
    public ReviewSection() {
        initComponents();
        loadStudentData();
    }

    /**
     * Memuat data mahasiswa yang memiliki status pendaftaran "Pending" ke tabel
     * jTable1.
     */
    private void loadStudentData() {
        try {
            // Mendapatkan daftar mahasiswa dengan pendaftaran "Pending"
            List<Object[]> studentList = ReviewService.getStudentsWithPendingEnrollments();

            // Memperbarui tabel mahasiswa
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            for (Object[] student : studentList) {
                model.addRow(student); // Menambahkan setiap mahasiswa ke tabel
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
        }
    }

    /**
     * Memuat detail pendaftaran untuk mahasiswa yang dipilih pada tabel
     * jTable1.
     *
     * @param studentNim NIM mahasiswa yang dipilih.
     * @param semester Semester mahasiswa yang dipilih.
     */
    private void loadEnrollmentDetails(String studentNim, int semester) {
        try {
            // Mendapatkan detail pendaftaran untuk mahasiswa yang dipilih
            List<Object[]> details = ReviewService.getEnrollmentDetails(studentNim, semester);

            // Memperbarui tabel detail pendaftaran
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            for (Object[] detail : details) {
                model.addRow(detail); // Add each enrollment detail to the table
            }

            // Memperbarui label nama dan semester
            nameLabel.setText("Name : " + jTable1.getValueAt(jTable1.getSelectedRow(), 1));
            semesterLabel.setText("Semester : " + semester);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading enrollment details: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        nameLabel = new javax.swing.JLabel();
        semesterLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        acceptButton = new javax.swing.JButton();
        rejectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Review Section");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NIM", "Name", "Semester"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Course Code", "Course Name", "Credits"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        nameLabel.setText("Name : -");

        semesterLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        semesterLabel.setText("Semester : -");

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        acceptButton.setText("Accept");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        rejectButton.setText("Reject");
        rejectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(exitButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(semesterLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rejectButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(acceptButton))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(semesterLabel)
                            .addComponent(acceptButton)
                            .addComponent(rejectButton))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(exitButton)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Menangani tindakan tombol "Accept". Mengubah status pendaftaran menjadi
     * "Accepted".
     *
     * @param evt Event ketika tombol "Accept" ditekan.
     */
    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        // Mendapatkan baris yang dipilih
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to accept their enrollment.");
            return;
        }

        String studentNim = (String) jTable1.getValueAt(selectedRow, 0); // Mendapatkan NIM mahasiswa
        int semester = (int) jTable1.getValueAt(selectedRow, 2); // Mendapatkan semester
        String adminUsername = AdminSession.getInstance().getUsername(); // Mendapatkan username admin

        // Memperbarui status pendaftaran menjadi "Accepted"
        ReviewService.updateEnrollmentStatus(studentNim, semester, "Accepted", adminUsername);
        JOptionPane.showMessageDialog(this, "Enrollment accepted successfully!");

        loadStudentData(); // Memuat ulang data mahasiswa
        ((DefaultTableModel) jTable2.getModel()).setRowCount(0);
    }//GEN-LAST:event_acceptButtonActionPerformed

    /**
     * Menangani tindakan tombol "Reject". Mengubah status pendaftaran menjadi
     * "Rejected".
     *
     * @param evt Event ketika tombol "Reject" ditekan.
     */
    private void rejectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectButtonActionPerformed
        // Mendapatkan baris yang dipilih
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to reject their enrollment.");
            return;
        }

        String studentNim = (String) jTable1.getValueAt(selectedRow, 0); // Mendapatkan NIM mahasiswa
        int semester = (int) jTable1.getValueAt(selectedRow, 2); // Mendapatkan semester
        String adminUsername = AdminSession.getInstance().getUsername(); // Mendapatkan username admin

        // Memperbarui status pendaftaran menjadi "Rejected"
        ReviewService.updateEnrollmentStatus(studentNim, semester, "Rejected", adminUsername);
        JOptionPane.showMessageDialog(this, "Enrollment rejected successfully!");

        loadStudentData();
        ((DefaultTableModel) jTable2.getModel()).setRowCount(0);
    }//GEN-LAST:event_rejectButtonActionPerformed

    /**
     * Menangani event klik pada tabel mahasiswa (jTable1). Ketika double-click,
     * detail pendaftaran mahasiswa akan ditampilkan.
     *
     * @param evt Event klik pada tabel mahasiswa.
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) { // Jika double-click terdeteksi
            int selectedRow = jTable1.getSelectedRow(); // Mendapatkan baris yang dipilih
            if (selectedRow != -1) {
                String studentNim = (String) jTable1.getValueAt(selectedRow, 0); // Mendapatkan NIM mahasiswa
                int semester = (int) jTable1.getValueAt(selectedRow, 2); // Mendapatkan semester

                // Memuat detail pendaftaran
                loadEnrollmentDetails(studentNim, semester);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * Menangani tindakan tombol "Exit". Membuka AdminDashboard dan menutup
     * ReviewSection.
     *
     * @param evt Event ketika tombol "Exit" ditekan.
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReviewSection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton rejectButton;
    private javax.swing.JLabel semesterLabel;
    // End of variables declaration//GEN-END:variables
}
