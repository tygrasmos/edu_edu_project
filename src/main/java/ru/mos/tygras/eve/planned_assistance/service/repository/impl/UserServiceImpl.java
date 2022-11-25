package ru.mos.tygras.eve.planned_assistance.service.repository.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.User;
import ru.mos.tygras.eve.planned_assistance.repository.UserRepository;
import ru.mos.tygras.eve.planned_assistance.service.repository.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final static String GENERATED_RECORD_ERROR = "error";
    private final static String simpleRole = "USER";

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        String login = user.getLogin();
        if(login != null && !login.equals("") &&!isNameMatch(user.getLogin())){
            user.setRoles(createRoles(login));
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Ошибка добавления нового пользователя. " +
                    "Пользователь с таким логином уже существует!");
        }

    }

    @Override
    public User changeUser(User oldUser, User newUser) {
        if(isNameMatch(oldUser.getLogin())){
            User currentUser = userRepository.findUserByLogin(oldUser.getLogin());
            currentUser.setLogin(newUser.getLogin());
            return userRepository.save(currentUser);
        } else {
            throw new RuntimeException("Ошибка изменения пользователя. " +
                    "Пользователь с таким логином не существует!");
        }
    }

    @Override
    public void deleteUser(User user) {
        if(isNameMatch(user.getLogin())){
            try{
                userRepository.delete(userRepository.findUserByLogin(user.getLogin()));
            } catch (DataIntegrityViolationException e){
                throw new RuntimeException(GENERATED_RECORD_ERROR);
            }
        } else {
            throw new RuntimeException("Ошибка удаления пользователя. " +
                    "Пользователь с таким логином не существует!");
        }
    }

    private Boolean isNameMatch(String login){
        for(User user : userRepository.findAll()){
            if(user.getLogin().equals(login)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private String createRoles(String login){
        StringBuilder sb = new StringBuilder();
        LocalDateTime date = LocalDateTime.now();
        sb.append(simpleRole).append(",");
        sb.append(login.toUpperCase()).append("_")
                .append(date.getHour()).append(".")
                .append(date.getMinute());
        return sb.toString();
    }
}
