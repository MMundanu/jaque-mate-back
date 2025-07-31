package jaqueMate.application.service;

import com.jaqueMate.domain.port.CryptoService;


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

}
