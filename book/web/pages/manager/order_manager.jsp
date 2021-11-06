<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Management</title>
	<%@ include file="/pages/share/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			$("a.delivery").click(function () {
				let s = $(this).attr("status");
				if(s == 0){
					return confirm("Do you want to deliver this order?")
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo1.gif" >
			<span class="wel_word">Order Management</span>
			<%@ include file="/pages/share/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>Date</td>
				<td>Price</td>
				<td>User Id</td>
				<td>Details</td>
				<td>Status</td>
				
			</tr>		
			<c:forEach items="${requestScope.allOrders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td>${order.userId}</td>
					<td><a href="orderServlet?action=showDetails&id=${order.orderId}">Details</a></td>
					<td><a class="delivery" status="${order.status}"  href="manager/orderServlet?action=delivery&id=${order.orderId}&status=${order.status}">
						<C:if test="${order.status == 0}">
							Processing
						</C:if>
						<c:if test="${order.status == 1}">
							Shipping
						</c:if>
						<c:if test="${order.status == 2}">
							Received
						</c:if>
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%@ include file="/pages/share/foot.jsp"%>
</body>
</html>