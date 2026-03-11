package org.example.dao;

import org.example.entity.CctResponseCodes;

public interface CctResponseCodesDao extends BaseDao<CctResponseCodes, Integer>{
    boolean existsById(Integer id);
}
