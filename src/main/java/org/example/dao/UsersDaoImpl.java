package org.example.dao;

import org.example.entity.Users;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Users findById(Integer id) {
        return sessionFactory.getCurrentSession().get(Users.class, id);
    }

    @Override
    public List<Users> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Users", Users.class).list();
    }

    @Override
    public void save(Users entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(Users entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(Users entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public Users findByEmail(String email) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Users u where u.email = :email", Users.class)
                .setParameter("email", email)
                .uniqueResult();
    }
}
