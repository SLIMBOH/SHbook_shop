package com.suhao.dao;

import com.suhao.pojo.Book;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BookDAO {

    public int addBook(Book book) throws SQLException;

    public int deleteBookByID(Integer id) throws SQLException;

    public int updateBook(Book book) throws SQLException;

    public Book queryBookByID(Integer id) throws SQLException;

    public List<Book> queryBooks() throws SQLException;

    public Integer queryForPageTotalCount() throws SQLException;

    public Integer queryForPageTotalCountByPrice(Integer min, Integer max) throws SQLException;

    public List<Book> queryForPageItems(Integer begin, Integer pageSize) throws SQLException;

    public List<Book> queryForPageItemsByPrice(Integer begin, Integer pageSize, Integer min, Integer max) throws SQLException;
}
