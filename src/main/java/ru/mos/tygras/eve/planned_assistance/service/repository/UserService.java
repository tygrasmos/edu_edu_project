package ru.mos.tygras.eve.planned_assistance.service.repository;

import ru.mos.tygras.eve.planned_assistance.model.entity.User;

import java.util.List;

public interface UserService {

    User findUserById(Long userId);

    User findUserByLogin(String login);

    List<User> findAll();

    User addUser(User user);

    User changeUser(User oldUser, User newUser);

    void deleteUser(User user);
}
