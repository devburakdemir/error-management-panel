package org.example.service;

import org.example.entity.CctResponseCodes;
import java.util.List;

public interface CctResponseCodesService {
    CctResponseCodes findById(Integer id);
    List<CctResponseCodes> findAll();
    void save(CctResponseCodes entity);
    void update(CctResponseCodes entity);
    void delete(CctResponseCodes entity);
}
