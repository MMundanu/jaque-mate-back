package com.jaqueMate.application.service.user;

import com.jaqueMate.application.service.validations.ValidationsUtils;
import com.jaqueMate.domain.exceptions.InvalidCredentialException;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.exceptions.UserNotRegisteredException;
import com.jaqueMate.domain.port.CryptoService;
import com.jaqueMate.domain.port.UserRepository;
import java.util.Optional;
import main.java.com.jaqueMate.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService {

    private final UserRepository userRepository;
    private final CryptoService cryptoService;

    @Autowired
    public LoginUserService(UserRepository userRepository, CryptoService cryptoService) {
        this.userRepository = userRepository;
        this.cryptoService = cryptoService;

    }
        public String execute(LoginUserRequest request){
            if (!ValidationsUtils.isValidEmail(request.getEmail())) {
                throw new InvalidDataException("Invalid email");
            }
            if (!ValidationsUtils.isValidPassword(request.getPassword())) {
                throw new InvalidDataException("Invalid password");
            }

            Optional<User> user = userRepository.getUserByEmail(request.getEmail());

            if(user.isEmpty()){
                throw new UserNotRegisteredException("Email not registered");
            }
            if (!cryptoService.comparePassword(request.getPassword(), user.get().getPassword())){
                throw new InvalidCredentialException("Credentials invalid");
            }

            return cryptoService.generateJWT(user.get());

        }

        public static class LoginUserRequest{
        private final String email;
        private final String password;

        public LoginUserRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() { return  this.email; }
        public String getPassword() {return this.password;}

        }


}
