package com.suhao.dao;

import com.suhao.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDAO {

    public int saveOrderItem(OrderItem orderItem) throws SQLException;

    public List<OrderItem> getOrderItem(String orderId) throws SQLException;
}
