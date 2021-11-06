package com.suhao.web;
import com.google.gson.Gson;
import com.suhao.pojo.User;
import com.suhao.service.UserService;
import com.suhao.service.impl.UserServiceImpl;
import com.suhao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    UserService us = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        User neoUser = (User)WebUtils.copyParameterToBean(req.getParameterMap(),new User());

        neoUser = us.login(neoUser);

        if(neoUser == null){
            req.setAttribute("msg","Wrong ID or Password");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            req.getSession().setAttribute("user", neoUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        String temp = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        req.getSession().invalidate();

        String code = req.getParameter("code");

        User newUser = WebUtils.copyParameterToBean(req.getParameterMap(),new User());

        if(temp != null && temp.equalsIgnoreCase(code)){
            if(us.existUsername(newUser.getUsername())){
                req.setAttribute("msg", "The username already exists");
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req,resp);
            }else{
                us.register(newUser);
                newUser = us.login(newUser);
                req.getSession().setAttribute("user", newUser);
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req,resp);
            }

        }else{
            req.setAttribute("msg","Wrong verification code");

            req.getRequestDispatcher("/pages/user/register.jsp").forward(req,resp);
        }

    }

    public void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String username = req.getParameter("username");
        boolean accessible = us.existUsername(username);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("accessible", accessible);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

}
