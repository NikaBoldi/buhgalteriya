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
public class EdenitsyIzmereniyaJpaController implements Serializable {

    public EdenitsyIzmereniyaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EdenitsyIzmereniya edenitsyIzmereniya) {
        if (edenitsyIzmereniya.getRaskhodyCollection() == null) {
            edenitsyIzmereniya.setRaskhodyCollection(new ArrayList<Raskhody>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Raskhody> attachedRaskhodyCollection = new ArrayList<Raskhody>();
            for (Raskhody raskhodyCollectionRaskhodyToAttach : edenitsyIzmereniya.getRaskhodyCollection()) {
                raskhodyCollectionRaskhodyToAttach = em.getReference(raskhodyCollectionRaskhodyToAttach.getClass(), raskhodyCollectionRaskhodyToAttach.getId());
                attachedRaskhodyCollection.add(raskhodyCollectionRaskhodyToAttach);
            }
            edenitsyIzmereniya.setRaskhodyCollection(attachedRaskhodyCollection);
            em.persist(edenitsyIzmereniya);
            for (Raskhody raskhodyCollectionRaskhody : edenitsyIzmereniya.getRaskhodyCollection()) {
                EdenitsyIzmereniya oldUnitsOfRaskhodyCollectionRaskhody = raskhodyCollectionRaskhody.getUnits();
                raskhodyCollectionRaskhody.setUnits(edenitsyIzmereniya);
                raskhodyCollectionRaskhody = em.merge(raskhodyCollectionRaskhody);
                if (oldUnitsOfRaskhodyCollectionRaskhody != null) {
                    oldUnitsOfRaskhodyCollectionRaskhody.getRaskhodyCollection().remove(raskhodyCollectionRaskhody);
                    oldUnitsOfRaskhodyCollectionRaskhody = em.merge(oldUnitsOfRaskhodyCollectionRaskhody);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EdenitsyIzmereniya edenitsyIzmereniya) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EdenitsyIzmereniya persistentEdenitsyIzmereniya = em.find(EdenitsyIzmereniya.class, edenitsyIzmereniya.getId());
            Collection<Raskhody> raskhodyCollectionOld = persistentEdenitsyIzmereniya.getRaskhodyCollection();
            Collection<Raskhody> raskhodyCollectionNew = edenitsyIzmereniya.getRaskhodyCollection();
            Collection<Raskhody> attachedRaskhodyCollectionNew = new ArrayList<Raskhody>();
            for (Raskhody raskhodyCollectionNewRaskhodyToAttach : raskhodyCollectionNew) {
                raskhodyCollectionNewRaskhodyToAttach = em.getReference(raskhodyCollectionNewRaskhodyToAttach.getClass(), raskhodyCollectionNewRaskhodyToAttach.getId());
                attachedRaskhodyCollectionNew.add(raskhodyCollectionNewRaskhodyToAttach);
            }
            raskhodyCollectionNew = attachedRaskhodyCollectionNew;
            edenitsyIzmereniya.setRaskhodyCollection(raskhodyCollectionNew);
            edenitsyIzmereniya = em.merge(edenitsyIzmereniya);
            for (Raskhody raskhodyCollectionOldRaskhody : raskhodyCollectionOld) {
                if (!raskhodyCollectionNew.contains(raskhodyCollectionOldRaskhody)) {
                    raskhodyCollectionOldRaskhody.setUnits(null);
                    raskhodyCollectionOldRaskhody = em.merge(raskhodyCollectionOldRaskhody);
                }
            }
            for (Raskhody raskhodyCollectionNewRaskhody : raskhodyCollectionNew) {
                if (!raskhodyCollectionOld.contains(raskhodyCollectionNewRaskhody)) {
                    EdenitsyIzmereniya oldUnitsOfRaskhodyCollectionNewRaskhody = raskhodyCollectionNewRaskhody.getUnits();
                    raskhodyCollectionNewRaskhody.setUnits(edenitsyIzmereniya);
                    raskhodyCollectionNewRaskhody = em.merge(raskhodyCollectionNewRaskhody);
                    if (oldUnitsOfRaskhodyCollectionNewRaskhody != null && !oldUnitsOfRaskhodyCollectionNewRaskhody.equals(edenitsyIzmereniya)) {
                        oldUnitsOfRaskhodyCollectionNewRaskhody.getRaskhodyCollection().remove(raskhodyCollectionNewRaskhody);
                        oldUnitsOfRaskhodyCollectionNewRaskhody = em.merge(oldUnitsOfRaskhodyCollectionNewRaskhody);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = edenitsyIzmereniya.getId();
                if (findEdenitsyIzmereniya(id) == null) {
                    throw new NonexistentEntityException("The edenitsyIzmereniya with id " + id + " no longer exists.");
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
            EdenitsyIzmereniya edenitsyIzmereniya;
            try {
                edenitsyIzmereniya = em.getReference(EdenitsyIzmereniya.class, id);
                edenitsyIzmereniya.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The edenitsyIzmereniya with id " + id + " no longer exists.", enfe);
            }
            Collection<Raskhody> raskhodyCollection = edenitsyIzmereniya.getRaskhodyCollection();
            for (Raskhody raskhodyCollectionRaskhody : raskhodyCollection) {
                raskhodyCollectionRaskhody.setUnits(null);
                raskhodyCollectionRaskhody = em.merge(raskhodyCollectionRaskhody);
            }
            em.remove(edenitsyIzmereniya);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EdenitsyIzmereniya> findEdenitsyIzmereniyaEntities() {
        return findEdenitsyIzmereniyaEntities(true, -1, -1);
    }

    public List<EdenitsyIzmereniya> findEdenitsyIzmereniyaEntities(int maxResults, int firstResult) {
        return findEdenitsyIzmereniyaEntities(false, maxResults, firstResult);
    }

    private List<EdenitsyIzmereniya> findEdenitsyIzmereniyaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EdenitsyIzmereniya.class));
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

    public EdenitsyIzmereniya findEdenitsyIzmereniya(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EdenitsyIzmereniya.class, id);
        } finally {
            em.close();
        }
    }

    public int getEdenitsyIzmereniyaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EdenitsyIzmereniya> rt = cq.from(EdenitsyIzmereniya.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
