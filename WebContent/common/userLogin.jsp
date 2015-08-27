<%@ page language="java" import="java.util.*,java.sql.*;" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆界面</title>
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  	<%!String str; %>
    <a href="mainpage.html">
    <%
    String user=request.getParameter("Username");
    String pass=request.getParameter("password");
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn=DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mysql",
    "root","123456");
    
    String sql="select * from usrinfo where name=? and password=?";
    PreparedStatement stat=conn.prepareStatement(sql);
    
    stat.setString(1,user);
    stat.setString(2,pass);
    
    ResultSet rs=stat.executeQuery();
    //sql注入
    //' or 1=1 or 1='1
    
    if(rs.next())
    {
    	str="登陆成功";
    	response.sendRedirect("");
    }else
    {
    	str="用户名不存在或密码不正确";
    }
    rs.close();
    stat.close();
    conn.close();

    %>
    </a>
  <h1><%=str%></h1>
    
    
  </body>
</html>
