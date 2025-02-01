package com.tenet.service;

import com.tenet.exceptions.UserNotFoundException;
import com.tenet.model.User;
import com.tenet.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User getUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User: " + email + " not found"));
    }
}
