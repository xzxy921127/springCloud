<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setHeader("X-Frame-Options", "SAMEORIGIN");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
 	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ui/bootstrap/js/bootstrap.min.js"></script>
    <!-- 引入 Bootstrap -->
    <link href="${pageContext.request.contextPath}/ui/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/ui/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
      <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
      <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
      <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
      <link href="${pageContext.request.contextPath}/ui/css/frameset.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2">
                <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
                    <li class="active">
                        <a href="#">
                            <i class="glyphicon glyphicon-th-large"></i>首页
                        </a>
                    </li>
                    <li>
                        <a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-cog"></i>系统管理
                               <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                        </a>
                        <ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="${pageContext.request.contextPath}/user/list" target="mainFrame"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
                            <li><a href="${pageContext.request.contextPath}/sessions" target="mainFrame"><i class="glyphicon glyphicon-asterisk"></i>会话管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#materialManager" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-credit-card"></i>物料管理        
                        </a>
                        <ul id="materialManager" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="#"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#distributeConfig" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-globe"></i>分发配置
                            <span class="label label-warning pull-right">5</span>
                        </a>
                        <ul id="distributeConfig" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="#"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#charts" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-calendar"></i>图表统计
                            <span class="label label-warning pull-right"></span>
                        </a>
                        <ul id="charts" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="#"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#aboutSystem" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-fire"></i>关于系统
                        </a>
                        <ul id="aboutSystem" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="#"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="col-md-10" id="mainFrame">
            	<!-- start -->
            	
            	<!-- end -->
            </div>
        </div>
    </div>
</body>
</html>