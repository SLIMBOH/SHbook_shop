package com.suhao.test;

import com.suhao.dao.UserDAO;
import com.suhao.dao.impl.UserDAOImpl;
import com.suhao.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {

//    @Test
//    public void queryUserByUsername() {
//        UserDAO userDao = new UserDAOImpl();
//        if(userDao.queryUserByUsername("admin") == null){
//            System.out.println("Username is Usable");
//        }else{
//            System.out.println("Username exists");
//        }
//    }
//
//    @Test
//    public void queryUserByUsernameAndPwd() {
//        UserDAO userDao = new UserDAOImpl();
//        if(userDao.queryUserByUsernameAndPwd("admin","admin") == null){
//            System.out.println("Login failed");
//        }else{
//            System.out.println("Welcome to bookshop");
//        }
//    }
//
//    @Test
//    public void saveUser() {
//        UserDAO userDao = new UserDAOImpl();
//        System.out.println(userDao.saveUser(new User(null,"suhao","suhao1234","suhao@gmail.com")));
//    }
}