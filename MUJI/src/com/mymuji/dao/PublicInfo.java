package com.mymuji.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PublicInfo {
	private final static String userName = "root";
	private final static String password = "123456";
	private final static String url = "jdbc:mysql://localhost:3306/"+CreateWord.MUJIDB;
	private final static String driver = "com.mysql.jdbc.Driver";
	
	static Connection conn = null;
	
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			conn = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void free(Connection conn, Statement ps, ResultSet rs){
			try {
				if(rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try{
					if(ps != null){
						ps.close();
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try{
						if(conn != null){
							conn.close();
						}
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
	}
	
}
