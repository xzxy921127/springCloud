<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="<%=basePath%>" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    	<base href="<%=basePath%>" /> 
        <title>Hello World!</title>
    </head>
    <body>
        <h1 th:inline="text">Hello [[${request.remoteUser}]]!</h1>
        <form action="${ctx}login" method="post">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="注销"/>
        </form>
    </body>
</html>