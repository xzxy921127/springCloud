var UserService = {
	getUserList : function(){
		var url = ctx + "/user/getUserList";
		$.ajax({
			url : url,
//			param : "",
			type :"get",
			success : function(data){
				var str = data.replace(new RegExp("\\\\\"",'gm'),"\"");
				str = str.replace(new RegExp("\"\\[",'gm'),"\[");
				str = str.replace(new RegExp("\\]\"",'gm'),"\]");
				var json = eval("("+str+")");
				var userlist = json.list;
				var myDataTableBody = document.getElementById("myDataTableBody");
				var userHtml = "";
				for(var i=0;i<userlist.length;i++){
					var user = userlist[i];
					userHtml += "<tr>";
					userHtml += "<td>"+user.name+"</td>";
					userHtml += "<td>"+user.age+"</td>";
					var delUrl = "javascript:UserService.deleteUser("+user.id+");";
					var updateUrl = "javascript:openUpdateUserPage("+user.id+");";
					userHtml += "<td>";
					userHtml += "<a href='"+updateUrl+"'>更新</a>&nbsp;&nbsp;";
					userHtml += "<a href='"+delUrl+"'>删除</a>";
					userHtml += "</td>";
					userHtml += "</tr>";
				}
				myDataTableBody.innerHTML = userHtml;
			},
			error : function(data){
				console.info(data);
			}
		});
	},
	getUserListByPage : function(){
		var pageNo = $("#pageNo").val();
		var name = $("#name").val();
		var age = $("#age").val();
		var url = ctx + "/user/getUserListByPage?pageNo="+pageNo+"&name="+name+"&age="+age;
		$.ajax({
			url : url,
//			param : "",
			type :"get",
			success : function(data){
				var str = data.replace(new RegExp("\\\\\"",'gm'),"\"");
				str = str.replace(new RegExp("\"\\[",'gm'),"\[");
				str = str.replace(new RegExp("\\]\"",'gm'),"\]");
				var json = eval("("+str+")");
				var userlist = json.list;
				var myDataTableBody = document.getElementById("myDataTableBody");
				var userHtml = "";
				for(var i=0;i<userlist.length;i++){
					var user = userlist[i];
					userHtml += "<tr>";
					userHtml += "<td>"+user.name+"</td>";
					userHtml += "<td>"+user.age+"</td>";
					var delUrl = "javascript:UserService.deleteUser("+user.id+");";
					var updateUrl = "javascript:openUpdateUserPage("+user.id+");";
					userHtml += "<td>";
					userHtml += "<a href='"+updateUrl+"'>更新</a>&nbsp;&nbsp;";
					userHtml += "<a href='"+delUrl+"'>删除</a>";
					userHtml += "</td>";
					userHtml += "</tr>";
				}
				myDataTableBody.innerHTML = userHtml;
				
				var pageNo = json.pageNo;
				var totalPage = json.totalPage;
				$("#pageNo").val(pageNo);
				$("#totalPage").val(totalPage);
				pageNo = pageNo - 0;
				totalPage = totalPage - 0;
				if(pageNo<totalPage){
					$("#nextPageBtn").html("<a href=\"javascript:UserService.getNextUserPage();\">下一页</a>");
				}else{
					$("#nextPageBtn").html("<a href=\"#\">下一页</a>");
				}
			},
			error : function(data){
				console.info(data);
			}
		});
	},
	getPreUserPage :function(){
		var pageNo = $("#pageNo").val();
		pageNo = pageNo - 0;
		pageNo = pageNo - 1;
		if(pageNo<1){
			pageNo = 1;
		}
		$("#pageNo").val(pageNo);
		UserService.getUserListByPage();
	},
	getNextUserPage :function(){
		var pageNo = $("#pageNo").val();
		pageNo = pageNo - 0;
		pageNo = pageNo + 1;
		$("#pageNo").val(pageNo);
		UserService.getUserListByPage();
	},
	deleteUser : function(userid){
		var url = ctx + "/user/delete?id="+userid;
		$.ajax({
			url : url,
			type :"get",
			success : function(data){
				var json = "("+data+")";
				if(json.reply="success"){
					alert("删除成功!");
					//UserService.getUserList();
					UserService.getUserListByPage();
				}
			},
			error : function(data){
				console.info(data);
				alert("接口调用失败!");
			}
		});
	},
	updateUser : function(){
		var userid = $("#userid").val();
		var name = $("#username").val();
		var age = $("#userage").val();
		
		//添加这个参数才能发送post请求(Spring Security)
		var csrfName = $("#csrfName").val();
		var csrfValue = $("#csrfValue").val();
		//var url = ctx + "/user/update?id="+userid+"&name="+name+"&age="+age;
		var url = ctx + "/user/update";
		var param ="id="+userid+"&name="+name+"&age="+age+"&"+csrfName+"="+csrfValue;
		url = url + "?" + param;
//console.info("更新用户参数:"+param);
		$.ajax({
			url : url,
			type :"get",
//			param : param,
			success : function(data){
				var json = "("+data+")";
				if(json.reply="success"){
					alert("修改成功!");
					//UserService.getUserList();
					UserService.getUserListByPage();
					hideModal();
				}
			},
			error : function(data){
				console.info(data);
				alert("接口调用失败!");
			}
		});
	},
	addUser : function(){
		var name = $("#username").val();
		var age = $("#userage").val();
		var password = $("#password").val();
		var csrfName = $("#csrfName").val();
		var csrfValue = $("#csrfValue").val();
		
		//var url = ctx + "/user/add?name="+name+"&age="+age+"&password="+password;
		var url = ctx + "/user/add";
		var param = "name="+name+"&age="+age+"&password="+password+"&"+csrfName+"="+csrfValue
		url = url + "?" + param;
//alert("添加用户:"+url);
//console.info("添加用户参数:"+param);
		$.ajax({
			url : url,
			type :"get",
//			param : param,
			success : function(data){
				var json = "("+data+")";
				if(json.reply="success"){
					alert("添加成功!");
					//UserService.getUserList();
					UserService.getUserListByPage();
					hideModal();
				}
			},
			error : function(data){
				console.info(data);
				alert("接口调用失败!");
			}
		});
	},
	validateNumberField : function(value) {
		var reg = new RegExp("^\d+$");  
		if(!reg.test(value)){  
			alert("请输入数字!");  
			return false;
		}
		return true;
	}
}