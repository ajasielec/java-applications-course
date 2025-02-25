import entity.Rate;
import repository.GroupRepository;
import repository.RateRepository;
import repository.TeacherRepository;
import entity.Teacher;
import entity.TeacherCondition;
import entity.Teachergroup;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        TeacherRepository teacherRepo = new TeacherRepository();
        GroupRepository groupRepo = new GroupRepository();
        RateRepository rateRepo = new RateRepository();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Teacher");
            System.out.println("2. Search Teacher by ID");
            System.out.println("3. Delete Teacher");
            System.out.println("4. Add Group");
            System.out.println("5. Add Teacher to Group");
            System.out.println("6. Remove Teacher from Group");
            System.out.println("7. Add Salary to Teacher");
            System.out.println("8. Change Teacher's Condition");
            System.out.println("9. Search Teacher in Group");
            System.out.println("10. Search Partial Teacher in Group");
            System.out.println("11. Count Teachers by Condition in Group");
            System.out.println("12. Add Rate to Group");
            System.out.println("13. Summary");
            System.out.println("14. Delete Group");
            System.out.println("15. Export Database to CSV");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left by nextInt()

            switch (choice) {
                case 1:
                    // Add Teacher
                    Teacher teacher1 = new Teacher();
                    System.out.println("Enter first name:");
                    teacher1.setFirstName(scanner.nextLine());
                    System.out.println("Enter last name:");
                    teacher1.setLastName(scanner.nextLine());
                    System.out.println("Enter salary:");
                    teacher1.setSalary(scanner.nextInt());
                    System.out.println("Enter birth year:");
                    teacher1.setBirthYear(scanner.nextInt());
                    System.out.println("Enter status (1 - PRESENT, 2 - DELEGATION, 3 - SICK, 4 - ABSENT):");
                    teacher1.setStatus(TeacherCondition.values()[scanner.nextInt() - 1]);

                    teacherRepo.addTeacher(teacher1);
                    break;

                case 2:
                    // Search Teacher by ID
                    System.out.println("Enter teacher ID to search:");
                    int teacherId = scanner.nextInt();
                    Teacher foundTeacher = teacherRepo.getTeacher(teacherId);
                    if (foundTeacher != null) {
                        System.out.println("Found teacher: " + foundTeacher.getFirstName() + " " + foundTeacher.getLastName());
                    } else {
                        System.out.println("No teacher found with ID: " + teacherId);
                    }
                    break;

                case 3:
                    // Delete Teacher
                    System.out.println("Enter teacher ID to delete:");
                    int deleteTeacherId = scanner.nextInt();
                    teacherRepo.removeTeacher(deleteTeacherId);
                    break;

                case 4:
                    // Add Group
                    Teachergroup group = new Teachergroup();
                    System.out.println("Enter group name:");
                    group.setName(scanner.nextLine());
                    System.out.println("Enter maximum number of teachers in the group:");
                    group.setMaxTeachers(scanner.nextInt());
                    groupRepo.addGroup(group);
                    break;

                case 5:
                    // Add Teacher to Group
                    System.out.println("Enter group ID:");
                    int groupId = scanner.nextInt();
                    System.out.println("Enter teacher ID to add:");
                    int teacherToAddId = scanner.nextInt();
                    groupRepo.addTeacherToGroup(groupId, teacherToAddId);
                    break;

                case 6:
                    // Remove Teacher from Group
                    System.out.println("Enter group ID:");
                    int groupIdToRemove = scanner.nextInt();
                    System.out.println("Enter teacher ID to remove:");
                    int teacherToRemoveId = scanner.nextInt();
                    groupRepo.removeTeacherFromGroup(groupIdToRemove, teacherToRemoveId);
                    break;

                case 7:
                    // Add Salary to Teacher
                    System.out.println("Enter group ID:");
                    int groupIdForSalary = scanner.nextInt();
                    System.out.println("Enter teacher ID:");
                    int teacherIdForSalary = scanner.nextInt();
                    System.out.println("Enter salary to add:");
                    int salary = scanner.nextInt();
                    groupRepo.addSalary(groupIdForSalary, teacherIdForSalary, salary);
                    break;

                case 8:
                    // Change Teacher's Condition
                    System.out.println("Enter group ID:");
                    int groupIdForCondition = scanner.nextInt();
                    System.out.println("Enter teacher ID:");
                    int teacherIdForCondition = scanner.nextInt();
                    System.out.println("Enter new condition (1 - PRESENT, 2 - DELEGATION, 3 - SICK, 4 - ABSENT):");
                    TeacherCondition newCondition = TeacherCondition.values()[scanner.nextInt() - 1];
                    groupRepo.changeCondition(groupIdForCondition, teacherIdForCondition, newCondition);
                    break;

                case 9:
                    // Search Teacher in Group
                    System.out.println("Enter group ID:");
                    int groupIdToSearch = scanner.nextInt();
                    System.out.println("Enter teacher last name to search:");
                    scanner.nextLine();  // Consume newline
                    String lastName = scanner.nextLine();
                    Teacher teacherInGroup = groupRepo.searchTeacher(groupIdToSearch, lastName);
                    if (teacherInGroup != null) {
                        System.out.println("Found teacher: " + teacherInGroup.getFirstName() + " " + teacherInGroup.getLastName());
                    } else {
                        System.out.println("No teacher found in group.");
                    }
                    break;

                case 10:
                    // Search Partial Teacher in Group
                    System.out.println("Enter group ID:");
                    int partialGroupId = scanner.nextInt();
                    System.out.println("Enter partial name to search:");
                    scanner.nextLine();  // Consume newline
                    String partialName = scanner.nextLine();
                    List<Teacher> partialTeachers = groupRepo.searchPartialInGroup(partialGroupId, partialName);
                    for (Teacher t : partialTeachers) {
                        System.out.println("Found teacher: " + t.getFirstName() + " " + t.getLastName());
                    }
                    break;

                case 11:
                    // Count Teachers by Condition in Group
                    System.out.println("Enter group ID:");
                    int groupIdForConditionCount = scanner.nextInt();
                    System.out.println("Enter condition (1 - PRESENT, 2 - DELEGATION, 3 - SICK, 4 - ABSENT):");
                    TeacherCondition conditionToCount = TeacherCondition.values()[scanner.nextInt() - 1];
                    int count = groupRepo.countByConditionInGroup(groupIdForConditionCount, conditionToCount);
                    System.out.println("Found " + count + " teacher(s) with condition " + conditionToCount + " in group.");
                    break;

                case 12:
                    // Add Rate to group
                    System.out.println("Enter group ID:");
                    int groupIdForRate = scanner.nextInt();
                    System.out.println("Enter rate value (0-6):");
                    int rateValue = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter comment (optional):");
                    String comment = scanner.nextLine();
                    if (comment == null) {
                        rateRepo.addRate(groupIdForRate, rateValue);
                    } else {
                        rateRepo.addRate(groupIdForRate, rateValue, comment);
                    }
                    break;

                case 13:
                    // Summary
                    groupRepo.summary();
                    break;

                case 14:
                    // Deleting group
                    System.out.println("Enter group ID:");
                    int groupIdForDelete = scanner.nextInt();
                    groupRepo.removeGroup(groupIdForDelete);
                    break;

                case 15:
                    // Exporting to cvs
                    exportDatabaseToCSV();
                    break;

                case 0:
                    // Exit
                    System.out.println("Exiting the application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static void exportDatabaseToCSV() {
        TeacherRepository teacherRepo = new TeacherRepository();
        GroupRepository groupRepo = new GroupRepository();
        RateRepository rateRepo = new RateRepository();

        try (FileWriter writer = new FileWriter("database_export.csv")) {
            writer.write("Teachers\n");
            List<Teacher> teachers = teacherRepo.getAllTeachers();
            for (Teacher teacher : teachers) {
                writer.write(teacher.getId() + "," + teacher.getFirstName() + "," + teacher.getLastName() + "," + teacher.getSalary() + "\n");
            }

            writer.write("\nGroups\n");
            List<Teachergroup> groups = groupRepo.getAllGroups();
            for (Teachergroup group : groups) {
                writer.write(group.getId() + "," + group.getName() + "," + group.getMaxTeachers() + "\n");
            }

            writer.write("\nRates\n");
            List<Rate> rates = rateRepo.getAllRates();
            for (Rate rate : rates) {
                writer.write(rate.getId() + "," + rate.getRate() + "," + rate.getComment() + "\n");
            }

            System.out.println("Database exported successfully to 'database_export.csv'.");
        } catch (IOException e) {
            System.out.println("Error exporting database to CSV: " + e.getMessage());
        }
    }
}
