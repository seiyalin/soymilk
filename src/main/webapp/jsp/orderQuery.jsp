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

  </head>
  
  <body>
    <div>
    	生产商：<select id="producerId"></select>&emsp;&emsp;
    	渠道商：<select id="channelId"></select>&emsp;&emsp;
    	终端：<select id="machineId"></select>
    	<br/><br/>
    	口味：<select id="productId">
    	<option value="--">--</option>
    	<option value="1">1</option>
    	<option value="2">2</option>
    	<option value="3">3</option>
    	<option value="all">all</option>
    	</select>
    	<br/><br/>
    	年：	<select id="year"></select>&emsp;&emsp;
    	月：	<select id="month"></select>&emsp;&emsp;
    	日：	<select id="day"></select>
    	<br/><br/>
    	<input type="submit" value="查看报表"></input>&emsp;&emsp;&emsp;&emsp;
    	<input type="submit" value="下载报表"></input>
    	<script type="text/javascript">
    		selectInit('producerId','channelId','machineId','year','month','day');
    	</script>
    </div>
  </body>
</html>
