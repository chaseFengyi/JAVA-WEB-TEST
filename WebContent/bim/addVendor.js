// JavaScript Document
//ȷ����ť������������ύ��ťʱ���ͻ���������Ϣ�����жϣ���ȷ��ʾ�Ժţ���������ʾ��ţ�����ʾ�û��������
function judge(){
	var t=true;
	var msg1="��Ӧ�̲���Ϊ�գ�";
	var msg2="���б�Ų���Ϊ������029��0755��ʽ";
	var msg3="��ַ����Ϊ��";
	var msg4="�ʱ಻��Ϊ�գ���Ϊ6λ����";
	var msg5="�绰����Ϊ����Ϊ11λ����";
	var msg6="��ϵ�˲���Ϊ��";
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
			alert('��Ӧ����ӳɹ���');
		return true;
		}
		else
		return false;
	}