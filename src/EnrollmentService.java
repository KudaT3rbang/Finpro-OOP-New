
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    // Get students with pending enrollments
    public static List<Object[]> getStudentsWithPendingEnrollments() {
        List<Object[]> students = new ArrayList<>();
        String query = """
            SELECT DISTINCT e.student_nim, s.name, e.semester
            FROM enrollment e
            JOIN student s ON e.student_nim = s.nim
            WHERE e.status = 'Pending'
        """;

        try (Connection connection = DatabaseConnection.connect(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                students.add(new Object[]{
                    resultSet.getString("student_nim"),
                    resultSet.getString("name"),
                    resultSet.getInt("semester")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Get enrollment details for a specific student and semester
    public static List<Object[]> getEnrollmentDetails(String studentNim, int semester) {
        List<Object[]> details = new ArrayList<>();
        String query = """
            SELECT c.code_course, c.course_name, c.credit_hours
            FROM enrollment_course ec
            JOIN course c ON ec.code_course = c.code_course
            JOIN enrollment e ON ec.enrollment_id = e.id
            WHERE e.student_nim = ? AND e.semester = ? AND e.status = 'Pending'
        """;

        try (Connection connection = DatabaseConnection.connect(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, studentNim);
            statement.setInt(2, semester);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    details.add(new Object[]{
                        resultSet.getString("code_course"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("credit_hours")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }

    // Update the status of enrollment (Accept or Reject)
    public static void updateEnrollmentStatus(String studentNim, int semester, String status, String adminUsername) {
        String getAdminIdQuery = "SELECT id FROM admin WHERE username = ?";
        String updateQuery = """
        UPDATE enrollment
        SET status = ?, reviewed_by = ?, reviewed_at = NOW()
        WHERE student_nim = ? AND semester = ?
    """;

        try (Connection connection = DatabaseConnection.connect()) {
            // Retrieve the admin ID
            int adminId = 0;
            try (PreparedStatement getAdminIdStmt = connection.prepareStatement(getAdminIdQuery)) {
                getAdminIdStmt.setString(1, adminUsername);
                try (ResultSet rs = getAdminIdStmt.executeQuery()) {
                    if (rs.next()) {
                        adminId = rs.getInt("id");
                    } else {
                        throw new SQLException("Admin username not found: " + adminUsername);
                    }
                }
            }

            // Update the enrollment status
            try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                updateStmt.setString(1, status);
                updateStmt.setInt(2, adminId); // Use admin ID instead of username
                updateStmt.setString(3, studentNim);
                updateStmt.setInt(4, semester);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated == 0) {
                    throw new SQLException("No enrollment record found to update.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
