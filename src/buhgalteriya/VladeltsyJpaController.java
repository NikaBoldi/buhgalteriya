/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buhgalteriya;

import buhgalteriya.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Максим
 */
public class VladeltsyJpaController implements Serializable {

    public VladeltsyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vladeltsy vladeltsy) {
        if (vladeltsy.getSchetCollection() == null) {
            vladeltsy.setSchetCollection(new ArrayList<Schet>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Schet> attachedSchetCollection = new ArrayList<Schet>();
            for (Schet schetCollectionSchetToAttach : vladeltsy.getSchetCollection()) {
                schetCollectionSchetToAttach = em.getReference(schetCollectionSchetToAttach.getClass(), schetCollectionSchetToAttach.getId());
                attachedSchetCollection.add(schetCollectionSchetToAttach);
            }
            vladeltsy.setSchetCollection(attachedSchetCollection);
            em.persist(vladeltsy);
            for (Schet schetCollectionSchet : vladeltsy.getSchetCollection()) {
                Vladeltsy oldAccountholderOfSchetCollectionSchet = schetCollectionSchet.getAccountholder();
                schetCollectionSchet.setAccountholder(vladeltsy);
                schetCollectionSchet = em.merge(schetCollectionSchet);
                if (oldAccountholderOfSchetCollectionSchet != null) {
                    oldAccountholderOfSchetCollectionSchet.getSchetCollection().remove(schetCollectionSchet);
                    oldAccountholderOfSchetCollectionSchet = em.merge(oldAccountholderOfSchetCollectionSchet);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vladeltsy vladeltsy) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vladeltsy persistentVladeltsy = em.find(Vladeltsy.class, vladeltsy.getId());
            Collection<Schet> schetCollectionOld = persistentVladeltsy.getSchetCollection();
            Collection<Schet> schetCollectionNew = vladeltsy.getSchetCollection();
            Collection<Schet> attachedSchetCollectionNew = new ArrayList<Schet>();
            for (Schet schetCollectionNewSchetToAttach : schetCollectionNew) {
                schetCollectionNewSchetToAttach = em.getReference(schetCollectionNewSchetToAttach.getClass(), schetCollectionNewSchetToAttach.getId());
                attachedSchetCollectionNew.add(schetCollectionNewSchetToAttach);
            }
            schetCollectionNew = attachedSchetCollectionNew;
            vladeltsy.setSchetCollection(schetCollectionNew);
            vladeltsy = em.merge(vladeltsy);
            for (Schet schetCollectionOldSchet : schetCollectionOld) {
                if (!schetCollectionNew.contains(schetCollectionOldSchet)) {
                    schetCollectionOldSchet.setAccountholder(null);
                    schetCollectionOldSchet = em.merge(schetCollectionOldSchet);
                }
            }
            for (Schet schetCollectionNewSchet : schetCollectionNew) {
                if (!schetCollectionOld.contains(schetCollectionNewSchet)) {
                    Vladeltsy oldAccountholderOfSchetCollectionNewSchet = schetCollectionNewSchet.getAccountholder();
                    schetCollectionNewSchet.setAccountholder(vladeltsy);
                    schetCollectionNewSchet = em.merge(schetCollectionNewSchet);
                    if (oldAccountholderOfSchetCollectionNewSchet != null && !oldAccountholderOfSchetCollectionNewSchet.equals(vladeltsy)) {
                        oldAccountholderOfSchetCollectionNewSchet.getSchetCollection().remove(schetCollectionNewSchet);
                        oldAccountholderOfSchetCollectionNewSchet = em.merge(oldAccountholderOfSchetCollectionNewSchet);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vladeltsy.getId();
                if (findVladeltsy(id) == null) {
                    throw new NonexistentEntityException("The vladeltsy with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vladeltsy vladeltsy;
            try {
                vladeltsy = em.getReference(Vladeltsy.class, id);
                vladeltsy.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vladeltsy with id " + id + " no longer exists.", enfe);
            }
            Collection<Schet> schetCollection = vladeltsy.getSchetCollection();
            for (Schet schetCollectionSchet : schetCollection) {
                schetCollectionSchet.setAccountholder(null);
                schetCollectionSchet = em.merge(schetCollectionSchet);
            }
            em.remove(vladeltsy);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vladeltsy> findVladeltsyEntities() {
        return findVladeltsyEntities(true, -1, -1);
    }

    public List<Vladeltsy> findVladeltsyEntities(int maxResults, int firstResult) {
        return findVladeltsyEntities(false, maxResults, firstResult);
    }

    private List<Vladeltsy> findVladeltsyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vladeltsy.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vladeltsy findVladeltsy(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vladeltsy.class, id);
        } finally {
            em.close();
        }
    }

    public int getVladeltsyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vladeltsy> rt = cq.from(Vladeltsy.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
