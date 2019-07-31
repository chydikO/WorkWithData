package com.mainacademy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Integer id;
    private String login;
    private String password;
    private String firstNmae;
    private String lastName;

    public User(String login, String password, String firstNmae, String lastName) {
        this.login = login;
        this.password = password;
        this.firstNmae = firstNmae;
        this.lastName = lastName;
    }
}

