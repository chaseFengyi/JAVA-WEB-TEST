package com.mymuji.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateInfo {
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	private static final String driver = "com.jdbc.mysql.Driver";
	
	public static boolean updateInfo(int mujiID, String accoutNum, String password){
		String sql = "update "+CreateWord.MUJITable+
				"set accountNum=? password=? where mujiID="+mujiID+";";
		try {
			Class.forName(driver);
			conn = PublicInfo.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, accoutNum);
			ps.setString(2, password);
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
