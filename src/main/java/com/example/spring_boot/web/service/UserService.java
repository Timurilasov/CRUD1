package com.example.spring_boot.web.service;

import com.example.spring_boot.web.model.User;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUserById(Long id);

    @Transactional
    void updateUser(User user);

    Object getUser(Long id);

    List<User> getAllUsers();
}
