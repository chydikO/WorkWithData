package com.mainacademy.dao;

import com.mainacademy.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemDAO {
    public static Item addItem(Item item) {
        String sql = "INSERT INTO items( item_code, name, price) VALUES( ?, ?, ?)";
        String sequenceSQL = "SELECT currval(pg_get_serial_sequence('items','id'))";// current value id in table

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement sequenceStatement = connection.prepareStatement(sequenceSQL);
                )
        {
            preparedStatement.setString(1, item.getItemCode());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(3, item.getPrice());

            preparedStatement.executeUpdate();
            ResultSet resultSet = sequenceStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                item.setId(id);
                return item;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return null;
    }

    public static Item update (Item item) {

        return null;
    }

    public static Item findById (Integer id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        )
        {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setItemCode(resultSet.getString("item_code"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));

                return item;
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public static List<Item> findName(String name) {

        return null;
    }

    public static void delete (Integer id) {
        String sql = "DELETE FROM items WHERE id = ?";
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

    public static List<Item> findAll() {

        return null;
    }

}
