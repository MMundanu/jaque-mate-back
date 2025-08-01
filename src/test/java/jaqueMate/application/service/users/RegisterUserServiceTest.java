package jaqueMate.application.service.users;

import com.jaqueMate.application.service.user.RegisterUserService;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.model.UserRole;
import jaqueMate.application.service.StubCryptoRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import main.java.com.jaqueMate.domain.model.User;
import static org.junit.jupiter.api.Assertions.*;


public class RegisterUserServiceTest {
    @Test
    public void registerUser(){
        StubUserRepository stubUserRepository = new StubUserRepository();
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        RegisterUserService service = new RegisterUserService(stubUserRepository, cryptoRepository);
        RegisterUserService.RegisterUserRequest request = new RegisterUserService.RegisterUserRequest("Mauro", "mauro@mail.com", "123456");

        service.execute(request);

        Optional<User> saved = stubUserRepository.getUserById(1);
        assertTrue(saved.isPresent());
        assertEquals("Mauro", saved.get().getName());
    }
    @Test
    public void shouldThrowExceptionWhenUserAlreadyExists() {
        User user = new User("Mauro", "mauro@mail.com", "123456", UserRole.USER);
        StubUserRepository stubUserRepository = new StubUserRepository();
        stubUserRepository.save(user);
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        RegisterUserService service = new RegisterUserService(stubUserRepository, cryptoRepository);
        RegisterUserService.RegisterUserRequest request = new RegisterUserService.RegisterUserRequest("Mauro", "mauro@mail.com", "123456");

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Email already exists", exception.getMessage());

    }
    @Test
    public void shouldThrowExceptionWhenNameIsInvalid() {
        StubUserRepository stubUserRepository = new StubUserRepository();
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        RegisterUserService service = new RegisterUserService(stubUserRepository, cryptoRepository);
        RegisterUserService.RegisterUserRequest request = new RegisterUserService.RegisterUserRequest("  ", "mauro@mail.com", "123456");

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Name is required", exception.getMessage());

    }
    @Test
    public void shouldThrowExceptionWhenEmailIsInvalid() {
        StubUserRepository stubUserRepository = new StubUserRepository();
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        RegisterUserService service = new RegisterUserService(stubUserRepository, cryptoRepository);
        RegisterUserService.RegisterUserRequest request = new RegisterUserService.RegisterUserRequest("Mauro", "mauro", "123456");

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Invalid email", exception.getMessage());

    }
    @Test
    public void shouldThrowExceptionWhenPasswordIsInvalid() {
        StubUserRepository stubUserRepository = new StubUserRepository();
        StubCryptoRepository cryptoRepository = new StubCryptoRepository();
        RegisterUserService service = new RegisterUserService(stubUserRepository, cryptoRepository);
        RegisterUserService.RegisterUserRequest request = new RegisterUserService.RegisterUserRequest("Mauro", "mauro@mail.com", "3456");

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Invalid password", exception.getMessage());

    }






}
