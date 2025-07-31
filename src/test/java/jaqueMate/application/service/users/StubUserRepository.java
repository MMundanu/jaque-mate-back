package jaqueMate.application.service.users;

import com.jaqueMate.domain.port.UserRepository;
import main.java.com.jaqueMate.domain.model.User;

import java.util.*;

public class StubUserRepository implements  UserRepository{
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public void save(User user){
        users.put(user.getId(), user);
    }

    @Override
    public Optional<User> getUserById(int id){
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }


}
