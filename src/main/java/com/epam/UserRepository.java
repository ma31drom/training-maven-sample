package com.epam;

import java.util.List;

public interface UserRepository {

    User getById(Integer id);

    List<User> getAll();

    void save(User user);

}
