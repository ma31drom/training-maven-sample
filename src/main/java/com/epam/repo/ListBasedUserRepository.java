package com.epam.repo;

import com.epam.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.*;

public class ListBasedUserRepository implements UserRepository, Closeable, AutoCloseable {

    private Map<Integer, User> users = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ListBasedUserRepository.class);

    @Override
    public User getById(Integer id) {
        LOGGER.debug("REPOSITORY: User with id {} getOperation", id);
        User user = users.get(id);
        if (user == null) {
            LOGGER.warn("REPOSITORY: User with id {} not found", id);
            return user;
        }
        LOGGER.info("REPOSITORY: User with id {} retrieved value {}", id, user);
        return user;
    }

    @Override
    public Collection<User> getAll() {
        return users.values();
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void close() {
        System.out.println("close called for repo");
    }
}
