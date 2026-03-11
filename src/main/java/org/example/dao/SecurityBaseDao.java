package org.example.dao;

import org.example.entity.SecurityBase;

public interface SecurityBaseDao extends BaseDao<SecurityBase, Integer> {
    SecurityBase findByUserId(Integer userId);
}
