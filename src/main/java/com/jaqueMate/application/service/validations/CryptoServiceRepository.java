package com.jaqueMate.application.service.validations;

import com.jaqueMate.domain.port.CryptoService;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceRepository implements CryptoService{
    @Override
    public String hashPassword(String password) {
        return "#hash#" + password;
    }

    @Override
    public Boolean comparePassword(String password, String hashedPassword) {
        String hashed = hashPassword(password);
        return hashed.equals(hashedPassword);
    }

    @Override
    public String generateJWT(main.java.com.jaqueMate.domain.model.User user) {
        return "JWT{\"id\":" + user.getId() +
                ",\"email\":\"" + user.getEmail() + "\"" +
                ",\"role\":\"" + user.getRole().name() + "\"}";
    }
}
