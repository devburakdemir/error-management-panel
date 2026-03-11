package org.example.dao;

import org.example.entity.CctResponseCodes;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CctResponseCodesDaoImpl implements CctResponseCodesDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CctResponseCodes findById(Integer id) {
        return sessionFactory.getCurrentSession().get(CctResponseCodes.class, id);
    }

    @Override
    public List<CctResponseCodes> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from CctResponseCodes", CctResponseCodes.class).list();
    }

    @Override
    public void save(CctResponseCodes entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(CctResponseCodes entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(CctResponseCodes entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public boolean existsById(Integer id) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("select count(c) from CctResponseCodes c where c.responseCode = :id", Long.class)
                    .setParameter("id", id)
                    .uniqueResult() > 0;
        } catch (Exception e) {
            return false;
        }
    }

}
