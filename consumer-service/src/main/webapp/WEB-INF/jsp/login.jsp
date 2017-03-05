<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath%>" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${ctx}/ui/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${ctx}/ui/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
		<title>登录</title>
		<style type="text/css">
		#login-box {
			margin: 50px auto;
		}
		</style>
		<script type="text/javascript">
	  		function login(){
		  		var form = document.getElementById("myform"); 
		  		form.submit();
	  		}
		 </script>
	</head>
	<body>
	${loginMsg}<br/>
	${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}<br/>
	<form id="myform" action="${ctx}/login" method="post">
		<div id="login-box">
			<div class="container">
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">用户登录</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<input type="text" id="username" name="username" class="form-control"
									placeholder="请输入用户名" />
							</div>
							<div class="form-group">
								<input type="password" id="password" name="password" class="form-control"
									placeholder="请输入密码" />
							</div>
						</div>
						<div class="panel-footer text-center">
							<input type="button" class="btn btn-primary"
								onclick="javascript:login();" value="登录" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	</body>
</html>