<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomePage</title>
	<%@ include file="/pages/share/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			$("button.addToCart").click(function () {
				let id = $(this).attr("id");
				let stock = $(this).attr("stock");
				if(stock == 0){
					alert("Sorry, we don't have enough books");
				}else{
					<%--%>location.href = "${pageScope.basePath}cartServlet?action=addItem&id=" + id;--%>
					$.getJSON("${pageScope.basePath}cartServlet", "action=ajaxAddItem&id=" + id, function (data) {
						if(data.Msg != null){
							alert("Not enough of books");
						}else{
							$("#totalCount").text("カートの中に【" + data.totalCount + "】冊の本があります");
							$("#lastBook").text("「" + data.lastBook + "」 をカートに入れました");
						}
					})
				}
			})
			$("a.authorization").click(function () {
				if(${empty sessionScope.user}){
					alert("login first");
				}else if($(this).attr("userId") != 1) {
					alert("No Authorization.");
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">Online Bookshop</span>
			<div>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">Login</a> |
					<a href="pages/user/register.jsp">Register</a> |
				</c:if>
				<c:if test="${not empty sessionScope.user}">
					<span>Hello <span class="um_span">${sessionScope.user.username}</span></span>
					<a href="pages/order/order.jsp">My Orders</a> |
					<a href="userServlet?action=logout">Logout</a> |
				</c:if>
				<a href="pages/cart/cart.jsp">Cart</a> |
				<a class="authorization" userId="${sessionScope.user.id}" href="pages/manager/manager.jsp">Management</a>

			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					価格：<input id="min" type="text" name="min" value="${param.min}"> 円 -
						<input id="max" type="text" name="max" value="${param.max}"> 円
						<input type="submit" value="GO" />
				</form>
			</div>
			<div style="text-align: center">

				<div>
					<c:if test="${not empty sessionScope.cart.items}">
						<span id="totalCount"></span>
						<%--<c:if test="${not empty sessionScope.lastBook}">--%>
						<span id="lastBook" style="color: red"></span>
					</c:if>
					<c:if test="${empty sessionScope.cart.items}">
						<span id="totalCount"> </span>
						<div>
							<span id="lastBook">Your cart is empty now</span>
						</div>
					</c:if>
				</div>
			</div>
			
			<c:forEach items="${requestScope.page.items}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="static/img/default.jpg" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">Name:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">Author:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">Price:</span>
							<span class="sp2">${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">Sales:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">Stock:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button id="${book.id}" stock="${book.stock}" class="addToCart">Add to cart</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<%@include file="/pages/share/page_navigator.jsp"%>
	
	</div>
	
	<%@ include file="/pages/share/foot.jsp"%>
</body>
</html>