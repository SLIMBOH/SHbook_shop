package com.suhao.dao.impl;

import com.suhao.dao.OrderDAO;
import com.suhao.pojo.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl extends BaseDAO implements OrderDAO{

    @Override
    public int saveOrder(Order order) throws SQLException {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public Integer queryForPageTotalCount(Integer userId) throws SQLException {
        String sql = "select count(*) from t_order where `user_id` = ?";
        Number count = (Number) queryForSingleValue(sql, userId);
        return count.intValue();
    }

    @Override
    public List<Order> queryForPageItems(Integer user_id, int begin, int pageSize) throws SQLException {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status` from t_order where `user_id`=? limit ?, ?";
//        System.out.println(queryForList(Order.class, sql, user_id, begin, pageSize));
        return queryForList(Order.class, sql, user_id, begin, pageSize);
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`, `user_id` userId from t_order order by `create_time`";
        return queryForList(Order.class, sql);
    }

    @Override
    public void updateStatus(String orderId) throws SQLException {
        String sql = "Update t_order set `status` = ? where order_Id = ?";
        update(sql, 1, orderId);
    }
}
