package org.example.service;

import org.example.entity.Users;
import java.util.List;

public interface UsersService {
    Users findById(Integer id);
    List<Users> findAll();
    void save(Users user, String plainPassword);
    void update(Users user);
    void delete(Users user);
    Users findByEmail(String email);
    Users authenticate(String email, String password);
}
