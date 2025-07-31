package com.jaqueMate.domain.port;

public interface CryptoService {
    String hashPassword(String password);
    Boolean comparePassword(String password, String hashedPassword);
}
