<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath%>" />
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
    <script type="text/javascript" src="${ctx}/ui/js/common/userService.js"></script>
    
    <script type="text/javascript">
    	var ctx = "${ctx}";
    	function init(){
    		//UserService.getUserList();
    		UserService.getUserListByPage();
    	}
    </script>
</head>
<body onload="javascript:init();">
	<div class="container">
		<div class="panel panel-default">		  
		   <div class="panel-heading">
		      	用户信息
		   </div>
		   <form method="post" id="myform">
		   <input type="hidden" id="pageNo" name="pageNo" value="${pageNo}" />
		   <input type="hidden" id="totalPage" name="pageNo" value="${totalPage}" />
		   <input type="hidden" id="csrfName" name="csrfName" value="${_csrf.parameterName}"/>
		   <input type="hidden" id="csrfValue" name="csrfValue" value="${_csrf.token}"/>
		   <div class="panel-body">
		    <div class="form-group"> 
		    	<label for="name" class="col-sm-1">姓名</label> 
				<div class="col-md-2"> 
					<input type="text" class="form-control" value="${user.name}" name="name" id="name">
				</div> 
				<label for="xh" class="col-sm-1">年龄</label> 
				<div class="col-md-2"> 
					<input type="text" class="form-control" value="${user.age}" name="age" id="age">
				</div>
				<!-- <button type="button" id="searchBtn" class="btn btn-success" onclick="javascript:showModal();">添加学生</button> -->
				<button type="button" onclick="javascript:openAddUserPage();" class="btn btn-success">添加</button>
				<button type="button" onclick="javascript:UserService.getUserListByPage();" class="btn btn-success">查询</button>
				<button type="button" class="btn btn-primary" onclick="javascript:Util.cleanForm('myform');">清空</button>
			</div>
		   </div>
		   </form>
		</div>
		<div class="panel panel-default">
		<!--
		   <div class="panel-heading">
		      <h3 class="panel-title">
		      </h3>
		   </div>
		   -->
		   <div class="panel-body">
				 <table id="myDataTable" class="table">
				   <thead>
				      <tr>
				         <th>姓名</th>
				         <th>年龄</th>
				         <th>操作</th>
				      </tr>
				   </thead>
				   <tbody id="myDataTableBody">
				   	<tr>
				      <td>Tanmay</td>
				      <td>Bangalore</td>
				      <td>delete</td>
				    </tr>
				    <tr>
				      <td>Sachin</td>
				      <td>Mumbai</td>
				      <td>delete</td>
				    </tr>
				   </tbody>
				</table>
		   </div>
		   <span>
		   	<a href="javascript:UserService.getPreUserPage();">上一页</a>
		   </span>
		   <span id="nextPageBtn">
		   	<a href="javascript:UserService.getNextUserPage();">下一页</a>
		   </span>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/user/edit.jsp"></jsp:include>
</body>
</html>