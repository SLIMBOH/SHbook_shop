<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
	<%@ include file="/pages/share/head.jsp"%>

</head>

<body>

		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">Welcome</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>Member</h1>
								<a href="pages/user/register.jsp">Register</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									${empty requestScope.msg ? "" : "Please enter your information"}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="login">
									<label>Username：</label>
									<input class="itxt" type="text" placeholder="User id please" autocomplete="off" tabindex="1" name="username"
										   <%--value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
										   value="${requestScope.username}"
									/>
									<br />
									<br />
									<label>Password：</label>
									<input class="itxt" type="password" placeholder="Password please" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="Login" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/share/foot.jsp"%>
</body>
</html>