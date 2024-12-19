import entity.Teacher;
import entity.Teachergroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Teachergroup teachergroup = new Teachergroup();
            teachergroup.setName("Dupa");
            teachergroup.setMaxTeachers(12);
            teachergroup.setCapacity(12);

            entityManager.persist(teachergroup);

            Teacher teacher1 = new Teacher();
            teacher1.setFirstName("John");
            teacher1.setLastName("Smith");
            teacher1.setSalary(12000);
            teacher1.setBirthYear(1999);
            teacher1.setStatus("SICK");
            teacher1.setTeachergroup(teachergroup);

            entityManager.persist(teacher1);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
