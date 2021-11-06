<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Order Detail</title>
    <%@ include file="/pages/share/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">Order Details</span>

    <%@ include file="/pages/share/welcome.jsp"%>
</div>

<div id="main">

    <table>
        <tr>
            <td>Name</td>
            <td>Count</td>
            <td>Price</td>
            <td>Total Price</td>
        </tr>

        <c:forEach items="${sessionScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>

    </table>

</div>

<%@ include file="/pages/share/foot.jsp"%>
</body>
</html>