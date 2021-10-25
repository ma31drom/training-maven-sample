package com.epam.service;

import com.epam.exception.UserException;
import com.epam.exception.UserNotFoundException;
import com.epam.model.User;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class RetriableUserService {
    private static final Logger LOGGER = getLogger(RetriableUserService.class);
    private UserService delegate;

    public RetriableUserService(UserService delegate) {
        this.delegate = delegate;
    }

    public User getUser(Integer id) throws UserException {
        try {
            return delegate.getUser(id);
        } catch (UserNotFoundException e) {
            LOGGER.warn("UserNotFound got", e);
            throw e;
        } catch (UserException e) {
            LOGGER.warn("UserException got", e);
            return delegate.getUser(id);
        }
    }


}
