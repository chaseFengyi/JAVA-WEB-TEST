package com.dataDeal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FindData {
	String password = null;
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	Tools t = null;
	String className = "com.dataDeal.Tools";
	Class tc = null;
	
	Map<String, Object> data = new HashMap<String, Object>();
	
	//查找图书信息
	//图书编号,bookId;
	public Map findBook(int bookId){
		try{
			//通过反射机制，使用类装载器装载该类
			tc = Class.forName(className);
			//创建该类的实例
			t = (Tools)tc.newInstance();
			
			//通过上面创建的类实例，调用其方法
			//连接数据库
			conn = t.getConnection();
			
			//创建查找语句
			String sql = "select * from 图书 where 图书编号=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			
			//执行语句
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colName = new String[count];
			for(int i=1;i<=count;i++){
				colName[i-1] = rsmd.getColumnLabel(i);
			}
			int j = 1;
			while(rs.next()){
				for(int i=0; i<colName.length; i++){
					data.put(colName[i], rs.getObject(1+i));
				}
				j = j+1;
			}
			data.put("总数", j-1);
		}catch (SQLException e) {
			System.out.println("连接数据库出错");
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
	
	
	//查找读者信息
	public Map findReader(String sql){
		try{
			//通过反射机制，使用类装载器装载该类
			tc = Class.forName(className);
			//创建该类的实例
			t = (Tools)tc.newInstance();
			
			//通过上面创建的类实例，调用其方法
			//连接数据库
			conn = t.getConnection();
			
			//创建查找语句
//			String sql = "select * from 读者 where 借书证编号=?";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, browId);
			
			//执行语句
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colName = new String[count];
			for(int i=1;i<=count;i++){
				colName[i-1] = rsmd.getColumnLabel(i);
			}
			
			int j = 1;
			while(rs.next()){
				for(int i=0; i<colName.length; i++){
					data.put(colName[i], rs.getObject(1+i));
				}
				j = j+1;
			}
			data.put("总数", j-1);
			System.out.println(data);
		}catch (SQLException e) {
//			System.out.println("连接数据库出错");
			e.printStackTrace();
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
	
	
	//查找书库信息
	//书库号,storeId
	public Map findStore(int storeId){
		try{
			//通过反射机制，使用类装载器装载该类
			tc = Class.forName(className);
			//创建该类的实例
			t = (Tools)tc.newInstance();
			
			//通过上面创建的类实例，调用其方法
			//连接数据库
			conn = t.getConnection();
			
			//创建查找语句
			String sql = "select * from 书库 where 书库号=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, storeId);
			
			//执行语句
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colName = new String[count];
			for(int i=1;i<=count;i++){
				colName[i-1] = rsmd.getColumnLabel(i);
			}
			
			int j = 1;
			while(rs.next()){
				for(int i=0; i<colName.length; i++){
					data.put(colName[i], rs.getObject(1+i));
				}
				j = j+1;
			}
			data.put("总数", j-1);
			
		}catch (SQLException e) {
			System.out.println("连接数据库出错");
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
		System.out.println(new FindData().findReader("select 姓名,书名,应还时间 from 借书,读者" +
				" where 借书.借书证编号=读者.借书证编号 and 读者.借书证编号='S04123126'"));
	}*/
}
