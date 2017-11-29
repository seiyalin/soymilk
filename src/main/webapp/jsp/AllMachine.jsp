<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>
<body>
<h2>所有设备信息：</h2>
<h3>机器编号		机器名称		appId		mchId		apiKey		createIp</h3>
<c:forEach items="${list}" var="machine">
	<br/>
	<h3>${machine.machineId}&emsp;${machine.machineName}&emsp;${machine.appId}&emsp;${machine.mchId}&emsp;
	${machine.apiKey}&emsp;${machine.createIp}</h3>	
</c:forEach>
</body>
</html>
