package repository;

import entity.TeacherCondition;
import entity.Teachergroup;
import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
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

    public void addSalary(int groupId, int teacherId, int amount) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT t FROM Teacher t WHERE t.teachergroup.id = :groupId AND t.id = :teacherId";
        Query query = (Query) em.createQuery(jpql);

        query.setParameter("groupId", groupId);
        query.setParameter("teacherId", teacherId);

        Teacher teacher = null;
        try {
            teacher = (Teacher) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Teacher not found in this group!");
        }

        if (teacher != null) {
            em.getTransaction().begin();
            teacher.setSalary(teacher.getSalary() + amount);
            em.getTransaction().commit();
        } else {
            System.out.println("Teacher is not in this group!");
        }

        em.close();
    }

    public void changeCondition(int groupId, int teacherId, TeacherCondition newCondition) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT t FROM Teacher t WHERE t.teachergroup.id = :groupId AND t.id = :teacherId";
        Query query = (Query) em.createQuery(jpql);

        query.setParameter("groupId", groupId);
        query.setParameter("teacherId", teacherId);

        Teacher teacher = null;
        try {
            teacher = (Teacher) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Teacher not found in this group!");
        }

        if (teacher != null) {
            em.getTransaction().begin();
            teacher.setStatus(newCondition);
            em.getTransaction().commit();
        } else {
            System.out.println("Teacher is not in this group!");
        }

        em.close();
    }


    public Teacher searchTeacher(int groupId, String name) {
        EntityManager em = emf.createEntityManager();
        Teacher foundTeacher = null;

        try {
            // zapytanie JPQL
            String jpql = "SELECT t FROM Teacher t WHERE t.teachergroup.id = :groupId AND (t.firstName = :name OR t.lastName = :name)";
            Query query = (Query) em.createQuery(jpql);
            query.setParameter("groupId", groupId);
            query.setParameter("name", name);

            foundTeacher = (Teacher) query.getSingleResult();
        } catch (NoResultException e) {
            foundTeacher = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return foundTeacher;
    }

    public List<Teacher> searchPartialInGroup(int groupId, String partial) {
        EntityManager em = emf.createEntityManager();

        // query
        String jpql = "SELECT t FROM Teacher t WHERE t.teachergroup.id = :groupId AND (LOWER(t.firstName) LIKE :partial OR LOWER(t.lastName) LIKE :partial)";
        Query query = (Query) em.createQuery(jpql);

        query.setParameter("groupId", groupId);
        query.setParameter("partial", "%" + partial.toLowerCase() + "%");

        List<Teacher> foundTeachers = query.getResultList();

        em.close();

        return foundTeachers;
    }

    public int countByConditionInGroup(int groupId, TeacherCondition tc) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT COUNT(t) FROM Teacher t WHERE t.teachergroup.id = :groupId AND t.status = :tc";
        Query query = (Query) em.createQuery(jpql);

        query.setParameter("groupId", groupId);
        query.setParameter("tc", tc);

        Long count = (Long) query.getSingleResult();

        em.close();

        return count.intValue();
    }

    public void summary() {
        EntityManager em = emf.createEntityManager();

        List<Teachergroup> groups = em.createQuery("SELECT g FROM Teachergroup g", Teachergroup.class).getResultList();

        for (Teachergroup group : groups) {
            String queryStr = "SELECT COUNT(t) FROM Teacher t WHERE t.teachergroup.id = :groupId";
            Query query = (Query) em.createQuery(queryStr);
            query.setParameter("groupId", group.getId());

            Long totalTeachers = (Long) query.getSingleResult();

            int maxTeachers = group.getMaxTeachers();

            double occupancy = (double) totalTeachers / maxTeachers * 100;

            System.out.println("Group: " + group.getName() + " - " + String.format("%.2f", occupancy) + "% occupancy");
        }
        em.close();
    }




}
