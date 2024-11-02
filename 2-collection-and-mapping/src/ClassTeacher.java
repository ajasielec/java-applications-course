import java.util.*;


public class ClassTeacher {
    private String groupName;
    private List<Teacher> teachers;
    private int maxTeachers;

    public ClassTeacher(String groupName, int maxTeachers) {
        this.groupName = groupName;
        this.teachers = new ArrayList<>();
        this.maxTeachers = maxTeachers;
    }

    public int getTeacherCount() {
        return teachers.size();
    }
    public int getMaxTeachers() {
        return maxTeachers;
    }
    public void displayGroupName() {
        System.out.println(groupName);
    }

    public void addTeacher(Teacher teacher){
        if (teachers.size() >= maxTeachers){
            System.out.println("Teacher is over max teachers!");
        }else if(teachers.contains(teacher)){
            System.out.println("Teacher already exists!");
        } else {
            teachers.add(teacher);
        }
    }

    public void addSalary(Teacher teacher, int amount){
        if(teachers.contains(teacher)){
            teacher.setSalary(amount);
        } else {
            System.out.println("Teacher is not in this group!");
        }
    }

    public void removeTeacher(Teacher teacher){
        teachers.remove(teacher);
    }

    public void changeCondition(Teacher teacher, TeacherCondition newCondition){
        if (teachers.contains(teacher)) {
            teacher.setCondition(newCondition);
        } else {
            System.out.println("Teacher is not in this group!");
        }
    }

    public Teacher searchTeacher(String lastName){
        for (int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getLastName().equals(lastName)){
                return teachers.get(i);
            }
        }
        return null;
    }

    public ArrayList<Teacher> searchPartial(String partial){
        ArrayList<Teacher> foundTeachers = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getFirstName().contains(partial) || teachers.get(i).getLastName().contains(partial)){
                foundTeachers.add(teachers.get(i));
            }
        }
        return foundTeachers;
    }

    public int countByCondition(TeacherCondition tc){
        int counter = 0;
        for (int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getCondition() == tc){
                counter++;
            }
        }
        return counter;
    }

    public void summary(){
        System.out.println("Teachers in group " + groupName);
        teachers.forEach(Teacher::printing);
    }

    public void sortByName() {
        teachers.sort(Comparator.comparing(t->t.getFirstName()));
    }

    public void sortBySalary(){
        teachers.sort(Comparator.comparing(Teacher::getSalary).reversed());
    }

    public Teacher max(){
        return Collections.max(teachers, Comparator.comparing(Teacher::getSalary));
    }

}
