<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <c:if test="${not empty sessionScope.user}">
        <span>Hello <span class="um_span">${sessionScope.user.username}</span></span>
        <a href="orderServlet?action=page">Order</a> |
        <a href="userServlet?action=logout">Logout</a> |
        <a href="index.jsp">Homepage</a>
    </c:if>
    <c:if test="${empty sessionScope.user}">
        <span>Welcome to SH bookshop</span>
        <a href="pages/user/login.jsp">Login</a> |
        <a href="pages/user/register.jsp">Register</a> |
        <a href="index.jsp">Homepage</a>
    </c:if>
</div>
