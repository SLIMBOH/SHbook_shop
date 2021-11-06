package com.suhao.web;

import com.suhao.pojo.Book;
import com.suhao.pojo.Page;
import com.suhao.service.BookService;
import com.suhao.service.impl.BookServiceImpl;
import com.suhao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookServlet extends BaseServlet{

    private BookService bs = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        int pageNo = WebUtils.StringToInt(req.getParameter("pageNo"), 1);
        int pageSize = Page.page_size;

        Page<Book> page = bs.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");

        req.setAttribute("page", page);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        System.out.println("add");
        int index = WebUtils.StringToInt(req.getParameter("pageNo"),0) + 1;
        Book book = WebUtils.copyParameterToBean(req.getParameterMap(), new Book());
        bs.addBook(book);
        System.out.println(req.getParameter("pageNo"));;
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + index);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = WebUtils.StringToInt(req.getParameter("id"),0);
        bs.deleteBookByID(id);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        Book book = WebUtils.copyParameterToBean(req.getParameterMap(), new Book());
        bs.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = WebUtils.StringToInt(req.getParameter("id"),0);
        Book book = bs.queryBookByID(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        List<Book> books = bs.queryBooks();

        req.setAttribute("books", books);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }
}
