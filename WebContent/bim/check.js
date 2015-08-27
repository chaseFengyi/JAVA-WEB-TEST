// JavaScript Document	
	function f1()
	{ var t=true; 
	 	var msg1="供应商不能为空！";
		var vendorName=form1.vendorName.value;
	if(vendorName=="")
	    {
		document.getElementById("verdorName").innerHTML='<img src="images/no.gif">'+msg1;
		t=false;
	    }
	else
		{
			document.getElementById("verdorName").innerHTML='<img src="images/yes.gif">';
		}
		return t;
	}
	function f2()
	{	var t=true;
		var msg2="城市编号不能为空且是029或0755格式";
	var cityId=form1.cityId.value;
	var city=/^029|0757-[0-9]{3,}$/;
	if((cityId.match(city)==null)||(cityId=""))
	    {
			document.getElementById("cityId").innerHTML='<img src="images/no.gif">'+msg2;
			t=false;
		}
	else
		{
			document.getElementById("cityId").innerHTML='<img src="images/yes.gif">';
		}
		return t;
	}
	function f3()
	{	var t=true;
		var msg3="地址不能为空";
		var address=form1.address.value;
	if(address=="")
		{
			document.getElementById("address").innerHTML='<img src="images/no.gif">'+msg3;
			t=false;
		}
	else
		{
			document.getElementById("address").innerHTML='<img src="images/yes.gif">';
		} 
		return t;
	}
	function f4(){
		var t= true;
		var msg4="邮编不能为空，且为6位数字";
		var postcode=form1.postcode.value;
		var post=/^[0-9]{6}$/;
	if((postcode=="")||(postcode.length!=6)||((postcode.match(post))==null))
		{
			document.getElementById("postcode").innerHTML='<img src="images/no.gif">'+msg4;
			t=false;
		}
	else
		{document.getElementById("postcode").innerHTML='<img src="images/yes.gif">';
			
		}
		return t;
	}
	function f5(){
		var t=true;
		var msg5="电话不能为空且为11位数字";
		var telphone=form1.telphone.value;
		var tel=/^1[0-9]{10}$/;
	if((telphone=="")||(telphone.length!=11)||(!(telphone.match(tel))))
		{
		document.getElementById("telphone").innerHTML='<img src="images/no.gif">'+msg5;
		t=false;
		}
	else
		{
			document.getElementById("telphone").innerHTML='<img src="images/yes.gif">';
		}
		return t;
	}
	function f6()
	{	var t=true;
		var msg6="联系人不能为空";
		var contact=form1.contact.value;
	if(contact=="")
		{
			document.getElementById("contact").innerHTML='<img src="images/no.gif">'+msg6;
			t=false;
		}
	else
		{
			document.getElementById("contact").innerHTML='<img src="images/yes.gif">';
		}
		return t;
	}
	function check(){
		var t=f1()&&f2()&&f3()&&f4()&&f5()&&f6();
      if(t)
		{
			alert('供应商添加成功！');
		return true;
		}
		else
		{
			alert("信息填写错误，请仔细填写");
		return false;
		}
	}