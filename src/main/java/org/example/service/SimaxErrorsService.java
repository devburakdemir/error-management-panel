package org.example.service;

import org.example.entity.SimaxErrors;
import java.util.List;

public interface SimaxErrorsService {
    SimaxErrors findById(Integer id);
    List<SimaxErrors> findAll();
    void save(SimaxErrors entity);
    void update(SimaxErrors entity);
    void delete(SimaxErrors entity);
}
