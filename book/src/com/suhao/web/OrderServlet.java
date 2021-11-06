package com.suhao.web;

import com.suhao.pojo.*;
import com.suhao.service.OrderService;
import com.suhao.service.impl.OrderServiceImpl;
import com.suhao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    OrderService os = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        Cart cart = (Cart)req.getSession().getAttribute("cart");
        User currentUser = (User)req.getSession().getAttribute("user");

        if(currentUser == null){
            req.getSession().removeAttribute("cart");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        String orderId = os.createOrder(cart, currentUser.getId());

        req.getSession().setAttribute("orderID", orderId);
        cart.clear();
        req.getSession().removeAttribute("cart");
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {

        int pageNo = WebUtils.StringToInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.StringToInt(req.getParameter("pageSize"), Page.page_size);

        User user = (User)req.getSession().getAttribute("user");
        Integer userId = user.getId();
        Page<Order> page = os.page(userId, pageNo, pageSize);
        page.setUrl("orderServlet?action=page");

        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void showDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException{

        List<OrderItem> orderItems = os.itemsByOrderId(req.getParameter("id"));

        System.out.println(orderItems);

        req.getSession().setAttribute("orderItems", orderItems);

        resp.sendRedirect(req.getContextPath() + "/pages/order/orderItem.jsp");
    }
}
