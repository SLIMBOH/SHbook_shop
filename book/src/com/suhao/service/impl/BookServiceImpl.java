package com.suhao.service.impl;

import com.suhao.dao.BookDAO;
import com.suhao.dao.impl.BookDAOImpl;
import com.suhao.pojo.Book;
import com.suhao.pojo.Page;
import com.suhao.service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bd = new BookDAOImpl();

    @Override
    public void addBook(Book book) throws SQLException {
        bd.addBook(book);
    }

    @Override
    public void deleteBookByID(Integer id) throws SQLException {
        bd.deleteBookByID(id);
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        bd.updateBook(book);
    }

    @Override
    public Book queryBookByID(Integer id) throws SQLException {
        return bd.queryBookByID(id);
    }

    @Override
    public List<Book> queryBooks() throws SQLException {
        return bd.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) throws SQLException {

        Page<Book> page = new Page<Book>();

        Integer pageTotalCount = bd.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = (pageTotalCount % pageSize > 0) ? (pageTotalCount / pageSize) + 1 : (pageTotalCount / pageSize);
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        Integer begin = (pageNo - 1) * pageSize;
        List<Book> items = bd.queryForPageItems(begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) throws SQLException {
        Page<Book> page = new Page<Book>();

        Integer pageTotalCount = bd.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = (pageTotalCount % pageSize > 0) ? (pageTotalCount / pageSize) + 1 : (pageTotalCount / pageSize);
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        Integer begin = (pageNo - 1) * pageSize;
        List<Book> items = bd.queryForPageItemsByPrice(begin, pageSize, min, max);
        page.setItems(items);

        return page;
    }
}
