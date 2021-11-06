<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@ include file="/pages/share/head.jsp"%>
	<script type="text/javascript">
			$(function () {
				$("a.deleteClass").click(function () {
					return confirm("Are you sure to delete <<" + $(this).parent().parent().find("td:first").text() + ">>?");
				})
			})
	</script>
	<script type="text/javascript">
		$(function () {
			$("#searchPageBtn").click(function () {
				const searchNO = $("#pn_input").val();
				const pageTotal = ${requestScope.page.pageTotal};
				if(searchNO<1 || searchNO> pageTotal){
					alert("Wrong Page Index")
				}else{
					location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + searchNO;
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">BOOK MANAGEMENT</span>
			<%@ include file="/pages/share/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">EDIT</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">DELETE</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">ADD BOOKS</a></td>
			</tr>	
		</table>

		<%@include file="/pages/share/page_navigator.jsp"%>

	</div>

	<%@ include file="/pages/share/foot.jsp"%>
</body>
</html>