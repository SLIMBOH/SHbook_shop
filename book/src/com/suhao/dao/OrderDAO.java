package com.suhao.dao;

import com.suhao.pojo.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    public int saveOrder(Order order) throws SQLException;

    Integer queryForPageTotalCount(Integer userId) throws SQLException;

    List<Order> queryForPageItems(Integer user_id, int begin, int pageSize) throws SQLException;

    public List<Order> getAllOrders() throws SQLException;

    public void updateStatus(String orderId) throws SQLException;
}
