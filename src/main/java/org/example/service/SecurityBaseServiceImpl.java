package org.example.service;

import org.example.dao.SecurityBaseDao;
import org.example.entity.SecurityBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SecurityBaseServiceImpl implements SecurityBaseService {

    @Autowired
    private SecurityBaseDao securityBaseDao;

    @Override
    public SecurityBase findById(Integer id) {
        return securityBaseDao.findById(id);
    }

    @Override
    public List<SecurityBase> findAll() {
        return securityBaseDao.findAll();
    }

    @Override
    public void save(SecurityBase entity) {
        securityBaseDao.save(entity);
    }

    @Override
    public void update(SecurityBase entity) {
        securityBaseDao.update(entity);
    }

    @Override
    public void delete(SecurityBase entity) {
        securityBaseDao.delete(entity);
    }
}
