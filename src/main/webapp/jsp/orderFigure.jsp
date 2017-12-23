<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>销售数据展示</title>
    
	<link rel="stylesheet" type="text/css" href="css/dzy.css">
	

  </head>
  
  <body> 
  <div id="content">
 	 <div class="container-fluid">
    	<div class="row-fluid">
      	<div class="span12">
       
        <div class="widget-box">   
          <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
            <!-- <h5>设备管理信息&emsp;&emsp;<a href="/soymilk/jsp/orderQuery.jsp">销售报表</a></h5>  -->
            <div class="navigate">
			<ul class="topbar brick">
				<li class="brick"><a href="/soymilk/jsp/machines.jsp" style="color:black;text-decoration:none;">设备管理</a></li>
				<li class="brick"><a href="/soymilk/jsp/orderResult.jsp" style="color:black;text-decoration:none;">销售报表</a></li>
				<li class="brick current"><a href="/soymilk/jsp/orderFigure.jsp" style="color:black;text-decoration:none;">图表展示</a></li>
			</ul>
			</div>
          </div>
          
          <div class="page-body">
    	<div class="row">
          <div class="col-xs-12 col-md-12">
            <div class="widget widget-table-container">
              <div class="widget-body">
    <div id="map-wrap" class="map"></div>
    <div class="queryForm">
    	<form action="" id="queryByMap" align="center">
    	生产商:&emsp;&emsp;<input id="producerId" name="producerId" type="text" readonly="readonly"><br>
    	渠道商:&emsp;&emsp;<input id="channelId" name="channelId" type="text" readonly="readonly"><br>
    	终端设备:&emsp;<input id="machineId" name="machineId" type="text" readonly="readonly"><br>
    	年：	<select id="year" name="year">
    		<option value="2017">2017</option>
    		<option value="2018">2018</option>
    		<option value="all">all</option>
    	</select>&emsp;&emsp;
		月：	<select id="month" name="month">
			<option value="12">12</option>
    		<option value="1">1</option>
    		<option value="2">2</option>
    		<option value="3">3</option>
    		<option value="4">4</option>
    		<option value="5">5</option>
    		<option value="6">6</option>
    		<option value="7">7</option>
    		<option value="8">8</option>
    		<option value="9">9</option>
    		<option value="10">10</option>
    		<option value="11">11</option>
    		<option value="all"></option>
    		<option value="--"></option>
    		
		</select><br>
		<input id="productId" name="productId" type="hidden" value="all"><br>
		<input id="day" name="day" type="hidden" value="all"><br>
    	</form>
    </div>
    <div class="figure">
    	<div id="day_sales" class="sales"></div>
    	<div id="month_sales" class="sales"></div>
    	<div id="year_sales" class="sales"></div>
    </div>
    
    </div>
    </div>
    </div></div></div></div></div></div></div></div>
    
    <script src="common/assets/js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="js/echarts.js"></script>
	<script type="text/javascript" src="js/china.js"></script>
	<script type="text/javascript" src="js/orderFigure.js"></script>
  </body>
</html>
