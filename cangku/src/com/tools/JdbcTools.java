package com.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import com.Vo.UserVo;


public class JdbcTools  {
	public static Connection getconnection(){
		Connection	con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/storagemanage", "root", "123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
