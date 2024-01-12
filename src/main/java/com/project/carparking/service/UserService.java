package com.project.carparking.service;

import com.project.carparking.entity.User;
import com.project.carparking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        // You can add additional business logic/validation here if needed
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        // You can add additional business logic/validation here if needed
        userRepository.deleteById(userId);
    }
}
