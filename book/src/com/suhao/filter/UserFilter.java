package com.suhao.filter;


import com.suhao.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)req;
        User user = (User)httpServletRequest.getSession().getAttribute("user");

        if(user == null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else{
            if(user.getUsername().equals("admin")){
                filterChain.doFilter(req, resp);
            }else{
                 ((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath() + "/index.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
