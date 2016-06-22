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
public class PodkategoriiDokhodovJpaController implements Serializable {

    public PodkategoriiDokhodovJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PodkategoriiDokhodov podkategoriiDokhodov) {
        if (podkategoriiDokhodov.getDokhodyCollection() == null) {
            podkategoriiDokhodov.setDokhodyCollection(new ArrayList<Dokhody>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            KategoriiDokhodov IDcategory = podkategoriiDokhodov.getIDcategory();
            if (IDcategory != null) {
                IDcategory = em.getReference(IDcategory.getClass(), IDcategory.getId());
                podkategoriiDokhodov.setIDcategory(IDcategory);
            }
            Collection<Dokhody> attachedDokhodyCollection = new ArrayList<Dokhody>();
            for (Dokhody dokhodyCollectionDokhodyToAttach : podkategoriiDokhodov.getDokhodyCollection()) {
                dokhodyCollectionDokhodyToAttach = em.getReference(dokhodyCollectionDokhodyToAttach.getClass(), dokhodyCollectionDokhodyToAttach.getId());
                attachedDokhodyCollection.add(dokhodyCollectionDokhodyToAttach);
            }
            podkategoriiDokhodov.setDokhodyCollection(attachedDokhodyCollection);
            em.persist(podkategoriiDokhodov);
            if (IDcategory != null) {
                IDcategory.getPodkategoriiDokhodovCollection().add(podkategoriiDokhodov);
                IDcategory = em.merge(IDcategory);
            }
            for (Dokhody dokhodyCollectionDokhody : podkategoriiDokhodov.getDokhodyCollection()) {
                PodkategoriiDokhodov oldPodkategoriyDokhodaOfDokhodyCollectionDokhody = dokhodyCollectionDokhody.getPodkategoriyDokhoda();
                dokhodyCollectionDokhody.setPodkategoriyDokhoda(podkategoriiDokhodov);
                dokhodyCollectionDokhody = em.merge(dokhodyCollectionDokhody);
                if (oldPodkategoriyDokhodaOfDokhodyCollectionDokhody != null) {
                    oldPodkategoriyDokhodaOfDokhodyCollectionDokhody.getDokhodyCollection().remove(dokhodyCollectionDokhody);
                    oldPodkategoriyDokhodaOfDokhodyCollectionDokhody = em.merge(oldPodkategoriyDokhodaOfDokhodyCollectionDokhody);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PodkategoriiDokhodov podkategoriiDokhodov) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PodkategoriiDokhodov persistentPodkategoriiDokhodov = em.find(PodkategoriiDokhodov.class, podkategoriiDokhodov.getIDsubcategory());
            KategoriiDokhodov IDcategoryOld = persistentPodkategoriiDokhodov.getIDcategory();
            KategoriiDokhodov IDcategoryNew = podkategoriiDokhodov.getIDcategory();
            Collection<Dokhody> dokhodyCollectionOld = persistentPodkategoriiDokhodov.getDokhodyCollection();
            Collection<Dokhody> dokhodyCollectionNew = podkategoriiDokhodov.getDokhodyCollection();
            if (IDcategoryNew != null) {
                IDcategoryNew = em.getReference(IDcategoryNew.getClass(), IDcategoryNew.getId());
                podkategoriiDokhodov.setIDcategory(IDcategoryNew);
            }
            Collection<Dokhody> attachedDokhodyCollectionNew = new ArrayList<Dokhody>();
            for (Dokhody dokhodyCollectionNewDokhodyToAttach : dokhodyCollectionNew) {
                dokhodyCollectionNewDokhodyToAttach = em.getReference(dokhodyCollectionNewDokhodyToAttach.getClass(), dokhodyCollectionNewDokhodyToAttach.getId());
                attachedDokhodyCollectionNew.add(dokhodyCollectionNewDokhodyToAttach);
            }
            dokhodyCollectionNew = attachedDokhodyCollectionNew;
            podkategoriiDokhodov.setDokhodyCollection(dokhodyCollectionNew);
            podkategoriiDokhodov = em.merge(podkategoriiDokhodov);
            if (IDcategoryOld != null && !IDcategoryOld.equals(IDcategoryNew)) {
                IDcategoryOld.getPodkategoriiDokhodovCollection().remove(podkategoriiDokhodov);
                IDcategoryOld = em.merge(IDcategoryOld);
            }
            if (IDcategoryNew != null && !IDcategoryNew.equals(IDcategoryOld)) {
                IDcategoryNew.getPodkategoriiDokhodovCollection().add(podkategoriiDokhodov);
                IDcategoryNew = em.merge(IDcategoryNew);
            }
            for (Dokhody dokhodyCollectionOldDokhody : dokhodyCollectionOld) {
                if (!dokhodyCollectionNew.contains(dokhodyCollectionOldDokhody)) {
                    dokhodyCollectionOldDokhody.setPodkategoriyDokhoda(null);
                    dokhodyCollectionOldDokhody = em.merge(dokhodyCollectionOldDokhody);
                }
            }
            for (Dokhody dokhodyCollectionNewDokhody : dokhodyCollectionNew) {
                if (!dokhodyCollectionOld.contains(dokhodyCollectionNewDokhody)) {
                    PodkategoriiDokhodov oldPodkategoriyDokhodaOfDokhodyCollectionNewDokhody = dokhodyCollectionNewDokhody.getPodkategoriyDokhoda();
                    dokhodyCollectionNewDokhody.setPodkategoriyDokhoda(podkategoriiDokhodov);
                    dokhodyCollectionNewDokhody = em.merge(dokhodyCollectionNewDokhody);
                    if (oldPodkategoriyDokhodaOfDokhodyCollectionNewDokhody != null && !oldPodkategoriyDokhodaOfDokhodyCollectionNewDokhody.equals(podkategoriiDokhodov)) {
                        oldPodkategoriyDokhodaOfDokhodyCollectionNewDokhody.getDokhodyCollection().remove(dokhodyCollectionNewDokhody);
                        oldPodkategoriyDokhodaOfDokhodyCollectionNewDokhody = em.merge(oldPodkategoriyDokhodaOfDokhodyCollectionNewDokhody);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = podkategoriiDokhodov.getIDsubcategory();
                if (findPodkategoriiDokhodov(id) == null) {
                    throw new NonexistentEntityException("The podkategoriiDokhodov with id " + id + " no longer exists.");
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
            PodkategoriiDokhodov podkategoriiDokhodov;
            try {
                podkategoriiDokhodov = em.getReference(PodkategoriiDokhodov.class, id);
                podkategoriiDokhodov.getIDsubcategory();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The podkategoriiDokhodov with id " + id + " no longer exists.", enfe);
            }
            KategoriiDokhodov IDcategory = podkategoriiDokhodov.getIDcategory();
            if (IDcategory != null) {
                IDcategory.getPodkategoriiDokhodovCollection().remove(podkategoriiDokhodov);
                IDcategory = em.merge(IDcategory);
            }
            Collection<Dokhody> dokhodyCollection = podkategoriiDokhodov.getDokhodyCollection();
            for (Dokhody dokhodyCollectionDokhody : dokhodyCollection) {
                dokhodyCollectionDokhody.setPodkategoriyDokhoda(null);
                dokhodyCollectionDokhody = em.merge(dokhodyCollectionDokhody);
            }
            em.remove(podkategoriiDokhodov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PodkategoriiDokhodov> findPodkategoriiDokhodovEntities() {
        return findPodkategoriiDokhodovEntities(true, -1, -1);
    }

    public List<PodkategoriiDokhodov> findPodkategoriiDokhodovEntities(int maxResults, int firstResult) {
        return findPodkategoriiDokhodovEntities(false, maxResults, firstResult);
    }

    private List<PodkategoriiDokhodov> findPodkategoriiDokhodovEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PodkategoriiDokhodov.class));
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

    public PodkategoriiDokhodov findPodkategoriiDokhodov(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PodkategoriiDokhodov.class, id);
        } finally {
            em.close();
        }
    }

    public int getPodkategoriiDokhodovCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PodkategoriiDokhodov> rt = cq.from(PodkategoriiDokhodov.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
