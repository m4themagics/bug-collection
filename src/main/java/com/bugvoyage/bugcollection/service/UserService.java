package com.bugvoyage.bugcollection.service;

import com.bugvoyage.bugcollection.model.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(int id);
    User saveUser(User user);
}