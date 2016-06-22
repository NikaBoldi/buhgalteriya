/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buhgalteriya;

import buhgalteriya.exceptions.NonexistentEntityException;
import buhgalteriya.exceptions.PreexistingEntityException;
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
public class TipValyutyJpaController implements Serializable {

    public TipValyutyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipValyuty tipValyuty) throws PreexistingEntityException, Exception {
        if (tipValyuty.getSchetCollection() == null) {
            tipValyuty.setSchetCollection(new ArrayList<Schet>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Schet> attachedSchetCollection = new ArrayList<Schet>();
            for (Schet schetCollectionSchetToAttach : tipValyuty.getSchetCollection()) {
                schetCollectionSchetToAttach = em.getReference(schetCollectionSchetToAttach.getClass(), schetCollectionSchetToAttach.getId());
                attachedSchetCollection.add(schetCollectionSchetToAttach);
            }
            tipValyuty.setSchetCollection(attachedSchetCollection);
            em.persist(tipValyuty);
            for (Schet schetCollectionSchet : tipValyuty.getSchetCollection()) {
                TipValyuty oldTipvalutOfSchetCollectionSchet = schetCollectionSchet.getTipvalut();
                schetCollectionSchet.setTipvalut(tipValyuty);
                schetCollectionSchet = em.merge(schetCollectionSchet);
                if (oldTipvalutOfSchetCollectionSchet != null) {
                    oldTipvalutOfSchetCollectionSchet.getSchetCollection().remove(schetCollectionSchet);
                    oldTipvalutOfSchetCollectionSchet = em.merge(oldTipvalutOfSchetCollectionSchet);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipValyuty(tipValyuty.getId()) != null) {
                throw new PreexistingEntityException("TipValyuty " + tipValyuty + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipValyuty tipValyuty) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipValyuty persistentTipValyuty = em.find(TipValyuty.class, tipValyuty.getId());
            Collection<Schet> schetCollectionOld = persistentTipValyuty.getSchetCollection();
            Collection<Schet> schetCollectionNew = tipValyuty.getSchetCollection();
            Collection<Schet> attachedSchetCollectionNew = new ArrayList<Schet>();
            for (Schet schetCollectionNewSchetToAttach : schetCollectionNew) {
                schetCollectionNewSchetToAttach = em.getReference(schetCollectionNewSchetToAttach.getClass(), schetCollectionNewSchetToAttach.getId());
                attachedSchetCollectionNew.add(schetCollectionNewSchetToAttach);
            }
            schetCollectionNew = attachedSchetCollectionNew;
            tipValyuty.setSchetCollection(schetCollectionNew);
            tipValyuty = em.merge(tipValyuty);
            for (Schet schetCollectionOldSchet : schetCollectionOld) {
                if (!schetCollectionNew.contains(schetCollectionOldSchet)) {
                    schetCollectionOldSchet.setTipvalut(null);
                    schetCollectionOldSchet = em.merge(schetCollectionOldSchet);
                }
            }
            for (Schet schetCollectionNewSchet : schetCollectionNew) {
                if (!schetCollectionOld.contains(schetCollectionNewSchet)) {
                    TipValyuty oldTipvalutOfSchetCollectionNewSchet = schetCollectionNewSchet.getTipvalut();
                    schetCollectionNewSchet.setTipvalut(tipValyuty);
                    schetCollectionNewSchet = em.merge(schetCollectionNewSchet);
                    if (oldTipvalutOfSchetCollectionNewSchet != null && !oldTipvalutOfSchetCollectionNewSchet.equals(tipValyuty)) {
                        oldTipvalutOfSchetCollectionNewSchet.getSchetCollection().remove(schetCollectionNewSchet);
                        oldTipvalutOfSchetCollectionNewSchet = em.merge(oldTipvalutOfSchetCollectionNewSchet);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipValyuty.getId();
                if (findTipValyuty(id) == null) {
                    throw new NonexistentEntityException("The tipValyuty with id " + id + " no longer exists.");
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
            TipValyuty tipValyuty;
            try {
                tipValyuty = em.getReference(TipValyuty.class, id);
                tipValyuty.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipValyuty with id " + id + " no longer exists.", enfe);
            }
            Collection<Schet> schetCollection = tipValyuty.getSchetCollection();
            for (Schet schetCollectionSchet : schetCollection) {
                schetCollectionSchet.setTipvalut(null);
                schetCollectionSchet = em.merge(schetCollectionSchet);
            }
            em.remove(tipValyuty);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipValyuty> findTipValyutyEntities() {
        return findTipValyutyEntities(true, -1, -1);
    }

    public List<TipValyuty> findTipValyutyEntities(int maxResults, int firstResult) {
        return findTipValyutyEntities(false, maxResults, firstResult);
    }

    private List<TipValyuty> findTipValyutyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipValyuty.class));
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

    public TipValyuty findTipValyuty(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipValyuty.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipValyutyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipValyuty> rt = cq.from(TipValyuty.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
