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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form id="saveForm" action="<%=basePath%>saveMachine" method="post">
    
         <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">机器编号</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="machineId" name="machine.machineId" maxlength="50">
                   </div>
               </div>
              
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">机器名称</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="machieName" name="machine.machineName" maxlength="50">
                   </div>
               </div>
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">appId</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="appId" name="machine.appId" maxlength="50">
                   </div>
               </div>         
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">mchId</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="mchId" name="machine.mchId" maxlength="50">
                   </div>
               </div>
               <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">apiKey</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="apiKey" name="machine.apiKey" maxlength="50">
                   </div>
               </div>
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">createIp</span></label>
                   <div class="col-sm-4">
                       <input type="text" class="form-control" id="createIp" name="machine.createIp" maxlength="50">
                   </div>
                   
               </div>
                        
                <input type="button" value="提交" onclick="saveMachine();"/>
                 </form>
                
                <script type="text/javascript">
                	function saveMachine(){
                		$.ajax({
                			type:"POST",
                			url:basePath+"machine/saveMachine",
                			dataType:"json",
                			contentType:"application/json",
                			data:JSON.stringify($('#saveForm').serializeJSON()),
                			success:function(data){
                				alert(data);}
                		});
                	}
                </script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.serializejson.js"></script>
  </body>
</html>
