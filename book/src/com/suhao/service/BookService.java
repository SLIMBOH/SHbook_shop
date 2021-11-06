package com.suhao.service;

import com.suhao.pojo.Book;
import com.suhao.pojo.Page;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    public void addBook(Book book) throws SQLException;

    public void deleteBookByID(Integer id) throws SQLException;

    public void updateBook(Book book) throws SQLException;

    public Book queryBookByID(Integer id) throws SQLException;

    public List<Book> queryBooks() throws SQLException;

    Page<Book> page(int pageNo, int pageSize) throws SQLException;

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) throws SQLException;
}
