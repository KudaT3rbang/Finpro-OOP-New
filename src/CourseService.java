
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService {

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

    public void saveCoursesWithStatus(String studentNim, int semester, List<String> courseCodes, String status) {
        try (Connection connection = DatabaseConnection.connect()) {
            if (connection == null) {
                throw new SQLException("Failed to establish database connection.");
            }

            connection.setAutoCommit(false); // Start transaction

            // Ensure enrollment exists or update its status
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

            // Retrieve enrollment ID
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

            // Delete existing courses for the enrollment
            String deleteQuery = "DELETE FROM enrollment_course WHERE enrollment_id = ?";
            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
                deleteStmt.setInt(1, enrollmentId);
                deleteStmt.executeUpdate();
                System.out.println("Existing courses cleared for enrollment ID: " + enrollmentId);
            }

            // Insert new courses
            String insertQuery = "INSERT INTO enrollment_course (enrollment_id, code_course) VALUES (?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                for (String codeCourse : courseCodes) {
                    insertStmt.setInt(1, enrollmentId);
                    insertStmt.setString(2, codeCourse);
                    insertStmt.addBatch(); // Batch operation
                }
                insertStmt.executeBatch();
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection connection = DatabaseConnection.connect()) {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }
}
