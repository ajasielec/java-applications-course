package repository;

import entity.Rate;
import entity.Teachergroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class RateRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void addRate(int groupId, int rate, String comment) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            entity.Teachergroup groupReference = em.getReference(entity.Teachergroup.class, groupId);

            Rate newRate = new Rate();
            newRate.setRate(rate);
            newRate.setComment(comment);
            newRate.setGroup(groupReference);

            em.persist(newRate);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void addRate(int groupId, int rate) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            entity.Teachergroup groupReference = em.getReference(entity.Teachergroup.class, groupId);


            Rate newRate = new Rate();
            newRate.setRate(rate);
            newRate.setGroup(groupReference);

            em.persist(newRate);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void removeRate(int rateId) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Rate rate = em.find(Rate.class, rateId);
            if(rate != null) {
                em.remove(rate);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Rate> getRatesForGroup(int groupId) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT r FROM Rate r WHERE r.group.id = :groupId";
            TypedQuery<Rate> query = em.createQuery(jpql, Rate.class);
            query.setParameter("groupId", groupId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Rate> getAllRates() {
        EntityManager em = emf.createEntityManager();
        List<Rate> rates = null;
        try {
            rates = em.createQuery("SELECT r FROM Rate r", Rate.class).getResultList();
        } catch (Exception e) {
            System.out.println("An error occurred while fetching all rates: " + e.getMessage());
        } finally {
            em.close();
        }
        return rates;
    }
}
