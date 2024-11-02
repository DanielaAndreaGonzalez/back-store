package com.store.back.models.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Long getUserIdByUsername(String username) {
        Optional<User> userEntity = userRepository.findByUsername(username);
        return userEntity.map(User::getId).orElse(null);
    }
}
