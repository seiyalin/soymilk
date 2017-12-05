package org.dzy.soymilk.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.dzy.soymilk.utils.ConnUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@EnableWebMvc
@Controller
public class Pipelinedb {
	
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
	
	@RequestMapping("/orderShow")
	@ResponseBody
	/*soymilk/orderShow.do?id=01*/
	public JSONObject getView(HttpServletRequest req) throws SQLException{
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
