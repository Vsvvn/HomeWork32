package homework32.example.service;

import homework32.example.repository.UserRepository;
import homework32.example.exception.UserNonUniqueException;
import homework32.example.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserService {

    private final List<User> allUsers = new ArrayList<>();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object getAllUserLogins() {
        try {
            Collection<User> users = userRepository.addAllUsers();
            if (users == null) {
                return 0;
            }
            return users
                    .stream()
                    .map(user -> user.getLogin())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        List<String> list = new ArrayList<>();
//        for (User user : users) {
//            String login = user.getLogin();
//            list.add(login);
//        }
//        return list;
    }

    public void createNewUser(String login, String password) {
        User user = new User(login, password);
        if (login == null || login.isEmpty() || login.isBlank()) {
            throw new IllegalArgumentException("Введены неверные значения логина");
        }
        if (password == null || password.isEmpty() || password.isBlank()) {
            throw new IllegalArgumentException("Введены неверные значения пароля");
        }
        boolean userExist = this.userRepository
                .addAllUsers()
                .stream()
                .allMatch(u -> u.equals(user));
//        boolean userExist = true;
//        for (User u : this.userRepository
//                .addAllUsers()) {
//            if (!u.equals(user)) {
//                userExist = false;
//                break;
//            }
//        }
        if (userExist) {
            throw new UserNonUniqueException();
        } else {
            this.userRepository.addUser(user);
        }
    }
}
