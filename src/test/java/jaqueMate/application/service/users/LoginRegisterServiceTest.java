package jaqueMate.application.service.users;

import com.jaqueMate.application.service.user.LoginUserService;
import com.jaqueMate.domain.exceptions.InvalidCredentialException;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.exceptions.UserNotRegisteredException;
import com.jaqueMate.domain.model.UserRole;
import jaqueMate.application.service.StubCryptoRepository;
import org.junit.jupiter.api.Test;
import main.java.com.jaqueMate.domain.model.User;
import static org.junit.jupiter.api.Assertions.*;

public class LoginRegisterServiceTest {
    @Test
    public void loginRegister(){
        StubUserRepository userRepository = new StubUserRepository();
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        LoginUserService service = new LoginUserService(userRepository,cryptoRepository);
        String passwordHashed = cryptoRepository.hashPassword("123456");
        User userExist = new User("Mauro", "mauro@mail.com", passwordHashed, UserRole.USER);
        userRepository.save(userExist);
        LoginUserService.LoginUserRequest request = new LoginUserService.LoginUserRequest("mauro@mail.com", "123456");

        String token = service.execute(request);

        assertEquals(token, "JWT{\"id\":" + userExist.getId() +
                ",\"email\":\"" + userExist.getEmail() + "\"" +
                ",\"role\":\"" + userExist.getRole().name() + "\"}");

    }
    @Test
    public void shouldThrowExceptionWhenEmailIsNotRegistered(){
        StubUserRepository userRepository = new StubUserRepository();
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        LoginUserService service = new LoginUserService(userRepository,cryptoRepository);
        String passwordHashed = cryptoRepository.hashPassword("123456");
        User userExist = new User("Mauro", "mauro@mail.com", passwordHashed, UserRole.USER);
        userRepository.save(userExist);
        LoginUserService.LoginUserRequest request = new LoginUserService.LoginUserRequest("user@mail.com", "123456");

        UserNotRegisteredException exception = assertThrows(
                UserNotRegisteredException.class,
                () -> service.execute(request)
        );
        assertEquals("Email not registered", exception.getMessage());
    }
    @Test
    public void shouldThrowExceptionWhenPasswordHashedIsNotRegistered(){
        StubUserRepository userRepository = new StubUserRepository();
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        LoginUserService service = new LoginUserService(userRepository,cryptoRepository);
        String passwordHashed = cryptoRepository.hashPassword("123456");
        User userExist = new User("Mauro", "mauro@mail.com", passwordHashed, UserRole.USER);
        userRepository.save(userExist);
        LoginUserService.LoginUserRequest request = new LoginUserService.LoginUserRequest("mauro@mail.com", "1234567");

        InvalidCredentialException exception = assertThrows(
                InvalidCredentialException.class,
                () -> service.execute(request)
        );
        assertEquals("Credentials invalid", exception.getMessage());
    }
    @Test
    public void shouldThrowExceptionWhenEmailIsInvalid(){
        StubUserRepository userRepository = new StubUserRepository();
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        LoginUserService service = new LoginUserService(userRepository,cryptoRepository);
        String passwordHashed = cryptoRepository.hashPassword("123456");
        User userExist = new User("Mauro", "mauro@mail.com", passwordHashed, UserRole.USER);
        userRepository.save(userExist);
        LoginUserService.LoginUserRequest request = new LoginUserService.LoginUserRequest("mauro@", "123456");

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Invalid email", exception.getMessage());
    }
    @Test
    public void shouldThrowExceptionWhenPasswordIsInvalid(){
        StubUserRepository userRepository = new StubUserRepository();
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        LoginUserService service = new LoginUserService(userRepository,cryptoRepository);
        String passwordHashed = cryptoRepository.hashPassword("123456");
        User userExist = new User("Mauro", "mauro@mail.com", passwordHashed, UserRole.USER);
        userRepository.save(userExist);
        LoginUserService.LoginUserRequest request = new LoginUserService.LoginUserRequest("mauro@mail.com", "12345");

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Invalid password", exception.getMessage());
    }





}
