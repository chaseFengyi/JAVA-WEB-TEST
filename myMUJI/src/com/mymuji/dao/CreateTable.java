package com.mymuji.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	private final static String userName = "root";
	private final static String password = "123456";
	private final static String url = "jdbc:mysql://localhost:3306/"+CreateWord.MUJIDB;
	private final static String driver = "com.mysql.jdbc.Driver";
	
	static Connection conn = null;
	static Statement st = null;
	static ResultSet rs = null;
	
	private static boolean publicPart(String dropSql, String createSql){
		try {
			Class.forName(driver);
			conn = PublicInfo.getConnection();
			st = conn.createStatement();
			st.executeUpdate(dropSql);
			st.executeUpdate(createSql);
			PublicInfo.free(conn, st, rs);
			
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean createDB(){
		return publicPart(CreateWord.dropMujiDB,CreateWord.createMujiDB);
	}
	
	public static boolean createTable(){
		return publicPart(CreateWord.dropMujiTable,CreateWord.createMujiTable);
	}
}
