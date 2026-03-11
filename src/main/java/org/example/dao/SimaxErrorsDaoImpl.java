package org.example.dao;

import org.example.entity.SimaxErrors;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SimaxErrorsDaoImpl implements SimaxErrorsDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SimaxErrors findById(Integer id) {
        return sessionFactory.getCurrentSession().get(SimaxErrors.class, id);
    }

    @Override
    public List<SimaxErrors> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from SimaxErrors", SimaxErrors.class).list();
    }

    @Override
    public void save(SimaxErrors entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(SimaxErrors entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(SimaxErrors entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public boolean existsById(Integer id) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("select count(e) from SimaxErrors e where e.errorCode = :id", Long.class)
                    .setParameter("id", id)
                    .uniqueResult() > 0;
        } catch (Exception e) {
            return false;
        }
    }

}
