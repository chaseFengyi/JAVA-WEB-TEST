<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>selectProduct.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <script type="text/javascript">
    function productSelected()
    {
        alert("将商品获得返回给前面的页面中的文本框");
    }
    </script>
  </head>
<body style="margin:0;background-color:#ECEEF0">
	<table width="100%" border="0">
	<tr><td align="center">选择商品</td></tr>
  <tr>
    <td align="center">
    <div id="test">
        <select name="selectProductForm" size="20">
            <option/>从数据库中取出的商品1
            <option/>从数据库中取出的商品2
            <option/>从数据库中取出的商品3
        </select>
    </div>&nbsp;</td>
  </tr>
  <tr>
    <td align="center"><input type="button" value=" 确  定 " onClick="productSelected()" />
	<input type="button" value=" 取  消 " onClick="window.close()" />&nbsp;</td>
  </tr>
</table>
</body>
</html>
