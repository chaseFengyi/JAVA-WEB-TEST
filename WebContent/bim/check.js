// JavaScript Document	
	function f1()
	{ var t=true; 
	 	var msg1="��Ӧ�̲���Ϊ�գ�";
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
		var msg2="���б�Ų���Ϊ������029��0755��ʽ";
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
		var msg3="��ַ����Ϊ��";
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
		var msg4="�ʱ಻��Ϊ�գ���Ϊ6λ����";
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
		var msg5="�绰����Ϊ����Ϊ11λ����";
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
		var msg6="��ϵ�˲���Ϊ��";
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
			alert('��Ӧ����ӳɹ���');
		return true;
		}
		else
		{
			alert("��Ϣ��д��������ϸ��д");
		return false;
		}
	}