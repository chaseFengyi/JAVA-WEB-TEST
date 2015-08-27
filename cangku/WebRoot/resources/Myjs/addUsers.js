$(function(){
	$.ajax({
		async:"true",
		type:"get",
		url:"addUser.action",
		dataType:"json",
		success:function(data){
			var str="";
			$("#id_select").empty();
			for(var i in data.list){
				str+="<option value="+data.list[i].id+">"+data.list[i].deptname+"</option>";
			}
			$("#id_select").append(str);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert("鏁版嵁鏌ヨ澶辫触锛�");
		}
	});
});
