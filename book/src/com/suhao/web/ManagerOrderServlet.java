package com.suhao.web;

import com.suhao.pojo.Order;
import com.suhao.service.OrderService;
import com.suhao.service.impl.OrderServiceImpl;
import com.suhao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManagerOrderServlet extends BaseServlet {

    private OrderService os = new OrderServiceImpl();

    protected void traverse(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {

        List<Order> allOrders = os.traverseAllOrders();
        req.setAttribute("allOrders", allOrders);

        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);

    }

    protected void delivery(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{

       Integer status = WebUtils.StringToInt(req.getParameter("status"), -1);

       if(status == 0){
           String orderId = req.getParameter("id");
           os.updateStatus(orderId);
       }

       resp.sendRedirect(req.getHeader("Referer"));
    }

}
