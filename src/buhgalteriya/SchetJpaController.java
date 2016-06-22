/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buhgalteriya;

import buhgalteriya.exceptions.IllegalOrphanException;
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
public class SchetJpaController implements Serializable {

    public SchetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Schet schet) {
        if (schet.getDokhodyCollection() == null) {
            schet.setDokhodyCollection(new ArrayList<Dokhody>());
        }
        if (schet.getRaskhodyCollection() == null) {
            schet.setRaskhodyCollection(new ArrayList<Raskhody>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipValyuty tipvalut = schet.getTipvalut();
            if (tipvalut != null) {
                tipvalut = em.getReference(tipvalut.getClass(), tipvalut.getId());
                schet.setTipvalut(tipvalut);
            }
            Vladeltsy accountholder = schet.getAccountholder();
            if (accountholder != null) {
                accountholder = em.getReference(accountholder.getClass(), accountholder.getId());
                schet.setAccountholder(accountholder);
            }
            Collection<Dokhody> attachedDokhodyCollection = new ArrayList<Dokhody>();
            for (Dokhody dokhodyCollectionDokhodyToAttach : schet.getDokhodyCollection()) {
                dokhodyCollectionDokhodyToAttach = em.getReference(dokhodyCollectionDokhodyToAttach.getClass(), dokhodyCollectionDokhodyToAttach.getId());
                attachedDokhodyCollection.add(dokhodyCollectionDokhodyToAttach);
            }
            schet.setDokhodyCollection(attachedDokhodyCollection);
            Collection<Raskhody> attachedRaskhodyCollection = new ArrayList<Raskhody>();
            for (Raskhody raskhodyCollectionRaskhodyToAttach : schet.getRaskhodyCollection()) {
                raskhodyCollectionRaskhodyToAttach = em.getReference(raskhodyCollectionRaskhodyToAttach.getClass(), raskhodyCollectionRaskhodyToAttach.getId());
                attachedRaskhodyCollection.add(raskhodyCollectionRaskhodyToAttach);
            }
            schet.setRaskhodyCollection(attachedRaskhodyCollection);
            em.persist(schet);
            if (tipvalut != null) {
                tipvalut.getSchetCollection().add(schet);
                tipvalut = em.merge(tipvalut);
            }
            if (accountholder != null) {
                accountholder.getSchetCollection().add(schet);
                accountholder = em.merge(accountholder);
            }
            for (Dokhody dokhodyCollectionDokhody : schet.getDokhodyCollection()) {
                Schet oldSchetOfDokhodyCollectionDokhody = dokhodyCollectionDokhody.getSchet();
                dokhodyCollectionDokhody.setSchet(schet);
                dokhodyCollectionDokhody = em.merge(dokhodyCollectionDokhody);
                if (oldSchetOfDokhodyCollectionDokhody != null) {
                    oldSchetOfDokhodyCollectionDokhody.getDokhodyCollection().remove(dokhodyCollectionDokhody);
                    oldSchetOfDokhodyCollectionDokhody = em.merge(oldSchetOfDokhodyCollectionDokhody);
                }
            }
            for (Raskhody raskhodyCollectionRaskhody : schet.getRaskhodyCollection()) {
                Schet oldSchetOfRaskhodyCollectionRaskhody = raskhodyCollectionRaskhody.getSchet();
                raskhodyCollectionRaskhody.setSchet(schet);
                raskhodyCollectionRaskhody = em.merge(raskhodyCollectionRaskhody);
                if (oldSchetOfRaskhodyCollectionRaskhody != null) {
                    oldSchetOfRaskhodyCollectionRaskhody.getRaskhodyCollection().remove(raskhodyCollectionRaskhody);
                    oldSchetOfRaskhodyCollectionRaskhody = em.merge(oldSchetOfRaskhodyCollectionRaskhody);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Schet schet) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Schet persistentSchet = em.find(Schet.class, schet.getId());
            TipValyuty tipvalutOld = persistentSchet.getTipvalut();
            TipValyuty tipvalutNew = schet.getTipvalut();
            Vladeltsy accountholderOld = persistentSchet.getAccountholder();
            Vladeltsy accountholderNew = schet.getAccountholder();
            Collection<Dokhody> dokhodyCollectionOld = persistentSchet.getDokhodyCollection();
            Collection<Dokhody> dokhodyCollectionNew = schet.getDokhodyCollection();
            Collection<Raskhody> raskhodyCollectionOld = persistentSchet.getRaskhodyCollection();
            Collection<Raskhody> raskhodyCollectionNew = schet.getRaskhodyCollection();
            List<String> illegalOrphanMessages = null;
            for (Dokhody dokhodyCollectionOldDokhody : dokhodyCollectionOld) {
                if (!dokhodyCollectionNew.contains(dokhodyCollectionOldDokhody)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dokhody " + dokhodyCollectionOldDokhody + " since its schet field is not nullable.");
                }
            }
            for (Raskhody raskhodyCollectionOldRaskhody : raskhodyCollectionOld) {
                if (!raskhodyCollectionNew.contains(raskhodyCollectionOldRaskhody)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Raskhody " + raskhodyCollectionOldRaskhody + " since its schet field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipvalutNew != null) {
                tipvalutNew = em.getReference(tipvalutNew.getClass(), tipvalutNew.getId());
                schet.setTipvalut(tipvalutNew);
            }
            if (accountholderNew != null) {
                accountholderNew = em.getReference(accountholderNew.getClass(), accountholderNew.getId());
                schet.setAccountholder(accountholderNew);
            }
            Collection<Dokhody> attachedDokhodyCollectionNew = new ArrayList<Dokhody>();
            for (Dokhody dokhodyCollectionNewDokhodyToAttach : dokhodyCollectionNew) {
                dokhodyCollectionNewDokhodyToAttach = em.getReference(dokhodyCollectionNewDokhodyToAttach.getClass(), dokhodyCollectionNewDokhodyToAttach.getId());
                attachedDokhodyCollectionNew.add(dokhodyCollectionNewDokhodyToAttach);
            }
            dokhodyCollectionNew = attachedDokhodyCollectionNew;
            schet.setDokhodyCollection(dokhodyCollectionNew);
            Collection<Raskhody> attachedRaskhodyCollectionNew = new ArrayList<Raskhody>();
            for (Raskhody raskhodyCollectionNewRaskhodyToAttach : raskhodyCollectionNew) {
                raskhodyCollectionNewRaskhodyToAttach = em.getReference(raskhodyCollectionNewRaskhodyToAttach.getClass(), raskhodyCollectionNewRaskhodyToAttach.getId());
                attachedRaskhodyCollectionNew.add(raskhodyCollectionNewRaskhodyToAttach);
            }
            raskhodyCollectionNew = attachedRaskhodyCollectionNew;
            schet.setRaskhodyCollection(raskhodyCollectionNew);
            schet = em.merge(schet);
            if (tipvalutOld != null && !tipvalutOld.equals(tipvalutNew)) {
                tipvalutOld.getSchetCollection().remove(schet);
                tipvalutOld = em.merge(tipvalutOld);
            }
            if (tipvalutNew != null && !tipvalutNew.equals(tipvalutOld)) {
                tipvalutNew.getSchetCollection().add(schet);
                tipvalutNew = em.merge(tipvalutNew);
            }
            if (accountholderOld != null && !accountholderOld.equals(accountholderNew)) {
                accountholderOld.getSchetCollection().remove(schet);
                accountholderOld = em.merge(accountholderOld);
            }
            if (accountholderNew != null && !accountholderNew.equals(accountholderOld)) {
                accountholderNew.getSchetCollection().add(schet);
                accountholderNew = em.merge(accountholderNew);
            }
            for (Dokhody dokhodyCollectionNewDokhody : dokhodyCollectionNew) {
                if (!dokhodyCollectionOld.contains(dokhodyCollectionNewDokhody)) {
                    Schet oldSchetOfDokhodyCollectionNewDokhody = dokhodyCollectionNewDokhody.getSchet();
                    dokhodyCollectionNewDokhody.setSchet(schet);
                    dokhodyCollectionNewDokhody = em.merge(dokhodyCollectionNewDokhody);
                    if (oldSchetOfDokhodyCollectionNewDokhody != null && !oldSchetOfDokhodyCollectionNewDokhody.equals(schet)) {
                        oldSchetOfDokhodyCollectionNewDokhody.getDokhodyCollection().remove(dokhodyCollectionNewDokhody);
                        oldSchetOfDokhodyCollectionNewDokhody = em.merge(oldSchetOfDokhodyCollectionNewDokhody);
                    }
                }
            }
            for (Raskhody raskhodyCollectionNewRaskhody : raskhodyCollectionNew) {
                if (!raskhodyCollectionOld.contains(raskhodyCollectionNewRaskhody)) {
                    Schet oldSchetOfRaskhodyCollectionNewRaskhody = raskhodyCollectionNewRaskhody.getSchet();
                    raskhodyCollectionNewRaskhody.setSchet(schet);
                    raskhodyCollectionNewRaskhody = em.merge(raskhodyCollectionNewRaskhody);
                    if (oldSchetOfRaskhodyCollectionNewRaskhody != null && !oldSchetOfRaskhodyCollectionNewRaskhody.equals(schet)) {
                        oldSchetOfRaskhodyCollectionNewRaskhody.getRaskhodyCollection().remove(raskhodyCollectionNewRaskhody);
                        oldSchetOfRaskhodyCollectionNewRaskhody = em.merge(oldSchetOfRaskhodyCollectionNewRaskhody);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = schet.getId();
                if (findSchet(id) == null) {
                    throw new NonexistentEntityException("The schet with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Schet schet;
            try {
                schet = em.getReference(Schet.class, id);
                schet.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The schet with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Dokhody> dokhodyCollectionOrphanCheck = schet.getDokhodyCollection();
            for (Dokhody dokhodyCollectionOrphanCheckDokhody : dokhodyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Schet (" + schet + ") cannot be destroyed since the Dokhody " + dokhodyCollectionOrphanCheckDokhody + " in its dokhodyCollection field has a non-nullable schet field.");
            }
            Collection<Raskhody> raskhodyCollectionOrphanCheck = schet.getRaskhodyCollection();
            for (Raskhody raskhodyCollectionOrphanCheckRaskhody : raskhodyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Schet (" + schet + ") cannot be destroyed since the Raskhody " + raskhodyCollectionOrphanCheckRaskhody + " in its raskhodyCollection field has a non-nullable schet field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipValyuty tipvalut = schet.getTipvalut();
            if (tipvalut != null) {
                tipvalut.getSchetCollection().remove(schet);
                tipvalut = em.merge(tipvalut);
            }
            Vladeltsy accountholder = schet.getAccountholder();
            if (accountholder != null) {
                accountholder.getSchetCollection().remove(schet);
                accountholder = em.merge(accountholder);
            }
            em.remove(schet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Schet> findSchetEntities() {
        return findSchetEntities(true, -1, -1);
    }

    public List<Schet> findSchetEntities(int maxResults, int firstResult) {
        return findSchetEntities(false, maxResults, firstResult);
    }

    private List<Schet> findSchetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Schet.class));
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

    public Schet findSchet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Schet.class, id);
        } finally {
            em.close();
        }
    }

    public int getSchetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Schet> rt = cq.from(Schet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
