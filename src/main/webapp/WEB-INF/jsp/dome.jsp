<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
pageContext.setAttribute("basePath",basePath);    
%>
<meta http-equiv="content-type" content="text/html; charset=gb2312" />
<title>jquery 超级select 插件 v3.0.0.0插件</title>
</head>
<script type="text/javascript" src="<%=basePath %>static/js/jquery1.4.2.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.selectseach.min.js"></script>
<!--<script type="text/javascript" src="js/jquery.selectseach.js"></script>-->
<script>

$(document).ready(function(){
   $('#sssss').selectseach(); 
}); 

</script>
<body style="height:">

<select name="sssss" id="sssss"  m='search'>
      <option value="139"> a english book </option>
      <option value="140">张三</option>
      <option value="141">李四</option>
      <option value="142">王五</option>
      <option value="143">浏阳市集里街道长南路小学</option>
      <option value="144">浏阳市集里街道平水桥小学</option>
      <option value="145">浏阳市集里街道禧和岭小学</option>
      <option value="146">浏阳市集里龚家桥小学</option>
      <option value="147" >浏阳市集里街道百宜小学</option>
      <option value="148">浏阳市荷花街道杨家小学</option>
      <option value="149">浏阳市荷花街道净溪小学</option>
      <option value="150">浏阳市荷花街道牛石小学</option>
      <option value="151">浏阳市荷花街道云桥小学</option>
      <option value="152">浏阳市荷花街道早禾小学</option>
      <option value="153">浏阳市荷花街道光彩小学</option>
      <option value="154">浏阳市荷花街道小水小学</option>
      <option value="155">浏阳市荷花街道建新小学</option>
      <option value="156">a chinese man 胡</option>
      <option value="157">浏阳市荷花街道胡坪小学</option>
      <option value="158">liuyan</option>
      <option value="159">changsha</option>
    </select>
