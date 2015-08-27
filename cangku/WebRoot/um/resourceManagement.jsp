<%@page import="com.Vo.Resource"%>
<%@ page language="java" import="java.util.*,java.sql.*;"
	pageEncoding="GB2312"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=gb2312">
<title>资源管理</title>
<link href="../resources/css/hottest.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
	font-family: "宋体";
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

.bt_01 {
	width: 71px;
	height: 25px;
	border: 0px;
	background: url(../resources/images/htdl.gif) no-repeat;
	color: #fff;
	font-size: 12px;
}

.bt_02 {
	width: 60px;
	height: 20px;
	border: 0px;
	background: #0099CC;
	color: #fff;
	font-size: 12px;
}

.bt_03 {
	width: 200px;
	height: 15px;
}
-->
</style>
</head>

<body>


	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="30"><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="15" height="30"><img
							src="../resources/images/tab_03.gif" width="15" height="30" />
						</td>
						<td background="../resources/images/tab_05.gif" align="left">
							<img src="../resources/images/311.gif" width="16" height="16" />
							<span class="STYLE4">资源管理</span></td>
						<td width="14"><img src="../resources/images/tab_07.gif"
							width="14" height="30" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="15"
							style="background:url(../resources/images/tab_12.gif) repeat-y left #d3e7fc; ">&nbsp;</td>
						<td width="97%" bgcolor="#FFFFFF" height="500" valign="top"
							align="center">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								bgcolor="#cecece" align="center">
								<tr class="dz">
									<td bgcolor="#eceef0" colspan="7" align="center"
										valign="middle">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											align="left" height="40" style="border:1px dashed #d4d4d4;">
											<tr>
												<td height="20" align="left" width="50%"
													style="padding-left:10px;">
													<form action="../servlet/QueryResourceController" method="post" class="STYLE1">
														资源名：<input type="text" name="resourceName" class="bt_04" />
														<input type="submit" name="Submit" value="查询"
															class="bt_02" />
													</form>
												</td>
												<td width="40%" style="color:red"></td>
												<td align="right" width="50%" style="padding-left:10px;">
													<table width="151" border="0" cellspacing="0"
														cellpadding="0"
														style="background:url(../resources/images/htbutton.gif) no-repeat; margin:5px; ">
														<tr>
															<td align="center" height="34"><a
																href="../um/addResource.jsp"
																style="font-size:15px; font-weight:bold; color:#fff;">添加资源</a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table></td>
								</tr>
								<tr class="dz" align="center" style="font:12">

									<td width="20%" height="28"
										background="../resources/images/sale_bg.gif">资源编号</td>
									<td width="20%" background="../resources/images/sale_bg.gif">资源名称</td>
									<td background="../resources/images/sale_bg.gif" width="30%">资源描述</td>
									<td background="../resources/images/sale_bg.gif" width="10%">创建时间</td>
									<td background="../resources/images/sale_bg.gif" width="10%">状态</td>
									<td background="../resources/images/sale_bg.gif" colspan="2"
										width="10%">操作</td>


								</tr>
								<%
									List<Resource> list = (List<Resource>) request
											.getAttribute("resources");
									for (Resource resource : list) {
								%>
								<tr class="my" align="center" bgcolor="#ffffff">
									<td><%=resource.getId()%></td>
									<td><%=resource.getName()%></td>
									<td><%=resource.getDescription()%></td>
									<td><%=resource.getCreateTime()%></td>
									<td><%=resource.getStatus()%></td>
									<td><a
										href="../servlet/UpdateResourceUIController?id=<%=resource.getId()%>">修改</a>
									</td>
									<td><a
										href="../servlet/DeleteResourcerController?id=<%=resource.getId()%>"
										onclick="return window.confirm('确定要删除吗?')">删除</a>
									</td>
								</tr>
								<%
									}
								%>
							</table></td>
						<td width="14"
							style="background:url(../resources/images/tab_16.gif) repeat-y right #d3e7fc; ">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="29"><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="15" height="29"><img
							src="../resources/images/tab_20.gif" width="15" height="29" />
						</td>
						<td background="../resources/images/tab_21.gif"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="21%" height="14">&nbsp;</td>
									<td width="79%" class="STYLE1" align="right"><a href="">首页</a>
										| <a href="">上一页</a> <span><font color="red"></font>
									</span> <a href="">下一页</a> | <a href="">尾页</a>&nbsp; 当前第 <span
										style="FONT-WEIGHT: bold; COLOR: #f46521"></span><span
										class="font_1">/</span>页</td>
								</tr>
							</table>
						</td>
						<td width="14"><img src="../resources/images/tab_22.gif"
							width="14" height="29" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>