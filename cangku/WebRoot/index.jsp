<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>用户登录</title>
        <link href="resources/css/hottest.css" rel="stylesheet" type="text/css" />
    </head>
    <style>
        body {
            background: url(resources/images/htbg.gif) repeat-x top left;
        }

        .d2 {
            border: 0px;
            background: url(resources/images/htdl.gif) no-repeat;
            font-size:12px;
            font-weight:bold;
            width: 71px;
            height: 25px;
            color: #ffffff;
        }
    </style>
    <body>
        <div id="htdlbg">
            &nbsp;
            <div id="httab">
                <form action="./servlet/login" method="post">
                    <table width="400" border="0" cellspacing="0" cellpadding="0" height="169" align="center">
                        <tr>
                            <td colspan="2" align="left" height="24">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="100" height="40" align="right">用户名：</td>
                            <td width="300" align="left">
                                <input type="text" name="userName" size="18" id="userName" style="width: 150px" />
                            </td>
                        </tr>
                        <tr>
                            <td width="100" height="40" align="right">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                            <td width="300" align="left">
                                <input type="password" name="password" size="18" id="pwd" style="width: 150px" />
                            </td>
                        </tr>
                        <tr>
                            <td height="40" align="left" colspan="2" style="padding-left: 100px;">
                                <input type="submit" value="登录" class="d2" />
                                <input type="reset" value="取消" class="d2" />
                            </td>
                        </tr>
                        <tr>
                            <td height="40" colspan="2" align="right" class="STYLE4">提示区</td>
                        </tr>
                    </table>
                </form>
             </div>
        </div>
    </body>
</html>
