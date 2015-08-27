<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String retCode =request.getParameter("retCode");
	String retMsg =request.getParameter("retMsg");
/*	
String retCode=null;
	String retMsg=null;
	if(request.getAttribute("retCode")!=null){
		retCode= request.getAttribute("retCode").toString();
	retMsg = request.getAttribute("retMsg").toString();
	}
	*/
	
	retMsg =retMsg == null ? "" : retMsg;
%>

<html>
  <frameset rows="75,*,30" cols="*" scrolling="auto"  border="0" framespacing="0" frameborder="0" scrolling="false">
        <frame src="top.html" id="Top_frame" frameborder="NO" scrolling="no" name="top" marginwidth="0" marginheight="0" noresize scrolling="false">
        <frameset cols="200,*" border="0" framespacing="0" frameborder="0" id="lkoamenu_frame" name="lkoamenu_frame" scrolling="false"> 
            <frame src="menu.html" frameborder="no" name="left" marginwidth="0" marginheight="0"  noresize scrolling="false">
            <frame src="welcome.html" frameborder="NO" name="content" marginwidth="0" marginheight="0" noresize  scrolling="false">
        </frameset>
        <frame src="down.html" name="down" frameborder="NO" scrolling="No" noresize="noresize" marginwidth="0" marginheight="0" id="down" />
  </frameset>

</html>