package com.suhao.service.impl;

import com.suhao.dao.UserDAO;
import com.suhao.dao.impl.UserDAOImpl;
import com.suhao.pojo.User;
import com.suhao.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDAO userDao = new UserDAOImpl();

    @Override
    public void register(User user) throws SQLException {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) throws SQLException {
        return userDao.queryUserByUsernameAndPwd(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) throws SQLException {
        if(userDao.queryUserByUsername(username) == null){
            System.out.println("Username usable");
            return false;
        }else{
            System.out.println("Username is not usable");
            return true;
        }
    }
}
