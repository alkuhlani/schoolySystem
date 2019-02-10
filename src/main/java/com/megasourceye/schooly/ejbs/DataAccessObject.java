/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.Student;
import com.megasourceye.schooly.entities.Users;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author said
 */
@Stateless
public class DataAccessObject {
//Generic//
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @PersistenceContext(unitName = "scoly")
    private EntityManager em;

    public void create(Object o) {
        em.persist(o);
    }

    public <T, I> T find(I id, Class<T> clazz) {
        return em.find(clazz, id);
    }

    public <T> T findBy(String q, Class<T> clazz, Long id) {
        TypedQuery<T> tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        return tq.getSingleResult();
    }

    public List findAll(String q, Class clazz) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        return tq.getResultList();
    }

    public List findAll(String q, Class clazz, Long id) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        return tq.getResultList();
    }

    public List findAll(String q, Class clazz, Long id, Long id1) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        return tq.getResultList();
    }
    public List findAll(String q, Class clazz, Long id, Character id1) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        return tq.getResultList();
    }
    public List findAll(String q, Class clazz, Long id, Character id1,Character id2) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        tq.setParameter("id2", id2);
        return tq.getResultList();
    }
    public List findAll(String q, Class clazz, Long id, Date id1) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        return tq.getResultList();
    }
    public List findAll(String q, Class clazz, Long id,Long id1, Date id2) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        tq.setParameter("id2", id2);
        return tq.getResultList();
    }
    public List findAll(String q, Class clazz, Long id, Date id1,Date id2) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        tq.setParameter("id2", id2);
        return tq.getResultList();
    }
    public List findAll(String q, Class clazz, Long id,Long id1, Date id2,Date id3) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        tq.setParameter("id2", id2);
        tq.setParameter("id3", id3);
        return tq.getResultList();
    }
    public List findAll(String q, Class clazz, Long id,Long id1, Long id2,Date id3) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        tq.setParameter("id2", id2);
        tq.setParameter("id3", id3);
        return tq.getResultList();
    }
    public List findAll(String q, Class clazz, Long id,Long id1,Long id2, Date id3,Date id4) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        tq.setParameter("id2", id2);
        tq.setParameter("id3", id3);
        tq.setParameter("id4", id4);
        return tq.getResultList();
    }

    public List findAll(String q, Class clazz, Long id, Long id1, Long id2) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        tq.setParameter("id2", id2);
        return tq.getResultList();
    }

    public List findAll(String q, Class clazz, Long id, Long id1, Long id2, Long id3) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        tq.setParameter("id2", id2);
        tq.setParameter("id3", id3);
        return tq.getResultList();
    }
    public List findAll(String q, Class clazz, Long id, Long id1, Long id2,Character id4) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", id);
        tq.setParameter("id1", id1);
        tq.setParameter("id2", id2);
        tq.setParameter("id3", id4);
        return tq.getResultList();
    }

    public List findAll(String q, Class clazz, String X) {
        TypedQuery tq = em.createNamedQuery(q, clazz);
        tq.setParameter("id", X);
        return tq.getResultList();
    }

    public Object findSingleResult(String namedQuery, Class classs, Entry<String, Object>... params) {
        try {
            em.joinTransaction();
            TypedQuery typedQuery = em.createNamedQuery(namedQuery, classs);
            for (Entry<String, Object> e : params) {
                typedQuery.setParameter(e.getKey(), e.getValue());
            }
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T findOneResult(String namedQuery, Class<T> clazz, Object... params) {
        try {
            TypedQuery<T> typedQuery = em.createNamedQuery(namedQuery, clazz);
            for (int i = 0; i < params.length; i += 2) {
                typedQuery.setParameter(params[i].toString(), params[i + 1]);
            }
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
//    public List getMax(String q, Class clazz,Long id) {
//        TypedQuery tq = em.createNamedQuery(q, clazz);
//        tq.setParameter("id", id);
//        return ((BigDecimal)tq.getSingleResult()).longValue();
//    }

    public <T> List<T> findThem(String namedQuery, Class<T> clazz, Map.Entry<String, Object>... params) {
        em.joinTransaction();
        TypedQuery<T> typedQuery = em.createNamedQuery(namedQuery, clazz);
        for (Map.Entry<String, Object> e : params) {
            typedQuery.setParameter(e.getKey(), e.getValue());
        }
        return typedQuery.getResultList();
    }

    public List findAll(Class entityClass) {
        em.joinTransaction();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public void delete(Object o) {
        em.remove(em.merge(o));
    }

    public void update(Object o) {
        em.merge(o);
    }

    public Users getUserInfo(String username) {
        TypedQuery<Users> tq = em.createNamedQuery("Users.findByUsername", Users.class);
        tq.setParameter("id", username);
        return tq.getSingleResult();
    }

    public Student getUserInfo2(String username) {
        TypedQuery<Student> tq = em.createNamedQuery("Student.findByUsername", Student.class);
        tq.setParameter("username", username);
        return tq.getSingleResult();
    }
    
}
