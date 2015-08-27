/*function getResult(){
	var url="/servlet/department";
	if (window.XMLHttpRequset) {
		req = new XMLHttpRequset();
	}else if(window.ActiveXObject){
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req){
		req.open("POST",url,ture);
		req.onreadystatechange=complete;
		req.setRequestHeader("Content-type","application/x-www-from-urlencoded");
		req.send();
	}
}

function complete(){
	if (req.readyState==4) {
		if(req.status ==200){
			var result = req.responseText;
			
		}
	}
}*/

$(document).ready(function(){
	$('form tr td select#deptName').click(function(){
		$.get('/servlet/department',function(data){
			
		});
		return false;
	});
});