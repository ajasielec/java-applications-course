import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassContainer {
    private Map<String, ClassTeacher> classMap;

    public ClassContainer() {
        this.classMap = new HashMap<>();
    }

    public ClassTeacher getClassTeacher(String className) {
        return classMap.get(className);
    }

    public void addClass(String className, int maxTeachers) {
        ClassTeacher classTeacher = new ClassTeacher(className, maxTeachers);
        this.classMap.put(className, classTeacher);
    }

    public void removeClass(String className) {
        this.classMap.remove(className);
    }

    public List<ClassTeacher> findEmpty() {
        return classMap.values().stream()
                .filter(classTeacher -> classTeacher.getTeacherCount() == 0)
                .collect(Collectors.toList());
    }

    public void summary(){
        for (Map.Entry<String, ClassTeacher> entry : classMap.entrySet()){
            double percentage = (double)entry.getValue().getTeacherCount() / (double)entry.getValue().getMaxTeachers() * 100;
            System.out.println(entry.getKey() + ": " + (int)percentage + "%");
        }
    }
}
