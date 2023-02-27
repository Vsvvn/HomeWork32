package homework32.example.repository;

import homework32.example.model.User;

import java.util.*;

public class UserRepository {

    List<User> users = new ArrayList<User>();

    public User addUser(User user) {
        this.users.add(user);
        return user;
    }

    public List<User> addAllUsers() {
        return users;
    }

    public Optional<User> userSearchByLogin(String login) {
        return users
                .stream()
                .filter(e -> e.getLogin().equals(login))
                .findAny();

//        for (User user : users) {
//            if (user.getLogin().equals(login)) {
//                return Optional.of(user);
//            }
//        }
//        return Optional.empty();
    }

    public Optional<User> userSearchByUserNameAndPassword(String login, String password) {
        return users
                .stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findAny();

//        for (User user : users) {
//            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
//                return Optional.of(user);
//            }
//        }
//        return Optional.empty();
    }
}
