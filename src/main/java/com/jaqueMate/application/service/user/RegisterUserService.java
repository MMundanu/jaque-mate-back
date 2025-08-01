package com.jaqueMate.application.service.user;
import com.jaqueMate.application.service.validations.ValidationsUtils;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.model.UserRole;
import com.jaqueMate.domain.port.CryptoService;
import com.jaqueMate.domain.port.UserRepository;
import main.java.com.jaqueMate.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserService {
    private final UserRepository userRepository;
    private final CryptoService cryptoService;

    @Autowired
    public RegisterUserService(UserRepository userRepository, CryptoService cryptoService) {
        this.userRepository = userRepository;
        this.cryptoService = cryptoService;

    }

    public void execute(RegisterUserRequest request){

        if (request.getName() == null || request.getName().isBlank()){
            throw new InvalidDataException("Name is required");
        }

        if(!ValidationsUtils.isValidEmail(request.getEmail())){
            throw new InvalidDataException("Invalid email");
        }
        if(!ValidationsUtils.isValidPassword(request.getPassword())){
            throw new InvalidDataException("Invalid password");
        }

        Optional<User> existUserEmail = userRepository.getUserByEmail(request.getEmail());

        if(existUserEmail.isPresent()){
            throw new InvalidDataException("Email already exists");
        }

        String hashedPassword = cryptoService.hashPassword(request.getPassword());

        User newUser = new User(request.getName(), request.getEmail(), hashedPassword, UserRole.USER);

        userRepository.save(newUser);

    }
    public static class RegisterUserRequest{
        private final String name;
        private final String email;
        private final String password;

        public RegisterUserRequest(String name, String email, String password){
            this.name = name;
            this.email = email;
            this.password = password;
        }


        public String getName() { return this.name; }

        public String getEmail() { return  this.email; }

        public String getPassword() { return this.password; }

    }
}
