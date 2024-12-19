package repository;

import entity.Teachergroup;
import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class GroupRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void addGroup(Teachergroup group) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(group);
        em.getTransaction().commit();
        em.close();
    }

    public void removeGroup(int groupId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Teachergroup group = em.find(Teachergroup.class, groupId);
        if (group != null) {
            em.remove(group);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Teachergroup getGroup(int groupId) {
        EntityManager em = emf.createEntityManager();
        Teachergroup group = em.find(Teachergroup.class, groupId);
        em.close();
        return group;
    }

    public void addTeacherToGroup(int groupId, Teacher teacher) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Teachergroup group = em.find(Teachergroup.class, groupId);
        if (group != null) {
            teacher.setTeachergroup(group);
            // group.getTeachers().add(teacher);

            em.merge(group);
            em.persist(teacher);
        }

        em.getTransaction().commit();
        em.close();
    }

    public void removeTeacherFromGroup(int groupId, int teacherId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Teachergroup group = em.find(Teachergroup.class, groupId);
            Teacher teacher = em.find(Teacher.class, teacherId);
            if (group != null && teacher != null && teacher.getTeachergroup() != null && teacher.getTeachergroup().getId() == groupId) {
                teacher.setTeachergroup(null);

                em.merge(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }


}
