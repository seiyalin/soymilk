<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testPost.jsp' starting page</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <input type="button" value = json onclick="requestByJson()"> <br>
    
    <script>
    	function requestByJson() {
    	alert("${path}");
         $.ajax({

                   type : 'post',

                   url : '${basePath}/soymilk/jsonsource.action',
                   //设置contentType类型为json
                   contentType : 'application/json;charset=utf-8',
                   //json数据
                   data : JSON.stringify('{"machineId":"3","machineName":"yp","appId":"fd16","mchId":"333","apiKey":"1","createIp":"192.168.2.3"}'),
                   //请求成功后的回调函数
                   success : function(data) {
                            alert(data.machineId);

                   }
         });
}
    </script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  </body>
</html>
