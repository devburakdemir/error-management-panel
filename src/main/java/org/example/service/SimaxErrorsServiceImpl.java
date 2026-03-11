package org.example.service;

import org.example.dao.SimaxErrorsDao;
import org.example.entity.SimaxErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SimaxErrorsServiceImpl implements SimaxErrorsService {

    @Autowired
    private SimaxErrorsDao simaxErrorsDao;

    @Override
    public SimaxErrors findById(Integer id) {
        return simaxErrorsDao.findById(id);
    }

    @Override
    public List<SimaxErrors> findAll() {
        return simaxErrorsDao.findAll();
    }

    @Override
    public void save(SimaxErrors error) {
        if (simaxErrorsDao.existsById(error.getErrorCode())) {
            throw new IllegalArgumentException("Bu hata koduna sahip kayıt zaten mevcut!");
        }
        simaxErrorsDao.save(error);
    }

    @Override
    public void update(SimaxErrors entity) {
        simaxErrorsDao.update(entity);
    }

    @Override
    public void delete(SimaxErrors entity) {
        simaxErrorsDao.delete(entity);
    }
}
