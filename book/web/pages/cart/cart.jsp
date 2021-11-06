<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
	<%@ include file="/pages/share/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.warnCart").click(function () {
				return confirm("Are you sure to clear the cart?");
			})
			$("a.warnBook").click(function () {
				return confirm("Are you sure to delete <<" + $(this).parent().parent().find("td:first").text() + ">>?");
			})
			$(".updateNum").change(function () {
				let num = $(this).attr("value");
				let bookId = $(this).attr("id");
				let stock = $(this).attr("stock");
				if(num > stock){
					alert("Sorry, we don't have enough books");
					this.value = this.defaultValue;
				}else{
					if(num != this.defaultValue){
						if(confirm("Do you confirm to modify the quantity of <<" + $(this).parent().parent().find("td:first").text() +
								">> to " + num + "?")){
							location.href = "${pageScope.basePath}cartServlet?action=updateCount&id=" + bookId + "&quantity=" + num;
						}else{
							this.value = this.defaultValue;
						}
					}
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">Cart</span>
			<%@ include file="/pages/share/welcome.jsp"%>
	</div>

	<div class="msg_cont">
		<b></b>
		<span class="errorMsg">
			${sessionScope.Msg}
		</span>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>Name</td>
				<td>Count</td>
				<td>Price</td>
				<td>Total Price</td>
				<td>Operation</td>
			</tr>		
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input id="${entry.value.id}" stock="${entry.value.stock}" class="updateNum" type="text" style="width: 60px" value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="warnBook" href="cartServlet?action=deleteItem&id=${entry.value.id}">Delete</a></td>
				</tr>
			</c:forEach>
			
		</table>

		<c:if test="${empty sessionScope.cart.items}">
			<br/>
			<br/>
			<br/>
			<h1 align="center">Cart is empty</h1>
		</c:if>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">There are <span class="b_count">${sessionScope.cart.totalCount}</span> books in your cart.</span>
				<span class="cart_span">Total Price<span class="b_price">${sessionScope.cart.totalPrice}</span>円</span>
				<span class="cart_span"><a href="cartServlet?action=clearCart" class="warnCart">Clear</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">Checkout</a></span>
			</div>
		</c:if>
	
	</div>

	<%@ include file="/pages/share/foot.jsp"%>
</body>
</html>