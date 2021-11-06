package com.suhao.dao.impl;

import com.suhao.dao.OrderItemDAO;
import com.suhao.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDAOImpl extends BaseDAO implements OrderItemDAO {

    @Override
    public int saveOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> getOrderItem(String orderId) throws SQLException {
        String sql = "Select `id`,`count`, `name`, `price`, `total_price` totalPrice from t_order_item where `order_id` = ?";
        System.out.println(queryForList(OrderItem.class, sql, orderId));
        return queryForList(OrderItem.class, sql, orderId);
    }
}
