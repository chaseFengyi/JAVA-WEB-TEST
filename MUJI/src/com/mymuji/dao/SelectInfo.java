package com.mymuji.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SelectInfo {
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	private static final String driver = "com.jdbc.mysql.Driver";
	
	public static List<Map<String, Object>> selectInfo(String name, Object des){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		String sql = "select * from "+CreateWord.MUJITable+"where "+name+"=?";
		try {
			Class.forName(driver);
			conn = PublicInfo.getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeQuery(sql);
			ps.setObject(1, des);
			rs = ps.executeQuery();
			
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colName = new String[count];
			for(int i = 1; i <= count; i++){
				colName[i-1] = rsmd.getColumnLabel(i);
			}
			int j = 1;
			while(rs.next()){
				map = new HashMap<String, Object>();
				for(int i = 0; i < colName.length; i++){
					map.put(colName[i], rs.getObject(i+1));
				}
				j = j + 1;
				list.add(map);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
