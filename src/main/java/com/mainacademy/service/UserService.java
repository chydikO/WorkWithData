package com.mainacademy.service;

import com.mainacademy.dao.UserDAO;
import com.mainacademy.model.User;

public class UserService {

    public static User create(User user) {
        return UserDAO.create(user);
    }

    public static User getAuthUser(String login, String password) {
        User user = UserDAO.findByLogin(login);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


}
