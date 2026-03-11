package org.example.service;

import org.example.dao.SecurityBaseDao;
import org.example.dao.UsersDao;
import org.example.entity.SecurityBase;
import org.example.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private SecurityBaseDao securityBaseDao;

    @Override
    public Users findById(Integer id) {
        return usersDao.findById(id);
    }

    @Override
    public List<Users> findAll() {
        return usersDao.findAll();
    }

    @Override
    public void save(Users user, String plainPassword) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Geçerli bir email giriniz");
        }
        if (usersDao.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Bu email zaten kayıtlı");
        }

        usersDao.save(user);

        byte[] salt = generateSalt();
        byte[] hash = hashPassword(plainPassword, salt);

        SecurityBase securityBase = new SecurityBase();
        securityBase.setUser(user);
        securityBase.setPasswordHash(hash);
        securityBase.setSalt(salt);
        securityBase.setRegistrationDate(LocalDateTime.now());
        securityBase.setLastPasswordChange(LocalDateTime.now());
        securityBase.setActive(true);
        securityBase.setLocked(false);
        securityBase.setLoginAttempts(0);

        securityBaseDao.save(securityBase);
    }

    @Override
    public void update(Users user) {
        usersDao.update(user);
    }

    @Override
    public void delete(Users user) {
        usersDao.delete(user);
    }

    @Override
    public Users findByEmail(String email) {
        return usersDao.findByEmail(email);
    }

    @Override
    public Users authenticate(String email, String password) {
        Users user = usersDao.findByEmail(email);
        if (user == null) {
            return null;
        }

        SecurityBase security = findSecurityByUserId(user.getUserId());
        if (security == null || !security.isActive() || security.isLocked()) {
            return null;
        }

        byte[] hash = hashPassword(password, security.getSalt());
        if (Arrays.equals(hash, security.getPasswordHash())) {
            security.setLastLogInDate(LocalDateTime.now());
            security.setLoginAttempts(0);
            securityBaseDao.update(security);
            return user;
        } else {
            security.setLoginAttempts(security.getLoginAttempts() + 1);
            if (security.getLoginAttempts() >= 5) {
                security.setLocked(true);
            }
            securityBaseDao.update(security);
            return null;
        }
    }

    private SecurityBase findSecurityByUserId(Integer userId) {
        List<SecurityBase> all = securityBaseDao.findAll();
        for (SecurityBase sb : all) {
            if (sb.getUser() != null && sb.getUser().getUserId().equals(userId)) {
                return sb;
            }
        }
        return null;
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            return md.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Şifreleme hatası", e);
        }
    }
}
