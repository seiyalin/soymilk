<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>销售报表生成</title>
	<script type="text/javascript" src="js/orderQuery.js"></script>
	
	<link href="css/classic.css" rel="stylesheet" />

  </head>
  
  <body>
  	<h1 align="center">豆芝缘销售报表查询</h1>
    <div align="center">
    	<form action="" id="selectQuery">
    	生产商：<select id="producerId" name="producerId"></select>&emsp;&emsp;
    	渠道商：<select id="channelId" name="channelId"></select>&emsp;&emsp;
    	终端：<select id="machineId" name="machineId"></select>&emsp;&emsp;
    	<!-- <br/><br/> -->
    	口味：<select id="productId" name="productId">
    	<option value="--">--</option>
    	<option value="1">1</option>
    	<option value="2">2</option>
    	<option value="3">3</option>
    	<option value="all">all</option>
    	</select>&emsp;&emsp;
    	<!-- <br/><br/> -->
    	年：	<select id="year" name="year"></select>&emsp;&emsp;
    	月：	<select id="month" name="month"></select>&emsp;&emsp;
    	日：	<select id="day" name="day"></select>
    	<br/><br/>
    	<input type="button" onclick="getExcel()" value="下载报表"></input>
    	&emsp;&emsp;&emsp;&emsp;
    	<input type="button" onclick="showOnline()" value="查看报表"></input>
    	
    	</form>
    </div>
    <div id="columns"></div>
    
    	<script type="text/javascript">
    		selectInit('producerId','channelId','machineId','year','month','day');
    	</script>
    	<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->
    	<script type="text/javascript" src="js/jquery.min.js"></script>
    	<script type="text/javascript" src="js/jquery.columns.min.js"></script>
		<script type="text/javascript" src="js/mustache.min.js"></script>
    
  </body>
</html>
