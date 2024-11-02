public class Teacher implements Comparable<Teacher> {
    private String firstName;
    private String lastName;
    private TeacherCondition condition;
    private int yearOfBirth;
    private int salary;

    public Teacher(String firstName, String surname, TeacherCondition condition, int yearOfBirth, int salary) {
        this.firstName = firstName;
        this.lastName = surname;
        this.condition = condition;
        this.yearOfBirth = yearOfBirth;
        this.salary = salary;
    }
    Teacher(String firstName, String surname) {
        this.firstName = firstName;
        this.lastName = surname;
    }

    // getters and setters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public TeacherCondition getCondition() {
        return condition;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setCondition(TeacherCondition condition) {
        this.condition = condition;
    }


    public void printing(){
        System.out.println("Full name: " + firstName + " " + lastName +
                ", Condition: " + condition + ", Year of birth: " + yearOfBirth +
                ", Salary: " + salary);
    }

    @Override
    public int compareTo(Teacher teacher) {
        return this.lastName.compareTo(teacher.lastName);
    }
}
