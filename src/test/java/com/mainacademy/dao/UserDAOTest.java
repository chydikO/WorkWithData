package com.mainacademy.dao;

import com.mainacademy.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserDAOTest {
    private static List<User> users = new ArrayList<>();

    @BeforeAll
    static void SetUp() {
        User user = new User("test_login", "test_pass", "test_name", "test_surname");
        users.add(user);
    }

    @AfterAll
    static void tearDown() {
        for (User user: users) {
            if (user.getId() != null) {
                UserDAO.delete(user.getId());
            }
        }
    }

    @Test
    void creatAndFindAndDelete() {
        assertNull(users.get(0).getId());
        User userInDB = UserDAO.create(users.get(0));

        assertNotNull(userInDB);
        assertNotNull(userInDB.getId());

        User checkedUSerInDB = UserDAO.findById(userInDB.getId());
        assertNotNull(checkedUSerInDB);

        UserDAO.delete(checkedUSerInDB.getId());
        User deletedUser = UserDAO.findById(userInDB.getId());
        assertNull(deletedUser);
    }
}
