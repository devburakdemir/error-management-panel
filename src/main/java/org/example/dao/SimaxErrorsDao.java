package org.example.dao;

import org.example.entity.SimaxErrors;

public interface SimaxErrorsDao extends BaseDao<SimaxErrors, Integer>{
    boolean existsById(Integer id);
}
