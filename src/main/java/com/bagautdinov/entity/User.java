package com.bagautdinov.entity;

public class User {

    private int id;
    private String name;
    private String lastname;
    private String login;
    private String password;

    public User(int id, String name, String lastname, String login, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
