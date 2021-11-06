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

public class ClientBookServlet extends BaseServlet {

    private BookService bs = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        int pageNo = WebUtils.StringToInt(req.getParameter("pageNo"), 1);
        int pageSize = Page.page_size;
//                WebUtils.StringToInt(req.getParameter("pageSize"), Page.page_size);

        Page<Book> page = bs.page(pageNo, pageSize);

        page.setUrl("client/bookServlet?action=page");

        req.setAttribute("page", page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        int pageNo = WebUtils.StringToInt(req.getParameter("pageNo"), 1);
        int pageSize = Page.page_size;
//                WebUtils.StringToInt(req.getParameter("pageSize"), Page.page_size);

        int min = WebUtils.StringToInt(req.getParameter("min"), 0);
        int max = WebUtils.StringToInt(req.getParameter("max"), Integer.MAX_VALUE);

        Page<Book> page = bs.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        if(req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }

        if(req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }

        page.setUrl(sb.toString());

        req.setAttribute("page", page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
