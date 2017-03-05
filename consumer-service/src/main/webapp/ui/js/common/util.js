var Util = {
	//清空表单
	cleanForm : function(formId){
		$("#" + formId + " :input").val("");
		$("#" + formId + " :select").remove("selected");
		$("#" + formId + " :input").remove("checked");
	},
	goPage : function(url){
		window.location.href=url;
	},
	replaceAll : function(source,replaceEle,ele){
		var replaceStr = replaceEle;
		return source.replace(new RegExp(replaceStr,'gm'),ele);
	}
}