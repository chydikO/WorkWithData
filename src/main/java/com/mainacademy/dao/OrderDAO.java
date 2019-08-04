package com.mainacademy.dao;

import com.mainacademy.model.Cart;
import com.mainacademy.model.Item;
import com.mainacademy.model.Order;
import com.mainacademy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderDAO {
    private static Logger logger = Logger.getLogger(OrderDAO.class.getName());

    public static Order create(Order order) {
        String sql = "INSERT INTO orders(item_id, amount, card_id) VALUES(?,?,?)";
        String seqSql = "SELECT currval(pg_get_serial_sequence('orders','id'))";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement seqStatement = connection.prepareStatement(seqSql);
                )
        {
            preparedStatement.setInt(1, order.getItemId());
            preparedStatement.setInt(2, order.getAmount());
            preparedStatement.setInt(3, order.getCardId());

            preparedStatement.executeUpdate();
            ResultSet resultSet = seqStatement.executeQuery();

            while (resultSet.next()) {
                Integer  orderId = resultSet.getInt("id");
                order.setId(orderId);
                return order;
            }
        }
        catch (Exception e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static Order findById(Integer id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        )
        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setItemId(resultSet.getInt("item_id"));
                order.setAmount(resultSet.getInt("amount"));
                order.setCardId(resultSet.getInt("card_id"));

                return order;
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public static void delete(Integer id) {
        String sql = "DELETE FROM orders WHERE id = ?";
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

    public static Order update(Order order) {

        return null;

    }

    public static List<Order> findByUser(User user) {

        return null;
    }

    public static List<Order> findByCart(Integer cartId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE card_id = ?";
        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        )
        {
            preparedStatement.setInt(1, cartId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setItemId(resultSet.getInt("item_id"));
                order.setAmount(resultSet.getInt("amount"));
                order.setCardId(resultSet.getInt("card_id"));
                orders.add(order);
            }
            return orders;

        } catch (Exception e) {
            e.getStackTrace();
        }

        return orders;
    }


    public static List<Order> findClosedOrdersByUserAndPeriod(Integer userId, Long from, Long to) {
        String sql = "SELECT o.id, o.item_id, o.amount, o.cart_id FROM orders o " +
                            "JOIN carts c On o.card_ = c_id" +
                                "WHERE c.user_id = ? AND c.creation_time >= ? AND c.creation_time <= ? " +
                                    "Order BY c.creation_time";


        return null;
    }
}
