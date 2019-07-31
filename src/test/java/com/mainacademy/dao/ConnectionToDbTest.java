package com.mainacademy.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionToDbTest {

    @Test
    void getConnection() {

        Connection connection = ConnectionToDB.getConnection();
        assertNotNull(connection);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
