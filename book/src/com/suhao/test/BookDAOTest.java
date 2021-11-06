package com.suhao.test;

import com.suhao.dao.BookDAO;
import com.suhao.dao.impl.BookDAOImpl;
import com.suhao.pojo.Book;
import com.suhao.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class BookDAOTest {

    private BookDAO bd = new BookDAOImpl();

    @Test
    public void addBook() throws SQLException {
        bd.addBook(new Book(null,"天使の囀り", "1911", new BigDecimal(5000), 200000, 0, null));
    }

    @Test
    public void deleteBookByID() throws SQLException {
        bd.deleteBookByID(3);
    }

//    @Test
//    public void updateBook() {
//        bd.updateBook(new Book(6,"麻婆豆腐", "1912", new BigDecimal(2000), 10000, 80, null));
//    }
//
//    @Test
//    public void queryBookByID() {
//        System.out.println(bd.queryBookByID(6));
//    }
//
//    @Test
//    public void queryBooks() {
//        for (Book queryBook : bd.queryBooks()) {
//            System.out.println(queryBook);
//        }
//    }
//    @Test
//    public void queryForPageTotalCount() {
//        System.out.println(bd.queryForPageTotalCount());
//    }
//
//    @Test
//    public void queryForPageItems() {
//        for (Book queryForPageItem : bd.queryForPageItems(0, Page.page_size)) {
//            System.out.println(queryForPageItem);
//        }
//    }
}