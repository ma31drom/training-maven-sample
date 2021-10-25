package com.epam.exception;

public class UserNotFoundException extends UserException {

    private Integer id;

    public UserNotFoundException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
