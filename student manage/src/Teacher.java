public class Teacher {
  public int id;
  public String firstName;
  public String lastName;

  public Teacher(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "ID: " + id + " | " + firstName + " " + lastName;
  }
}
