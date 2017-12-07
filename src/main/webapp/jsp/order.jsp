<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加销售记录</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form id="saveForm" action="orderSave.do" method="post">
        <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">生产商</span></label>
          <div class="col-sm-6">
              <input type="text" class="form-control" id="producerId" name="producerId" maxlength="50">
          </div>    
        <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">渠道商</span></label>
           <div class="col-sm-6">
               <input type="text" class="form-control" id="channelId" name="channelId" maxlength="50">
           </div>              
         <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">终端设备</span></label>
           <div class="col-sm-6">
               <input type="text" class="form-control" id="machineId" name="machineId" maxlength="50">
           </div>           
          <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">口味Id</span></label>
            <div class="col-sm-6">
               <input type="text" class="form-control" id="productId" name="productId" maxlength="50">
            </div>            
          <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">购买数量</span></label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="num" name="num" maxlength="50">
            </div>
            
           <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">总价</span></label>
             <div class="col-sm-4">
               <input type="text" class="form-control" id="price" name="price" maxlength="50">
             </div>
            <input type="submit" value="提交"/>
 	</form>
 		
</body>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<!-- <script type="text/javascript" src="../js/jquery.serializejson.js"></script> -->
	<script src="https://cdn.bootcss.com/jquery.serializeJSON/2.8.1/jquery.serializejson.js"></script>
  </body>
</html>
