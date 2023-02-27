package homework32.example.service;

import homework32.example.exception.UserNonUniqueException;
import homework32.example.model.User;
import homework32.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void whenInvalidUserIsPassedThenServiceThrowsException() {
        assertThatThrownBy(() -> userService.createNewUser("", "password"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Введены неверные значения логина");
        verify(userRepository, new NoInteractions()).addAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }

    @Test
    void whenRepositoryReturnsNullThenSomethingHappened() {
        when(userRepository.addAllUsers()).thenReturn(null);
        assertThat(userService.getAllUserLogins()).isEqualTo(0);
    }

    @Test
    void createUserNullLogin() {
        assertThrows(IllegalArgumentException.class, () -> userService.createNewUser(null, "password"));
    }

    @Test
    void whenCorrectUserIsAddedThenAddTeamIsCalledFromRepo() {
        when(userRepository.addAllUsers()).thenReturn(List.of());
        when(userRepository.addUser(any())).thenReturn(null);
        userService.createNewUser("login", "password");
        verify(userRepository).addUser(any());
    }

    @Test
    void whenExistUserIsPassedThenServiceThrowsException() {
        when(userRepository.addAllUsers()).thenReturn(List.of(new User("login", "password")));
        assertThatThrownBy(() -> userService.createNewUser("login", "password"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
