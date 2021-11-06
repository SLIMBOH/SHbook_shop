package com.suhao.service;

import com.suhao.pojo.Cart;
import com.suhao.pojo.Order;
import com.suhao.pojo.OrderItem;
import com.suhao.pojo.Page;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    public String createOrder(Cart cart, Integer userId) throws SQLException;

    public Page<Order> page(Integer userId, int pageNo, int pageSize) throws SQLException;

    public List<OrderItem> itemsByOrderId(String orderId) throws SQLException;

    public List<Order> traverseAllOrders() throws SQLException;

    public void updateStatus(String orderId) throws SQLException;
}
