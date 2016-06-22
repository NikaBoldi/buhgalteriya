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
public class RaskhodyJpaController implements Serializable {

    public RaskhodyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Raskhody raskhody) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EdenitsyIzmereniya units = raskhody.getUnits();
            if (units != null) {
                units = em.getReference(units.getClass(), units.getId());
                raskhody.setUnits(units);
            }
            Schet schet = raskhody.getSchet();
            if (schet != null) {
                schet = em.getReference(schet.getClass(), schet.getId());
                raskhody.setSchet(schet);
            }
            PodkategoriiRaskhodov podkategoriyRaskhodov = raskhody.getPodkategoriyRaskhodov();
            if (podkategoriyRaskhodov != null) {
                podkategoriyRaskhodov = em.getReference(podkategoriyRaskhodov.getClass(), podkategoriyRaskhodov.getIDsubcategory());
                raskhody.setPodkategoriyRaskhodov(podkategoriyRaskhodov);
            }
            em.persist(raskhody);
            if (units != null) {
                units.getRaskhodyCollection().add(raskhody);
                units = em.merge(units);
            }
            if (schet != null) {
                schet.getRaskhodyCollection().add(raskhody);
                schet = em.merge(schet);
            }
            if (podkategoriyRaskhodov != null) {
                podkategoriyRaskhodov.getRaskhodyCollection().add(raskhody);
                podkategoriyRaskhodov = em.merge(podkategoriyRaskhodov);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Raskhody raskhody) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Raskhody persistentRaskhody = em.find(Raskhody.class, raskhody.getId());
            EdenitsyIzmereniya unitsOld = persistentRaskhody.getUnits();
            EdenitsyIzmereniya unitsNew = raskhody.getUnits();
            Schet schetOld = persistentRaskhody.getSchet();
            Schet schetNew = raskhody.getSchet();
            PodkategoriiRaskhodov podkategoriyRaskhodovOld = persistentRaskhody.getPodkategoriyRaskhodov();
            PodkategoriiRaskhodov podkategoriyRaskhodovNew = raskhody.getPodkategoriyRaskhodov();
            if (unitsNew != null) {
                unitsNew = em.getReference(unitsNew.getClass(), unitsNew.getId());
                raskhody.setUnits(unitsNew);
            }
            if (schetNew != null) {
                schetNew = em.getReference(schetNew.getClass(), schetNew.getId());
                raskhody.setSchet(schetNew);
            }
            if (podkategoriyRaskhodovNew != null) {
                podkategoriyRaskhodovNew = em.getReference(podkategoriyRaskhodovNew.getClass(), podkategoriyRaskhodovNew.getIDsubcategory());
                raskhody.setPodkategoriyRaskhodov(podkategoriyRaskhodovNew);
            }
            raskhody = em.merge(raskhody);
            if (unitsOld != null && !unitsOld.equals(unitsNew)) {
                unitsOld.getRaskhodyCollection().remove(raskhody);
                unitsOld = em.merge(unitsOld);
            }
            if (unitsNew != null && !unitsNew.equals(unitsOld)) {
                unitsNew.getRaskhodyCollection().add(raskhody);
                unitsNew = em.merge(unitsNew);
            }
            if (schetOld != null && !schetOld.equals(schetNew)) {
                schetOld.getRaskhodyCollection().remove(raskhody);
                schetOld = em.merge(schetOld);
            }
            if (schetNew != null && !schetNew.equals(schetOld)) {
                schetNew.getRaskhodyCollection().add(raskhody);
                schetNew = em.merge(schetNew);
            }
            if (podkategoriyRaskhodovOld != null && !podkategoriyRaskhodovOld.equals(podkategoriyRaskhodovNew)) {
                podkategoriyRaskhodovOld.getRaskhodyCollection().remove(raskhody);
                podkategoriyRaskhodovOld = em.merge(podkategoriyRaskhodovOld);
            }
            if (podkategoriyRaskhodovNew != null && !podkategoriyRaskhodovNew.equals(podkategoriyRaskhodovOld)) {
                podkategoriyRaskhodovNew.getRaskhodyCollection().add(raskhody);
                podkategoriyRaskhodovNew = em.merge(podkategoriyRaskhodovNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = raskhody.getId();
                if (findRaskhody(id) == null) {
                    throw new NonexistentEntityException("The raskhody with id " + id + " no longer exists.");
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
            Raskhody raskhody;
            try {
                raskhody = em.getReference(Raskhody.class, id);
                raskhody.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The raskhody with id " + id + " no longer exists.", enfe);
            }
            EdenitsyIzmereniya units = raskhody.getUnits();
            if (units != null) {
                units.getRaskhodyCollection().remove(raskhody);
                units = em.merge(units);
            }
            Schet schet = raskhody.getSchet();
            if (schet != null) {
                schet.getRaskhodyCollection().remove(raskhody);
                schet = em.merge(schet);
            }
            PodkategoriiRaskhodov podkategoriyRaskhodov = raskhody.getPodkategoriyRaskhodov();
            if (podkategoriyRaskhodov != null) {
                podkategoriyRaskhodov.getRaskhodyCollection().remove(raskhody);
                podkategoriyRaskhodov = em.merge(podkategoriyRaskhodov);
            }
            em.remove(raskhody);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Raskhody> findRaskhodyEntities() {
        return findRaskhodyEntities(true, -1, -1);
    }

    public List<Raskhody> findRaskhodyEntities(int maxResults, int firstResult) {
        return findRaskhodyEntities(false, maxResults, firstResult);
    }

    private List<Raskhody> findRaskhodyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Raskhody.class));
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

    public Raskhody findRaskhody(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Raskhody.class, id);
        } finally {
            em.close();
        }
    }

    public int getRaskhodyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Raskhody> rt = cq.from(Raskhody.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
