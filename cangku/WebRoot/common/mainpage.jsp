<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <frameset rows="75,*,30" cols="*" scrolling="auto"  border="0" framespacing="0" frameborder="0" scrolling="false">
        <frame src="top.jsp" id="Top_frame" frameborder="NO" scrolling="no" name="top" marginwidth="0" marginheight="0" noresize scrolling="false">
        <frameset cols="200,*" border="0" framespacing="0" frameborder="0" id="lkoamenu_frame" name="lkoamenu_frame" scrolling="false"> 
            <frame src="menu.jsp" frameborder="no" name="left" marginwidth="0" marginheight="0"  noresize scrolling="false">
            <frame src="welcome.jsp" frameborder="NO" name="content" marginwidth="0" marginheight="0" noresize  scrolling="false">
        </frameset>
        <frame src="down.jsp" name="down" frameborder="NO" scrolling="No" noresize="noresize" marginwidth="0" marginheight="0" id="down" />
  </frameset>

</html>