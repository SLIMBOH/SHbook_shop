package com.suhao.test;

import com.suhao.pojo.User;
import com.suhao.service.UserService;
import com.suhao.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService us = new UserServiceImpl();

//    @Test
//    public void register() {
//        us.register(new User(null,"tom","tom1234","tom@gmail.com"));
//    }
//
//    @Test
//    public void login() {
//        us.login(new User(null,"suhao", "suhao1234",null));
//    }
//
//    @Test
//    public void existUsername() {
//        us.existUsername("admin");
//    }
}