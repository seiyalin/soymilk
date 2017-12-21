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
	<link href="css/dzy.css" type="text/css" rel="stylesheet">
	<!--Basic Styles-->
    <link href="common/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link id="bootstrap-rtl-link" href="" rel="stylesheet" />
    <link href="common/assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="common/assets/css/weather-icons.min.css" rel="stylesheet" />
    
     <!--Beyond styles-->
    <link id="beyond-link" href="css/beyond.min.css" rel="stylesheet" />
    <link href="common/assets/css/demo.min.css" rel="stylesheet" />
    <link href="common/assets/css/typicons.min.css" rel="stylesheet" />
    <link href="common/assets/css/animate.min.css" rel="stylesheet" />
    <link href="common/assets/css/customer.css" rel="stylesheet" />
    <link id="skin-link" href="" rel="stylesheet" type="text/css" />
    
	

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
				<li class="brick current"><a href="/soymilk/jsp/orderQuery.jsp" style="color:black;text-decoration:none;">销售报表</a></li>
			</ul>
			</div>
          </div>
  
          <!-- <div id="hd" class="nav clearfix"> 
            <h5>设备管理信息&emsp;&emsp;<a href="/soymilk/jsp/orderQuery.jsp">销售报表</a></h5> 
            <div class="navigate">
			<ul class="topbar brick">
				<li class="brick"><a href="/soymilk/jsp/machines.jsp" style="color:black;text-decoration:none;">设备管理</a></li>
				<li class="brick current"><a href="/soymilk/jsp/orderQuery.jsp" style="color:black;text-decoration:none;">销售报表</a></li>
			</ul>
		</div>
          </div> -->
  	<!-- <h1 align="center">豆芝缘销售报表查询</h1> -->
    <div class="page-body">
    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <div class="widget widget-table-container">
                            <div class="widget-body">
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
    	 <div id="columns"></div>
    	</div>
    	 
    </div>
    </div>
    </div>
  
    </div>
    </div>
    </div>
    </div>
    </div>
    </div>
    
    	<script type="text/javascript">
    		selectInit('producerId','channelId','machineId','year','month','day');
    	</script>
    	<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->
    	<script type="text/javascript" src="js/jquery.min.js"></script>
    	<script type="text/javascript" src="js/jquery.columns.min.js"></script>
		<script type="text/javascript" src="js/mustache.min.js"></script>
    
  </body>
</html>
