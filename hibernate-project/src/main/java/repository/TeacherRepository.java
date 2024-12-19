package repository;

import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TeacherRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void addTeacher(Teacher teacher) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
        em.close();
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
