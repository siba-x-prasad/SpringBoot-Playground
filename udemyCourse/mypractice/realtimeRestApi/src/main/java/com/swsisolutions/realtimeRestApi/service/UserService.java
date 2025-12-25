package com.swsisolutions.realtimeRestApi.service;

import com.swsisolutions.realtimeRestApi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findUser(int id);
    void deleteUser(int id);
    User addUser(User user);
    User updateUser(User user);
}
