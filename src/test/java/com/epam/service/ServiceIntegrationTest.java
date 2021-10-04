package com.epam.service;

import com.epam.model.User;
import com.epam.repo.ListBasedUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServiceIntegrationTest {

    @Test
    public void testSaveGetUser() {

        //GIVEN
        ListBasedUserRepository repo = new ListBasedUserRepository();
        UserService underTest = new UserService(repo);
        User user = new User();
        user.setId(1);
        user.setFirstName("Maxim");
        user.setLastName("Naumovich");

        repo.save(user);

        //WHEN

        User user1 = underTest.getUser(1);
        //THEN
        Assertions.assertEquals(user, user1);

        //SECOND SCENARIO
        Assertions.assertNull(underTest.getUser(2));
    }

}
