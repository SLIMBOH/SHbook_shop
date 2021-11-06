package com.suhao.test;

import com.suhao.dao.OrderDAO;
import com.suhao.dao.OrderItemDAO;
import com.suhao.dao.impl.OrderDAOImpl;
import com.suhao.dao.impl.OrderItemDAOImpl;
import com.suhao.pojo.Order;
import com.suhao.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

public class OrderDAOImplTest {

    @Test
    public void saveOrder() throws SQLException {
        OrderDAO od = new OrderDAOImpl();

        od.saveOrder(new Order("123456", new Date(), new BigDecimal(100), 0, 1));
    }

    @Test
    public void saveOrderItem() throws SQLException {
        OrderItemDAO oid = new OrderItemDAOImpl();

        oid.saveOrderItem(new OrderItem( null,"EldenRings5", 1, new BigDecimal(80), new BigDecimal(80), "123456"));
        oid.saveOrderItem(new OrderItem( null,"EldenRings6", 1, new BigDecimal(80), new BigDecimal(80), "123456"));
        oid.saveOrderItem(new OrderItem( null,"EldenRings7", 1, new BigDecimal(80), new BigDecimal(80), "123456"));
    }
}