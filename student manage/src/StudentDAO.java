import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDAO {
  public static void addStudent(Scanner sc, Connection connection) {
    sc.nextLine(); // Clear leftover newline before reading the name

    System.out.print("First name: ");
    String first_name = sc.nextLine();
    System.out.print("Last name: ");
    String last_name = sc.nextLine();
    System.out.print("Age: ");
    int age = sc.nextInt();
    System.out.print("GPA: ");
    double gpa = sc.nextDouble();
    System.out.println("Taught_by: ");
    TeacherDAO.getAllTeacherID(connection);
    int taught_by = sc.nextInt();

    try {
      String sql = "INSERT INTO students (first_name, last_name, age, gpa, taught_by) VALUES(?, ?, ?, ?, ?)";
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, first_name);
      ps.setString(2, last_name);
      ps.setInt(3, age);
      ps.setDouble(4, gpa);
      ps.setInt(5, taught_by);
      ps.executeUpdate();
      System.out.println("Student added to MYSQL!");
    } catch (SQLException e) {
      System.out.println("Failed to insert into MySQL.");
      e.printStackTrace();
    }
  }

  public static void deleteStudent(Scanner sc, Connection connection) {
    sc.nextLine();

    System.out.print("Enter the student's first name to be deleted: ");
    String first_name = sc.nextLine();
    System.out.print("Enter the student's last name to be deleted: ");
    String last_name = sc.nextLine();

    try {
      String sql = "DELETE FROM  students where first_name = ? AND last_name = ?";
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, first_name);
      ps.setString(2, last_name);

      int rowAffected = ps.executeUpdate();

      if (rowAffected > 0) {
        System.out.println("student deleted successfully!");
      } else {
        System.out.println("no matching student found.");
      }
    } catch (SQLException e) {
      System.out.println("failed to delete the student from MYSQL");
      e.printStackTrace();
    }

  }

  public static void searchStudent(Scanner sc, Connection connection) {
    sc.nextLine();

    System.out.print("Enter the first name of the student to be searched: ");
    String first_name = sc.nextLine();
    System.out.print("Enter the last name of the student to be searched: ");
    String last_name = sc.nextLine();

    try {
      String sql = "SELECT * from students WHERE first_name = ? AND last_name = ?";
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, first_name);
      ps.setString(2, last_name);

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("Student found:");
        System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
        System.out.println("Age: " + rs.getInt("age"));
        System.out.println("GPA: " + rs.getBigDecimal("gpa"));
      } else {
        System.out.println("No student found with that name.");
      }
    } catch (SQLException e) {
      System.out.println("Failed to search through MySQL.");
      e.printStackTrace();
    }
  }

  public static void getAllStudentInfo(Connection connection) {
    try {

      String sql = "SELECT students.id AS student_id," +
              "CONCAT(students.first_name, ' ', students.last_name) AS student_fullname," +
              "students.age, students.gpa, " +
              "CONCAT(teacher.first_name, ' ', teacher.last_name) AS teacher_fullname " +
              "FROM students " +
              "INNER JOIN teacher ON students.taught_by = teacher.id;";
      PreparedStatement ps = connection.prepareStatement(sql);

      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        System.out.println("Student " + resultSet.getInt("student_id") + " | "
                + resultSet.getString("student_fullname") + " | age: "
                + resultSet.getInt("students.age") + " | "
                + "GPA: " + resultSet.getDouble("students.gpa") + " | "
                + "taught by: " + resultSet.getString("teacher_fullname"));
        System.out.println("------------------------------");
      }
    } catch (SQLException e) {
      System.out.println("Failed to extract info out of MySQL.");
      e.printStackTrace();
    }
  }

  public static void getGpaRank(Connection connection) {

    ArrayList<Student> studentList = new ArrayList<>();

    try {
      String sql = "SELECT * FROM students";
      PreparedStatement ps = connection.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        studentList.add(new Student(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("age"),
                rs.getDouble("gpa"),
                rs.getInt("taught_by")
                ));
      }
    } catch (SQLException e) {
      System.out.println("Failed to extract info from MYSQL");
      e.printStackTrace();
    }

    if (!studentList.isEmpty()) {
      for (int i = 0; i < studentList.size() - 1; i++) {
        for (int j = 0; j < studentList.size() - i - 1; j++) {  // Fix here
          if (studentList.get(j).gpa < studentList.get(j + 1).gpa) {
            Student temp = studentList.get(j);
            studentList.set(j, studentList.get(j + 1));
            studentList.set(j + 1, temp);
          }
        }
      }
    } else {
      System.out.println("Your system is empty.");
    }

    System.out.println("Here is the sorted rank:");
    for (Student s : studentList) {
      System.out.println(s.firstName + " " + s.lastName + " " + s.gpa);
    }
  }

}
