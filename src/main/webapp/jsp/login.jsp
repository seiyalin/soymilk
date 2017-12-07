<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台登录</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/login.css" type="text/css" rel="stylesheet"> 

  </head>
  
  <body>
  	<div class="login">
    <div class="message">豆芝缘-管理登录</div>
    <div id="darkbannerwrap"></div>
    
    <form id="loginForm" action="login.do" method="post">
		<!-- <input name="action" value="login" type="hidden"> -->
		<input name="username" placeholder="用户名" type="text">
		<hr class="hr15">
		<input name="password" placeholder="请输入密码" type="password">
		<hr class="hr15">
		<input value="登录" style="width:100%;" type="button" onclick="login()">
		<hr class="hr20">
		<!-- 帮助 <a onClick="alert('请联系管理员')">忘记密码</a> -->
	</form>

</div>
<div class="copyright">© 豆芝缘 by <a href="16210720161@fudan.edu.cn" target="_blank">seiya</a></div>
    
</body>
	<script type="text/javascript">
		function login(){
			$.post("login.do",$("#loginForm").serialize(),function(json){
				if(json.success==true)
					window.location.href="/soymilk/jsp/machines.jsp";
			});
		}
	</script>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	
	<!-- <script src="https://cdn.bootcss.com/jquery.serializeJSON/2.8.1/jquery.serializejson.js"></script> -->
	<!-- <script src="http://www.mycodes.net/js/tongji.js"></script>
	<center><script src="http://www.mycodes.net/js/youxia.js" type="text/javascript"></script></center> -->
 
</html>
