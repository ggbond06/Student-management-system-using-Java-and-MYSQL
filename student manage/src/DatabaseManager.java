import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
  private static final String URL = "jdbc:mysql://127.0.0.1:3306/student_database";
  private static final String USER = "root";
  private static final String PASSWORD = "bljy6688";

  public static Connection getConnection(){
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(URL, USER, PASSWORD);
      System.out.println("Database connected!");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }
}
