<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>machine management</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form id="saveForm" action="saveMachine.do" method="post">
        <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">机器编号</span></label>
          <div class="col-sm-6">
              <input type="text" class="form-control" id="machineId" name="machineId" maxlength="50">
          </div>    
        <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">机器名称</span></label>
           <div class="col-sm-6">
               <input type="text" class="form-control" id="machieName" name="machineName" maxlength="50">
           </div>              
         <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">appId</span></label>
           <div class="col-sm-6">
               <input type="text" class="form-control" id="appId" name="appId" maxlength="50">
           </div>           
          <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">mchId</span></label>
            <div class="col-sm-6">
               <input type="text" class="form-control" id="mchId" name="mchId" maxlength="50">
            </div>            
          <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">apiKey</span></label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="apiKey" name="apiKey" maxlength="50">
            </div>
            
           <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">createIp</span></label>
             <div class="col-sm-4">
               <input type="text" class="form-control" id="createIp" name="createIp" maxlength="50">
             </div>
            <input type="submit" value="提交"/>
 	</form>
 		
</body>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<!-- <script type="text/javascript" src="../js/jquery.serializejson.js"></script> -->
	<script src="https://cdn.bootcss.com/jquery.serializeJSON/2.8.1/jquery.serializejson.js"></script>
  </body>
</html>
