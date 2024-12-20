package repository;

import entity.TeacherCondition;
import entity.Teachergroup;
import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import org.hibernate.query.Query;

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

    public void addTeacherToGroup(int groupId, int teacherId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try{
            Teachergroup group = em.find(Teachergroup.class, groupId);
            Teacher teacher = em.find(Teacher.class, teacherId);

            if (group != null) {
                // checking if teacher exists in this group
                String queryCheckTeacherInGroup = "SELECT COUNT(t) FROM Teacher t WHERE t.id = :teacherId AND t.teachergroup.id = :groupId";
                Query checkTeacherQuery = (Query) em.createQuery(queryCheckTeacherInGroup);
                checkTeacherQuery.setParameter("teacherId", teacherId);
                checkTeacherQuery.setParameter("groupId", groupId);
                Long isTeacherInGroup = (Long) checkTeacherQuery.getSingleResult();

                if (isTeacherInGroup > 0) {
                    System.out.println("Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " is already in group " + group.getName() + ".");
                    em.getTransaction().rollback();
                    return;
                }

                // counting teachers in group
                String queryTeachersCount = "SELECT COUNT(t) FROM Teacher t WHERE t.teachergroup.id = :groupId";
                Query teachersCountQuery = (Query) em.createQuery(queryTeachersCount);
                teachersCountQuery.setParameter("groupId", groupId);
                Long teachersCount = (Long) teachersCountQuery.getSingleResult();

                if (teachersCount < group.getMaxTeachers()) {
                    teacher.setTeachergroup(group);
                    em.persist(teacher);
                    em.getTransaction().commit();
                    System.out.println("Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " successfully added to group " + group.getName());
                } else {
                    System.out.println("Cannot add teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " to group " + group.getName() +
                            " - maximum number of teachers (" + group.getMaxTeachers() + ") has been reached.");
                    em.getTransaction().rollback();
                }
            } else {
                System.out.println("Group with ID " + groupId + " not found.");
                em.getTransaction().rollback();
            }
        } catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally{
                em.close();
        }


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
            // counting teachers
            String queryStr = "SELECT COUNT(t) FROM Teacher t WHERE t.teachergroup.id = :groupId";
            Query query = (Query) em.createQuery(queryStr);
            query.setParameter("groupId", group.getId());

            Long totalTeachers = (Long) query.getSingleResult();

            // calculating occupancy
            int maxTeachers = group.getMaxTeachers();
            double occupancy = (double) totalTeachers / maxTeachers * 100;

            // calculating average rate and its count
            String queryRatesSummary = "SELECT COUNT(r), COALESCE(AVG(r.rate), 0) " +
                                        "FROM Rate r " +
                                        "WHERE r.group.id = :groupId";
            Query rateSummaryQuery = (Query) em.createQuery(queryRatesSummary);
            rateSummaryQuery.setParameter("groupId", group.getId());
            Object[] rateSummaryResult = (Object[]) rateSummaryQuery.getSingleResult();
            Long totalRates = (Long) rateSummaryResult[0];
            Double averageRate = (Double) rateSummaryResult[1];

            System.out.println("Group: " + group.getName());
            System.out.println("Occupancy: " + String.format("%.2f", occupancy) + "%");
            System.out.println("Number of rates: " + totalRates);
            System.out.println("Average rate: " + String.format("%.2f", averageRate));
            System.out.println("---------------------------------------------");
        }
        em.close();
    }


    public List<Teachergroup> getAllGroups() {
        EntityManager em = emf.createEntityManager();
        List<Teachergroup> groups = null;
        try {
            groups = em.createQuery("SELECT g FROM Teachergroup g", Teachergroup.class).getResultList();
        } catch (Exception e) {
            System.out.println("An error occurred while fetching all groups: " + e.getMessage());
        } finally {
            em.close();
        }
        return groups;
    }
}
