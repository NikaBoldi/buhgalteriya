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
public class KategoriiDokhodovJpaController implements Serializable {

    public KategoriiDokhodovJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KategoriiDokhodov kategoriiDokhodov) {
        if (kategoriiDokhodov.getPodkategoriiDokhodovCollection() == null) {
            kategoriiDokhodov.setPodkategoriiDokhodovCollection(new ArrayList<PodkategoriiDokhodov>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PodkategoriiDokhodov> attachedPodkategoriiDokhodovCollection = new ArrayList<PodkategoriiDokhodov>();
            for (PodkategoriiDokhodov podkategoriiDokhodovCollectionPodkategoriiDokhodovToAttach : kategoriiDokhodov.getPodkategoriiDokhodovCollection()) {
                podkategoriiDokhodovCollectionPodkategoriiDokhodovToAttach = em.getReference(podkategoriiDokhodovCollectionPodkategoriiDokhodovToAttach.getClass(), podkategoriiDokhodovCollectionPodkategoriiDokhodovToAttach.getIDsubcategory());
                attachedPodkategoriiDokhodovCollection.add(podkategoriiDokhodovCollectionPodkategoriiDokhodovToAttach);
            }
            kategoriiDokhodov.setPodkategoriiDokhodovCollection(attachedPodkategoriiDokhodovCollection);
            em.persist(kategoriiDokhodov);
            for (PodkategoriiDokhodov podkategoriiDokhodovCollectionPodkategoriiDokhodov : kategoriiDokhodov.getPodkategoriiDokhodovCollection()) {
                KategoriiDokhodov oldIDcategoryOfPodkategoriiDokhodovCollectionPodkategoriiDokhodov = podkategoriiDokhodovCollectionPodkategoriiDokhodov.getIDcategory();
                podkategoriiDokhodovCollectionPodkategoriiDokhodov.setIDcategory(kategoriiDokhodov);
                podkategoriiDokhodovCollectionPodkategoriiDokhodov = em.merge(podkategoriiDokhodovCollectionPodkategoriiDokhodov);
                if (oldIDcategoryOfPodkategoriiDokhodovCollectionPodkategoriiDokhodov != null) {
                    oldIDcategoryOfPodkategoriiDokhodovCollectionPodkategoriiDokhodov.getPodkategoriiDokhodovCollection().remove(podkategoriiDokhodovCollectionPodkategoriiDokhodov);
                    oldIDcategoryOfPodkategoriiDokhodovCollectionPodkategoriiDokhodov = em.merge(oldIDcategoryOfPodkategoriiDokhodovCollectionPodkategoriiDokhodov);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(KategoriiDokhodov kategoriiDokhodov) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KategoriiDokhodov persistentKategoriiDokhodov = em.find(KategoriiDokhodov.class, kategoriiDokhodov.getId());
            Collection<PodkategoriiDokhodov> podkategoriiDokhodovCollectionOld = persistentKategoriiDokhodov.getPodkategoriiDokhodovCollection();
            Collection<PodkategoriiDokhodov> podkategoriiDokhodovCollectionNew = kategoriiDokhodov.getPodkategoriiDokhodovCollection();
            Collection<PodkategoriiDokhodov> attachedPodkategoriiDokhodovCollectionNew = new ArrayList<PodkategoriiDokhodov>();
            for (PodkategoriiDokhodov podkategoriiDokhodovCollectionNewPodkategoriiDokhodovToAttach : podkategoriiDokhodovCollectionNew) {
                podkategoriiDokhodovCollectionNewPodkategoriiDokhodovToAttach = em.getReference(podkategoriiDokhodovCollectionNewPodkategoriiDokhodovToAttach.getClass(), podkategoriiDokhodovCollectionNewPodkategoriiDokhodovToAttach.getIDsubcategory());
                attachedPodkategoriiDokhodovCollectionNew.add(podkategoriiDokhodovCollectionNewPodkategoriiDokhodovToAttach);
            }
            podkategoriiDokhodovCollectionNew = attachedPodkategoriiDokhodovCollectionNew;
            kategoriiDokhodov.setPodkategoriiDokhodovCollection(podkategoriiDokhodovCollectionNew);
            kategoriiDokhodov = em.merge(kategoriiDokhodov);
            for (PodkategoriiDokhodov podkategoriiDokhodovCollectionOldPodkategoriiDokhodov : podkategoriiDokhodovCollectionOld) {
                if (!podkategoriiDokhodovCollectionNew.contains(podkategoriiDokhodovCollectionOldPodkategoriiDokhodov)) {
                    podkategoriiDokhodovCollectionOldPodkategoriiDokhodov.setIDcategory(null);
                    podkategoriiDokhodovCollectionOldPodkategoriiDokhodov = em.merge(podkategoriiDokhodovCollectionOldPodkategoriiDokhodov);
                }
            }
            for (PodkategoriiDokhodov podkategoriiDokhodovCollectionNewPodkategoriiDokhodov : podkategoriiDokhodovCollectionNew) {
                if (!podkategoriiDokhodovCollectionOld.contains(podkategoriiDokhodovCollectionNewPodkategoriiDokhodov)) {
                    KategoriiDokhodov oldIDcategoryOfPodkategoriiDokhodovCollectionNewPodkategoriiDokhodov = podkategoriiDokhodovCollectionNewPodkategoriiDokhodov.getIDcategory();
                    podkategoriiDokhodovCollectionNewPodkategoriiDokhodov.setIDcategory(kategoriiDokhodov);
                    podkategoriiDokhodovCollectionNewPodkategoriiDokhodov = em.merge(podkategoriiDokhodovCollectionNewPodkategoriiDokhodov);
                    if (oldIDcategoryOfPodkategoriiDokhodovCollectionNewPodkategoriiDokhodov != null && !oldIDcategoryOfPodkategoriiDokhodovCollectionNewPodkategoriiDokhodov.equals(kategoriiDokhodov)) {
                        oldIDcategoryOfPodkategoriiDokhodovCollectionNewPodkategoriiDokhodov.getPodkategoriiDokhodovCollection().remove(podkategoriiDokhodovCollectionNewPodkategoriiDokhodov);
                        oldIDcategoryOfPodkategoriiDokhodovCollectionNewPodkategoriiDokhodov = em.merge(oldIDcategoryOfPodkategoriiDokhodovCollectionNewPodkategoriiDokhodov);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kategoriiDokhodov.getId();
                if (findKategoriiDokhodov(id) == null) {
                    throw new NonexistentEntityException("The kategoriiDokhodov with id " + id + " no longer exists.");
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
            KategoriiDokhodov kategoriiDokhodov;
            try {
                kategoriiDokhodov = em.getReference(KategoriiDokhodov.class, id);
                kategoriiDokhodov.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kategoriiDokhodov with id " + id + " no longer exists.", enfe);
            }
            Collection<PodkategoriiDokhodov> podkategoriiDokhodovCollection = kategoriiDokhodov.getPodkategoriiDokhodovCollection();
            for (PodkategoriiDokhodov podkategoriiDokhodovCollectionPodkategoriiDokhodov : podkategoriiDokhodovCollection) {
                podkategoriiDokhodovCollectionPodkategoriiDokhodov.setIDcategory(null);
                podkategoriiDokhodovCollectionPodkategoriiDokhodov = em.merge(podkategoriiDokhodovCollectionPodkategoriiDokhodov);
            }
            em.remove(kategoriiDokhodov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<KategoriiDokhodov> findKategoriiDokhodovEntities() {
        return findKategoriiDokhodovEntities(true, -1, -1);
    }

    public List<KategoriiDokhodov> findKategoriiDokhodovEntities(int maxResults, int firstResult) {
        return findKategoriiDokhodovEntities(false, maxResults, firstResult);
    }

    private List<KategoriiDokhodov> findKategoriiDokhodovEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KategoriiDokhodov.class));
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

    public KategoriiDokhodov findKategoriiDokhodov(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KategoriiDokhodov.class, id);
        } finally {
            em.close();
        }
    }

    public int getKategoriiDokhodovCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KategoriiDokhodov> rt = cq.from(KategoriiDokhodov.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
