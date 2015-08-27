<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String userName = request.getParameter("userName");
	String userPass = request.getParameter("password");
	if(userName.equals("zhangsan")){
		if(userPass.equals("123")){
			response.sendRedirect("common/mainpage.html");
		}else{
			out.print("pass error");
		}
	}else{
		out.print("userName or pass error");
	}
 %>
