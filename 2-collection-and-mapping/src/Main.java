import com.sun.tools.jconsole.JConsoleContext;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // creating a class container
        ClassContainer container = new ClassContainer();

        // adding class to container
        container.addClass("Math", 4);
        container.addClass("English", 5);
        container.addClass("Music", 2);
        container.addClass("a", 2);


        // creating teachers
        Teacher t1 = new Teacher("Anna", "Jasielec", TeacherCondition.absent, 2003, 6000);
        Teacher t2 = new Teacher("Adam", "Nowak", TeacherCondition.delegation, 1978, 1000);
        Teacher t3 = new Teacher("Jacek", "Adamus", TeacherCondition.absent, 1994, 3000);
        Teacher t4 = new Teacher("Danuta", "Werner", TeacherCondition.sick, 1983, 2000);
        Teacher t5 = new Teacher("Karol", "Laudanski", TeacherCondition.present, 1967, 4000);

        // adding teachers to the groups
        System.out.println("Adding teachers to the groups:");
        container.getClassTeacher("Music").addTeacher(t1);
        container.getClassTeacher("Music").addTeacher(t4);
        container.getClassTeacher("English").addTeacher(t1);
        container.getClassTeacher("English").addTeacher(t2);
        container.getClassTeacher("English").addTeacher(t3);
        container.getClassTeacher("English").addTeacher(t4);
        container.getClassTeacher("Math").addTeacher(t5);

//        // adding existing teacher
//        container.getClassTeacher("English").addTeacher(t4);
//
//        // adding teacher when max is reached
//        container.getClassTeacher("Music").addTeacher(t2);
//
//        // adding salary
//        System.out.println("\nTeacher before adding salary:");
//        t2.printing();
//        container.getClassTeacher("English").addSalary(t2, 4000);
//        System.out.println("Teacher after adding salary:");
//        t2.printing();
//
//        // removing teacher
//        System.out.println("\nBefore removing a teacher:");
//        container.getClassTeacher("Math").summary();
//        container.getClassTeacher("Math").removeTeacher(t5);
//        System.out.println("\nAfter removing a teacher:");
//        container.getClassTeacher("Math").summary();
//
//        // changing condition
//        container.getClassTeacher("English").changeCondition(t2, TeacherCondition.present);
//        System.out.println("\nTeacher after changing a condition:");
//        t2.printing();
//
//        // searching for a teacher
//        System.out.println("\nSearch teacher result:");
//        Teacher foundTeacher =  container.getClassTeacher("English").searchTeacher("Nowak");
//        if (foundTeacher != null) foundTeacher.printing();
//
        // searching partial
        System.out.println("\nSearch partial result:");
        ArrayList <Teacher> foundTeachers = container.getClassTeacher("English").searchPartial("er");
        for (int i = 0; i < foundTeachers.size(); i++) {
            foundTeachers.get(i).printing();
        }
//
//        // count by condition
//        int absentCount = container.getClassTeacher("English").countByCondition(TeacherCondition.absent);
//        System.out.println("\nAbsent count: " + absentCount);
//
//        // summary
//        System.out.println("\nSummary:");
//        container.getClassTeacher("English").summary();
//
//        // sort by name
//        container.getClassTeacher("English").sortByName();
//        System.out.println("\nsorted by name:");
//        container.getClassTeacher("English").summary();
//
        // sort by salary
        container.getClassTeacher("English").sortBySalary();
        System.out.println("\nsorted by salary:");
        container.getClassTeacher("English").summary();
//
//        // max
//        System.out.println("\nTeacher with max salary:");
//        Teacher max = container.getClassTeacher("English").max();
//        if (max != null) max.printing();
//
//        // add class
//        container.addClass("Biology", 3);
//
        // displaying empty classes
        List<ClassTeacher> emptyClasses = container.findEmpty();
        System.out.println("\nEmpty classes: ");
        emptyClasses.forEach(ClassTeacher::displayGroupName);
//
//        // removing class
//        container.removeClass("Math");
//
        // summary
        System.out.println("\nGroups in container:");
        container.summary();
    }

}

