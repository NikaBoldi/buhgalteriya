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
public class KategoriiRaskhodovJpaController implements Serializable {

    public KategoriiRaskhodovJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KategoriiRaskhodov kategoriiRaskhodov) {
        if (kategoriiRaskhodov.getPodkategoriiRaskhodovCollection() == null) {
            kategoriiRaskhodov.setPodkategoriiRaskhodovCollection(new ArrayList<PodkategoriiRaskhodov>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PodkategoriiRaskhodov> attachedPodkategoriiRaskhodovCollection = new ArrayList<PodkategoriiRaskhodov>();
            for (PodkategoriiRaskhodov podkategoriiRaskhodovCollectionPodkategoriiRaskhodovToAttach : kategoriiRaskhodov.getPodkategoriiRaskhodovCollection()) {
                podkategoriiRaskhodovCollectionPodkategoriiRaskhodovToAttach = em.getReference(podkategoriiRaskhodovCollectionPodkategoriiRaskhodovToAttach.getClass(), podkategoriiRaskhodovCollectionPodkategoriiRaskhodovToAttach.getIDsubcategory());
                attachedPodkategoriiRaskhodovCollection.add(podkategoriiRaskhodovCollectionPodkategoriiRaskhodovToAttach);
            }
            kategoriiRaskhodov.setPodkategoriiRaskhodovCollection(attachedPodkategoriiRaskhodovCollection);
            em.persist(kategoriiRaskhodov);
            for (PodkategoriiRaskhodov podkategoriiRaskhodovCollectionPodkategoriiRaskhodov : kategoriiRaskhodov.getPodkategoriiRaskhodovCollection()) {
                KategoriiRaskhodov oldIDcategoryOfPodkategoriiRaskhodovCollectionPodkategoriiRaskhodov = podkategoriiRaskhodovCollectionPodkategoriiRaskhodov.getIDcategory();
                podkategoriiRaskhodovCollectionPodkategoriiRaskhodov.setIDcategory(kategoriiRaskhodov);
                podkategoriiRaskhodovCollectionPodkategoriiRaskhodov = em.merge(podkategoriiRaskhodovCollectionPodkategoriiRaskhodov);
                if (oldIDcategoryOfPodkategoriiRaskhodovCollectionPodkategoriiRaskhodov != null) {
                    oldIDcategoryOfPodkategoriiRaskhodovCollectionPodkategoriiRaskhodov.getPodkategoriiRaskhodovCollection().remove(podkategoriiRaskhodovCollectionPodkategoriiRaskhodov);
                    oldIDcategoryOfPodkategoriiRaskhodovCollectionPodkategoriiRaskhodov = em.merge(oldIDcategoryOfPodkategoriiRaskhodovCollectionPodkategoriiRaskhodov);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KategoriiRaskhodov kategoriiRaskhodov) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KategoriiRaskhodov persistentKategoriiRaskhodov = em.find(KategoriiRaskhodov.class, kategoriiRaskhodov.getId());
            Collection<PodkategoriiRaskhodov> podkategoriiRaskhodovCollectionOld = persistentKategoriiRaskhodov.getPodkategoriiRaskhodovCollection();
            Collection<PodkategoriiRaskhodov> podkategoriiRaskhodovCollectionNew = kategoriiRaskhodov.getPodkategoriiRaskhodovCollection();
            Collection<PodkategoriiRaskhodov> attachedPodkategoriiRaskhodovCollectionNew = new ArrayList<PodkategoriiRaskhodov>();
            for (PodkategoriiRaskhodov podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodovToAttach : podkategoriiRaskhodovCollectionNew) {
                podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodovToAttach = em.getReference(podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodovToAttach.getClass(), podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodovToAttach.getIDsubcategory());
                attachedPodkategoriiRaskhodovCollectionNew.add(podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodovToAttach);
            }
            podkategoriiRaskhodovCollectionNew = attachedPodkategoriiRaskhodovCollectionNew;
            kategoriiRaskhodov.setPodkategoriiRaskhodovCollection(podkategoriiRaskhodovCollectionNew);
            kategoriiRaskhodov = em.merge(kategoriiRaskhodov);
            for (PodkategoriiRaskhodov podkategoriiRaskhodovCollectionOldPodkategoriiRaskhodov : podkategoriiRaskhodovCollectionOld) {
                if (!podkategoriiRaskhodovCollectionNew.contains(podkategoriiRaskhodovCollectionOldPodkategoriiRaskhodov)) {
                    podkategoriiRaskhodovCollectionOldPodkategoriiRaskhodov.setIDcategory(null);
                    podkategoriiRaskhodovCollectionOldPodkategoriiRaskhodov = em.merge(podkategoriiRaskhodovCollectionOldPodkategoriiRaskhodov);
                }
            }
            for (PodkategoriiRaskhodov podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov : podkategoriiRaskhodovCollectionNew) {
                if (!podkategoriiRaskhodovCollectionOld.contains(podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov)) {
                    KategoriiRaskhodov oldIDcategoryOfPodkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov = podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov.getIDcategory();
                    podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov.setIDcategory(kategoriiRaskhodov);
                    podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov = em.merge(podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov);
                    if (oldIDcategoryOfPodkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov != null && !oldIDcategoryOfPodkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov.equals(kategoriiRaskhodov)) {
                        oldIDcategoryOfPodkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov.getPodkategoriiRaskhodovCollection().remove(podkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov);
                        oldIDcategoryOfPodkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov = em.merge(oldIDcategoryOfPodkategoriiRaskhodovCollectionNewPodkategoriiRaskhodov);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kategoriiRaskhodov.getId();
                if (findKategoriiRaskhodov(id) == null) {
                    throw new NonexistentEntityException("The kategoriiRaskhodov with id " + id + " no longer exists.");
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
            KategoriiRaskhodov kategoriiRaskhodov;
            try {
                kategoriiRaskhodov = em.getReference(KategoriiRaskhodov.class, id);
                kategoriiRaskhodov.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kategoriiRaskhodov with id " + id + " no longer exists.", enfe);
            }
            Collection<PodkategoriiRaskhodov> podkategoriiRaskhodovCollection = kategoriiRaskhodov.getPodkategoriiRaskhodovCollection();
            for (PodkategoriiRaskhodov podkategoriiRaskhodovCollectionPodkategoriiRaskhodov : podkategoriiRaskhodovCollection) {
                podkategoriiRaskhodovCollectionPodkategoriiRaskhodov.setIDcategory(null);
                podkategoriiRaskhodovCollectionPodkategoriiRaskhodov = em.merge(podkategoriiRaskhodovCollectionPodkategoriiRaskhodov);
            }
            em.remove(kategoriiRaskhodov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KategoriiRaskhodov> findKategoriiRaskhodovEntities() {
        return findKategoriiRaskhodovEntities(true, -1, -1);
    }

    public List<KategoriiRaskhodov> findKategoriiRaskhodovEntities(int maxResults, int firstResult) {
        return findKategoriiRaskhodovEntities(false, maxResults, firstResult);
    }

    private List<KategoriiRaskhodov> findKategoriiRaskhodovEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KategoriiRaskhodov.class));
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

    public KategoriiRaskhodov findKategoriiRaskhodov(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KategoriiRaskhodov.class, id);
        } finally {
            em.close();
        }
    }

    public int getKategoriiRaskhodovCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KategoriiRaskhodov> rt = cq.from(KategoriiRaskhodov.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
