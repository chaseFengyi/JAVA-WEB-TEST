<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>计数器</title>
</head>

<body>
<?php 
if(!file_exists("count.txt"))
{//文件不存在
$fp=foppen("count.txt","w");
	fwrite($fp,"000001");
	$count="000001";
	fclose($fp);
	
}
else
{
$fp=fopen("count.txt","r");
$count=fread($fp,6);
$count+=1;
fclose($fp);
switch(strlen($count)){
	case 1:$count="00000".$count;break;
	case 2:$count="0000".count;break;
	case 3:$count="000".$count;break;
	case 4:$count="00".$count;break;
	case 5:$count="0".$count;break;

	}
		//保存网页被访问的次数
	$fp=fopen("count.txt","w");
	fwrite($fp,$count);
	fclose(fp);
}
?>
</body>
</html>