package Views;

import Services.CourseService;
import Models.UserSession;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class untuk mengelola pemilihan enrollment/mata kuliah mahasiswa. Menyediakan
 * fitur untuk memilih mata kuliah yang tersedia, menghapus mata kuliah yang
 * telah dipilih dan menyimpan data ke database.
 *
 * Fitur: - Memilih mata kuliah berdasarkan semester. - Menampilkan total SKS
 * yang diambil. - Menyimpan data mata kuliah yang dipilih ke dalam database.
 *
 * @author natha
 */
public class CoursePlanner extends javax.swing.JFrame {

    // Total credit/SKS yang diambil oleh student.
    private int totalCredits = 0;

    /**
     * Konstruktor untuk class CoursePlanner, inisasi gui.
     */
    public CoursePlanner() {
        initComponents();
        // Muat semua data ke tabel setelah semua gui di inisiasi.
        SwingUtilities.invokeLater(() -> {
            loadTables(1); // Loat tabel semester 1 sebagai default.
        });
    }

    /**
     * Mengambil NIM mahasiswa yang sedang login dari sesi student.
     *
     * @return NIM mahasiswa yang sedang login.
     */
    private String getStudentNimFromSession() {
        return UserSession.getInstance().getNim();
    }

    /**
     * Memuat data mata kuliah yang tersedia dan data mata kuliah yang sudah
     * dipilih di semester. Method ini juga mengecek status pendaftaran krs
     * (enrollment) mahasiswa untuk menentukan apakah masih boleh mengedit krs
     * untuk semester tersebut.
     *
     * @param semester Nomor semester yang akan di load datanya.
     */
    private void loadTables(int semester) {
        // Mendapatkan NIM mahasiswa dari sesi pengguna saat ini
        String studentNim = getStudentNimFromSession();
        if (studentNim == null) {
            // Menampilkan pesan error jika sesi tidak valid dan mengarahkan ke halaman login
            JOptionPane.showMessageDialog(this, "Session expired. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            new LoginFormStudent().setVisible(true);
            return;
        }

        // Membuat objek service untuk menangani operasi terkait mata kuliah
        CourseService courseService = new CourseService();

        // Mendapatkan status pendaftaran mahasiswa untuk semester yang dipilih
        String enrollmentStatus = courseService.getEnrollmentStatus(studentNim, semester);

        // Mendapatkan daftar mata kuliah yang tersedia dan yang telah dipilih untuk semester ini
        List<Object[]> availableCourses = courseService.getAvailableCourses(studentNim);
        List<Object[]> selectedCourses = courseService.getSelectedCourses(studentNim, semester);

        // Memperbarui tabel dengan data yang diambil
        updateTable(jTable1, availableCourses);
        updateTable(jTable2, selectedCourses);

        // Menghitung total credits/SKS dari mata kuliah yang dipilih
        calculateTotalCredits(selectedCourses);

        // Menangani status pendaftaran dan memperbarui izin pengeditan berdasarkan status tersebut
        if (null == enrollmentStatus) {
            // Mengizinkan pengeditan jika status pendaftaran tidak ditemukan
            enableEditing();
        } else {
            switch (enrollmentStatus) {
                case "Pending" -> {
                    // Menampilkan pesan bahwa pendaftaran sedang dalam proses
                    JOptionPane.showMessageDialog(this, "Your enrollment is pending. You cannot modify it at this time.");
                    disableEditing(); // Menonaktifkan pengeditan
                }
                case "Accepted" -> {
                    // Menampilkan pesan bahwa pendaftaran telah diterima
                    JOptionPane.showMessageDialog(this, "Your enrollment has been accepted. You cannot modify it.");
                    disableEditing(); // Menonaktifkan pengeditan
                }
                case "Rejected" -> {
                    // Menampilkan pesan bahwa pendaftaran ditolak dan mengizinkan pengeditan
                    JOptionPane.showMessageDialog(this, "Your enrollment was rejected. Please review and resubmit your enrollment.", "Rejected", JOptionPane.ERROR_MESSAGE);
                    enableEditing();
                }
                default ->
                    enableEditing();
            }
        }
    }

    /**
     * Memperbarui data dalam tabel dengan data yang diberikan.
     *
     * @param table Tabel yang akan diperbarui.
     * @param data Daftar data dalam bentuk array objek untuk ditampilkan di
     * tabel.
     */
    private void updateTable(JTable table, List<Object[]> data) {
        // Mendapatkan tabel dan menghapus semua baris yang ada
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Hapus baris yang sudah ada

        // Menambahkan data baru ke tabel
        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    /**
     * Menghitung total credits/SKS dari data yang dipilih dan memperbarui label
     * total SKS pada antarmuka pengguna.
     *
     * @param data Daftar data mata kuliah yang dipilih, di mana elemen ketiga
     * adalah jumlah SKS.
     */
    private void calculateTotalCredits(List<Object[]> data) {
        // Reset total SKS ke 0
        totalCredits = 0;

        // Menjumlahkan SKS dari setiap mata kuliah
        for (Object[] row : data) {
            totalCredits += (int) row[2]; // Menambahkan jumlah SKS
        }

        // Memperbarui label untuk menampilkan total SKS
        jLabel1.setText("Total Credits: " + totalCredits + " (Min : 16 ; Max : 24)");
    }

    /**
     * Menonaktifkan semua tombol pengeditan pada antarmuka pengguna.
     * Tombol-tombol yang dinonaktifkan meliputi: insertButton, removeButton,
     * dan saveButton.
     */
    private void disableEditing() {
        insertButton.setEnabled(false);
        removeButton.setEnabled(false);
        saveButton.setEnabled(false);
    }

    /**
     * Mengaktifkan semua tombol pengeditan pada antarmuka pengguna.
     * Tombol-tombol yang diaktifkan meliputi: insertButton, removeButton, dan
     * saveButton.
     */
    private void enableEditing() {
        insertButton.setEnabled(true);
        removeButton.setEnabled(true);
        saveButton.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        semesterSelectorPanel = new javax.swing.JPanel();
        headerSemester = new javax.swing.JLabel();
        semesterComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        insertButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Course Planner");
        setResizable(false);

        semesterSelectorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        headerSemester.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        headerSemester.setText("Semester Selector");

        semesterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8" }));
        semesterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semesterComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout semesterSelectorPanelLayout = new javax.swing.GroupLayout(semesterSelectorPanel);
        semesterSelectorPanel.setLayout(semesterSelectorPanelLayout);
        semesterSelectorPanelLayout.setHorizontalGroup(
            semesterSelectorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(semesterSelectorPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(semesterSelectorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(semesterSelectorPanelLayout.createSequentialGroup()
                        .addComponent(headerSemester)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(semesterComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        semesterSelectorPanelLayout.setVerticalGroup(
            semesterSelectorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(semesterSelectorPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(headerSemester)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(semesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(700, 340));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        insertButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        insertButton.setText("Insert >>");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        removeButton.setText("<< Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(insertButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
        );

        saveButton.setText("Checkout");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.setToolTipText("");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Course Credit Taken : 20");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(semesterSelectorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(exitButton)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(semesterSelectorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(exitButton)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Event listener untuk tombol "Insert". Memindahkan mata kuliah yang
     * dipilih dari tabel "Mata Kuliah Tersedia" (jTable1) ke tabel "Mata Kuliah
     * Diambil" (jTable2). Sebelum memindahkan, metode ini melakukan validasi
     * untuk memastikan bahwa jumlah total SKS tidak melebihi batas maksimum (24
     * SKS).
     *
     * @param evt Event yang dijalankan saat tombol "Insert" diklik.
     */
    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        // Mendapatkan baris yang dipilih pada tabel mata kuliah yang tersedia
        int selectedRow = jTable1.getSelectedRow();

        // Jika user ingin memasukkan mata kuliah tanpa memilih di tabel maka tampilkan pesan error.
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a course to insert!");
            return;
        }

        // Mendapatkan model data dari tabel "Mata Kuliah Tersedia" dan "Mata Kuliah Diambil"
        DefaultTableModel availableModel = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel selectedModel = (DefaultTableModel) jTable2.getModel();

        // Mengambil jumlah SKS dari baris yang dipilih
        int creditsToAdd = (int) availableModel.getValueAt(selectedRow, 2);

        // Mengecek apakah total SKS setelah penambahan melebihi batas maksimum (24 SKS)
        if (totalCredits + creditsToAdd > 24) {
            JOptionPane.showMessageDialog(this, "Total credits cannot exceed 24!");
            return;
        }

        // Membuat baris data yang akan dipindahkan ke tabel "Mata Kuliah Diambil"
        Object[] row = {
            availableModel.getValueAt(selectedRow, 0), // Kode Mata Kuliah
            availableModel.getValueAt(selectedRow, 1), // Nama Mata Kuliah
            availableModel.getValueAt(selectedRow, 2) // SKS
        };

        // Menambahkan baris ke tabel "Mata Kuliah Diambil" dan menghapus baris dari tabel "Mata Kuliah Tersedia"
        selectedModel.addRow(row);
        availableModel.removeRow(selectedRow);

        // Menghitung ulang total SKS setelah penambahan mata kuliah
        calculateTotalCredits(getTableData(jTable2));
    }//GEN-LAST:event_insertButtonActionPerformed

    /**
     * Event listener untuk tombol "Remove". Memindahkan mata kuliah yang
     * dipilih dari tabel "Mata Kuliah Diambil" (jTable2) ke tabel "Mata Kuliah
     * Tersedia" (jTable1).
     *
     * @param evt Event yang dijalankan saat tombol "Remove" diklik.
     */
    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // Mendapatkan baris yang dipilih pada tabel "Mata Kuliah Diambil"
        int selectedRow = jTable2.getSelectedRow();

        // Jika user ingin menghapus mata kuliah tanpa memilih di tabel maka tampilkan pesan error.
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a course to remove!");
            return;
        }

        // Mendapatkan model data dari tabel "Mata Kuliah Diambil" dan "Mata Kuliah Tersedia"
        DefaultTableModel selectedModel = (DefaultTableModel) jTable2.getModel();
        DefaultTableModel availableModel = (DefaultTableModel) jTable1.getModel();

        // Membuat baris data yang akan dipindahkan kembali ke tabel "Mata Kuliah Tersedia"
        Object[] row = {
            selectedModel.getValueAt(selectedRow, 0), // Kode Mata Kuliah
            selectedModel.getValueAt(selectedRow, 1), // Nama Mata Kuliah
            selectedModel.getValueAt(selectedRow, 2) // SKS
        };

        // Menambahkan baris ke tabel "Mata Kuliah Tersedia" dan menghapus baris dari tabel "Mata Kuliah Diambil"
        availableModel.addRow(row);
        selectedModel.removeRow(selectedRow);

        // Menghitung ulang total SKS setelah penghapusan mata kuliah
        calculateTotalCredits(getTableData(jTable2));
    }//GEN-LAST:event_removeButtonActionPerformed

    /**
     * Event listener untuk tombol "Save". Method ini menvalidasi terlebih
     * dahulu bila sudah memenuhi syarat jumlah sks. Lalu ketika sudah memenuhi
     * syarat maka data akan di save di database.
     *
     * @param evt Event yang dijalankan saat tombol "Save" diklik.
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // Mengecek apakah total SKS kurang dari batas minimum (16 SKS)
        if (totalCredits < 16) {
            JOptionPane.showMessageDialog(this, "Total credits must be at least 16!");
            return;
        }

        // Mendapatkan semester yang dipilih dari ComboBox
        int semester = semesterComboBox.getSelectedIndex() + 1;

        // Mendapatkan NIM mahasiswa dari sesi
        String studentNim = getStudentNimFromSession();

        // Mengambil data mata kuliah dari tabel "Mata Kuliah Diambil"
        List<Object[]> data = getTableData(jTable2);

        // Menyimpan kode mata kuliah dalam daftar (List)
        List<String> courseCodes = new ArrayList<>();
        for (Object[] row : data) {
            courseCodes.add((String) row[0]); // Menambahkan kode mata kuliah ke daftar
        }

        // Membuat instance dari CourseService untuk mengakses logika penyimpanan data
        CourseService courseService = new CourseService();

        try {
            // Menyimpan data mata kuliah dengan status "Pending" ke dalam database
            courseService.saveCoursesWithStatus(studentNim, semester, courseCodes, "Pending");

            // Menampilkan pesan berhasil menyimpan
            JOptionPane.showMessageDialog(this, "Courses saved successfully! Your enrollment is now pending review.");

            // Menonaktifkan fungsi edit
            disableEditing();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while saving: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * Mengambil data dari JTable yang diubah kedalam bentuk daftar array objek.
     * Setiap baris dalam JTable diubah menjadi array objek yang berisi
     * nilai-nilai dari kolomnya.
     *
     * @param table JTable yang datanya akan diambil
     * @return daftar array objek, di mana setiap array merepresentasikan satu
     * baris data dalam tabel
     */
    private List<Object[]> getTableData(JTable table) {
        List<Object[]> data = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            data.add(new Object[]{
                model.getValueAt(i, 0),
                model.getValueAt(i, 1),
                model.getValueAt(i, 2)
            });
        }
        return data;
    }

    /**
     * Event listener untuk tombol "Exit" diklik. Metode ini digunakan untuk
     * menutup coursePlanner dan membuka studentDashboard.
     *
     * @param evt
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        StudentDashboard dashboard = new StudentDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * Digunakan untuk memilih semester.
     * @param evt 
     */
    private void semesterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semesterComboBoxActionPerformed
        int semester = semesterComboBox.getSelectedIndex() + 1;
        loadTables(semester);
    }//GEN-LAST:event_semesterComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoursePlanner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel headerSemester;
    private javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> semesterComboBox;
    private javax.swing.JPanel semesterSelectorPanel;
    // End of variables declaration//GEN-END:variables
}
