package com.mainacademy.dao;

import com.mainacademy.model.Cart;
import com.mainacademy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Logger;

public class CartDAO {

    private static Logger logger = Logger.getLogger(CartDAO.class.getName());

    public static Cart create(Cart cart) {

        String sql = "INSERT INTO carts(creation_time, cloused, user_id) VALUES(?,?,?)";
        String seqSql = "SELECT currval(pg_get_serial_sequence('carts','id'))";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement seqStatement = connection.prepareStatement(seqSql);
            )
        {
            preparedStatement.setLong(1, cart.getCreationTime());
            preparedStatement.setBoolean(2, cart.getClosed());
            preparedStatement.setInt(3, cart.getUserId());

            preparedStatement.executeUpdate();
            ResultSet resultSet = seqStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                cart.setId(id);
            }
        }
        catch (Exception e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static Cart findById(Integer id) {

        String sql = "SELECT * FROM carts WHERE id = ?";
        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
            )
    {
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Cart cart = new Cart();
            cart.setId(resultSet.getInt("id"));
            cart.setCreationTime(resultSet.getLong("creation_time"));
            cart.setClosed(resultSet.getBoolean("closed"));
            cart.setUserId(resultSet.getInt("user_id"));

            return cart;
        }

    } catch (Exception e) {
        e.getStackTrace();
    }
        return null;
}

    public static Cart update(Cart cart) {

        return null;

    }

    public static List<Cart> findByUser(User user) {

        return null;
    }

    public static Cart findOpenCartByUser(Integer userId) {

        String sql = "SELECT * FROM carts WHERE closed = 0 AND id = ?";
            try (
        Connection connection = ConnectionToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            )
        {
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setId(resultSet.getInt("id"));
                cart.setCreationTime(resultSet.getLong("creation_time"));
                cart.setClosed(resultSet.getBoolean("closed"));
                cart.setUserId(resultSet.getInt("user_id"));

                return cart;
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public static Integer findIdOpenCartByUser(User user) {

        String sql = "SELECT * FROM carts WHERE closed = 0 AND id = ?";
            try (
                    Connection connection = ConnectionToDB.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                )
        {
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return new Integer(resultSet.getInt("id"));
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
}
