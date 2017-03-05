<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
	<link href="${pageContext.request.contextPath}/ui/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/ui/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/ui/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">    
	<link href="${pageContext.request.contextPath}/ui/css/screen.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/ui/datatables/jquery.dataTables.min.css" rel="stylesheet">
    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
       <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
       <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript" src="${ctx}/ui/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/ui/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/ui/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/ui/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${ctx}/ui/bootstrap/js/bootstrap-modal.js"></script>
    <script type="text/javascript" src="${ctx}/ui/js/common/util.js"></script>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading" style="font-size: 15px;">session信息</div>
			<div class="panel-body">
				<table style="font-size: 15px;">
					<thead>
						<tr align="center">
							<td width="25%" style="font-weight: bold;" align="center">ID</td>
							<td width="25%" style="font-weight: bold;" align="center">isNew</td>
							<td width="25%" style="font-weight: bold;" align="center">creationTime</td>
							<td width="25%" style="font-weight: bold;" align="center">失效时间</td>
						</tr>
					</thead>
					<tbody>
						<tr align="center">
							<td><%=session.getId() %></td>
							<td><%=session.isNew() %></td>
							<td><%=session.getCreationTime() %></td>
							<td><%=session.getMaxInactiveInterval() %></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading" style="font-size: 15px;">cookie信息</div>
			<div class="panel-body">
				<table style="font-size: 20px;">
					<thead>
						<tr>
							<th width="50%">name</th>
							<th width="50%">value</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cookies}" var="c">
						<tr>
							<td>${c.name}</td>
							<td>${c.value}</td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>