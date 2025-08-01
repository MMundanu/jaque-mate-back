package com.jaqueMate.domain.port;

import main.java.com.jaqueMate.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> getUserById(int id);
    Optional<User> getUserByEmail(String email);

}
