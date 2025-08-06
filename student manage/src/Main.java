import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    while (true) {

      System.out.println("************************************");
      System.out.println("Welcome to the student manage system");
      System.out.println("************************************");
      System.out.println("****Select the following choices****");
      System.out.println("1. add a student");
      System.out.println("2. delete a student");
      System.out.println("3. search a student");
      System.out.println("4. get all student info");
      System.out.println("5. show student gpa rank");
      System.out.println("6. exit the program");
      System.out.println("************************************");

      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          StudentDAO.addStudent(sc, DatabaseManager.getConnection());
          break;
        case 2:
          StudentDAO.deleteStudent(sc, DatabaseManager.getConnection());
          break;
        case 3:
          StudentDAO.searchStudent(sc, DatabaseManager.getConnection());
          break;
        case 5:
          StudentDAO.getGpaRank(DatabaseManager.getConnection());
          break;
        case 4:
          StudentDAO.getAllStudentInfo(DatabaseManager.getConnection());
          break;
        case 6:
          System.out.println("Exiting the program...");
          sc.close();
          if (DatabaseManager.getConnection() != null) {
            try {
              DatabaseManager.getConnection().close();
              System.out.println("Database connection closed.");
            } catch (SQLException e) {
              e.printStackTrace();
            }
          }
          return;
        default:
          System.out.println("Invalid choice");
      }
    }
  }
}

