package org.example.service;

import org.example.dao.CctResponseCodesDao;
import org.example.entity.CctResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CctResponseCodesServiceImpl implements CctResponseCodesService {

    @Autowired
    private CctResponseCodesDao cctResponseCodesDao;

    @Override
    public CctResponseCodes findById(Integer id) { return cctResponseCodesDao.findById(id); }

    @Override
    public List<CctResponseCodes> findAll() { return cctResponseCodesDao.findAll(); }

    @Override
    public void save(CctResponseCodes code) {
        if (cctResponseCodesDao.existsById(code.getResponseCode())) {
            throw new IllegalArgumentException("Bu response koduna sahip kayıt zaten mevcut!");
        }
        cctResponseCodesDao.save(code);
    }


    @Override
    public void update(CctResponseCodes entity) { cctResponseCodesDao.update(entity); }

    @Override
    public void delete(CctResponseCodes entity) { cctResponseCodesDao.delete(entity); }
}
