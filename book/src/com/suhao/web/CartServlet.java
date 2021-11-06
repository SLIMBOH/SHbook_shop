package com.suhao.web;

import com.google.gson.Gson;
import com.suhao.pojo.Book;
import com.suhao.pojo.Cart;
import com.suhao.pojo.CartItem;
import com.suhao.service.BookService;
import com.suhao.service.impl.BookServiceImpl;
import com.suhao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    BookService bs = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        Integer itemId = WebUtils.StringToInt(req.getParameter("id"), -1);

        Book book = bs.queryBookByID(itemId);

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        CartItem cartItem = new CartItem(book.getId(), 1, book.getName(), book.getPrice(), book.getPrice());

        if(cart.getItems().get(book.getId()) != null && book.getStock() < cart.getItems().get(book.getId()).getCount() + 1){
            req.getSession().setAttribute("Msg", "Not enough of books");
            resp.sendRedirect(req.getHeader("Referer"));
            return;
        }

        cartItem.setStock(book.getStock());

        req.getSession().setAttribute("lastBook", book);

        cart.addItem(cartItem);

        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = WebUtils.StringToInt(req.getParameter("id"), -1);
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        req.setAttribute("cart", cart);

        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.getSession().removeAttribute("cart");
        Cart cart = (Cart)req.getSession().getAttribute("cart");

        if(cart != null){
            cart.clear();
        }

        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int id = WebUtils.StringToInt(req.getParameter("id"), -1);
        int quantity = WebUtils.StringToInt(req.getParameter("quantity"), -1);


        if(id > 0 && quantity > 0){
            Cart cart = (Cart)req.getSession().getAttribute("cart");

            cart.updateCount(id, quantity);
            req.getSession().setAttribute("cart", cart);
        }

        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {

        Integer itemId = WebUtils.StringToInt(req.getParameter("id"), -1);

        Book book = bs.queryBookByID(itemId);

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        Map<String, Object> resultMap = new HashMap<>();

        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        CartItem cartItem = new CartItem(book.getId(), 1, book.getName(), book.getPrice(), book.getPrice());

        if(cart.getItems().get(book.getId()) != null && book.getStock() < cart.getItems().get(book.getId()).getCount() + 1){
//            req.getSession().setAttribute("Msg", "Not enough of books");
//            resp.sendRedirect(req.getHeader("Referer"));
            resultMap.put("Msg", "Not enough of books");
            Gson gson = new Gson();
            String json = gson.toJson(resultMap);
            resp.getWriter().write(json);
            return;
        }

        cartItem.setStock(book.getStock());
        cart.addItem(cartItem);

        resultMap.put("lastBook", cartItem.getName());
        resultMap.put("totalCount", cart.getTotalCount());

//        resp.sendRedirect(req.getHeader("Referer"));

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }
}
