
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

    public void saveCourses(String studentNim, int semester, List<String> courseCodes) {
        String ensureEnrollmentQuery = """
            INSERT INTO enrollment (student_nim, semester)
            SELECT ?, ?
            WHERE NOT EXISTS (
                SELECT 1 FROM enrollment WHERE student_nim = ? AND semester = ?
            )
        """;
        String deleteQuery = """
            DELETE FROM enrollment_course 
            WHERE enrollment_id = (SELECT id FROM enrollment WHERE student_nim = ? AND semester = ?)
        """;
        String insertQuery = """
            INSERT INTO enrollment_course (enrollment_id, code_course)
            VALUES (
                (SELECT id FROM enrollment WHERE student_nim = ? AND semester = ?),
                ?
            )
        """;

        try (Connection connection = DatabaseConnection.connect()) {
            connection.setAutoCommit(false);

            // Ensure enrollment exists
            try (PreparedStatement stmt = connection.prepareStatement(ensureEnrollmentQuery)) {
                stmt.setString(1, studentNim);
                stmt.setInt(2, semester);
                stmt.setString(3, studentNim);
                stmt.setInt(4, semester);
                stmt.executeUpdate();
            }

            // Delete existing courses
            try (PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {
                stmt.setString(1, studentNim);
                stmt.setInt(2, semester);
                stmt.executeUpdate();
            }

            // Insert new courses
            try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
                for (String courseCode : courseCodes) {
                    stmt.setString(1, studentNim);
                    stmt.setInt(2, semester);
                    stmt.setString(3, courseCode);
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveCoursesWithStatus(String studentNim, int semester, List<String> courseCodes, String status) throws SQLException {
        try (Connection connection = DatabaseConnection.connect()) {
            // Ensure enrollment exists with the specified status
            String ensureEnrollmentQuery = """
                INSERT INTO enrollment (student_nim, semester, status)
                SELECT ?, ?, ?
                WHERE NOT EXISTS (
                    SELECT 1 FROM enrollment WHERE student_nim = ? AND semester = ?
                )
                ON DUPLICATE KEY UPDATE status = VALUES(status)
            """;
            PreparedStatement ensureStmt = connection.prepareStatement(ensureEnrollmentQuery);
            ensureStmt.setString(1, studentNim);
            ensureStmt.setInt(2, semester);
            ensureStmt.setString(3, status);
            ensureStmt.setString(4, studentNim);
            ensureStmt.setInt(5, semester);
            ensureStmt.executeUpdate();

            // Get enrollment ID
            String getEnrollmentIdQuery = """
                SELECT id FROM enrollment WHERE student_nim = ? AND semester = ?
            """;
            PreparedStatement enrollmentStmt = connection.prepareStatement(getEnrollmentIdQuery);
            enrollmentStmt.setString(1, studentNim);
            enrollmentStmt.setInt(2, semester);
            ResultSet resultSet = enrollmentStmt.executeQuery();

            int enrollmentId = 0;
            if (resultSet.next()) {
                enrollmentId = resultSet.getInt("id");
            }

            if (enrollmentId == 0) {
                throw new SQLException("Failed to retrieve enrollment ID.");
            }

            // Clear existing courses for this enrollment
            String deleteQuery = """
                DELETE FROM enrollment_course WHERE enrollment_id = ?
            """;
            PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, enrollmentId);
            deleteStmt.executeUpdate();

            // Insert new courses
            String insertQuery = """
                INSERT INTO enrollment_course (enrollment_id, code_course) VALUES (?, ?)
            """;
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            for (String codeCourse : courseCodes) {
                insertStmt.setInt(1, enrollmentId);
                insertStmt.setString(2, codeCourse);
                insertStmt.executeUpdate();
            }
        }
    }
}
