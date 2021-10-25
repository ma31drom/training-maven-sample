package com.epam.service;

import com.epam.exception.UserException;
import com.epam.exception.UserNotFoundException;
import com.epam.model.User;
import com.epam.repo.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    private static UserService underTest;
    private static UserRepository repoMock;

    @BeforeAll
    public static void init() {
        repoMock = mock(UserRepository.class);
        underTest = new UserService(repoMock);
    }

    @AfterEach
    public void resetMock() {
        reset(repoMock);
    }

    @Test
    public void testUserGetById() throws UserException {
        //GIVEN
        User source = new User();
        when(repoMock.getById(eq(1))).thenReturn(source);

        //WHEN
        User user = underTest.getUser(1);

        //THEN
        Assertions.assertEquals(source, user);
        verify(repoMock, times(1)).getById(any());


    }

    @Test
    public void testUsersGetAll() {
        //GIVEN
        User source = new User();
        when(repoMock.getAll()).thenReturn(Arrays.asList(source));

        //WHEN
        Collection<User> users = underTest.getUsers();

        //THEN
        Assertions.assertEquals(Arrays.asList(source), users);
    }

    @Test
    public void testUserSave() {
        //GIVEN
        User source = new User();

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        doNothing().when(repoMock).save(captor.capture());


        //WHEN
        underTest.saveUser(source);

        //THEN
        Assertions.assertEquals(source, captor.getValue());
    }

    @Test
    public void testUserGetByIdUserNotFound() {
        //GIVEN
        User source = new User();
        when(repoMock.getById(eq(1))).thenReturn(source);

        //WHEN
        //User user = underTest.getUser(1);

        //THEN
        Assertions.assertThrows(UserNotFoundException.class, () -> underTest.getUser(2));
        //Assertions.assertEquals(source, user);
        verify(repoMock, times(1)).getById(any());


    }

    @Test
    public void testUserGetByIdWithRetry() {
        //GIVEN
        User source = new User();
        when(repoMock.getById(eq(1))).thenReturn(source);
        RetriableUserService underTestRetry = new RetriableUserService(underTest);
        //WHEN
        //User user = underTest.getUser(1);

        //THEN
        Assertions.assertThrows(UserNotFoundException.class, () -> underTestRetry.getUser(2));
        //Assertions.assertEquals(source, user);
        verify(repoMock, times(1)).getById(any());


    }

    @Test
    public void testUserGetByIdWithRetry2() throws UserException {
        //GIVEN
        User source = new User();
        when(repoMock.getById(eq(1))).thenReturn(source);
        RetriableUserService underTestRetry = new RetriableUserService(underTest);
        //WHEN


        //THEN
        for (int i = 0; i < 50; i++) {
            underTestRetry.getUser(1);
        }
        //Assertions.assertEquals(source, user);
        verify(repoMock, times(50)).getById(any());

    }
}
