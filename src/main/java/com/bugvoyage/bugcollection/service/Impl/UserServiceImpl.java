package com.bugvoyage.bugcollection.service.Impl;

import com.bugvoyage.bugcollection.model.User;
import com.bugvoyage.bugcollection.repository.UserRepository;
import com.bugvoyage.bugcollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}