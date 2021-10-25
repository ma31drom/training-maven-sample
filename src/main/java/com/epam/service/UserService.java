package com.epam.service;

import com.epam.exception.UserException;
import com.epam.exception.UserNotFoundException;
import com.epam.model.User;
import com.epam.repo.UserRepository;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;


import java.util.Collection;

public class UserService {

    private UserRepository repo;

    private static final Logger LOGGER = getLogger(UserService.class);

    public UserService(UserRepository repo) {
        repo.getRepoType();
        this.repo = repo;
    }

    public User getUser(Integer id) throws UserException {
        User user;
        LOGGER.debug("SERVICE: User with id {} getOperation", id);

        if (Math.random() * 100 < 50) { //DB died
            user = repo.getById(id);
        } else {
            throw new UserException();
        }

        if (user == null) {
            LOGGER.warn("SERVICE: User with id {} not found", id);
            throw new UserNotFoundException(id);
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
