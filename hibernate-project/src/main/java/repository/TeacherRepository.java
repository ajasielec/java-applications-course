package repository;

import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class TeacherRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void addTeacher(Teacher teacher) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try{
        // checking if this teacher exists in table
        String queryCheckTeacher = "SELECT COUNT(t) FROM Teacher t WHERE t.firstName = :firstName AND t.lastName = :lastName";
        Query checkTeacherQuery = em.createQuery(queryCheckTeacher);
        checkTeacherQuery.setParameter("firstName", teacher.getFirstName());
        checkTeacherQuery.setParameter("lastName", teacher.getLastName());

        Long teacherExists = (Long) checkTeacherQuery.getSingleResult();

        if (teacherExists > 0) {
            System.out.println("Cannot add. Teacher " + teacher.getFirstName() + " " + teacher.getLastName() +
                    " already exists.");
            em.getTransaction().rollback();
        } else {
            em.persist(teacher);
            em.getTransaction().commit();
        }
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    } finally {
        em.close();
    }
    }

    public void removeTeacher(int teacherId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Teacher teacher = em.find(Teacher.class, teacherId);
        if (teacher != null) {
            em.remove(teacher);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Teacher getTeacher(int teacherId) {
        EntityManager em = emf.createEntityManager();
        Teacher teacher = em.find(Teacher.class, teacherId);
        em.close();
        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        EntityManager em = emf.createEntityManager();
        List<Teacher> teachers = em.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
        em.close();
        return teachers;
    }

    public void updateTeacher(Teacher teacher) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(teacher);
        em.getTransaction().commit();
        em.close();
    }
}
