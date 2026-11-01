import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteRecordJDBC {

    // Database connection parameters (Modify as per your database configuration)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        // SQL query to delete a record by ID using a parameterized placeholder (?)
        String deleteSql = "DELETE FROM students WHERE id = ?";

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Student ID to delete: ");
            int studentId = scanner.nextInt();

            // Establish connection and prepare statement using try-with-resources
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = connection.prepareStatement(deleteSql)) {

                System.out.println("Connected to the database successfully!");

                // Set parameter for the placeholder
                pstmt.setInt(1, studentId);

                // Execute the delete operation
                int rowsAffected = pstmt.executeUpdate();

                // Check if any record was deleted
                if (rowsAffected > 0) {
                    System.out.println("Record with ID " + studentId + " deleted successfully!");
                    System.out.println("Rows affected: " + rowsAffected);
                } else {
                    System.out.println("No record found with ID " + studentId + ".");
                }

            } catch (SQLException e) {
                System.err.println("Database error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
