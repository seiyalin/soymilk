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
	@ResponseBody
	public JSONObject OrderSave(HttpServletRequest req) throws SQLException{
		Connection conn = ConnUtil.getConn();
		Statement stmt = conn.createStatement();
		ResultSet rs;
		int x = Integer.parseInt(req.getParameter("x"));
		int y = Integer.parseInt(req.getParameter("y"));
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String sql = "INSERT INTO stream1 (x,y) VALUES (" + Integer.toString(x) +","+ Integer.toString(y)+")";
		stmt.execute(sql);
		rs = stmt.executeQuery("SELECT * FROM v");
	    while (rs.next())
	    {
	      int id = rs.getInt("x");
	      int count = rs.getInt("count");
	      System.out.println(id + " = " + count);
	    }    
	    stmt.close();
	    conn.close();
		JSONObject result = new JSONObject();
		result.put("success", true);
		return result;
	}
	
	@RequestMapping("/orderShow")
	@ResponseBody
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

}
