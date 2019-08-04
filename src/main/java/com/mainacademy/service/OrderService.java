package com.mainacademy.service;

import com.mainacademy.dao.CartDAO;
import com.mainacademy.dao.OrderDAO;
import com.mainacademy.model.Cart;
import com.mainacademy.model.Item;
import com.mainacademy.model.Order;
import com.mainacademy.model.User;

import java.util.List;

public class OrderService {

    public static Order createOrderByItemAndUser(Item item, Integer amount,User user) {

        Order order = new Order();
        order.setItemId(item.getId());
        order.setAmount(amount);

        //get or create open chart
        Cart cart = CartDAO.findOpenCartByUser(user.getId());

        if (cart == null) {
            cart = CartService.createCartForUser(user.getId());
        }
        order.setCardId(cart.getId());

        return OrderDAO.create(order);
    }

    public static List<Order> getOrdersByCart(Cart cart) {
        return OrderDAO.findByCart(cart.getId());
    }

    public List<Order> findClosedOrdersByUserAndPeriod(User user, Long from, Long to) {
        return OrderDAO.findClosedOrdersByUserAndPeriod(user.getId(), from, to);
    }
}
