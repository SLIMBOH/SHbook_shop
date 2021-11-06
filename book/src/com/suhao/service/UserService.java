package com.suhao.service;

import com.suhao.pojo.User;

import java.sql.SQLException;

public interface UserService {

    public void register(User user) throws SQLException;

    public User login(User user) throws SQLException;

    public boolean existUsername(String username) throws SQLException;
}
