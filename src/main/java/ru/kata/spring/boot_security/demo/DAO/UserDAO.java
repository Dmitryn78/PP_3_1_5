package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);

    void deleteUserById(long id);

    void updateUser(User user);

    User getUserById(long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
