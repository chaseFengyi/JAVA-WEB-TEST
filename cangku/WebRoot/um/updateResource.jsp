<%@page import="com.Vo.Resource"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>网站后台管理</title>
	<script defer="defer" language="javascript" type="text/javascript" src="../datepicker/WdatePicker.js"></script>
	<link href="../resources/css/hottest.css" rel="stylesheet" type="text/css" />
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
		font-family:"宋体";
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
	.bt_01{
	    width:71px;
		height:25px;
		border:0px;
	    background: url(../resources/images/htdl.gif) no-repeat;
		color:#fff;
		font-size:12px;
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
        <td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">资源修改</span></td>
        <td width="14"><img src="../resources/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" style="background:url(../resources/images/tab_12.gif) repeat-y left #d3e7fc; ">&nbsp;</td>
        <td width="97%" bgcolor="#FFFFFF" height="500" valign="top" align="center" style="padding-top:20px;">
		<table width="505px" border="0" cellspacing="0" cellpadding="0">
			<tr style="width:100%;height:20px;background:url(../resources/images/htbg1.gif);">
				<td></td>
			</tr>
			<tr style="width:100%; height:250px; background: url(../resources/images/htbg3.gif) repeat-y; text-align:center;">
				<td>
				
					<%
						Resource resource = (Resource)request.getAttribute("resource");
					 %>
					<FORM action="../servlet/UpdateResourceController?id=<%=resource.getId() %>" method="post" >
											<table width="80%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="20%" height="40" align="right" class="STYLE4">资源编号：</td>
													<td align="left"><input type="text" name="resourceId"
														value="<%=resource.getId()%> " disabled="disabled" />
													</td>
												</tr>
												<tr>
													<td width="20%" height="40" align="right" class="STYLE4" /></html>资源名称：</td>
													<td align="left"><input type="text" name="name"
														value="<%=resource.getName()%> " name="resourceName" />
													</td>
												</tr>
												<tr>
													<td height="40" align="right" class="STYLE4">资源描述：</td>
													<td align="left"><textarea name="description"><%=resource.getDescription()%></textarea>
													</td>
												</tr>
												<tr>
													<td height="40" align="right" class="STYLE4">创建时间：</td>
													<td align="left"><input type="text" name="createTime"
														value="<%=resource.getCreateTime()%>" id="createTime" />&nbsp;<img
														onClick="WdatePicker({el:$dp.$('createTime')})"
														src="../datepicker/skin/datePicker.gif" width="16"
														height="22" align="absmiddle">
													</td>
												</tr>
												<tr>
													<td height="40" align="right" class="STYLE4">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
													<td height="30" align="left"><select name="status">
															<option value="1">正常</option>
															<option value="0">冻结</option>
															<%-- <c:if test="<%resource.getStatus().equals('1'); %>">
						  	 <option value="1" selected="selected">正常
						  	 <option value="0" >冻结</option>
						   </c:if>
						   
						  	</option>
						  	<c:if test="<%resource.getStatus().equals('0'); %>">
						  	<option value="1" >正常</option>
						  	<option value="0" selected="selected">冻结</option>
						   </c:if> --%>
													</select>
													</td>
												</tr>
												<tr>
													<td height="40" colspan="2" align="center"
														style="color:blue"><input type="submit" value="确定"
														class="bt_01" /> &nbsp; <input type="submit" value="取消"
														class="bt_01" />
													</td>

												</tr>
												<tr>
													<td height="40" colspan="2" align="center" class="STYLE4"></td>
												</tr>
											</table>
					</FORM>
				</td>
			</tr>
			<tr style="width:100%; height:17px; background: url(../resources/images/htbg2.gif) no-repeat;">
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
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
            <td width="79%" class="STYLE1" align="right">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="../resources/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>



