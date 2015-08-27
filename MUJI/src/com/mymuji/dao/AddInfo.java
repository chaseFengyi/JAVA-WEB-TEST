package com.mymuji.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddInfo {
	static Connection conn = null;
	static PreparedStatement ps = null;
	
	private static final String driver = "com.jdbc.mysql.Driver";
	
	public static boolean addMujiInfo(String accountNum, String password){
		String sql = "insert into "+CreateWord.MUJITable +
				"(accountNum,password) values(?,?)";
		try {
			Class.forName(driver);
			conn = PublicInfo.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountNum);
			ps.setString(2, password);
			ps.executeUpdate();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
