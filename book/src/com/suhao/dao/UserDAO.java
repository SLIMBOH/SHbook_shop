package com.suhao.dao;

import com.suhao.pojo.User;

import java.sql.SQLException;

public interface UserDAO {

    public User queryUserByUsername(String username) throws SQLException;

    public User queryUserByUsernameAndPwd(String username, String password) throws SQLException;

    public int saveUser(User user) throws SQLException;

}
