<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${ctx}/ui/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${ctx}/ui/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
  <script type="text/javascript" src="${ctx}/ui/js/jquery.min.js"></script>
  <script type="text/javascript" src="${ctx}/ui/js/common/util.js"></script>
  <title>404</title>
</head>
<body>
	<div class="container-fluid">
		<h1 align="center">页面找不到</h1>
		<h1 align="center">404</h1>
		<div class="row">
			<div class="col-md-6" align="right">
				<button type="button" class="btn btn-success" onclick="javascript:Util.goPage('${ctx}/index');">返回首页</button>
			</div>
 			<div class="col-md-6" align="left">
 				<button type="button" class="btn btn-success" onclick="javascript:Util.goPage('${ctx}/login');">重新登录</button>
 			</div>
		</div>
	</div>
</body>
</html>