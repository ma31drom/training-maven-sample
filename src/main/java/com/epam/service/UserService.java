package com.epam.service;

import com.epam.model.User;
import com.epam.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class UserService {

    private UserRepository repo;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User getUser(Integer id) {

        LOGGER.debug("SERVICE: User with id {} getOperation", id);
        User user = repo.getById(id);
        if (user == null) {
            LOGGER.warn("SERVICE: User with id {} not found", id);
            return user;
        }
        LOGGER.info("SERVICE: User with id {} retrieved value {}", id, user);
        return user;
    }

    public Collection<User> getUsers() {
        return repo.getAll();
    }

    public void saveUser(User user) {
        repo.save(user);
    }

}
