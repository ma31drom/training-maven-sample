package com.epam.model;

import java.io.Serializable;

public class Profile implements Serializable {

    private static final Long serialVersionUID = 1l;

    private Integer id;
    private String nickname;
    private String descripion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", descripion='" + descripion + '\'' +
                '}';
    }
}
