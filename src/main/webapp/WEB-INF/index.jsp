<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<%
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
pageContext.setAttribute("basePath",basePath);    
%>

<script language="JavaScript"> 
if (window != top) 
top.location.href = location.href; 
</script>

<link type="text/css" rel="stylesheet" href="${pageScope.basePath}res/css/login.css" />
</head>
<body>
<div class="login_bg">
</div>

<div class="login_div">
	<div style="height:350px;"></div>
    <div class="loginBox" style=" font-size:20px">
    	<form action="${pageScope.basePath}sys/user/login" method="post" id="sysform">
			<table width="320" border="0" cellpadding="5" cellspacing="5">
	          <tr>
	            <td colspan="2">
	              <input type="text" name="username" id="username" class="inputUser"/>
	            </td>
	          </tr>
	          <tr>
	            <td colspan="2">
	              <input type="password" name="password" id="password" class="inputPass"/>
	              <font color="red">${sign}</font>
	            </td>
	          </tr>
	          <tr>
	            <td>
<!-- 	            <a>忘记密码？</a> -->
	            </td>
	            <td align="right"><input name="" type="submit" value="登 录" class="login_btn"/></td>
	          </tr>
	        </table>
		</form>
  </div>
</div>
</body>
</html>
