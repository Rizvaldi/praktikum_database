/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum.database.database;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import praktikum.database.database.exceptions.NonexistentEntityException;
import praktikum.database.database.exceptions.PreexistingEntityException;

/**
 *
 * @author MSI 65 SERIES
 */
public class C2022wsJpaController implements Serializable {

    public C2022wsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("praktikum.database_database_jar_0.0.1-SNAPSHOTPU");
    
    public C2022wsJpaController(){}

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(C2022ws c2022ws) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(c2022ws);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findC2022ws(c2022ws.getId()) != null) {
                throw new PreexistingEntityException("C2022ws " + c2022ws + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(C2022ws c2022ws) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            c2022ws = em.merge(c2022ws);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = c2022ws.getId();
                if (findC2022ws(id) == null) {
                    throw new NonexistentEntityException("The c2022ws with id " + id + " no longer exists.");
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
            C2022ws c2022ws;
            try {
                c2022ws = em.getReference(C2022ws.class, id);
                c2022ws.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The c2022ws with id " + id + " no longer exists.", enfe);
            }
            em.remove(c2022ws);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<C2022ws> findC2022wsEntities() {
        return findC2022wsEntities(true, -1, -1);
    }

    public List<C2022ws> findC2022wsEntities(int maxResults, int firstResult) {
        return findC2022wsEntities(false, maxResults, firstResult);
    }

    private List<C2022ws> findC2022wsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(C2022ws.class));
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

    public C2022ws findC2022ws(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(C2022ws.class, id);
        } finally {
            em.close();
        }
    }

    public int getC2022wsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<C2022ws> rt = cq.from(C2022ws.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
