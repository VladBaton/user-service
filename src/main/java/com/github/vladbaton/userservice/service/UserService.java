package com.github.vladbaton.userservice.service;

import com.github.vladbaton.userservice.entity.User;
import com.github.vladbaton.userservice.exception.UserNotFoundException;
import com.github.vladbaton.userservice.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.listAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional(rollbackOn = Exception.class)
    public void createUser(User user) throws ConstraintViolationException {
        userRepository.persistAndFlush(user);
    }

    @Transactional
    public User updateUser(Long userId, User user) throws ConstraintViolationException, UserNotFoundException {
        User foundUser = userRepository.findById(userId);
        if (foundUser == null) {
            throw new UserNotFoundException(userId);
        }
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());
        foundUser.setEmail(user.getEmail());
        userRepository.persistAndFlush(foundUser);
        return foundUser;
    }

    @Transactional
    public Boolean deleteUser(Long userId) throws UserNotFoundException {
        User foundUser = userRepository.findById(userId);
        if (foundUser == null) {
            throw new UserNotFoundException(userId);
        }
        userRepository.delete(foundUser);
        return true;
    }

    @Transactional(rollbackOn = Exception.class)
    public void createUsers(List<User> users) throws ConstraintViolationException {
        for (User user : users) {
            createUser(user);
            
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateUsers(List<User> users) throws ConstraintViolationException, UserNotFoundException {
        for (User user : users) {
            if(updateUser(user.getUserId(), user) == null) {
                throw new UserNotFoundException(user.getUserId());
            }
        }
    }

    /*
        Удаляет пользователей, пользуясь списком Id.
        Если хотя бы один пользователь не найден по Id, то выкидывает исключение, и транзакция
        отменяется.
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteUsers(List<Long> userIds) throws UserNotFoundException{
        for (Long userId : userIds) {
            if(!deleteUser(userId))
                throw new UserNotFoundException(userId);
        }
    }

}
