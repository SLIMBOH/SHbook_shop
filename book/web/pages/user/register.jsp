<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
	<%@ include file="/pages/share/head.jsp"%>
	<script>

		$(function () {

			$("#username").blur(function () {
				let name = this.value;

				$.getJSON("${pageScope.basePath}userServlet", "action=ajaxExistsUsername&username=" + name, function (data) {
					if(data.accessible){
						$("span.errorMsg").text("Used name");
					}
				})
			});

			$("#codeImg").click(function () {
				this.src = "${basePath}/kaptcha.jpg?d=" + new date();
			})

			$("#sub_btn").click(function () {
				var name = $("#username").val();
				var pwd = $("#password").val();
				var emailT = $("#email").val();
				var repwd = $("#repwd").val();

				var namePattern = /^\w{5,12}$/;
				var pwdPattern = /^\w{5,12}$/;
				var emailPattern = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;

				if(!namePattern.test(name)){

					$("span.errorMsg").text("Invalid username");
					return false;
				}
				if(!pwdPattern.test(pwd)){

					$("span.errorMsg").text("Invalid password");
					return false;
				}

				if(repwd != pwd){
					$("span.errorMsg").text("Unsynchronized password");
					return false;
				}

				if(!emailPattern.test(emailT)){

					$("span.errorMsg").text("Invalid Email Address");
					return false;
				}

				var verify = $("#code").val();

				verify = $.trim(verify);

				if(verify == null || verify == ""){

					$("span.errorMsg").text("Wrong Verification Code");
					return false;
				}

				$("span.errorMsg").text("");

			})

		})

	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">Welcome to SHBookshop</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>Register</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="register">
									<label>Username：</label>
									<input class="itxt" type="text" placeholder="Decide your username"
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="${requestScope.username}"
									/>
									<br />
									<br />
									<label>Password：</label>
									<input class="itxt" type="password" placeholder="Password"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>Password：</label>
									<input class="itxt" type="password" placeholder="Password again please"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>E-mail：</label>
									<input class="itxt" type="text" placeholder="Your email address"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="${requestScope.email}"
									/>
									<br />
									<br />
									<label>Verification：</label>
									<input class="itxt" name="code" type="text" style="width: 80px;" id="code"/>
									<img id="codeImg" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 140px; height: 40px;">
									<br />
									<br />
									<input type="submit" value="Regester" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/share/foot.jsp"%>
</body>
</html>