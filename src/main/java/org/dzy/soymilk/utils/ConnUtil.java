package org.dzy.soymilk.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {
	
	public static Connection getConn() throws SQLException{
		
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://101.132.74.172:1922/pipeline"; 
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			conn = DriverManager.getConnection(url, props);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}

}
