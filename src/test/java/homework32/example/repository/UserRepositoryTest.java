package homework32.example.repository;

import homework32.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest {

    private User user1;
    private User user2;
    private User user3;

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {

        userRepository = new UserRepository();

        user1 = new User("login", "password");
        user2 = new User("log", "pass");
        user3 = new User("lo", "pa");
    }

    @Test
    @DisplayName("When an empty list of users is received, then the test is correct")
    void getEmptyUsersList() {
        assertTrue(userRepository.addAllUsers().isEmpty());
    }

    @Test
    @DisplayName("When the initially filled list of the service returns those users who were added, then the test is correct")
    public void gettingListOfUsers() {
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        Collection<User> actual = userRepository.addAllUsers();

        List<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);
        expected.add(user3);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When we find a user by login, if there is such a user, then the test is correct")
    void searchUserByLoginIfUserExists() {
        userRepository.addUser(user1);
        Optional<User> user = userRepository.userSearchByLogin("login");
        assertTrue(user.isPresent());
    }

    @Test
    @DisplayName("When we find a user by login, if there is no such user, then the test is correct")
    void searchUserByLoginIfUserDoNotExists() {
        userRepository.addUser(user1);
        Optional<User> user = userRepository.userSearchByLogin("logn");
        assertFalse(user.isPresent());
    }

    @Test
    @DisplayName("When we find a user by login and password, if there is such a user, then the test is correct")
    void searchUserByLoginAndPasswordIfUserExists() {
        userRepository.addUser(user1);
        Optional<User> user = userRepository.userSearchByUserNameAndPassword("login","password");
        assertTrue(user.isPresent());
    }

    @Test
    @DisplayName("When searching for a user, the password matches the existing one and the login does not, then the test is correct")
    void searchUserByLoginAndPasswordButLoginDoNotExists() {
        userRepository.addUser(user1);
        Optional<User> user = userRepository.userSearchByUserNameAndPassword("logn","password");
        assertFalse(user.isPresent());
    }

    @Test
    @DisplayName("When searching for a user, the login matches the existing one and the password does not, then the test is correct")
    void searchUserByLoginAndPasswordButPasswordDoNotExists() {
        userRepository.addUser(user1);
        Optional<User> user = userRepository.userSearchByUserNameAndPassword("login","pasword");
        assertFalse(user.isPresent());
    }
}
