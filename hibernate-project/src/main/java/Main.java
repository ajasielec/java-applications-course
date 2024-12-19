import repository.GroupRepository;
import repository.TeacherRepository;
import entity.Teacher;
import entity.TeacherCondition;
import entity.Teachergroup;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        TeacherRepository teacherRepo = new TeacherRepository();

        // ADDING A TEACHER
        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Karol");
        teacher1.setLastName("Laudanski");
        teacher1.setSalary(120);
        teacher1.setBirthYear(1940);
        teacher1.setStatus(TeacherCondition.SICK);

//         teacherRepo.addTeacher(teacher1);

        // SEARCHING TEACHER BY ID
//        Teacher foundTeacher = teacherRepo.getTeacher(2);
//        if (foundTeacher != null) {
//            System.out.println("Founded teacher: " + foundTeacher.getFirstName() + " " + foundTeacher.getLastName());
//        } else {
//            System.out.println("No teacher found");
//        }

        // DELETING TEACHEr
//        teacherRepo.removeTeacher(10);


        GroupRepository groupRepo = new GroupRepository();

        // ADDING A GROUP
//        Teachergroup group = new Teachergroup();
//        group.setName("Biology");
//        group.setMaxTeachers(12);
//
//        groupRepo.addGroup(group);

        // DELETING A GROUP
//        groupRepo.removeGroup(8);

        // ADDING TEACHER TO GROUP
//        groupRepo.addTeacherToGroup(1, teacher1);

        // REMOVING TEACHER FROM GROUP
//        groupRepo.removeTeacherFromGroup(1, 13);

        // SEARCH TEACHER IN GROUP


    }
}
