<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Order</title>
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
			<span class="wel_word">My Orders</span>

			<%@ include file="/pages/share/welcome.jsp"%>
	</div>
	
	<div id="main">


		<c:if test="${empty requestScope.page.items}">
			<h1 align="center">You have no orders yet</h1>
			<h2 align="center"><a href="index.jsp">Buy some books</a></h2>
		</c:if>
		
		<c:if test="${not empty requestScope.page.items}">
			<table>
				<tr>
					<td>Date</td>
					<td>Price</td>
					<td>Status</td>
					<td>Details</td>
				</tr>

				<c:forEach items="${requestScope.page.items}" var="order">
					<tr>
						<td>${order.createTime}</td>
						<td>${order.price}</td>
						<td>
							<c:if test="${order.status == 0}">
								Processing
							</c:if>
							<c:if test="${order.status == 1}">
								Shipping
							</c:if>
							<c:if test="${order.status == 2}">
								Received
							</c:if>
						</td>
						<td><a href="orderServlet?action=showDetails&id=${order.orderId}">Show Details</a></td>
					</tr>
				</c:forEach>


			</table>
		</c:if>

		<br/>
		<c:if test="${not empty requestScope.page.items}">
			<%@include file="/pages/share/page_navigator.jsp"%>
		</c:if>

	</div>

	<%@ include file="/pages/share/foot.jsp"%>
</body>
</html>