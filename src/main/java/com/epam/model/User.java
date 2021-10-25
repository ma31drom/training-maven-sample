package com.epam.model;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ']';
    }
}
