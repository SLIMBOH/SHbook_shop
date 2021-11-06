package com.suhao.service.impl;

import com.suhao.dao.BookDAO;
import com.suhao.dao.OrderDAO;
import com.suhao.dao.OrderItemDAO;
import com.suhao.dao.impl.BookDAOImpl;
import com.suhao.dao.impl.OrderDAOImpl;
import com.suhao.dao.impl.OrderItemDAOImpl;
import com.suhao.pojo.*;
import com.suhao.service.OrderService;
import com.suhao.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDAO od = new OrderDAOImpl();
    private OrderItemDAO oid = new OrderItemDAOImpl();
    private BookDAO bd = new BookDAOImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);

        od.saveOrder(order);

//        int i = 1/0;

        for(Map.Entry<Integer, CartItem> entry: cart.getItems().entrySet()){

            CartItem ci = entry.getValue();
            OrderItem oi = new OrderItem(null, ci.getName(), ci.getCount(), ci.getPrice(), ci.getTotalPrice(), orderId);
            oid.saveOrderItem(oi);

            Book book = bd.queryBookByID(ci.getId());

            book.setSales(book.getSales() + ci.getCount());

            book.setStock(book.getStock() - ci.getCount());

            bd.updateBook(book);
        }

        cart.clear();

        return orderId;
    }


    @Override
    public Page<Order> page(Integer userId, int pageNo, int pageSize) throws SQLException {

        Page<Order> page = new Page<Order>();

        page.setPageSize(pageSize);
        Integer pageTotalCount = od.queryForPageTotalCount(userId);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = (pageTotalCount % pageSize > 0) ? (pageTotalCount / pageSize) + 1 : (pageTotalCount / pageSize);
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        Integer begin = (pageNo - 1) * pageSize;
        List<Order> items = od.queryForPageItems(userId, begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public List<OrderItem> itemsByOrderId(String orderId) throws SQLException {
        return oid.getOrderItem(orderId);
    }

    @Override
    public List<Order> traverseAllOrders() throws SQLException {
        return od.getAllOrders();
    }

    @Override
    public void updateStatus(String orderId) throws SQLException {
        od.updateStatus(orderId);
    }
}
