package org.example.service;

import org.example.entity.SecurityBase;
import java.util.List;

public interface SecurityBaseService {
    SecurityBase findById(Integer id);
    List<SecurityBase> findAll();
    void save(SecurityBase entity);
    void update(SecurityBase entity);
    void delete(SecurityBase entity);
}
