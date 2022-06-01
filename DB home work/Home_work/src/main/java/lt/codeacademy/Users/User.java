package lt.codeacademy.Users;

import java.util.UUID;

public class User {
    private UUID id;
    private UserType userType;
    private String username;
    private String password;
    private String name;
    private String surname;

    public User() {
    }

    public User(UUID id, UserType userType, String username, String password, String name, String surname) {
        this.id = id;
        this.userType = userType;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public User setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }
}


