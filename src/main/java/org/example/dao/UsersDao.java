package org.example.dao;

import org.example.entity.Users;

public interface UsersDao extends BaseDao<Users, Integer> {
    Users findByEmail(String email);
}
