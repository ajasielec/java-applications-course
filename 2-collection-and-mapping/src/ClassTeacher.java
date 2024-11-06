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
            teacher.setSalary(teacher.getSalary() + amount);
        } else {
            System.out.println("Teacher is not in this group!");
        }
    }

    public void removeTeacher(Teacher teacher){
        if (teachers.contains(teacher)){
            teachers.remove(teacher);
        } else {
            System.out.println("Teacher is not in this group!");
        }
    }

    public void changeCondition(Teacher teacher, TeacherCondition newCondition){
        if (teachers.contains(teacher)) {
            teacher.setCondition(newCondition);
        } else {
            System.out.println("Teacher is not in this group!");
        }
    }

    public Teacher searchTeacher(String lastName){
        Teacher teacher = new Teacher("", lastName);
        for (int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).compareTo(teacher) == 0){
                return teachers.get(i);
            }
        }
        System.out.println("Teacher is not in this group!");
        return null;
    }

    public ArrayList<Teacher> searchPartial(String partial){
        ArrayList<Teacher> foundTeachers = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getFirstName().toLowerCase().contains(partial.toLowerCase()) || teachers.get(i).getLastName().toLowerCase().contains(partial.toLowerCase())){
                foundTeachers.add(teachers.get(i));
            }
        }
        return foundTeachers;
    }

    public int countByCondition(TeacherCondition tc){
        return (int) teachers.stream()
                .filter(teacher -> teacher.getCondition() == tc)
                .count();
    }

    public void summary(){
        System.out.println("Teachers in group " + groupName);
        teachers.forEach(Teacher::printing);
    }

    public void sortByName() {
        Collections.sort(teachers);
    }

    public void sortBySalary(){
        Collections.sort(teachers, (t1, t2) -> {
            return Integer.compare(t2.getSalary(), t1.getSalary());
        });
    }

    public Teacher max(){
        if (teachers.isEmpty()){
            System.out.println("Teachers list is empty!");
            return null;
        }
        return Collections.max(teachers, (t1, t2) -> {
            return Integer.compare(t1.getSalary(), t2.getSalary());
        });
    }

}
