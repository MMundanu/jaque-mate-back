package jaqueMate.application.service;

import com.jaqueMate.domain.port.CryptoService;
import main.java.com.jaqueMate.domain.model.User;


public class StubCryptoRepository implements CryptoService {
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
    public String generateJWT(User user) {
        return "JWT{\"id\":" + user.getId() +
                ",\"email\":\"" + user.getEmail() + "\"" +
                ",\"role\":\"" + user.getRole().name() + "\"}";
    }

}
