package com.epam.repo;

import com.epam.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Collection;

public class JDBCUserRepositoryTest {

    @Test
    public void testRepository() throws SQLException {
        JDBCUserRepository instance = JDBCUserRepository.getInstance();

        User u1 = new User();
        u1.setFirstName("Max");
        u1.setLastName("Naumovich");


        User u2 = new User();
        u2.setFirstName("Alex");
        u2.setLastName("Alexandrov");

        User save = instance.save(u1);
        instance.save(u2);
        Assertions.assertNotNull(save.getId());

        Collection<User> all = instance.getAll();

        Assertions.assertEquals(all.size(), 2);

        User next = all.iterator().next();

        User byId = instance.getById(next.getId());

        Assertions.assertEquals(next, byId);

        instance.removeAll();

        Assertions.assertEquals(instance.getAll().size(), 0);

    }

}
