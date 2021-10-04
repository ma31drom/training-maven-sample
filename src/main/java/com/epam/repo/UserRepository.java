package com.epam.repo;

import com.epam.model.User;

import java.util.Collection;

public interface UserRepository {

    User getById(Integer id);

    Collection<User> getAll();

    void save(User user);

}
