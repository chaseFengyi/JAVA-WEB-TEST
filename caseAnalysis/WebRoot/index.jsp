<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
   <style>
        body {
            background: url(WebRoot/images/htbg.gif) repeat-x top left;
        }

        .d2 {
            border: 0px;
            background: url(WebRoot/images/htdl.gif) no-repeat;
            font-size:12px;
            font-weight:bold;
            width: 71px;
            height: 25px;
            color: #ffffff;
        }
    </style>
  <body>
    <div id="htdlbg">
            <div id="httab">
                <form action="" method="post">
                    <input type="hidden" name="sumit" value="yes"
                        id="systemLogin_execute_sumit" />
                    <table width="400" border="0" cellspacing="0" cellpadding="0"
                        height="169" align="center">
                        <tr>
                            <td colspan="3">
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="left" height="24"
                                style="color: red; padding-left: 40px">&nbsp;<span id="span"></span>
                            </td>
                        </tr>
                        <tr>
                            <td width="100" height="40" align="right">
                                用户名：
                            </td>
                            <td width="300" align="left">
                                <input type="text" name="userName" size="18"
                                    id="userName" style="width: 150px" />
                            </td>
                        </tr>
                        <tr>
                            <td width="100" height="40" align="right">
                                密&nbsp;&nbsp;&nbsp;&nbsp;码：
                            </td>
                            <td width="300" align="left">
                                <input type="password" name="password" size="18" id="pwd"
                                    style="width: 150px" />
                            </td>
                        </tr>
                        <tr>
                            <td height="40" align="left" colspan="2"
                                style="padding-left: 100px;">
                                <input type="submit" id="systemLogin_execute_save" name="save"
                                    value="登录" class="d2" onclick="check();" />
                                <input type="reset" name="reset" value="取消" class="d2" />
                            </td>
                        </tr>
                        <tr><td height="40" colspan="2" align="center" class="STYLE4"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>      
    </body>
  </body>
</html>
