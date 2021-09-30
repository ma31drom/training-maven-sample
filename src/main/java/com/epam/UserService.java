package com.epam;

import java.util.List;

public class UserService {

    private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User getUser(Integer id) {
        return repo.getById(id);
    }

    public List<User> getUsers() {
        return repo.getAll();
    }

    public void saveUser(User user) {
        repo.save(user);
    }

}
