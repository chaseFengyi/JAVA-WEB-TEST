<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<HTML>
<HEAD>
<title>����ҳ</title>
<META http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="refresh" content="5;URL=http://localhost:8080/cangku/">
<STYLE type=text/css>
INPUT TD  {
	FONT-SIZE: 14px
}
.p2 {
	FONT-SIZE: 14px; COLOR: red; font-weight: bold;
}
.p6 {
	FONT-SIZE: 14px; COLOR: #1b6ad8
}
A {
	COLOR: #1b6ad8; TEXT-DECORATION: none
}
A:hover {
	COLOR: red
}
</STYLE>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false">
<P align=center>��</P>
<P align=center>��</P>
<TABLE cellSpacing=0 cellPadding=0 width=540 align=center border=0>
  <TR>
    <TD height=270>
      <DIV align=center><BR><IMG height=211 src="resources/images/error-error.gif" width=329><BR><BR>
      <TABLE cellSpacing=0 cellPadding=0 width="80%" border=0>

        <TR>
          <TD>
             <FONT class=p2>&nbsp;&nbsp;&nbsp;
             <IMG height=13 src="resources/images/error-message.gif" width=12>�޷����ʱ�ҳ��ԭ���ǣ����������ҳ�治����!</FONT>
          </TD>
        </TR>

      </TABLE>
      </DIV>
    </TD>
  </TR>
  <TR>
    <TD align=middle>
      <CENTER>
      <TABLE cellSpacing=0 cellPadding=0 width=480 border=0>
        <TR>
          <TD width=6><IMG height=26 src="resources/images/error-left.gif" width=7></TD>
          <TD background="resources/images/error-bg.gif" align=center>
              <FONT class=p6>
              <A href="http://localhost:8080/cangku/">&nbsp;&nbsp;������ҳ</A>&nbsp;&nbsp;|
              <A href="javascript:history.go(-1)">&nbsp;&nbsp;���س���ҳ</A>&nbsp;&nbsp;|
              <A href="#" onclick="window.close()">&nbsp;&nbsp;�رձ�ҳ</A>
              </FONT>
           </TD>
          <TD width=7><IMG src="resources/images/error-right.gif"></TD>
        </TR>
      </TABLE>
      </CENTER>
      </TD>
    </TR>
 </TABLE>
</BODY>
</HTML>