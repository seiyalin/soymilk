package org.dzy.soymilk.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dzy.soymilk.utils.ConnUtil;
import org.dzy.soymilk.utils.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@EnableWebMvc
@Controller
public class Pipelinedb {
	
	String[] titles = new String[]{"none","终端设备分口味日销量统计表","终端设备日销量统计表","终端设备分口味月销量统计表","终端设备月销量统计表",
			"终端设备分口味年销量统计表","终端设备年销量统计表","终端设备分口味总销量统计表","终端设备总销量统计表","渠道商分口味日销量统计表","渠道商日销量统计表",
			"渠道商分口味月销量统计表","渠道商月销量统计表","渠道商分口味年销量统计表","渠道商年销量统计表","渠道商分口味总销量统计表","渠道商总销量统计表",
			"生产商分口味日销量统计表","生产商日销量统计表","生产商分口味月销量统计表","生产商月销量统计表","生产商分口味年销量统计表","生产商年销量统计表",
			"生产商分口味总销量统计表","生产商总销量统计表","豆芝缘分口味日销量统计表","豆芝缘日销量统计表","豆芝缘分口味月销量统计表","豆芝缘月销量统计表",
			"豆芝缘分口味年销量统计表","豆芝缘年销量统计表","豆芝缘分口味总销量统计表","豆芝缘总销量统计表"};
	
	@RequestMapping("/orderSave")
	/*@ResponseBody*/
	/* soymilk/orderSave.do?producerId=SH&channelId=HK&machineId=SHJ001&productId=1&num=2&price=8*/
	public String OrderSave(HttpServletRequest req) throws SQLException{
		Connection conn = ConnUtil.getConn();
		Statement stmt = conn.createStatement();
		/*ResultSet rs;*/
		/*int x = Integer.parseInt(req.getParameter("x"));
		int y = Integer.parseInt(req.getParameter("y"));*/
		String producer_id = req.getParameter("producerId");
		String channel_id = req.getParameter("channelId");
		String machine_id = req.getParameter("machineId");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String product_id = req.getParameter("productId");
		int num = Integer.parseInt(req.getParameter("num"));
		String price = req.getParameter("price");
		String sql = "INSERT INTO stm (producer_id,channel_id,machine_id,time,product_id,num,price) VALUES "
				+ "('" + producer_id +"','" + channel_id +"','"+ machine_id +"','" + time+"','"+product_id
				+"',"+num+","+ price+")";
		System.out.println(sql);
		stmt.execute(sql);
		/*rs = stmt.executeQuery("SELECT * FROM v");
	    while (rs.next())
	    {
	      int id = rs.getInt("x");
	      int count = rs.getInt("count");
	      System.out.println(id + " = " + count);
	    }    */
	    stmt.close();
	    conn.close();
		/*JSONObject result = new JSONObject();
		result.put("success", true);
		return result;*/
	    return "order";
	}
	
	@RequestMapping("/getExcel")
	@ResponseBody
	/*soymilk/orderShow.do?id=01*/
	public void getExcel(HttpServletRequest req, HttpServletResponse response) throws SQLException{
		String producerId = req.getParameter("producerId");  //生产商
		String channelId = req.getParameter("channelId");
		String machineId = req.getParameter("machineId");
		String productId = req.getParameter("productId");
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		String whereClaus = new String();
		int viewId =1;
		if(producerId.equals("--")){
			viewId += 24;
		}
		else{
			if(!producerId.equals("all")){
				whereClaus = addWhereClaus(whereClaus, "producer_id", producerId);
			}
			if(channelId.equals("--")){
				viewId += 16;
			}
			else{
				if(!channelId.equals("all")){
					whereClaus = addWhereClaus(whereClaus, "channel_id", channelId);
				}
				if(machineId.equals("--"))
					viewId += 8;
				else{
					if(!machineId.equals("all")){
						whereClaus = addWhereClaus(whereClaus, "machine_id", machineId);
					}
				}
			}
		}
		if(productId.equals("--"))
			viewId++;
		else if(!productId.equals("all")){
			whereClaus = addWhereClaus(whereClaus, "product_id", productId);
		}
		if(year.equals("--"))
			viewId +=6;
		else{
			if(month.equals("--")){
				viewId +=4;
				if(!year.equals("all"))
					whereClaus = addWhereClaus(whereClaus, "extract(year from year)", year);
			}
			else{
				if(day.equals("--")){
					viewId +=2;
					if(!year.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(year from month)", year);
					if(!month.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(month from month)", month);
				}
				else{
					if(!year.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(year from day)", year);
					if(!month.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(month from day)", month);
					if(!day.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(day from day)", day);
				}
			}				
		}
		JSONArray arr = new JSONArray();
		Connection conn = ConnUtil.getConn();
		Statement stmt = conn.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM sv"+ viewId + whereClaus);
		System.out.println("SELECT * FROM sv"+ viewId + whereClaus);
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next())
	    {
			JSONObject obj = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {  
		        String columnName =metaData.getColumnLabel(i);  
		        String value = rs.getString(columnName);
		        if(columnName.equals("day"))
		        	value = value.substring(0,10);
		        if(columnName.equals("month"))
		        	value = value.substring(0,7);
		        if(columnName.equals("year"))
		        	value = value.substring(0,4);
		        obj.put(columnName, value);  
		    }   
			//obj.put("producer_id", rs.getString("producer_id"));
			arr.add(obj);
	    } 
		String title = titles[viewId];
		ExcelUtil.downloadExcelFile(title, arr, response);
		
	}
	
	@RequestMapping("/showOnline")
	@ResponseBody
	/*soymilk/showOnline*/
	public JSONArray showOnline(HttpServletRequest req, HttpServletResponse response) throws SQLException{
		String producerId = req.getParameter("producerId");  //生产商
		String channelId = req.getParameter("channelId");
		String machineId = req.getParameter("machineId");
		String productId = req.getParameter("productId");
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		String whereClaus = new String();
		int viewId =1;
		if(producerId.equals("--")){
			viewId += 24;
		}
		else{
			if(!producerId.equals("all")){
				whereClaus = addWhereClaus(whereClaus, "producer_id", producerId);
			}
			if(channelId.equals("--")){
				viewId += 16;
			}
			else{
				if(!channelId.equals("all")){
					whereClaus = addWhereClaus(whereClaus, "channel_id", channelId);
				}
				if(machineId.equals("--"))
					viewId += 8;
				else{
					if(!machineId.equals("all")){
						whereClaus = addWhereClaus(whereClaus, "machine_id", machineId);
					}
				}
			}
		}
		if(productId.equals("--"))
			viewId++;
		else if(!productId.equals("all")){
			whereClaus = addWhereClaus(whereClaus, "product_id", productId);
		}
		if(year.equals("--"))
			viewId +=6;
		else{
			if(month.equals("--")){
				viewId +=4;
				if(!year.equals("all"))
					whereClaus = addWhereClaus(whereClaus, "extract(year from year)", year);
			}
			else{
				if(day.equals("--")){
					viewId +=2;
					if(!year.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(year from month)", year);
					if(!month.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(month from month)", month);
				}
				else{
					if(!year.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(year from day)", year);
					if(!month.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(month from day)", month);
					if(!day.equals("all"))
						whereClaus = addWhereClaus(whereClaus, "extract(day from day)", day);
				}
			}				
		}
		JSONArray arr = new JSONArray();
		Connection conn = ConnUtil.getConn();
		Statement stmt = conn.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM sv"+ viewId + whereClaus);
		System.out.println("SELECT * FROM sv"+ viewId + whereClaus);
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next())
	    {
			JSONObject obj = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {  
		        String columnName =metaData.getColumnLabel(i);  
		        String value = rs.getString(columnName);
		        if(columnName.equals("day"))
		        	value = value.substring(0,10);
		        if(columnName.equals("month"))
		        	value = value.substring(0,7);
		        if(columnName.equals("year"))
		        	value = value.substring(0,4);
		        obj.put(columnName, value);  
		    }   
			//obj.put("producer_id", rs.getString("producer_id"));
			arr.add(obj);
	    } 
		return arr;
		/*String title = titles[viewId];
		ExcelUtil.downloadExcelFile(title, arr, response);*/
		
	}
	
	public String addWhereClaus(String whereClaus, String key, String value){
		if(whereClaus.length()>0)
			whereClaus += " AND "+ key + "="+"'"+value+"'";
		else
			whereClaus = " WHERE "+key + "="+"'"+value+"'";
		return whereClaus;
	}
	
	@RequestMapping("/orderShow")
	@ResponseBody
	/*soymilk/orderShow.do?id=01*/
	public JSONObject getView(HttpServletRequest req, HttpServletResponse response) throws SQLException{
		String id = req.getParameter("id");  //流视图编号
		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();
		Connection conn = ConnUtil.getConn();
		Statement stmt = conn.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM sv"+id);
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next())
	    {
			JSONObject obj = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {  
		        String columnName =metaData.getColumnLabel(i);  
		        String value = rs.getString(columnName);  
		        obj.put(columnName, value);  
		    }   
			//obj.put("producer_id", rs.getString("producer_id"));
			arr.add(obj);
	    } 
		String title = titles[Integer.parseInt(id)];
		ExcelUtil.downloadExcelFile(title, arr, response);
		result.put("data", arr);
		return result;
	}
	
	@RequestMapping("/orderShowSelected")
	@ResponseBody
	/*soymilk/orderShowSelected.do?id=01&producer_id=SH&channel_id=YP&year=2017&month=12*/
	public JSONArray getViewSelected(HttpServletRequest req) throws SQLException{
		String id = req.getParameter("id");  //流视图编号
		String whereClaus = new String();
		if(req.getParameter("producer_id")!=null)
			whereClaus = " WHERE producer_id ="+"'"+req.getParameter("producer_id")+"'";
		if(req.getParameter("channel_id")!=null){
			if(whereClaus.length()>0)
				whereClaus += " AND channel_id ="+"'"+req.getParameter("channel_id")+"'";
			else
				whereClaus = " WHERE channel_id ="+"'"+req.getParameter("channel_id")+"'";
		}
		if(req.getParameter("year")!=null){
			if(whereClaus.length()>0){
				if(Integer.parseInt(id)%8<3)  //要过滤的视图是日销量
					whereClaus += " AND extract(year from day) ="+req.getParameter("year");
				else		//要过滤的视图是月销量
					whereClaus += " AND extract(year from month) ="+req.getParameter("year");
			}				
			else
				if(Integer.parseInt(id)%8<3)  //要过滤的视图是日销量
					whereClaus = " WHERE extract(year from day) ="+req.getParameter("year");
				else		//要过滤的视图是月销量
					whereClaus = " WHERE extract(year from month) ="+req.getParameter("year");
		}
		if(req.getParameter("month")!=null){
			if(whereClaus.length()>0){
				whereClaus += " AND extract(month from day) ="+req.getParameter("month");
			}
			else
				whereClaus = " WHERE extract(month from day) ="+req.getParameter("month");
		}
		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();
		Connection conn = ConnUtil.getConn();
		Statement stmt = conn.createStatement();
		ResultSet rs;
		System.out.println(whereClaus);
		rs = stmt.executeQuery("SELECT * FROM sv" + id + whereClaus);
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next())
	    {
			JSONObject obj = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {  
		        String columnName =metaData.getColumnLabel(i);  
		        String value = rs.getString(columnName);  
		        obj.put(columnName, value);  
		    }   
			//obj.put("producer_id", rs.getString("producer_id"));
			arr.add(obj);
	    } 
		result.put("data", arr);
		return arr;
	}

}
