<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
    <link href="${ctx}/ui/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/ui/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="${ctx}/ui/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link href="${ctx}/ui/bootstrap/css/bootstrapValidator.min.css" rel="stylesheet" media="screen">
    <link href="${ctx}/ui/css/screen.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/ui/js/common/util.js"></script>
    <script type="text/javascript" src="${ctx}/ui/js/common/userService.js"></script>
    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->	
</head>
<body>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" 
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	               	添加用户
	            </h4>
	         </div>
	         <div class="modal-body">
	         <!-- start -->
			<form id="editform" method="post">
			    <input type="hidden" id="userid" name="id" value="${user.id}" />	
				<table class="table">
				   <tbody>
				      <tr>
				         <td>姓名</td>
				         <td><input type="text" class="form-control" name="name" id="username" value="${user.name}" maxlength="15"/></td>
				         <td>年龄</td>
				         <td><input type="text" class="form-control" name="age" id="userage" value="${user.age}" maxlength="15"></td>
				      </tr>
				      <tr>
				         <td>密码</td>
				         <td><input type="password" class="form-control" name="password" id="password" maxlength="15"/></td>
				         <td></td>
				         <td></td>
				      </tr>
				   </tbody>
				</table>
				</form>	         
	         <!-- end -->
	         </div>
	         <div class="modal-footer">
	         	<button type="button" id="editBtn" class="btn btn-primary" onclick="javascript:UserService.addUser();">
	              	添加
	            </button>
	            <button type="button" class="btn btn-default" 
	               onclick="javascript:hideModal();">关闭
	            </button>
	         </div>
	      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<script type="text/javascript">
	var ctx = "${ctx}";
	function openUpdateUserPage(userid){
		$('#myModal').modal('show');
		$("#editBtn").html("修改");
		$("#myModalLabel").html("修改用户");
		$("#editBtn").attr("onclick","javascript:UserService.updateUser();");
		var url = ctx+"/user/getUserById?id="+userid;
		$.ajax({
			url : url,
//			param : "",
			type :"get",
			success : function(data){
				var str = data.replace(new RegExp("\\\\\"",'gm'),"\"");
				str = str.replace(new RegExp("\"\\{",'gm'),"\{");
				str = str.replace(new RegExp("\\}\"",'gm'),"\}");
				var json = eval("("+str+")");
				var user = json.user;
				$("#userid").val(user.id);
				$("#username").val(user.name);
				$("#userage").val(user.age);
			},
			error : function(data){
				alert("接口调用失败!")
				console.info(data);
			}
		});
	}
	
	function openAddUserPage(){
		$('#myModal').modal('show');
		$("#myModalLabel").html("添加用户");
		$("#editBtn").html("添加");
		$("#editBtn").attr("onclick","javascript:UserService.addUser();");
	}
	
	function showModal(){
		$('#myModal').modal('show');
	}
	
	function hideModal(){
		$('#myModal').modal('hide');
		Util.cleanForm("editform");
	}
	</script>
</body>
</html>