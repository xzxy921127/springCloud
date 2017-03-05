var MongoService = {
	//分页查询
	find : function(){
		MongoService.clearDiv();
		var queryUrl = "find.do";
		var startNum = $("#startNum").val();
		var endNum = $("#endNum").val();
		var count = endNum - startNum;
		var param = {"startNum":startNum,"endNum":endNum};
		if(!MongoService.validateNum()){
			alert("输入正确的数字参数");
			return false;
		}
		$.ajax({
			url: queryUrl, 
			data : param,
			dateType : "text", 
			success: function(data){
        		var jsonData = eval("("+data+")");
        		if(jsonData.replyCode == "success"){
        			var msg = "mongoDB查询"+count+"条数据花费时间:"+jsonData.mongoDBTime+"毫秒"
        					+ "<br/>"
        					+ "oracle查询"+count+"条数据花费时间:"+jsonData.oracleTime+"毫秒";
        			$("#queryDiv").html(msg);
        			alert("查询成功!")
        		}
      		},
      		error : function(){
      			alert("请求失败!")
      		}
		});
	},
	add : function(){
		MongoService.clearDiv();
		var addUrl = "add.do";
		var startNum = $("#startNum").val();
		var endNum = $("#endNum").val();
		var count = endNum - startNum;
		var param = {"num":count};
		if(!MongoService.validateNum()){
			alert("输入正确的数字参数");
			return false;
		}
		$.ajax({
			url: addUrl, 
			data : param,
			dateType : "text", 
			success: function(data){
        		var jsonData = eval("("+data+")");
        		if(jsonData.replyCode == "success"){
        			var msg = "mongoDB插入"+count+"条数据花费时间:"+jsonData.mongoDBTime+"毫秒"
        					+ "<br/>"
        					+ "oracle插入"+count+"条数据花费时间:"+jsonData.oracleTime+"毫秒";
        			$("#addDiv").html(msg);
        			alert("添加成功!");
        		}
      		}, error : function(){
      			alert("请求失败!")
      		}
		});
	},
	del : function(){
		MongoService.clearDiv();
		var addUrl = "delete.do";
		var startNum = $("#startNum").val();
		var endNum = $("#endNum").val();
		var num = endNum - startNum;
		var param = {"startNum":startNum,"endNum":endNum};
		$.ajax({
			url: addUrl, 
			data : param,
			dateType : "text", 
			success: function(data){
        		var jsonData = eval("("+data+")");
        		if(jsonData.replyCode == "success"){
        			var msg = "mongoDB删除"+count+"条数据花费时间:"+jsonData.mongoDBTime+"毫秒"
        					+ "<br/>"
        					+ "oracle删除"+count+"条数据花费时间:"+jsonData.oracleTime+"毫秒";
        			$("#delDiv").html(msg);
        			alert("删除成功!");
        		}
      		}
		});
	},
	//条件查询
	count : function(){
		MongoService.clearDiv();
		var addUrl = "count.do";
		//属性名
		var propertyName = $("#propertyName").val();
		//属性值
		var propertyValue = $("#propertyValue").val();
		var num = endNum - startNum;
		var param = {"propertyName":propertyName,"propertyValue":propertyValue};
		if(!MongoService.validateCondition()){
			alert("输入正确的属性参数");
			return false;
		}
		$.ajax({
			url: addUrl, 
			data : param,
			dateType : "text", 
			success: function(data){
        		var jsonData = eval("("+data+")");
        		if(jsonData.replyCode == "success"){
        			var msg = "mongoDB执行条件查询花费时间:"+jsonData.mongoDBTime+"毫秒"
        					+ "<br/>"
        					+ "oracl执行条件查询花费时间:"+jsonData.oracleTime+"毫秒";
        			$("#delDiv").html(msg);
        			alert("查询成功!");
        		}
      		}
		});
	},
	//验证输入框
	validateNum :function(){
		var startNum = $("#startNum").val();
		var endNum = $("#endNum").val();
		if(startNum==""){
			return false;
		}
		if(endNum==""){
			return false;
		}
		return true;
	},
	//验证输入框
	validateCondition :function(){
		var propertyName = $("#propertyName").val();
		var propertyValue = $("#propertyValue").val();
		if(propertyName==""){
			return false;
		}
		if(propertyName==""){
			return false;
		}
		return true;
	},
	clearDiv : function(){
		$("#queryDiv").html("");
		$("#addDiv").html("");
		$("#delDiv").html("");
	}
}