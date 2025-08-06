public class Student {
  public int id;
  public String firstName;
  public String lastName;
  public int age;
  public double gpa;
  public int taughtBy;

  public Student(int id, String firstName, String lastName, int age, double gpa, int taughtBy) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.gpa = gpa;
    this.taughtBy = taughtBy;
  }

  public Student(String firstName, String lastName, int age, double gpa, int taughtBy) {
    this(-1, firstName, lastName, age, gpa, taughtBy);
  }

  @Override
  public String toString() {
    return firstName + " " + lastName + " | Age: " + age + " | GPA: " + gpa + " | Teacher ID: " + taughtBy;
  }
}
