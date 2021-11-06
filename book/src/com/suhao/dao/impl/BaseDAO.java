package com.suhao.dao.impl;

import com.suhao.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO {

    private QueryRunner qr = new QueryRunner();

    public int update(String sql, Object ...args) throws SQLException {

        Connection conn = JDBCUtils.getConnection();

        return qr.update(conn, sql, args);
    }

    public <T> T queryForOne(Class<T> type, String sql, Object ...args) throws SQLException {

        Connection conn = JDBCUtils.getConnection();
        return qr.query(conn, sql, new BeanHandler<T>(type), args);
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args) throws SQLException {

        Connection conn = JDBCUtils.getConnection();
        return qr.query(conn, sql, new BeanListHandler<T>(type), args);
    }

    public Object queryForSingleValue(String sql, Object ...args) throws SQLException {

        Connection conn = JDBCUtils.getConnection();

        return qr.query(conn, sql, new ScalarHandler(), args);
    }



}
