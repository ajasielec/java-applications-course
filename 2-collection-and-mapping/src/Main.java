import com.sun.tools.jconsole.JConsoleContext;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // creating a class container
        ClassContainer container = new ClassContainer();

        // adding teachers groups
        container.addClass("Math", 5);
        container.addClass("English", 10);
        container.addClass("Music", 2);

        // creating teachers
        Teacher t1 = new Teacher("Anna", "Jasielec", TeacherCondition.absent, 2003, 6000);
        Teacher t2 = new Teacher("Adam", "Nowak", TeacherCondition.delegation, 1978, 1000);
        Teacher t3 = new Teacher("Jacek", "Adamus", TeacherCondition.absent, 1994, 3000);
        Teacher t4 = new Teacher("Ewa", "Werner", TeacherCondition.sick, 1983, 2000);

        // adding teachers to group
        container.getClassTeacher("Math").addTeacher(t1);
        container.getClassTeacher("Math").addTeacher(t4);
        container.getClassTeacher("English").addTeacher(t2);
        container.getClassTeacher("English").addTeacher(t3);
        container.getClassTeacher("English").addTeacher(t4);

        container.summary();

        // removing class
        container.removeClass("Math");
        // container.summary();

        // displaying empty classes
        List<ClassTeacher> emptyClasses = container.findEmpty();
        System.out.println("\nEmpty classes: ");
        emptyClasses.forEach(ClassTeacher::displayGroupName);

        // adding salary
        container.getClassTeacher("English").addSalary(t2, 4000);

        // removing a teacher
        container.getClassTeacher("English").removeTeacher(t4);
        container.getClassTeacher("English").summary();

        // changing condition
        container.getClassTeacher("English").changeCondition(t2, TeacherCondition.absent);

        // searching for a teacher
        Teacher foundTeacher =  container.getClassTeacher("English").searchTeacher("Nowak");
        System.out.println("\nfoundTeacher:");
        foundTeacher.printing();

        // searching partial
        ArrayList <Teacher> foundTeachers = container.getClassTeacher("English").searchPartial("Ad");
        System.out.println("\nfoundTeachers:");
        for (int i = 0; i < foundTeachers.size(); i++) {
            foundTeachers.get(i).printing();
        }

        // count by condition
        int absentCount = container.getClassTeacher("English").countByCondition(TeacherCondition.absent);
        System.out.println("\nAbsent count: " + absentCount);

        // sort by name
        container.getClassTeacher("English").addTeacher(t1);
        container.getClassTeacher("English").sortByName();
        System.out.println("\nsorted by name:");
        container.getClassTeacher("English").summary();

        // sort by salary
        container.getClassTeacher("English").sortBySalary();
        System.out.println("\nsorted by salary:");
        container.getClassTeacher("English").summary();
    }

}

