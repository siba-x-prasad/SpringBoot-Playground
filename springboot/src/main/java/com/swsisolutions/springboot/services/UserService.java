package com.swsisolutions.springboot.services;

import org.springframework.stereotype.Service;

import com.swsisolutions.springboot.model.User;

import java.util.*;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();
    private long nextId = 1;

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUserById(Long id) {
        return users.get(id);
    }

    public User createUser(User user) {
        user.setId(nextId++);
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        if (!users.containsKey(id)) {
            return null;
        }
        updatedUser.setId(id);
        users.put(id, updatedUser);
        return updatedUser;
    }

    public boolean deleteUser(Long id) {
        return users.remove(id) != null;
    }

//    public Page<User> getUsers(int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return userRepository.findAll(pageable);
//    }
}

