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
public class PodkategoriiRaskhodovJpaController implements Serializable {

    public PodkategoriiRaskhodovJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PodkategoriiRaskhodov podkategoriiRaskhodov) {
        if (podkategoriiRaskhodov.getRaskhodyCollection() == null) {
            podkategoriiRaskhodov.setRaskhodyCollection(new ArrayList<Raskhody>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KategoriiRaskhodov IDcategory = podkategoriiRaskhodov.getIDcategory();
            if (IDcategory != null) {
                IDcategory = em.getReference(IDcategory.getClass(), IDcategory.getId());
                podkategoriiRaskhodov.setIDcategory(IDcategory);
            }
            Collection<Raskhody> attachedRaskhodyCollection = new ArrayList<Raskhody>();
            for (Raskhody raskhodyCollectionRaskhodyToAttach : podkategoriiRaskhodov.getRaskhodyCollection()) {
                raskhodyCollectionRaskhodyToAttach = em.getReference(raskhodyCollectionRaskhodyToAttach.getClass(), raskhodyCollectionRaskhodyToAttach.getId());
                attachedRaskhodyCollection.add(raskhodyCollectionRaskhodyToAttach);
            }
            podkategoriiRaskhodov.setRaskhodyCollection(attachedRaskhodyCollection);
            em.persist(podkategoriiRaskhodov);
            if (IDcategory != null) {
                IDcategory.getPodkategoriiRaskhodovCollection().add(podkategoriiRaskhodov);
                IDcategory = em.merge(IDcategory);
            }
            for (Raskhody raskhodyCollectionRaskhody : podkategoriiRaskhodov.getRaskhodyCollection()) {
                PodkategoriiRaskhodov oldPodkategoriyRaskhodovOfRaskhodyCollectionRaskhody = raskhodyCollectionRaskhody.getPodkategoriyRaskhodov();
                raskhodyCollectionRaskhody.setPodkategoriyRaskhodov(podkategoriiRaskhodov);
                raskhodyCollectionRaskhody = em.merge(raskhodyCollectionRaskhody);
                if (oldPodkategoriyRaskhodovOfRaskhodyCollectionRaskhody != null) {
                    oldPodkategoriyRaskhodovOfRaskhodyCollectionRaskhody.getRaskhodyCollection().remove(raskhodyCollectionRaskhody);
                    oldPodkategoriyRaskhodovOfRaskhodyCollectionRaskhody = em.merge(oldPodkategoriyRaskhodovOfRaskhodyCollectionRaskhody);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PodkategoriiRaskhodov podkategoriiRaskhodov) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PodkategoriiRaskhodov persistentPodkategoriiRaskhodov = em.find(PodkategoriiRaskhodov.class, podkategoriiRaskhodov.getIDsubcategory());
            KategoriiRaskhodov IDcategoryOld = persistentPodkategoriiRaskhodov.getIDcategory();
            KategoriiRaskhodov IDcategoryNew = podkategoriiRaskhodov.getIDcategory();
            Collection<Raskhody> raskhodyCollectionOld = persistentPodkategoriiRaskhodov.getRaskhodyCollection();
            Collection<Raskhody> raskhodyCollectionNew = podkategoriiRaskhodov.getRaskhodyCollection();
            if (IDcategoryNew != null) {
                IDcategoryNew = em.getReference(IDcategoryNew.getClass(), IDcategoryNew.getId());
                podkategoriiRaskhodov.setIDcategory(IDcategoryNew);
            }
            Collection<Raskhody> attachedRaskhodyCollectionNew = new ArrayList<Raskhody>();
            for (Raskhody raskhodyCollectionNewRaskhodyToAttach : raskhodyCollectionNew) {
                raskhodyCollectionNewRaskhodyToAttach = em.getReference(raskhodyCollectionNewRaskhodyToAttach.getClass(), raskhodyCollectionNewRaskhodyToAttach.getId());
                attachedRaskhodyCollectionNew.add(raskhodyCollectionNewRaskhodyToAttach);
            }
            raskhodyCollectionNew = attachedRaskhodyCollectionNew;
            podkategoriiRaskhodov.setRaskhodyCollection(raskhodyCollectionNew);
            podkategoriiRaskhodov = em.merge(podkategoriiRaskhodov);
            if (IDcategoryOld != null && !IDcategoryOld.equals(IDcategoryNew)) {
                IDcategoryOld.getPodkategoriiRaskhodovCollection().remove(podkategoriiRaskhodov);
                IDcategoryOld = em.merge(IDcategoryOld);
            }
            if (IDcategoryNew != null && !IDcategoryNew.equals(IDcategoryOld)) {
                IDcategoryNew.getPodkategoriiRaskhodovCollection().add(podkategoriiRaskhodov);
                IDcategoryNew = em.merge(IDcategoryNew);
            }
            for (Raskhody raskhodyCollectionOldRaskhody : raskhodyCollectionOld) {
                if (!raskhodyCollectionNew.contains(raskhodyCollectionOldRaskhody)) {
                    raskhodyCollectionOldRaskhody.setPodkategoriyRaskhodov(null);
                    raskhodyCollectionOldRaskhody = em.merge(raskhodyCollectionOldRaskhody);
                }
            }
            for (Raskhody raskhodyCollectionNewRaskhody : raskhodyCollectionNew) {
                if (!raskhodyCollectionOld.contains(raskhodyCollectionNewRaskhody)) {
                    PodkategoriiRaskhodov oldPodkategoriyRaskhodovOfRaskhodyCollectionNewRaskhody = raskhodyCollectionNewRaskhody.getPodkategoriyRaskhodov();
                    raskhodyCollectionNewRaskhody.setPodkategoriyRaskhodov(podkategoriiRaskhodov);
                    raskhodyCollectionNewRaskhody = em.merge(raskhodyCollectionNewRaskhody);
                    if (oldPodkategoriyRaskhodovOfRaskhodyCollectionNewRaskhody != null && !oldPodkategoriyRaskhodovOfRaskhodyCollectionNewRaskhody.equals(podkategoriiRaskhodov)) {
                        oldPodkategoriyRaskhodovOfRaskhodyCollectionNewRaskhody.getRaskhodyCollection().remove(raskhodyCollectionNewRaskhody);
                        oldPodkategoriyRaskhodovOfRaskhodyCollectionNewRaskhody = em.merge(oldPodkategoriyRaskhodovOfRaskhodyCollectionNewRaskhody);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = podkategoriiRaskhodov.getIDsubcategory();
                if (findPodkategoriiRaskhodov(id) == null) {
                    throw new NonexistentEntityException("The podkategoriiRaskhodov with id " + id + " no longer exists.");
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
            PodkategoriiRaskhodov podkategoriiRaskhodov;
            try {
                podkategoriiRaskhodov = em.getReference(PodkategoriiRaskhodov.class, id);
                podkategoriiRaskhodov.getIDsubcategory();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The podkategoriiRaskhodov with id " + id + " no longer exists.", enfe);
            }
            KategoriiRaskhodov IDcategory = podkategoriiRaskhodov.getIDcategory();
            if (IDcategory != null) {
                IDcategory.getPodkategoriiRaskhodovCollection().remove(podkategoriiRaskhodov);
                IDcategory = em.merge(IDcategory);
            }
            Collection<Raskhody> raskhodyCollection = podkategoriiRaskhodov.getRaskhodyCollection();
            for (Raskhody raskhodyCollectionRaskhody : raskhodyCollection) {
                raskhodyCollectionRaskhody.setPodkategoriyRaskhodov(null);
                raskhodyCollectionRaskhody = em.merge(raskhodyCollectionRaskhody);
            }
            em.remove(podkategoriiRaskhodov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PodkategoriiRaskhodov> findPodkategoriiRaskhodovEntities() {
        return findPodkategoriiRaskhodovEntities(true, -1, -1);
    }

    public List<PodkategoriiRaskhodov> findPodkategoriiRaskhodovEntities(int maxResults, int firstResult) {
        return findPodkategoriiRaskhodovEntities(false, maxResults, firstResult);
    }

    private List<PodkategoriiRaskhodov> findPodkategoriiRaskhodovEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PodkategoriiRaskhodov.class));
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

    public PodkategoriiRaskhodov findPodkategoriiRaskhodov(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PodkategoriiRaskhodov.class, id);
        } finally {
            em.close();
        }
    }

    public int getPodkategoriiRaskhodovCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PodkategoriiRaskhodov> rt = cq.from(PodkategoriiRaskhodov.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
