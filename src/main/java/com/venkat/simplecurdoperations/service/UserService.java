package com.venkat.simplecurdoperations.service;

import com.venkat.simplecurdoperations.models.User;
import com.venkat.simplecurdoperations.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final User user) {
        final User existingUser = findUserByEmail(user.getEmail());
        if (existingUser != null) {
            return existingUser;
        }
        return userRepository.save(user);
    }

    public User updateUser(final User user) {
        final User existingUser = findUserById(user.getId());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setPassword(user.getPassword());
            existingUser.setActive(user.isActive());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(final long id) {
        userRepository.deleteById(id);
    }

    public User findUserById(final long id) {
        final Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    private User findUserByEmail(final String email) {
        final Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }
}
