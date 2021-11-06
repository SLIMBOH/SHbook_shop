package com.suhao.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource dataSource;
    private static ThreadLocal<Connection> connection = new ThreadLocal<>();

    static{

        try {
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties prop = new Properties();
            prop.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(prop);
//            Connection conn = dataSource.getConnection();
//
//            System.out.println(conn);
//            connection.set(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//
//    }

    public static Connection getConnection(){
        Connection conn = connection.get();

        if(conn == null){
            try {
                conn = dataSource.getConnection();
                connection.set(conn);

                conn.setAutoCommit(false);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return conn;
    }

    public static void commitAndClose(){

        Connection conn = connection.get();
        if(conn != null){
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        connection.remove();

    }

    public static void rollbackAndClose(){

        Connection conn = connection.get();
        if(conn != null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        connection.remove();

    }

//    public static void close(Connection conn) {
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
}
