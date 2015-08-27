// JavaScript Document
//确定按钮触发，当点击提交按钮时，就会对输入的信息进行判断，正确显示对号，错误则显示查号，并提示用户输入错误。
function judge(){
	var t=true;
	var msg1="供应商不能为空！";
	var msg2="城市编号不能为空且是029或0755格式";
	var msg3="地址不能为空";
	var msg4="邮编不能为空，且为6位数字";
	var msg5="电话不能为空且为11位数字";
	var msg6="联系人不能为空";
	var vendorName=form1.vendorName.value;
	var cityId=form1.cityId.value;
	var address=form1.address.value;
	var postcode=form1.postcode.value;
	var telphone=form1.telphone.value;
	var contact=form1.contact.value;
	var city=/^029|0757-[0-9]{3,}$/;
	var post=/^[0-9]{6}$/;
	var tel=/^1[0-9]{10}$/;
	if(vendorName=="")
	    {
		document.getElementById("verdorName").innerHTML='<img src="images/no.gif">'+msg1;
		t=false;
	    }
	else
		{
			document.getElementById("verdorName").innerHTML='<img src="images/yes.gif">';
		}
	if((cityId.match(city)==null)||(cityId=""))
	    {
			document.getElementById("cityId").innerHTML='<img src="images/no.gif">'+msg2;
			t=false;
		}
	else
		{
			document.getElementById("cityId").innerHTML='<img src="images/yes.gif">';
		}
	if(address=="")
		{
			document.getElementById("address").innerHTML='<img src="images/no.gif">'+msg3;
			ft=false;
		}
	else
		{
			document.getElementById("address").innerHTML='<img src="images/yes.gif">';
		}
	if((postcode=="")||(postcode.length!=6)||((postcode.match(post))==null))
		{
			document.getElementById("postcode").innerHTML='<img src="images/no.gif">'+msg4;
			t=false;
		}
	else
		{document.getElementById("postcode").innerHTML='<img src="images/yes.gif">';
			
		}
	if((telphone=="")||(telphone.length!=11)||(!(telphone.match(tel))))
		{
		document.getElementById("telphone").innerHTML='<img src="images/no.gif">'+msg5;
		t=false;
		}
	else
		{
			document.getElementById("telphone").innerHTML='<img src="images/yes.gif">';
		}
	if(contact=="")
		{
			document.getElementById("contact").innerHTML='<img src="images/no.gif">'+msg6;
			t=false;
		}
	else
		{
			document.getElementById("contact").innerHTML='<img src="images/yes.gif">';
		}
if(t)
		{
			alert('供应商添加成功！');
		return true;
		}
		else
		return false;
	}