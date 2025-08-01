package com.jaqueMate.domain.port;
import main.java.com.jaqueMate.domain.model.User;

public interface CryptoService {
    String hashPassword(String password);
    Boolean comparePassword(String password, String hashedPassword);
    String generateJWT(User user);
}
