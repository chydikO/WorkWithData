package com.mainacademy.dao;

import com.mainacademy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO {

    public static User create(User user) {
        String sql = "INSERT INTO users(login,password, first_name, last_name) VALUES(?,?,?,?) ";
        String sequenceSQL = "SELECT currval(pg_get_serial_sequence('users','id'))";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement sequenceStatement = connection.prepareStatement(sequenceSQL);
            )
        {
            preparedStatement.setString(1 ,user.getLogin());
            preparedStatement.setString(2 ,user.getPassword());
            preparedStatement.setString(3 ,user.getFirstNmae());
            preparedStatement.setString(4 ,user.getLastName());

            preparedStatement.executeUpdate();

            ResultSet resultSet = sequenceStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                user.setId(id);
                return user;
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public static User update(User user) {
        String sql = "UPDATE users SET login = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?";
        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        )
        {
            preparedStatement.setString(1 ,user.getLogin());
            preparedStatement.setString(2 ,user.getPassword());
            preparedStatement.setString(3 ,user.getFirstNmae());
            preparedStatement.setString(4 ,user.getLastName());
            preparedStatement.setInt   (5 ,user.getId());

            preparedStatement.executeUpdate();
            return user;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public static User find (Integer id) {

        return null;
    }

    public static User findById (Integer id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        )
        {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFirstNmae(resultSet.getString("first_name"));

                return user;
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public static List<User> findLogin (String login) {
        String sql = "SELECT * FROM users WHERE login = ?";

        return null;
    }

    public static void delete (Integer id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);)
        {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> findAll () {
        String sql = "SELECT * FROM users";

        return null;
    }
}
