package com.epam;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

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
    public void testUserGetById() {
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
        List<User> users = underTest.getUsers();

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


}
