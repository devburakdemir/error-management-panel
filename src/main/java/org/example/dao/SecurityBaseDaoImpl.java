package org.example.dao;

import org.example.entity.SecurityBase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SecurityBaseDaoImpl implements SecurityBaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SecurityBase findById(Integer id) {
        return sessionFactory.getCurrentSession().get(SecurityBase.class, id);
    }

    @Override
    public List<SecurityBase> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from SecurityBase", SecurityBase.class).list();
    }

    @Override
    public void save(SecurityBase entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(SecurityBase entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(SecurityBase entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public SecurityBase findByUserId(Integer userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from SecurityBase s where s.user.userId = :userId", SecurityBase.class)
                .setParameter("userId", userId)
                .uniqueResult();
    }
}
