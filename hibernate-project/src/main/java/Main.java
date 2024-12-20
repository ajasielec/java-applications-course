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

import java.util.List;


public class Main {
    public static void main(String[] args) {
        TeacherRepository teacherRepo = new TeacherRepository();
        GroupRepository groupRepo = new GroupRepository();
        RateRepository rateRepo = new RateRepository();

        // ADDING A TEACHER
        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Adam");
        teacher1.setLastName("Nowak");
        teacher1.setSalary(5000);
        teacher1.setBirthYear(1987);
        teacher1.setStatus(TeacherCondition.ABSENT);

//        teacherRepo.addTeacher(teacher1);

        // SEARCHING TEACHER BY ID
//        Teacher foundTeacher = teacherRepo.getTeacher(2);
//        if (foundTeacher != null) {
//            System.out.println("Found teacher: " + foundTeacher.getFirstName() + " " + foundTeacher.getLastName());
//        } else {
//            System.out.println("No teacher found");
//        }

        // DELETING TEACHER
//        teacherRepo.removeTeacher(20);



        // ADDING A GROUP
//        Teachergroup group = new Teachergroup();
//        group.setName("Biology");
//        group.setMaxTeachers(12);
//
//        groupRepo.addGroup(group);

        // DELETING A GROUP
//        groupRepo.removeGroup(8);

        // ADDING TEACHER TO GROUP
//        groupRepo.addTeacherToGroup(8, 20);

        // REMOVING TEACHER FROM GROUP
//        groupRepo.removeTeacherFromGroup(9, 5);

        // ADDING SALARY
//        groupRepo.addSalary(8, 3, 100);

        // CHANGE CONDITION
//        groupRepo.changeCondition(8, 3, TeacherCondition.SICK);

        // SEARCH TEACHER IN GROUP
//        Teacher foundTeacher = groupRepo.searchTeacher(8, "Smith");
//        if (foundTeacher != null) {
//            System.out.println("Found teacher: " + foundTeacher.getFirstName() + " " + foundTeacher.getLastName());
//        } else {
//            System.out.println("No teacher found");
//        }

        // SEARCH PARTIAL
        List<Teacher> foundTeachers = groupRepo.searchPartialInGroup(8, "A");
        for (Teacher teacher : foundTeachers) {
            System.out.println("Found teacher: " + teacher.getFirstName() + " " + teacher.getLastName());
        }

        // COUNT BY CONDITION
        int count = groupRepo.countByConditionInGroup(8, TeacherCondition.SICK);
        System.out.println("Found " + count + " sick teacher(s) in group");



        // RATING
//        rateRepo.addRate(8, 5, "Dobrze");
//        rateRepo.addRate(8, 1);
//        rateRepo.addRate(8, 4);


        // SUMMARY
        groupRepo.summary();



    }
}
