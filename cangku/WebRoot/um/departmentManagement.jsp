<%@page import="com.Vo.DeptVo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>部门管理</title>
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
							<span class="STYLE4">部门管理</span></td>
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
									<td bgcolor="#eceef0" colspan="7" align="center">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											align="left" height="40" style="border:1px dashed #d4d4d4;">
											<tr>
												<td height="20" align="left" width="50%"
													style="padding-left:10px;">
													<form action="../servlet/QueryDeptController" method="post" class="STYLE1">
														部门名：<input type="text" name="deptName" class="bt_04" /> <input
															type="submit" name="Submit" value="查询" class="bt_02" />
													</form>
												<td width="40%" style="color:red"></td>
												<td align="right" width="50%" style="padding-left:10px;"><table
														width="151" border="0" cellspacing="0" cellpadding="0"
														style="background:url(../resources/images/htbutton.gif) no-repeat; margin:5px; ">
														<tr>
															<td align="center" height="34"><a
																href="../um/addDepartment.jsp"
																style="font-size:15px; font-weight:bold; color:#fff;">添加部门</a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="dz" align="center" style="font:12">
									<td width="10%" height="28"
										background="../resources/images/sale_bg.gif">ID</td>
									<td background="../resources/images/sale_bg.gif" width="15%">部门名称</td>
									<td background="../resources/images/sale_bg.gif" width="15%">部门经理</td>
									<td background="../resources/images/sale_bg.gif" width="20%">描述</td>
									<td background="../resources/images/sale_bg.gif" width="20%">状态</td>
									<td background="../resources/images/sale_bg.gif" colspan="2"
										width="20%">操作</td>
								</tr>
								<%
									List<DeptVo> list = (List<DeptVo>) request.getAttribute("DeptVo");
									for (DeptVo vo : list) {
								%>
								<tr class="my" align="center" bgcolor="#ffffff">
									<td align="center"><%=vo.getId()%></td>
									<td align="center"><%=vo.getDeptname()%></td>
									<td align="center"><%=vo.getDeptmaster()%></td>
									<td align="center"><%=vo.getDesc()%></td>
									<td align="center"><%=vo.getStatus()%></td>
									<td align="center"><a
										href="../servlet/UpdateDeptUIController?id=<%=vo.getId()%>">修改</a>
									</td>
									<td align="center"><a style="font:12"
										href="../servlet/DeleteDeptController?id=<%=vo.getId()%>"
										onclick="return window.confirm('确定要删除吗?')">删除</a>
									</td>
								</tr>
								<%
									}
								%>
							</table>
						</td>
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
