<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品管理</title>
<link href="css/ambipolar.css" rel="stylesheet" type="text/css" />
<link href="css/homepage.css" rel="stylesheet" type="text/css" />
<link href="css/hottest.css" rel="stylesheet" type="text/css" />
<link href="css/Comments.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
-->
</style>
</head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
        <td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">商品管理</span></td>
        <td width="14"><img src="../resources/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" style="background:url(../resources/images/tab_12.gif) repeat-y left #d3e7fc; ">&nbsp;</td>
        <td width="97%" bgcolor="#FFFFFF" height="550" valign="top">
		<table width="100%" border="0" cellspacing="1" cellpadding="0"  align="center" bgcolor="#cecece">
	<tr class="dz" align="center">
		<td width="40%" height="28"
			background="../resources/images/sale_bg.gif">商品名称</td>
		<td width="30%"
			background="../resources/images/sale_bg.gif"
			style="border-left:1px solid #fff;">价格</td>
		<td width="15%"
			background="../resources/images/sale_bg.gif"
			style="border-left:1px solid #fff;">数量</td>
		<td colspan="3" width="15%"
			background="../resources/images/sale_bg.gif"
			style="border-left:1px solid #fff;">编辑</td>
	</tr>
		<tr align="center" bgcolor="#FFFFFF">
			<td width="40%" height="28" style="padding-left:20px; text-align:left;"> VIVI时</td>
			<td width="30%" align="right" style="padding-right:150px;">￥10168.00 </td>
			<td width="15%"><input name="textfield2" type="text" style="width:30px; border:1px solid #cecece; text-align:center;" value="1" /></td>
			<td width="5%"><a href="HT3.htm">购买</a></td>
			<td width="5%"><a href="#">充值</a></td>
			<td width="5%"><a href="#">删除</a></td>
		</tr>
		<tr align="center" bgcolor="#FFFFFF">
			<td width="40%" height="28" style="padding-left:20px; text-align:left;"> VIVI时尚简约的大</td>
			<td width="30%" align="right" style="padding-right:150px;">￥68.00 </td>
			<td width="15%"><input name="textfield2" type="text" style="width:30px; border:1px solid #cecece; text-align:center;" value="1" /></td>
			<td width="5%"><a href="HT3.htm">购买</a></td>
			<td width="5%"><a href="#">充值</a></td>
			<td width="5%"><a href="#">删除</a></td>
		</tr>
		<tr align="center" bgcolor="#FFFFFF">
			<td width="40%" height="28" style="padding-left:20px; text-align:left;"> VIVI时尚简约的大单 </td>
			<td width="30%" align="right" style="padding-right:150px;">￥68.00 </td>
			<td width="15%"><input name="textfield2" type="text" style="width:30px; border:1px solid #cecece; text-align:center;" value="1" /></td>
			<td width="5%"><a href="HT3.htm">购买</a></td>
			<td width="5%"><a href="#">充值</a></td>
			<td width="5%"><a href="#">删除</a></td>
		</tr>
	</table></td>
        <td width="14"  style="background:url(../resources/images/tab_16.gif) repeat-y right #d3e7fc; ">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="../resources/images/tab_20.gif" width="15" height="29" /></td>
        <td background="../resources/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="21%" height="14">&nbsp;</td>
            <td width="79%" class="STYLE1" align="right"><a href="#">首页</a> | <a href="#">上一页</a> | <a href="#">下一页</a> | <a href="#">尾页</a> &nbsp;转到第
              <input name="textfield" type="text" size="5"  style="height:10px; width:20px;"/>
              页  &nbsp;<img src="../resources/images/arrow_082.gif" width="9" height="8" /> <a href="#">跳转</a></td>
          </tr>
        </table></td>
        <td width="14"><img src="../resources/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
