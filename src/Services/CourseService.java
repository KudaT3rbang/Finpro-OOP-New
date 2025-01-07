package Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseService berguna untuk service yang relate dengan CoursePlanner.
 *
 * @author natha
 */
public class CourseService {

    /**
     * Mendapatkan IPK mahasiswa pada semester tertentu.
     *
     * @param studentNim NIM mahasiswa.
     * @param semester Semester yang ingin dicek (semester saat ini - 1).
     * @return IPK semester sebelumnya, atau -1 jika tidak ditemukan.
     */
    public double getPreviousSemesterIPK(String studentNim, int semester) {
        if (semester <= 1) {
            return -1; // Semester 1 tidak memiliki semester sebelumnya
        }

        String query = "SELECT ipk FROM student_ipk WHERE student_nim = ? AND semester = ?";
        try (Connection connection = DatabaseConnection.connect(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentNim);
            stmt.setInt(2, semester - 1); // Semester sebelumnya
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("ipk");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Tidak ditemukan
    }

    /**
     * Mendapatkan daftar mata kuliah yang tersedia untuk mahasiswa berdasarkan
     * NIM. Mata kuliah yang telah diambil mahasiswa tidak akan ditampilkan.
     *
     * @param studentNim NIM Mahasiswa
     * @return daftar matakuliah yang masih bisa diambil students.
     */
    public List<Object[]> getAvailableCourses(String studentNim) {
        String query = """
            SELECT code_course, course_name, credit_hours 
            FROM course 
            WHERE code_course NOT IN (
                SELECT ec.code_course 
                FROM enrollment_course ec
                JOIN enrollment e ON ec.enrollment_id = e.id
                WHERE e.student_nim = ?
            )
        """;
        List<Object[]> courses = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentNim);
            ResultSet rs = stmt.executeQuery();

            // Menyimpan hasil query kedalam list.
            while (rs.next()) {
                courses.add(new Object[]{
                    rs.getString("code_course"),
                    rs.getString("course_name"),
                    rs.getInt("credit_hours")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    /**
     * Mendapatkan daftar mata kuliah yang telah dipilih oleh mahasiswa pada
     * semester tertentu.
     *
     * @param studentNim NIM mahasiswa.
     * @param semester Semester yang dipilih.
     * @return Daftar mata kuliah yang telah dipilih.
     */
    public List<Object[]> getSelectedCourses(String studentNim, int semester) {
        String query = """
            SELECT c.code_course, c.course_name, c.credit_hours 
            FROM course c
            JOIN enrollment_course ec ON c.code_course = ec.code_course
            JOIN enrollment e ON ec.enrollment_id = e.id
            WHERE e.student_nim = ? AND e.semester = ?
        """;
        List<Object[]> courses = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentNim);
            stmt.setInt(2, semester);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                courses.add(new Object[]{
                    rs.getString("code_course"),
                    rs.getString("course_name"),
                    rs.getInt("credit_hours")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    /**
     * Mendapatkan status pendaftaran mahasiswa pada semester tertentu.
     *
     * @param studentNim NIM mahasiswa.
     * @param semester Semester yang dipilih.
     * @return Status pendaftaran ("Pending", "Accepted", "Rejected", atau null
     * jika tidak ditemukan).
     */
    public String getEnrollmentStatus(String studentNim, int semester) {
        String query = "SELECT status FROM enrollment WHERE student_nim = ? AND semester = ?";
        try (Connection connection = DatabaseConnection.connect(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentNim);
            stmt.setInt(2, semester);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Menyimpan mata kuliah yang dipilih oleh mahasiswa dengan status tertentu.
     *
     * @param studentNim NIM mahasiswa.
     * @param semester Semester yang dipilih.
     * @param courseCodes Daftar kode mata kuliah yang dipilih.
     * @param status Status pendaftaran ("Pending", "Accepted", dll.).
     */
    public void saveCoursesWithStatus(String studentNim, int semester, List<String> courseCodes, String status) {
        try (Connection connection = DatabaseConnection.connect()) {
            if (connection == null) {
                throw new SQLException("Failed to establish database connection.");
            }

            connection.setAutoCommit(false); // Memulai transaksi

            // 1. Cek IPK semester sebelumnya
            double previousIPK = getPreviousSemesterIPK(studentNim, semester);
            if (semester > 1 && previousIPK == -1) { // Validasi IPK hanya jika bukan semester 1
                throw new SQLException("Student cannot enroll without a valid GPA from the previous semester.");
            }

            // 2. Tentukan batas SKS berdasarkan IPK
            int maxSKS = (semester == 1) ? 24 : (previousIPK >= 3.5 ? 24 : previousIPK >= 2.5 ? 20 : previousIPK >= 2.0 ? 18 : 15);

            // 3. Hitung total SKS dari mata kuliah yang dipilih
            int totalSKS = 0;
            String courseQuery = "SELECT credit_hours FROM course WHERE code_course = ?";
            try (PreparedStatement courseStmt = connection.prepareStatement(courseQuery)) {
                for (String codeCourse : courseCodes) {
                    courseStmt.setString(1, codeCourse);
                    ResultSet rs = courseStmt.executeQuery();
                    if (rs.next()) {
                        totalSKS += rs.getInt("credit_hours");
                    }
                }
            }
            if (totalSKS > maxSKS) {
                throw new SQLException("Student exceeds the maximum allowed credits (" + maxSKS + " SKS) for their GPA.");
            }

            // 4. Pastikan data pendaftaran mahasiswa ada atau perbarui statusnya
            String ensureEnrollmentQuery = """
            INSERT INTO enrollment (student_nim, semester, status)
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE status = VALUES(status)
        """;
            try (PreparedStatement ensureStmt = connection.prepareStatement(ensureEnrollmentQuery)) {
                ensureStmt.setString(1, studentNim);
                ensureStmt.setInt(2, semester);
                ensureStmt.setString(3, status);
                ensureStmt.executeUpdate();
            }

            // 5. Ambil ID pendaftaran
            String getEnrollmentIdQuery = "SELECT id FROM enrollment WHERE student_nim = ? AND semester = ?";
            int enrollmentId;
            try (PreparedStatement enrollmentStmt = connection.prepareStatement(getEnrollmentIdQuery)) {
                enrollmentStmt.setString(1, studentNim);
                enrollmentStmt.setInt(2, semester);
                ResultSet resultSet = enrollmentStmt.executeQuery();
                if (resultSet.next()) {
                    enrollmentId = resultSet.getInt("id");
                } else {
                    throw new SQLException("Failed to retrieve enrollment ID for student: " + studentNim + ", semester: " + semester);
                }
            }

            // 6. Hapus mata kuliah yang sudah ada
            String deleteQuery = "DELETE FROM enrollment_course WHERE enrollment_id = ?";
            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
                deleteStmt.setInt(1, enrollmentId);
                deleteStmt.executeUpdate();
            }

            // 7. Masukkan mata kuliah baru
            String insertQuery = "INSERT INTO enrollment_course (enrollment_id, code_course) VALUES (?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                for (String codeCourse : courseCodes) {
                    insertStmt.setInt(1, enrollmentId);
                    insertStmt.setString(2, codeCourse);
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch();
            }

            connection.commit(); // Commit transaksi

        } catch (SQLException e) {
            e.printStackTrace(); // Menangani error dengan mencetak stack trace
        }
    }
}
