$(function(){
	$.ajax({
		async:"true",
		type:"post",
		url:"getUser.action",
		dataType:"json",
		success:function(data){
			$("#id_body").empty();
			var str="";
			for(var i in data.list){
				var obj=data.list[i];
				str+="<tr class='my' align='center'  bgcolor='#ffffff'>";
				str+="<td class='STYLE1'>"+obj.id+"</td>";
				str+="<td align='left' class='STYLE1'>"+obj.username+"</td>";
				str+="<td align='center' class='STYLE1'>"+obj.usertype+"</td>";
				str+="<td align='left' class='STYLE1'>"+obj.department+"</td>";
				str+="<td align='left' class='STYLE1'>"+obj.createdate+"</td>";
				str+="<td align='left' class='STYLE1'>"+obj.logindate+"</td>";
				str+="<td width='5%'  class='STYLE1'><a href='usersDetail.html' >璇︾粏</a></td>";
				str+="<td width='5%' class='STYLE1'><a href='grantsManagement.html' >鏉冮檺</a></td>";
				str+="<td width='5%' class='STYLE1'><a href='updateUsers.html' >淇敼</a></td>";
				str+="<td width='5%' class='STYLE1'><a onclick=''>鍒犻櫎</a></td>";
			}
			$("#id_body").append(str);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert("鏁版嵁鏌ヨ澶辫触锛�");
		}
	});
});