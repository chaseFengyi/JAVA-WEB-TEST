<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'listcart.jsp' starting page</title>
    
    <script type="text/javascript">
    	function clearcart(){
    		var b = window.confirm("您确定要清空购物车吗？");
    		if(b){
    			window.location.href="${pageContext.request.contextPath }/servlet/ClearCartServlet";
    		}
    	}
    	
    	function updatecart(input,id,oldValue){
    		var quantity = input.value;
    		
    		var b = window.confirm("您确认改为:"+quantity+" 吗？");
    		if(b){
    			window.location.href="${pageContext.request.contextPath }/servlet/UpdateCartServlet?bookid="+id+"&quantity="+quantity;
    		}else{
    			input.value = oldValue;
    		}
    	}
    	
    </script>
    
  </head>
  
  <body style="text-align: center;">    
  	<h2>购物车列表</h2><br><br>
  	
  	<c:if test="${!empty(cart.map) }">
  	
	<table border="1" width="80%">
		<tr>
			<td>书名</td>
			<td>作者</td>
			<td>单价</td>
			<td>数量</td>
			<td>小计</td>
			<td>操作</td>
		</tr>
		
		<c:forEach var="me" items="${cart.map }">
		<tr>
			<td>${me.value.book.name }</td>
			<td>${me.value.book.author}</td>
			<td>${me.value.book.price }</td>
			<td>
				<input type="text" name="quantity" value="${me.value.quantity }" style="width: 30px;" onchange="updatecart(this, ${me.value.book.id},${me.value.quantity })">
			</td>
			<td>${me.value.price }</td>
			<td>
				<a href="${pageContext.request.contextPath }/servlet/DeleteServlet?bookid=${me.value.book.id}">删除</a>
			</td>
		</tr>
		</c:forEach>
		
		<tr>
			<td colspan="2">
				<a href="javascript:clearcart()">清空购物车</a>
			</td>
			<td colspan="2">合计</td>
			<td colspan="2">${cart.price }</td>
		</tr>
	</table>
	</c:if>
	<c:if test="${empty(cart.map) }">
		对不起，您还没有购买任何商品
	</c:if>
  </body>
</html>
