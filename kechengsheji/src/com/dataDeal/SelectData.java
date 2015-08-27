package com.dataDeal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SelectData {
	public Map<String, Object> select(String sql){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Tools t = null;
		String className = "com.dataDeal.Tools";
		Class tc = null;

		Map<String, Object> data = null;
		try{
			//通过反射机制，使用类装载器装载该类
			tc = Class.forName(className);
			//创建该类的实例
			t = (Tools)tc.newInstance();
			//通过上面创建的类实例，调用其方法
			//连接数据库
			conn = t.getConnection();
			//创建查找语句
			ps = conn.prepareStatement(sql);
			//执行语句
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colName = new String[count];
			for(int i=1;i<=count;i++){/*
				System.out.print(rsmd.getColumnClassName(i)+"\t");
				System.out.print(rsmd.getCatalogName(i)+"\t");
				System.out.println(rsmd.getColumnLabel(i));*/
				colName[i-1] = rsmd.getColumnLabel(i);
			}
			//遍历结果集
			int i = 1;
			data = new HashMap<String, Object>();
			while(rs.next()){				
				for(int j=0;j<colName.length;j++){
					data.put(colName[j]+i, rs.getObject(j+1));
				}	
				i = i+1;
			}
			data.put("总数", i-1);
			//取出数据，放在Map里
		}catch (SQLException e) {
			System.out.println("连接数据库出错");
//			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("没有找到该装载类");
		}catch (IllegalAccessException e) {
			System.out.println("转化为接口过程中出错");
		}catch (InstantiationException e) {
			System.out.println("转化为接口过程中出错");
		}finally{
			t.free(conn, ps, rs);
		}
		return data;
	}
	/*public static void main(String[] args) {
		Map<String, Object> data = new SelectData().select("select * from 图书");
		System.out.println(data);
	}*/
}
