<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
	<%@ include file="/pages/share/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >

				<%@ include file="/pages/share/welcome.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>Welcome Back. <a href="index.jsp">Buy some books~</a></h1>
	
		</div>

		<%@ include file="/pages/share/foot.jsp"%>
</body>
</html>