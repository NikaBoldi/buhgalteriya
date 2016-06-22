/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buhgalteriya;

import buhgalteriya.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Максим
 */
public class DokhodyJpaController implements Serializable {

    public DokhodyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dokhody dokhody) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Schet schet = dokhody.getSchet();
            if (schet != null) {
                schet = em.getReference(schet.getClass(), schet.getId());
                dokhody.setSchet(schet);
            }
            PodkategoriiDokhodov podkategoriyDokhoda = dokhody.getPodkategoriyDokhoda();
            if (podkategoriyDokhoda != null) {
                podkategoriyDokhoda = em.getReference(podkategoriyDokhoda.getClass(), podkategoriyDokhoda.getIDsubcategory());
                dokhody.setPodkategoriyDokhoda(podkategoriyDokhoda);
            }
            em.persist(dokhody);
            if (schet != null) {
                schet.getDokhodyCollection().add(dokhody);
                schet = em.merge(schet);
            }
            if (podkategoriyDokhoda != null) {
                podkategoriyDokhoda.getDokhodyCollection().add(dokhody);
                podkategoriyDokhoda = em.merge(podkategoriyDokhoda);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dokhody dokhody) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dokhody persistentDokhody = em.find(Dokhody.class, dokhody.getId());
            Schet schetOld = persistentDokhody.getSchet();
            Schet schetNew = dokhody.getSchet();
            PodkategoriiDokhodov podkategoriyDokhodaOld = persistentDokhody.getPodkategoriyDokhoda();
            PodkategoriiDokhodov podkategoriyDokhodaNew = dokhody.getPodkategoriyDokhoda();
            if (schetNew != null) {
                schetNew = em.getReference(schetNew.getClass(), schetNew.getId());
                dokhody.setSchet(schetNew);
            }
            if (podkategoriyDokhodaNew != null) {
                podkategoriyDokhodaNew = em.getReference(podkategoriyDokhodaNew.getClass(), podkategoriyDokhodaNew.getIDsubcategory());
                dokhody.setPodkategoriyDokhoda(podkategoriyDokhodaNew);
            }
            dokhody = em.merge(dokhody);
            if (schetOld != null && !schetOld.equals(schetNew)) {
                schetOld.getDokhodyCollection().remove(dokhody);
                schetOld = em.merge(schetOld);
            }
            if (schetNew != null && !schetNew.equals(schetOld)) {
                schetNew.getDokhodyCollection().add(dokhody);
                schetNew = em.merge(schetNew);
            }
            if (podkategoriyDokhodaOld != null && !podkategoriyDokhodaOld.equals(podkategoriyDokhodaNew)) {
                podkategoriyDokhodaOld.getDokhodyCollection().remove(dokhody);
                podkategoriyDokhodaOld = em.merge(podkategoriyDokhodaOld);
            }
            if (podkategoriyDokhodaNew != null && !podkategoriyDokhodaNew.equals(podkategoriyDokhodaOld)) {
                podkategoriyDokhodaNew.getDokhodyCollection().add(dokhody);
                podkategoriyDokhodaNew = em.merge(podkategoriyDokhodaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dokhody.getId();
                if (findDokhody(id) == null) {
                    throw new NonexistentEntityException("The dokhody with id " + id + " no longer exists.");
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
            Dokhody dokhody;
            try {
                dokhody = em.getReference(Dokhody.class, id);
                dokhody.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dokhody with id " + id + " no longer exists.", enfe);
            }
            Schet schet = dokhody.getSchet();
            if (schet != null) {
                schet.getDokhodyCollection().remove(dokhody);
                schet = em.merge(schet);
            }
            PodkategoriiDokhodov podkategoriyDokhoda = dokhody.getPodkategoriyDokhoda();
            if (podkategoriyDokhoda != null) {
                podkategoriyDokhoda.getDokhodyCollection().remove(dokhody);
                podkategoriyDokhoda = em.merge(podkategoriyDokhoda);
            }
            em.remove(dokhody);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dokhody> findDokhodyEntities() {
        return findDokhodyEntities(true, -1, -1);
    }

    public List<Dokhody> findDokhodyEntities(int maxResults, int firstResult) {
        return findDokhodyEntities(false, maxResults, firstResult);
    }

    private List<Dokhody> findDokhodyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dokhody.class));
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

    public Dokhody findDokhody(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dokhody.class, id);
        } finally {
            em.close();
        }
    }

    public int getDokhodyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dokhody> rt = cq.from(Dokhody.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
