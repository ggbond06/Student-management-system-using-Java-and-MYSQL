import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {
  public static void getAllTeacherID(Connection connection){
    try{
      String sql = "SELECT * FROM teacher";
      PreparedStatement ps = connection.prepareStatement(sql);

      ResultSet rs = ps.executeQuery();

      while(rs.next()){
        System.out.println("Teacher " + rs.getInt("id") + ", " + rs.getString("first_name") + " " + rs.getString("last_name"));
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
  }
}
